import { useState, useEffect, useRef } from 'react';
import api from '../services/api';
import { Search, Clock, Award, Radar, Building2, TrendingUp } from 'lucide-react';

const Dashboard = () => {
    const [courses, setCourses] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [loading, setLoading] = useState(true);
    const [message, setMessage] = useState('');
    const [enrollingCourseId, setEnrollingCourseId] = useState(null);
    const [blossomCourseId, setBlossomCourseId] = useState(null);
    const messageTimerRef = useRef(null);
    const blossomTimerRef = useRef(null);

    useEffect(() => {
        fetchCourses();
    }, []);

    useEffect(() => {
        return () => {
            if (messageTimerRef.current) {
                window.clearTimeout(messageTimerRef.current);
            }
            if (blossomTimerRef.current) {
                window.clearTimeout(blossomTimerRef.current);
            }
        };
    }, []);

    const fetchCourses = async (search = '') => {
        try {
            const { data } = await api.get(`/courses${search ? `?search=${search}` : ''}`);
            setCourses(data);
        } catch (err) {
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    const handleSearch = (e) => {
        e.preventDefault();
        fetchCourses(searchTerm);
    };

    const reserveCourse = async (courseId) => {
        try {
            if (messageTimerRef.current) {
                window.clearTimeout(messageTimerRef.current);
            }
            if (blossomTimerRef.current) {
                window.clearTimeout(blossomTimerRef.current);
            }
            setBlossomCourseId(courseId);
            setEnrollingCourseId(courseId);
            await api.post(`/reservations/${courseId}`);
            setMessage('Successfully reserved course!');
            fetchCourses(searchTerm);
        } catch (err) {
            const errorMsg = typeof err.response?.data === 'string' ? err.response.data : 'Failed to reserve course';
            setMessage(errorMsg);
        } finally {
            setEnrollingCourseId(null);
            blossomTimerRef.current = window.setTimeout(() => setBlossomCourseId(null), 1200);
        }
        messageTimerRef.current = window.setTimeout(() => setMessage(''), 3000);
    };

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

    if (loading) return <div className="container mt-20 text-center" style={{ color: 'var(--text-muted)' }}>Loading catalog...</div>;

    return (
        <div className="container py-8 animate-fade-in relative z-10">
            <div className="flex justify-between items-center mb-12 flex-col sm:flex-row gap-6">
                <div>
                    <h1 className="text-4xl font-bold mb-2" style={{ letterSpacing: '-0.025em' }}>Course Catalog</h1>
                    <p style={{ color: 'var(--text-muted)' }}>Explore our premium collection of advanced courses.</p>
                </div>
                <form onSubmit={handleSearch} className="flex gap-2 w-full glass-panel" style={{ maxWidth: '28rem', borderRadius: '999px', padding: '0.5rem' }}>
                    <input
                        type="text"
                        placeholder="Search catalog..."
                        className="form-input"
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                        style={{ background: 'transparent', border: 'none', boxShadow: 'none' }}
                    />
                    <button type="submit" className="btn btn-primary" style={{ borderRadius: '999px', padding: '0.5rem 1.5rem' }}><Search size={20} /></button>
                </form>
            </div>

            {message && (
                <div className="mb-8 p-4 rounded text-center animate-fade-in font-bold glass-panel message-pop" style={{ color: 'var(--secondary)', border: '1px solid rgba(14, 165, 233, 0.3)' }}>
                    {message}
                </div>
            )}

            {courses.length === 0 ? (
                <div className="text-center mt-12 p-12 glass-panel" style={{ color: 'var(--text-muted)' }}>
                    <Search size={48} style={{ margin: '0 auto 1rem', opacity: 0.5 }} />
                    <p className="text-xl">No courses found matching your criteria.</p>
                </div>
            ) : (
                <div className="courses-grid">
                    {courses.map((course, index) => (
                        <div key={course.id} className={`card motion-card animate-slide-up delay-${(index % 3 + 1) * 100}`}>
                            {blossomCourseId === course.id && (
                                <div className="reserve-popup" aria-live="polite" aria-hidden="true">
                                    <span className="reserve-popup-icon">✓</span>
                                    <span className="reserve-popup-text">Seat reserved</span>
                                    <span className="reserve-popup-glow" />
                                </div>
                            )}
                            <div className="card-body">
                                <div className="flex justify-between items-start mb-4">
                                    <span className="badge flex items-center gap-1">
                                        <Award size={14} /> Premium
                                    </span>
                                    <span className="badge" style={{ background: course.seats === 0 ? 'rgba(244, 63, 94, 0.1)' : 'rgba(16, 185, 129, 0.1)', color: course.seats === 0 ? 'var(--accent)' : 'var(--success-color)', borderColor: course.seats === 0 ? 'rgba(244, 63, 94, 0.2)' : 'rgba(16, 185, 129, 0.2)' }}>
                                        {course.seats > 0 ? `${course.seats} seats left` : 'Fully Booked'}
                                    </span>
                                </div>
                                <div className="mb-4">
                                    <span className={`trend-pill trend-${(course.hiringTrend || 'steady').toLowerCase()}`}>
                                        <TrendingUp size={13} /> Hiring Trend: {course.hiringTrend || 'Steady'}
                                    </span>
                                </div>
                                <h3 className="card-title" style={{ fontSize: '1.5rem' }}>{course.title}</h3>
                                <p className="card-text">{course.description}</p>
                                <div className="mt-4" style={{ borderTop: '1px solid var(--border-soft)', paddingTop: '0.9rem' }}>
                                    <p className="text-sm" style={{ color: 'var(--text-muted)', marginBottom: '0.45rem', display: 'flex', alignItems: 'center', gap: '0.4rem' }}>
                                        <Radar size={14} color="var(--secondary)" /> Scope
                                    </p>
                                    <p className="text-sm" style={{ color: 'var(--text-main)', lineHeight: 1.55, marginBottom: '0.8rem' }}>{course.scope}</p>
                                    <p className="text-sm" style={{ color: 'var(--text-muted)', marginBottom: '0.45rem', display: 'flex', alignItems: 'center', gap: '0.4rem' }}>
                                        <Building2 size={14} color="var(--secondary)" /> Companies hiring
                                    </p>
                                    <div className="company-link-grid">
                                        {parseCompanyProfiles(course).map((company) => (
                                            <a
                                                className="company-link-chip"
                                                key={`${course.id}-${company.name}`}
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
                                <div className="flex items-center gap-4 text-sm mt-4" style={{ color: 'var(--text-muted)' }}>
                                    <span className="flex items-center gap-1"><Clock size={16} color="var(--primary)" /> {Math.floor(course.duration / 60)}h {course.duration % 60}m</span>
                                </div>
                            </div>
                            <div className="card-footer" style={{ borderTop: '1px solid var(--border)', paddingTop: '1.25rem' }}>
                                <button
                                    onClick={() => reserveCourse(course.id)}
                                    disabled={course.seats === 0 || enrollingCourseId === course.id}
                                    className="btn btn-primary w-full"
                                >
                                    {course.seats === 0
                                        ? 'Waitlist Full'
                                        : enrollingCourseId === course.id
                                            ? <span className="flex items-center gap-2"><span className="mini-spinner" /> Reserving...</span>
                                            : 'Reserve Your Seat'}
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default Dashboard;
