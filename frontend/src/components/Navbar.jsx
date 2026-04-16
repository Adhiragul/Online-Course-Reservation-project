import { Link, useNavigate } from 'react-router-dom';
import { useContext, useState, useEffect } from 'react';
import AuthContext from '../context/auth-context';
import { Hexagon, LogOut, User } from 'lucide-react';

const Navbar = () => {
    const { user, logout } = useContext(AuthContext);
    const navigate = useNavigate();
    const [scrolled, setScrolled] = useState(false);

    useEffect(() => {
        const handleScroll = () => setScrolled(window.scrollY > 20);
        window.addEventListener('scroll', handleScroll);
        return () => window.removeEventListener('scroll', handleScroll);
    }, []);

    const handleLogout = () => {
        logout();
        navigate('/login');
    };

    return (
        <nav className="navbar" style={{ background: scrolled ? 'var(--background)' : 'rgba(15, 23, 42, 0.8)' }}>
            <div className="container navbar-content flex justify-between items-center">
                <Link to="/" className="nav-brand flex items-center gap-2" style={{ textDecoration: 'none' }}>
                    <Hexagon className="text-secondary" size={28} style={{ color: 'var(--secondary)' }} />
                    <span>Course<span style={{ color: 'var(--primary)' }}>Reserve</span></span>
                </Link>
                <div className="flex items-center gap-6">
                    {user ? (
                        <>
                            {user.role === 'ROLE_ADMIN' ? (
                                <Link to="/admin" className="nav-link" style={{ fontSize: '0.875rem', textTransform: 'uppercase', letterSpacing: '0.05em', color: 'var(--accent)', fontWeight: 700 }}>Admin Panel</Link>
                            ) : (
                                <>
                                    <Link to="/dashboard" className="nav-link" style={{ fontSize: '0.875rem', textTransform: 'uppercase', letterSpacing: '0.05em' }}>Catalog</Link>
                                    <Link to="/my-courses" className="nav-link" style={{ fontSize: '0.875rem', textTransform: 'uppercase', letterSpacing: '0.05em' }}>My Portfolio</Link>
                                </>
                            )}
                            <div className="flex items-center gap-4 border-l pl-4" style={{ borderColor: 'var(--border)', marginLeft: '0.5rem' }}>
                                <span className="flex items-center gap-2" style={{ fontSize: '0.875rem', fontWeight: 500, color: 'var(--text-main)' }}>
                                    <div style={{ width: '2rem', height: '2rem', borderRadius: '50%', background: 'rgba(79, 70, 229, 0.2)', display: 'flex', alignItems: 'center', justifyContent: 'center', border: '1px solid rgba(79, 70, 229, 0.5)', color: 'var(--primary)' }}>
                                        {user.name.charAt(0).toUpperCase()}
                                    </div>
                                    {user.name}
                                </span>
                                <button onClick={handleLogout} className="btn btn-outline flex items-center gap-2 text-sm" style={{ padding: '0.4rem 1rem' }}>
                                    <LogOut size={16} />
                                </button>
                            </div>
                        </>
                    ) : (
                        <>
                            <Link to="/login" className="nav-link" style={{ fontSize: '0.875rem', textTransform: 'uppercase', letterSpacing: '0.05em' }}>Login</Link>
                            <Link to="/register" className="btn btn-primary" style={{ fontSize: '0.875rem', textTransform: 'uppercase', letterSpacing: '0.05em' }}>Access Platform</Link>
                        </>
                    )}
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
