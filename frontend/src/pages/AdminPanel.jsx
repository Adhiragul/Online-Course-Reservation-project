import { useEffect, useState } from 'react';
import api from '../services/api';
import { Users2, BookCheck, Radar, Gauge, Shield, RefreshCw } from 'lucide-react';

const AdminPanel = () => {
    const [analytics, setAnalytics] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchAnalytics();
    }, []);

    const fetchAnalytics = async () => {
        setLoading(true);
        setError('');
        try {
            const { data } = await api.get('/admin/analytics');
            setAnalytics(data);
        } catch (err) {
            const status = err.response?.status;
            if (status === 401) {
                setError('Session expired. Please sign in again.');
            } else if (status === 403) {
                setError('You do not have permission to access this data.');
            } else {
                setError('Failed to load analytics data.');
            }
            console.error('Analytics error:', err);
        } finally {
            setLoading(false);
        }
    };

    if (loading) {
        return (
            <div className="container py-8 text-center">
                <p style={{ color: 'var(--text-muted)' }}>Loading admin dashboard...</p>
            </div>
        );
    }

    if (error) {
        return (
            <div className="container py-8">
                <div className="p-4 rounded" style={{ background: 'rgba(239, 68, 68, 0.1)', border: '1px solid rgb(239, 68, 68)' }}>
                    <p style={{ color: 'rgb(239, 68, 68)', fontWeight: 'bold' }}>{error}</p>
                </div>
            </div>
        );
    }

    const data = analytics || {
        totalUsers: 0,
        totalStudents: 0,
        totalAdmins: 0,
        totalReservations: 0,
        seatsRemaining: 0,
        totalCourses: 0,
        userProgress: [],
        courseProgress: [],
        recentActivity: []
    };

    return (
        <div className="container py-8">
            {/* Header */}
            <div style={{ marginBottom: '2rem', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                <div>
                    <h1 style={{ fontSize: '2rem', fontWeight: 'bold', marginBottom: '0.5rem' }}>
                        <Shield size={28} style={{ display: 'inline-block', marginRight: '0.5rem', verticalAlign: 'middle' }} />
                        Admin Dashboard
                    </h1>
                    <p style={{ color: 'var(--text-muted)' }}>Platform overview and course reservation analytics</p>
                </div>
                <button 
                    onClick={fetchAnalytics}
                    style={{
                        padding: '0.5rem 1rem',
                        borderRadius: '0.5rem',
                        background: 'var(--secondary)',
                        color: 'white',
                        border: 'none',
                        cursor: 'pointer',
                        fontWeight: '500',
                        display: 'flex',
                        alignItems: 'center',
                        gap: '0.5rem'
                    }}
                    disabled={loading}
                >
                    <RefreshCw size={16} /> Refresh
                </button>
            </div>

            {/* Key Metrics */}
            <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))', gap: '1rem', marginBottom: '2rem' }}>
                <div style={{ padding: '1.5rem', background: 'var(--surface)', borderRadius: '0.75rem', border: '1px solid var(--border)' }}>
                    <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', marginBottom: '0.75rem', color: 'var(--text-muted)' }}>
                        <Users2 size={18} />
                        <span style={{ fontSize: '0.875rem', fontWeight: '500' }}>Total Users</span>
                    </div>
                    <div style={{ fontSize: '2rem', fontWeight: 'bold' }}>{data.totalUsers}</div>
                    <p style={{ fontSize: '0.875rem', color: 'var(--text-muted)', marginTop: '0.5rem' }}>
                        {data.totalStudents} students, {data.totalAdmins} admin
                    </p>
                </div>

                <div style={{ padding: '1.5rem', background: 'var(--surface)', borderRadius: '0.75rem', border: '1px solid var(--border)' }}>
                    <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', marginBottom: '0.75rem', color: 'var(--text-muted)' }}>
                        <BookCheck size={18} />
                        <span style={{ fontSize: '0.875rem', fontWeight: '500' }}>Reservations</span>
                    </div>
                    <div style={{ fontSize: '2rem', fontWeight: 'bold' }}>{data.totalReservations}</div>
                    <p style={{ fontSize: '0.875rem', color: 'var(--text-muted)', marginTop: '0.5rem' }}>
                        active course enrollments
                    </p>
                </div>

                <div style={{ padding: '1.5rem', background: 'var(--surface)', borderRadius: '0.75rem', border: '1px solid var(--border)' }}>
                    <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', marginBottom: '0.75rem', color: 'var(--text-muted)' }}>
                        <Gauge size={18} />
                        <span style={{ fontSize: '0.875rem', fontWeight: '500' }}>Seats Available</span>
                    </div>
                    <div style={{ fontSize: '2rem', fontWeight: 'bold' }}>{data.seatsRemaining}</div>
                    <p style={{ fontSize: '0.875rem', color: 'var(--text-muted)', marginTop: '0.5rem' }}>
                        open seats across all courses
                    </p>
                </div>

                <div style={{ padding: '1.5rem', background: 'var(--surface)', borderRadius: '0.75rem', border: '1px solid var(--border)' }}>
                    <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', marginBottom: '0.75rem', color: 'var(--text-muted)' }}>
                        <Radar size={18} />
                        <span style={{ fontSize: '0.875rem', fontWeight: '500' }}>Courses</span>
                    </div>
                    <div style={{ fontSize: '2rem', fontWeight: 'bold' }}>{data.totalCourses}</div>
                    <p style={{ fontSize: '0.875rem', color: 'var(--text-muted)', marginTop: '0.5rem' }}>
                        available in catalog
                    </p>
                </div>
            </div>


            {/* Course Fill Rates */}
            {data.courseProgress && data.courseProgress.length > 0 && (
                <div style={{ marginBottom: '2rem', padding: '1.5rem', background: 'var(--surface)', borderRadius: '0.75rem', border: '1px solid var(--border)' }}>
                    <h2 style={{ fontSize: '1.25rem', fontWeight: 'bold', marginBottom: '1.5rem' }}>Course Fill Status</h2>
                    <div style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
                        {data.courseProgress.map((course) => (
                            <div key={course.id}>
                                <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '0.5rem' }}>
                                    <div>
                                        <strong>{course.title}</strong>
                                        <p style={{ fontSize: '0.875rem', color: 'var(--text-muted)' }}>
                                            {course.reservations} reservations, {course.seatsLeft} seats left
                                        </p>
                                    </div>
                                    <span style={{ fontWeight: 'bold', color: 'var(--secondary)' }}>{Math.round(course.fillRate)}%</span>
                                </div>
                                <div style={{ height: '0.5rem', background: 'var(--border)', borderRadius: '0.25rem', overflow: 'hidden' }}>
                                    <div style={{
                                        height: '100%',
                                        background: course.fillRate > 80 ? '#ef4444' : course.fillRate > 50 ? '#f59e0b' : '#10b981',
                                        width: `${course.fillRate}%`,
                                        transition: 'width 0.3s ease'
                                    }} />
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            )}

            {/* Top Users */}
            {data.userProgress && data.userProgress.length > 0 && (
                <div style={{ marginBottom: '2rem', padding: '1.5rem', background: 'var(--surface)', borderRadius: '0.75rem', border: '1px solid var(--border)' }}>
                    <h2 style={{ fontSize: '1.25rem', fontWeight: 'bold', marginBottom: '1.5rem' }}>Top Learners</h2>
                    <div style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
                        {data.userProgress.slice(0, 5).map((user) => (
                            <div key={user.id} style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', paddingBottom: '1rem', borderBottom: '1px solid var(--border)' }}>
                                <div>
                                    <strong>{user.name}</strong>
                                    <p style={{ fontSize: '0.875rem', color: 'var(--text-muted)' }}>
                                        {user.reservations} course{user.reservations !== 1 ? 's' : ''} • {user.role === 'ROLE_ADMIN' ? 'Admin' : 'Student'}
                                    </p>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            )}

            {/* Recent Activity */}
            {data.recentActivity && data.recentActivity.length > 0 && (
                <div style={{ padding: '1.5rem', background: 'var(--surface)', borderRadius: '0.75rem', border: '1px solid var(--border)' }}>
                    <h2 style={{ fontSize: '1.25rem', fontWeight: 'bold', marginBottom: '1.5rem' }}>Recent Reservations</h2>
                    <div style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
                        {data.recentActivity.slice(0, 8).map((item) => (
                            <div key={item.id} style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', paddingBottom: '1rem', borderBottom: '1px solid var(--border)' }}>
                                <div>
                                    <strong>{item.userName}</strong>
                                    <p style={{ fontSize: '0.875rem', color: 'var(--text-muted)' }}>
                                        Reserved: {item.courseTitle}
                                    </p>
                                </div>
                                <span style={{ fontSize: '0.875rem', color: 'var(--text-muted)' }}>
                                    {new Date(item.reservationDate).toLocaleDateString()} {new Date(item.reservationDate).toLocaleTimeString()}
                                </span>
                            </div>
                        ))}
                    </div>
                </div>
            )}

            {/* Empty State */}
            {(!data.courseProgress || data.courseProgress.length === 0) && (!data.userProgress || data.userProgress.length === 0) && (!data.recentActivity || data.recentActivity.length === 0) && (
                <div style={{ padding: '2rem', textAlign: 'center', color: 'var(--text-muted)' }}>
                    <p>No data available yet. Once users start making reservations, you'll see analytics here.</p>
                </div>
            )}
        </div>
    );
};

export default AdminPanel;
