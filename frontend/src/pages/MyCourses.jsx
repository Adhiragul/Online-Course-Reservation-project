import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import api from '../services/api';
import { Calendar, XCircle, BookOpen, Radar, Building2, TrendingUp } from 'lucide-react';

const MyCourses = () => {
    const [reservations, setReservations] = useState([]);
    const [loading, setLoading] = useState(true);
    const [message, setMessage] = useState('');

    const parseCompanyProfiles = (course) => {
        if (course.hiringCompanyProfiles) {
            return course.hiringCompanyProfiles
                .split(';')
                .map((line) => line.trim())
                .filter(Boolean)
                .map((line) => {
                    const [name = '', url = '', domain = ''] = line.split('|').map((part) => part.trim());
                    return { name, url, domain };
                })
                .filter((item) => item.name);
        }

        return (course.hiringCompanies || '')
            .split(',')
            .map((name) => name.trim())
            .filter(Boolean)
            .map((name) => ({
                name,
                url: '#',
                domain: `${name.toLowerCase().replace(/[^a-z0-9]/g, '')}.com`,
            }));
    };

    useEffect(() => {
        fetchReservations();
    }, []);

    const fetchReservations = async () => {
        try {
            const { data } = await api.get('/reservations/my');
            setReservations(data);
        } catch (err) {
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    const cancelReservation = async (reservationId) => {
        if (!window.confirm('Are you sure you want to cancel this reservation?')) return;

        try {
            await api.delete(`/reservations/${reservationId}`);
            setMessage('Reservation cancelled successfully!');
            fetchReservations();
        } catch (err) {
            setMessage(err.response?.data || 'Failed to cancel reservation');
        }
        setTimeout(() => setMessage(''), 3000);
    };

    if (loading) return <div className="container mt-20 text-center" style={{ color: 'var(--text-muted)' }}>Loading your portfolio...</div>;

    return (
        <div className="container py-8 animate-fade-in relative z-10">
            <div className="mb-12">
                <h1 className="text-4xl font-bold mb-2" style={{ letterSpacing: '-0.025em' }}>My Portfolio</h1>
                <p style={{ color: 'var(--text-muted)' }}>Manage your enrolled courses and upcoming schedules.</p>
            </div>

            {message && (
                <div className="mb-8 p-4 rounded text-center animate-fade-in font-bold glass-panel message-pop" style={{ color: 'var(--secondary)', border: '1px solid rgba(14, 165, 233, 0.3)' }}>
                    {message}
                </div>
            )}

            {reservations.length === 0 ? (
                <div className="text-center mt-12 p-12 glass-panel" style={{ color: 'var(--text-muted)' }}>
                    <BookOpen size={48} style={{ margin: '0 auto 1rem', opacity: 0.5 }} />
                    <p className="text-xl mb-4">Your portfolio is currently empty.</p>
                    <Link to="/dashboard" className="btn btn-primary">Browse Catalog</Link>
                </div>
            ) : (
                <div className="courses-grid" style={{ gridTemplateColumns: 'repeat(auto-fill, minmax(350px, 1fr))' }}>
                    {reservations.map((reservation, index) => (
                        <div key={reservation.id} className={`card motion-card animate-slide-up delay-${(index % 3 + 1) * 100}`}>
                            <div className="card-body">
                                <div className="mb-4">
                                    <span className={`trend-pill trend-${(reservation.course.hiringTrend || 'steady').toLowerCase()}`}>
                                        <TrendingUp size={13} /> Hiring Trend: {reservation.course.hiringTrend || 'Steady'}
                                    </span>
                                </div>
                                <h3 className="card-title" style={{ fontSize: '1.5rem' }}>{reservation.course.title}</h3>
                                <p className="card-text">{reservation.course.description}</p>
                                <div className="mt-4" style={{ borderTop: '1px solid var(--border-soft)', paddingTop: '0.9rem' }}>
                                    <p className="text-sm" style={{ color: 'var(--text-muted)', marginBottom: '0.45rem', display: 'flex', alignItems: 'center', gap: '0.4rem' }}>
                                        <Radar size={14} color="var(--secondary)" /> Scope
                                    </p>
                                    <p className="text-sm" style={{ color: 'var(--text-main)', lineHeight: 1.55, marginBottom: '0.8rem' }}>{reservation.course.scope}</p>
                                    <p className="text-sm" style={{ color: 'var(--text-muted)', marginBottom: '0.45rem', display: 'flex', alignItems: 'center', gap: '0.4rem' }}>
                                        <Building2 size={14} color="var(--secondary)" /> Companies hiring
                                    </p>
                                    <div className="company-link-grid">
                                        {parseCompanyProfiles(reservation.course).map((company) => (
                                            <a
                                                className="company-link-chip"
                                                key={`${reservation.id}-${company.name}`}
                                                href={company.url && company.url !== '#' ? company.url : undefined}
                                                target={company.url && company.url !== '#' ? '_blank' : undefined}
                                                rel={company.url && company.url !== '#' ? 'noreferrer noopener' : undefined}
                                                aria-label={`Hiring company ${company.name}`}
                                            >
                                                <img
                                                    src={`https://logo.clearbit.com/${company.domain}`}
                                                    alt={`${company.name} logo`}
                                                    className="company-logo"
                                                />
                                                <span>{company.name}</span>
                                            </a>
                                        ))}
                                    </div>
                                </div>
                                <div className="flex items-center gap-4 text-sm mt-4 p-3 rounded-lg" style={{ background: 'rgba(255,255,255,0.03)', color: 'var(--text-muted)' }}>
                                    <span className="flex items-center gap-2">
                                        <Calendar size={16} color="var(--secondary)" />
                                        Reserved on {new Date(reservation.reservationDate).toLocaleDateString()}
                                    </span>
                                </div>
                            </div>
                            <div className="card-footer" style={{ borderTop: '1px solid var(--border)', paddingTop: '1.25rem' }}>
                                <button
                                    onClick={() => cancelReservation(reservation.id)}
                                    className="btn btn-danger w-full flex items-center justify-center gap-2"
                                >
                                    <XCircle size={18} /> Cancel Registration
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default MyCourses;
