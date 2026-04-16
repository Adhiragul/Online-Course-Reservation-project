import { useState, useContext } from 'react';
import AuthContext from '../context/auth-context';
import { useNavigate, Link } from 'react-router-dom';

const Register = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const { register } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            await register(name, email, password);
            navigate('/login');
        } catch (err) {
            setError(err.response?.data || 'Failed to register');
        }
    };

    return (
        <div className="auth-shell container mx-auto py-20 animate-fade-in flex justify-center items-center" style={{ minHeight: '80vh' }}>
            <div className="auth-orb auth-orb-left" />
            <div className="auth-orb auth-orb-right" />
            <div className="card glass-panel w-full auth-card" style={{ maxWidth: '28rem', padding: '2.5rem' }}>
                <h2 className="text-3xl font-bold mb-2 text-center" style={{ color: 'var(--text-main)', letterSpacing: '-0.025em' }}>Create Account</h2>
                <p className="text-center mb-8" style={{ color: 'var(--text-muted)' }}>Join our premium learning platform</p>

                {error && <div className="mb-6 p-3 rounded text-sm text-center" style={{ background: 'rgba(244, 63, 94, 0.1)', border: '1px solid var(--accent)', color: 'var(--accent)' }}>{error}</div>}

                <form onSubmit={handleSubmit} className="auth-form-stagger">
                    <div className="form-group mb-5">
                        <label className="form-label">Full Name</label>
                        <input
                            type="text"
                            className="form-input"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            placeholder="John Doe"
                            required
                        />
                    </div>
                    <div className="form-group mb-5">
                        <label className="form-label">Email Address</label>
                        <input
                            type="email"
                            className="form-input"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            placeholder="name@example.com"
                            required
                        />
                    </div>
                    <div className="form-group mb-8">
                        <label className="form-label">Password</label>
                        <input
                            type="password"
                            className="form-input"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                            minLength="6"
                            placeholder="••••••••"
                        />
                    </div>
                    <button type="submit" className="btn btn-primary w-full btn-shimmer" style={{ padding: '0.875rem' }}>Create Account</button>
                </form>
                <p className="text-center mt-6 text-sm" style={{ color: 'var(--text-muted)' }}>
                    Already have an account? <Link to="/login" style={{ color: 'var(--secondary)', textDecoration: 'none', fontWeight: 600 }}>Sign in</Link>
                </p>
            </div>
        </div>
    );
};

export default Register;
