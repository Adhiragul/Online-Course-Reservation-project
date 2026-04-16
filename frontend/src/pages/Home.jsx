import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';
import {
    Sparkles,
    Shield,
    Radar,
    BriefcaseBusiness,
    TrendingUp,
    Cloud,
    BarChart3,
    Bot,
    Check,
    Building2,
    Clock3,
    Users2,
    GraduationCap,
} from 'lucide-react';

const Home = () => {
    const courseTicker = [
        'Full Stack Development Bootcamp',
        'Data Science with Python',
        'Cloud Computing Foundations',
        'UI and UX Design Masterclass',
        'DevOps and Kubernetes Essentials',
        'Java Spring Backend Engineering',
        'AI and Machine Learning Basics',
        'Cybersecurity Analyst Program',
    ];

    const demoFrames = [
        {
            fillRate: '61%',
            openSeats: '24',
            placementFit: '89%',
            seatWindows: [
                { batch: 'APR 22', seats: 24 },
                { batch: 'MAY 03', seats: 21 },
                { batch: 'MAY 16', seats: 19 },
                { batch: 'JUN 01', seats: 17 },
                { batch: 'JUN 14', seats: 14 },
            ],
        },
        {
            fillRate: '73%',
            openSeats: '16',
            placementFit: '92%',
            seatWindows: [
                { batch: 'APR 22', seats: 16 },
                { batch: 'MAY 03', seats: 14 },
                { batch: 'MAY 16', seats: 12 },
                { batch: 'JUN 01', seats: 10 },
                { batch: 'JUN 14', seats: 8 },
            ],
        },
        {
            fillRate: '88%',
            openSeats: '8',
            placementFit: '95%',
            seatWindows: [
                { batch: 'APR 22', seats: 8 },
                { batch: 'MAY 03', seats: 7 },
                { batch: 'MAY 16', seats: 6 },
                { batch: 'JUN 01', seats: 5 },
                { batch: 'JUN 14', seats: 4 },
            ],
        },
    ];

    const [frameIndex, setFrameIndex] = useState(0);

    useEffect(() => {
        const interval = window.setInterval(() => {
            setFrameIndex((prev) => (prev + 1) % demoFrames.length);
        }, 2400);

        return () => window.clearInterval(interval);
    }, [demoFrames.length]);

    const currentFrame = demoFrames[frameIndex];

    return (
        <div className="overflow-hidden">
            <section className="hero-shell">
                <div className="hero-noise" />
                <div className="orb orb-one" />
                <div className="orb orb-two" />
                <div className="orb orb-three" />
                <div className="course-marquee" aria-hidden="true">
                    <div className="course-marquee-track">
                        {[...courseTicker, ...courseTicker].map((courseName, index) => (
                            <span key={`top-${index}`} className="course-name-pill">{courseName}</span>
                        ))}
                    </div>
                    <div className="course-marquee-track reverse">
                        {[...courseTicker, ...courseTicker].map((courseName, index) => (
                            <span key={`bottom-${index}`} className="course-name-pill">{courseName}</span>
                        ))}
                    </div>
                </div>

                <div className="container hero-grid">
                    <div className="hero-copy animate-slide-up">
                        <div className="hero-pill">
                            <Sparkles size={14} /> Smart course reservation platform
                        </div>
                        <h1 className="hero-title">
                            Reserve the right course.
                            <br />
                            Build the right career.
                        </h1>
                        <p className="hero-subtitle">
                            CourseReserve helps you compare scope, job roles, seat availability, and hiring-company demand
                            before you reserve your place.
                        </p>
                        <div className="hero-actions">
                            <Link to="/register" className="btn btn-primary">Reserve a Seat</Link>
                            <Link to="/dashboard" className="btn btn-outline">Browse Courses</Link>
                        </div>
                    </div>

                    <div className="hero-stage animate-slide-up delay-100">
                        <div className="scene-card scene-main motion-card">
                            <div className="scene-label">Reservation Snapshot</div>
                            <h3>Full Stack Development Bootcamp</h3>
                            <p>Scope: Build production-ready web apps and reserve seats before upcoming batches fill.</p>
                            <div className="scene-badges">
                                <span className="badge">Java</span>
                                <span className="badge">React</span>
                                <span className="badge">Interview Prep</span>
                            </div>
                            <div className="mini-demo" aria-live="polite">
                                <div className="mini-kpis">
                                    <div className="mini-kpi-tile">
                                        <small>Fill Rate</small>
                                        <strong>{currentFrame.fillRate}</strong>
                                    </div>
                                    <div className="mini-kpi-tile">
                                        <small>Open Seats</small>
                                        <strong>{currentFrame.openSeats}</strong>
                                    </div>
                                    <div className="mini-kpi-tile">
                                        <small>Placement Fit</small>
                                        <strong>{currentFrame.placementFit}</strong>
                                    </div>
                                </div>
                                <div className="seat-orb-grid" role="img" aria-label="Batch window and live open-seat capsules">
                                    {currentFrame.seatWindows.map((window, index) => (
                                        <span key={`${frameIndex}-${window.batch}-${index}`} className="seat-orb">
                                            <small>{window.batch}</small>
                                            <strong>{window.seats}</strong>
                                        </span>
                                    ))}
                                </div>
                            </div>
                        </div>
                        <div className="scene-card scene-side-top motion-card">
                            <TrendingUp size={18} /> Live seat alerts and waitlist tracking
                        </div>
                        <div className="scene-card scene-side-bottom motion-card">
                            <Radar size={18} /> Seat matrix + roles + hiring on every course
                        </div>
                    </div>
                </div>
            </section>

            <section className="py-20 relative z-10 command-deck-section">
                <div className="container command-deck-grid">
                    <article className="command-card command-card-main motion-card animate-slide-up">
                        <div className="command-label">Reservation Command Deck</div>
                        <h3>Plan batches, lock seats, and align each reservation with job outcomes.</h3>
                        <p>
                            Move from browsing to booking with a layered dashboard that shows demand pressure,
                            upcoming batch windows, and role relevance in one view.
                        </p>
                        <div className="command-badges">
                            <span className="badge"><Clock3 size={13} /> Real-time seat windows</span>
                            <span className="badge"><Users2 size={13} /> Waitlist intelligence</span>
                            <span className="badge"><GraduationCap size={13} /> Role-ready pathways</span>
                        </div>
                    </article>

                    <div className="command-stack animate-slide-up delay-100">
                        <article className="command-card command-card-mini motion-card card-a">
                            <h4>Demand Pressure</h4>
                            <p>Full Stack Development: 89% seats reserved this cycle.</p>
                        </article>
                        <article className="command-card command-card-mini motion-card card-b">
                            <h4>Next Batch Pulse</h4>
                            <p>Cloud Computing Foundations opens in 3 days with 12 seats.</p>
                        </article>
                        <article className="command-card command-card-mini motion-card card-c">
                            <h4>Role Alignment</h4>
                            <p>Top mapped outcomes: Backend Engineer, Data Analyst, DevOps Engineer.</p>
                        </article>
                    </div>
                </div>
            </section>

            <section className="py-20 relative z-10" style={{ borderTop: '1px solid var(--border-soft)' }}>
                <div className="container">
                    <div className="text-center mb-16 animate-slide-up">
                        <h2 className="text-3xl font-bold mb-4" style={{ fontSize: '2.25rem' }}>Built for Career Outcomes</h2>
                        <p style={{ color: 'var(--text-muted)', maxWidth: '42rem', margin: '0 auto', fontSize: '1.125rem' }}>
                            Every workflow is designed around clear course comparison, role readiness, and confident seat reservation.
                        </p>
                    </div>

                    <div className="courses-grid" style={{ gridTemplateColumns: 'repeat(auto-fit, minmax(280px, 1fr))' }}>
                        <div className="card motion-card animate-slide-up delay-100 floating-card">
                            <div className="feature-icon feature-primary">
                                <Radar size={24} />
                            </div>
                            <h3 className="card-title">Scope-driven curriculum</h3>
                            <p className="card-text">Know exactly what capability each course unlocks before reserving a seat.</p>
                        </div>

                        <div className="card motion-card animate-slide-up delay-200 floating-card">
                            <div className="feature-icon feature-secondary">
                                <BriefcaseBusiness size={24} />
                            </div>
                            <h3 className="card-title">Role-mapped learning paths</h3>
                            <p className="card-text">Each course links directly to real job roles so portfolio planning becomes effortless.</p>
                        </div>

                        <div className="card motion-card animate-slide-up delay-300 floating-card">
                            <div className="feature-icon feature-accent">
                                <Shield size={24} />
                            </div>
                            <h3 className="card-title">Hiring-company visibility</h3>
                            <p className="card-text">See which companies actively hire each role before enrolling, so decisions stay market-driven.</p>
                        </div>
                    </div>

                    <div className="saas-icons-grid animate-slide-up delay-200">
                        <article className="saas-feature-card motion-card">
                            <div className="feature-icon feature-secondary"><Cloud size={22} /></div>
                            <h4>Live Seat Visibility</h4>
                            <p>Track available seats in real time so you can reserve before a batch closes.</p>
                        </article>
                        <article className="saas-feature-card motion-card">
                            <div className="feature-icon feature-primary"><BarChart3 size={22} /></div>
                            <h4>Course-to-Role Clarity</h4>
                            <p>See what each course teaches and which roles it supports before enrolling.</p>
                        </article>
                        <article className="saas-feature-card motion-card">
                            <div className="feature-icon feature-accent"><Bot size={22} /></div>
                            <h4>Hiring Company Signals</h4>
                            <p>Check hiring demand and company links connected to each course path.</p>
                        </article>
                    </div>

                    <div className="card animate-slide-up delay-300" style={{ marginTop: '2.5rem' }}>
                        <div className="flex justify-between items-center" style={{ flexWrap: 'wrap', gap: '1rem' }}>
                            <div>
                                <h3 className="card-title" style={{ marginBottom: '0.4rem' }}>Ready to build your next role?</h3>
                                <p style={{ color: 'var(--text-muted)' }}>Reserve now and start collecting role-aligned credentials.</p>
                            </div>
                            <Link to="/dashboard" className="btn btn-primary">Enter Catalog</Link>
                        </div>
                    </div>
                </div>
            </section>

            <section className="py-20 relative z-10">
                <div className="container">
                    <div className="text-center mb-12 animate-slide-up">
                        <h2 className="text-3xl font-bold mb-4" style={{ fontSize: '2.25rem' }}>Immersive Dashboard Preview</h2>
                        <p style={{ color: 'var(--text-muted)', maxWidth: '44rem', margin: '0 auto' }}>
                            A multi-layer 3D dashboard built for fast course discovery, reservation planning, and market-aware enrollment.
                        </p>
                    </div>

                    <div className="dashboard-preview-shell animate-slide-up delay-100" role="img" aria-label="3D course reservation dashboard preview with seat updates and hiring signals">
                        <div className="preview-layer preview-back motion-card">
                            <h4>Seat Heatmap</h4>
                            <p>Full Stack Development: 8 seats left</p>
                            <p>Data Science with Python: 12 seats left</p>
                        </div>
                        <div className="preview-layer preview-mid motion-card">
                            <h4>Reservation Status</h4>
                            <p>Seat tracker synced 2 minutes ago</p>
                            <p>Waitlist monitor active for 3 courses</p>
                        </div>
                        <div className="preview-layer preview-front motion-card">
                            <h4>Reservation Control Panel</h4>
                            <div className="preview-metrics">
                                <span><BarChart3 size={14} /> Avg fill rate this week: 73%</span>
                                <span><Building2 size={14} /> Hiring partners linked: 26</span>
                                <span><Sparkles size={14} /> Role fit confidence: 92%</span>
                            </div>
                            <Link to="/dashboard" className="btn btn-primary">Open Live Dashboard</Link>
                        </div>
                    </div>
                </div>
            </section>

            <section className="py-20 relative z-10" style={{ borderTop: '1px solid var(--border-soft)' }}>
                <div className="container">
                    <div className="text-center mb-12 animate-slide-up">
                        <h2 className="text-3xl font-bold mb-4" style={{ fontSize: '2.25rem' }}>Reservation Plans</h2>
                        <p style={{ color: 'var(--text-muted)', maxWidth: '40rem', margin: '0 auto' }}>
                            Flexible plans for learners, career switchers, and professionals reserving advanced tracks.
                        </p>
                    </div>

                    <div className="pricing-grid">
                        <article className="pricing-card animate-slide-up delay-100" aria-label="Starter pricing plan">
                            <h3>Starter</h3>
                            <p className="price-line">$19<span>/month</span></p>
                            <ul className="pricing-list">
                                <li><Check size={14} /> Core catalog access</li>
                                <li><Check size={14} /> Scope and role mapping</li>
                                <li><Check size={14} /> Basic analytics</li>
                            </ul>
                            <Link to="/register" className="btn btn-outline w-full">Choose Starter</Link>
                        </article>

                        <article className="pricing-card pricing-card-pro animate-slide-up delay-200" aria-label="Pro pricing plan">
                            <div className="pricing-badge">Most Popular</div>
                            <h3>Pro</h3>
                            <p className="price-line">$49<span>/month</span></p>
                            <ul className="pricing-list">
                                <li><Check size={14} /> Hiring-company signals</li>
                                <li><Check size={14} /> AI role-fit insights</li>
                                <li><Check size={14} /> Priority reservations</li>
                            </ul>
                            <Link to="/register" className="btn btn-primary w-full">Choose Pro</Link>
                        </article>

                        <article className="pricing-card animate-slide-up delay-300" aria-label="Scale pricing plan">
                            <h3>Scale</h3>
                            <p className="price-line">$99<span>/month</span></p>
                            <ul className="pricing-list">
                                <li><Check size={14} /> Full automation suite</li>
                                <li><Check size={14} /> Advanced dashboard views</li>
                                <li><Check size={14} /> Dedicated success support</li>
                            </ul>
                            <Link to="/register" className="btn btn-outline w-full">Choose Scale</Link>
                        </article>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default Home;
