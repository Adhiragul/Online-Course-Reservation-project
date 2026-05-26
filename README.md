Online Course Reservation System
A full-stack web application designed to streamline the process of browsing, selecting, and reserving online courses. This project provides a seamless interface for students to manage their learning journey and an administrative suite for managing course offerings.

🚀 Features
Course Catalog: Browse through available courses with detailed descriptions.

Reservation System: Securely book and manage course enrollments.

Admin Panel: Dedicated interface for administrators to manage courses, users, and reservations.

Data Management: Structured data handling for persistent storage of course and user information.

Automated Docs: Includes PowerShell scripts (update_doc.ps1) for maintaining project documentation.

🛠️ Tech Stack
Frontend: JavaScript, HTML5, CSS3

Backend: Java (Spring Framework)

Tooling: PowerShell, VS Code Configuration

📂 Project Structure
Plaintext
├── backend/                # Java source code and server-side logic
├── frontend/               # UI components, styles, and client-side logic
├── data/                   # Database scripts or mock data files
├── .vscode/                # Editor-specific configurations
├── ADMIN_PANEL_SOLUTION.md # Documentation for administrative features
└── DEMO_CHECKLIST.md       # Guide for project demonstration and testing
⚙️ Installation & Setup
Prerequisites
Java JDK 11+

Node.js & npm (for frontend dependencies)

PowerShell (for running automation scripts)

Backend Setup
Navigate to the backend directory.

Configure your database settings in application.properties.

Run the application using your IDE or via command line:

Bash
./mvnw spring-boot:run
Frontend Setup
Navigate to the frontend directory.

Install dependencies:

Bash
npm install
Start the development server:

Bash
npm start
📝 Documentation
For detailed information on specific modules, refer to:

ADMIN_PANEL_SOLUTION.md: Detailed breakdown of admin functionalities.

DEMO_CHECKLIST.md: Steps to verify the core features during a live demo.
