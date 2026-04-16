package com.reservation.backend;

import com.reservation.backend.model.Course;
import com.reservation.backend.model.Role;
import com.reservation.backend.model.User;
import com.reservation.backend.repository.CourseRepository;
import com.reservation.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = new User("Admin User", "admin@demo.com", passwordEncoder.encode("admin123"), Role.ROLE_ADMIN);
            User standard = new User("Demo User", "user@demo.com", passwordEncoder.encode("user123"),
                    Role.ROLE_USER);
            userRepository.save(admin);
            userRepository.save(standard);
        }

        if (courseRepository.count() < 10) {
            String[] titles = {
                    "React Native Mobile App Development", "Advanced Node.js Scaling", "Python Machine Learning",
                    "Cybersecurity Ethical Hacking", "AWS Cloud Architect", "Digital Marketing Analytics",
                    "UI/UX Figma Design Mastery", "Fullstack Vue & Firebase", "Go Programming for Systems",
                    "Data Science with R", "DevOps Docker & Kubernetes", "Blockchain Smart Contracts",
                    "Angular Enterprise Applications", "Flutter Cross-Platform", "C++ Game Development",
                    "Rust System Programming", "GraphQL API Design", "MongoDB Data Modeling",
                    "Advanced SQL Optimization", "Salesforce Administrator", "C# .NET Core Microservices",
                    "PMP Certification Prep", "Financial Quantitative Analysis", "SEO Masterclass",
                    "Unity 3D VR Development", "Artificial Intelligence Intro", "iOS Swift Programming",
                    "Android Kotlin Advanced", "SvelteKit Web Applications", "Linux System Administration",
                    "Agile Scrum Master", "Business Intelligence PowerBI", "Deep Learning Neural Networks",
                    "Web3 Decentralized Apps", "Cybersecurity CISSP Prep", "Data Engineering Pipeline",
                    "Robotics ROS Framework", "Vue3 Composition API", "React Three Fiber 3D",
                    "Network Routing & Switching", "Graphic Design Photoshop", "Cloud Security Posture",
                    "IoT Internet of Things", "Information Systems Auditing", "Next.js Framework",
                    "Remix Fullstack Web", "SAP ERP Fundamentals", "Tableau Data Visualization",
                    "Machine Learning in Healthcare", "Big Data Hadoop & Spark"
            };

                    String[] scopes = {
                        "Build production-ready mobile interfaces and offline-first app architecture.",
                        "Design scalable backend systems with event-driven communication patterns.",
                        "Ship applied machine learning workflows from data prep to model deployment.",
                        "Conduct vulnerability assessments and foundational security testing.",
                        "Architect cloud-native services with resilience and observability.",
                        "Measure campaign impact and optimize digital growth funnels.",
                        "Create high-conversion product UX flows and component systems.",
                        "Deliver fullstack web apps with modern frontend and serverless backends.",
                        "Develop efficient low-level services and concurrent runtime utilities.",
                        "Analyze business datasets and communicate statistical insights.",
                        "Implement CI/CD pipelines and containerized infrastructure automation.",
                        "Build secure smart contracts and decentralized application workflows.",
                        "Create enterprise-grade SPAs with maintainable architecture patterns.",
                        "Build polished cross-platform apps with shared UI business logic.",
                        "Design real-time game systems and performance-focused gameplay loops.",
                        "Write memory-safe high-performance systems for modern platforms.",
                        "Design graph APIs, schema evolution, and client data orchestration.",
                        "Model scalable document data with index and query optimization.",
                        "Tune advanced SQL workloads for reporting and transactional systems.",
                        "Configure and automate CRM workflows for sales operations.",
                        "Implement robust microservices with API gateways and service discovery.",
                        "Manage delivery lifecycle, risk, and stakeholder communication.",
                        "Apply quantitative models to financial scenarios and forecasting.",
                        "Execute search strategy and technical optimization for growth.",
                        "Build immersive VR interactions and real-time rendering scenes.",
                        "Understand AI foundations, tooling, and practical business applications.",
                        "Create native iOS apps with modern architecture and state management.",
                        "Develop advanced Android apps with maintainable Kotlin patterns.",
                        "Ship high-performance web apps with modern SSR and routing.",
                        "Administer Linux servers and automate operations with shell tools.",
                        "Facilitate agile teams, planning cycles, and delivery cadences.",
                        "Build interactive BI dashboards and executive-ready analytics views.",
                        "Train and evaluate deep neural networks for practical use cases.",
                        "Create decentralized web products and wallet-based experiences.",
                        "Prepare for enterprise security governance and defense strategy.",
                        "Build resilient ETL and streaming pipelines across data platforms.",
                        "Program robotics behaviors and perception pipelines using ROS.",
                        "Develop large Vue apps with composition patterns and testing.",
                        "Build interactive 3D web experiences with performant rendering.",
                        "Configure modern enterprise networking and troubleshooting workflows.",
                        "Create professional design assets with production-ready workflows.",
                        "Assess and improve cloud security posture and policy controls.",
                        "Connect and automate IoT devices with telemetry and orchestration.",
                        "Perform IS audits, controls validation, and compliance reporting.",
                        "Build fullstack apps with server components and hybrid rendering.",
                        "Develop fullstack routes and data-loading patterns in Remix.",
                        "Understand ERP foundations and core business process integrations.",
                        "Tell data stories with robust Tableau dashboards and KPIs.",
                        "Apply ML techniques to healthcare classification and prediction.",
                        "Build distributed big data jobs using Hadoop and Spark frameworks."
                    };

                    String[] roles = {
                        "Mobile Developer, React Native Engineer",
                        "Node.js Engineer, Backend Architect",
                        "ML Engineer, Data Scientist",
                        "Security Analyst, Penetration Tester",
                        "Cloud Architect, DevOps Engineer",
                        "Growth Analyst, Digital Marketing Manager",
                        "Product Designer, UX Designer",
                        "Fullstack Developer, Firebase Engineer",
                        "Systems Engineer, Backend Developer",
                        "Data Analyst, BI Specialist",
                        "DevOps Engineer, Platform Engineer",
                        "Blockchain Developer, Smart Contract Engineer",
                        "Frontend Engineer, Angular Architect",
                        "Flutter Developer, Mobile Engineer",
                        "Game Developer, Gameplay Programmer",
                        "Systems Programmer, Rust Developer",
                        "API Engineer, Backend Developer",
                        "Database Engineer, Data Platform Engineer",
                        "Data Engineer, SQL Developer",
                        "Salesforce Admin, CRM Specialist",
                        "Dotnet Engineer, Microservices Developer",
                        "Project Manager, Program Manager",
                        "Quant Analyst, Financial Analyst",
                        "SEO Specialist, Content Strategist",
                        "XR Developer, Unity Engineer",
                        "AI Associate, Product Analyst",
                        "iOS Developer, Mobile Engineer",
                        "Android Developer, Kotlin Engineer",
                        "Frontend Engineer, Svelte Developer",
                        "Linux Administrator, Site Reliability Engineer",
                        "Scrum Master, Delivery Manager",
                        "BI Analyst, Data Visualization Engineer",
                        "Deep Learning Engineer, AI Engineer",
                        "Web3 Developer, Blockchain Product Engineer",
                        "Security Consultant, Compliance Engineer",
                        "Data Engineer, Analytics Engineer",
                        "Robotics Engineer, Automation Developer",
                        "Vue Developer, Frontend Engineer",
                        "3D Frontend Engineer, Creative Developer",
                        "Network Engineer, Infrastructure Specialist",
                        "Graphic Designer, Visual Designer",
                        "Cloud Security Engineer, Security Analyst",
                        "IoT Engineer, Embedded Systems Developer",
                        "Information Systems Auditor, Risk Analyst",
                        "Fullstack Engineer, Next.js Developer",
                        "Fullstack Developer, Platform Engineer",
                        "ERP Consultant, Business Systems Analyst",
                        "Data Analyst, Tableau Developer",
                        "Healthcare Data Scientist, ML Engineer",
                        "Big Data Engineer, Spark Developer"
                    };

                    String[] companies = {
                        "Zoho, Freshworks, Razorpay",
                        "Amazon, Flipkart, Swiggy",
                        "Google, Microsoft, NVIDIA",
                        "Deloitte, EY, KPMG",
                        "AWS, Accenture, Infosys",
                        "HubSpot, Google, Meta",
                        "Adobe, CRED, PhonePe",
                        "Firebase, Vercel, Netlify",
                        "Uber, Atlassian, Stripe",
                        "Mu Sigma, Fractal, NielsenIQ",
                        "GitLab, Red Hat, VMware",
                        "Polygon, ConsenSys, Chainlink",
                        "TCS, Cognizant, Capgemini",
                        "Dream11, Swiggy, Paytm",
                        "Ubisoft, EA, Rockstar",
                        "Cloudflare, Datadog, Discord",
                        "Apollo, Shopify, Airbnb",
                        "MongoDB, CleverTap, BrowserStack",
                        "Oracle, SAP, Snowflake",
                        "Salesforce, PwC, Wipro",
                        "Microsoft, Thoughtworks, EPAM",
                        "IBM, Siemens, Honeywell",
                        "JPMorgan, Goldman Sachs, BlackRock",
                        "Semrush, Ahrefs, HubSpot",
                        "Unity, Meta, HTC Vive",
                        "OpenAI, Anthropic, Adobe",
                        "Apple, Walmart Global Tech, PayPal",
                        "Google, Samsung, Qualcomm",
                        "Vercel, Cloudflare, New Relic",
                        "Red Hat, Canonical, IBM",
                        "Atlassian, Jira, ServiceNow",
                        "Microsoft, Tableau, SAP",
                        "Tesla, Waymo, Amazon",
                        "Coinbase, Binance, Polygon",
                        "Palo Alto, Cisco, CrowdStrike",
                        "Databricks, Snowflake, Confluent",
                        "ABB, Bosch, Siemens",
                        "GitLab, Vue Storefront, Laravel",
                        "Shopify, Stripe, Spline",
                        "Cisco, Juniper, Nokia",
                        "Canva, Figma, Adobe",
                        "Microsoft, Oracle, Wiz",
                        "Bosch, Intel, Honeywell",
                        "Deloitte, EY, Grant Thornton",
                        "Vercel, Netflix, Shopify",
                        "Shopify, Remix Labs, Netlify",
                        "SAP, Deloitte, IBM",
                        "Tableau, Accenture, Capgemini",
                        "Siemens Healthineers, Philips, GE Healthcare",
                        "Amazon, Databricks, Cloudera"
                    };

            for (int i = 0; i < titles.length; i++) {
                Course c = new Course();
                c.setTitle(titles[i]);
                c.setDescription("Comprehensive course on " + titles[i]
                        + " covering all the advanced premium topics from ground up.");
                    c.setScope(scopes[i]);
                    c.setJobRoles(roles[i]);
                    c.setHiringCompanies(companies[i]);
                    c.setHiringCompanyProfiles(buildProfiles(companies[i]));
                    c.setHiringTrend(resolveTrend(i));
                c.setDuration(120 + (i * 10));
                c.setSeats(15 + (i % 20));
                courseRepository.save(c);
            }
        }

        for (Course existing : courseRepository.findAll()) {
            boolean updated = false;
            if (existing.getScope() == null || existing.getScope().isBlank()) {
                existing.setScope("Build practical expertise with measurable project outcomes.");
                updated = true;
            }
            if (existing.getJobRoles() == null || existing.getJobRoles().isBlank()) {
                existing.setJobRoles("Software Engineer, Product Specialist");
                updated = true;
            }
            if (existing.getHiringCompanies() == null || existing.getHiringCompanies().isBlank()) {
                existing.setHiringCompanies("Google, Microsoft, Amazon");
                updated = true;
            }
            if (existing.getHiringCompanyProfiles() == null || existing.getHiringCompanyProfiles().isBlank()) {
                existing.setHiringCompanyProfiles(buildProfiles(existing.getHiringCompanies()));
                updated = true;
            }
            if (existing.getHiringTrend() == null || existing.getHiringTrend().isBlank()) {
                existing.setHiringTrend("Growing");
                updated = true;
            }
            if (updated) {
                courseRepository.save(existing);
            }
        }

        System.out.println("=========================================================");
        System.out.println("Demo Data (Users & " + courseRepository.count() + " Courses) Seeded/Verified!");
        System.out.println("Admin login: admin@demo.com / admin123");
        System.out.println("User login:  user@demo.com / user123");
        System.out.println("=========================================================");
    }

    private String buildProfiles(String companiesCsv) {
        if (companiesCsv == null || companiesCsv.isBlank()) {
            return "";
        }
        String[] companies = companiesCsv.split(",");
        StringBuilder profileBuilder = new StringBuilder();
        for (int i = 0; i < companies.length; i++) {
            String company = companies[i].trim();
            String domain = company.toLowerCase().replaceAll("[^a-z0-9]", "") + ".com";
            if (profileBuilder.length() > 0) {
                profileBuilder.append(";");
            }
            profileBuilder.append(company)
                    .append("|https://www.")
                    .append(domain)
                    .append("/careers|")
                    .append(domain);
        }
        return profileBuilder.toString();
    }

    private String resolveTrend(int index) {
        if (index % 3 == 0) {
            return "High";
        }
        if (index % 3 == 1) {
            return "Growing";
        }
        return "Steady";
    }
}
