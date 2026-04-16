$word = New-Object -ComObject Word.Application
$word.Visible = $false

$doc = $word.Documents.Open("C:\Users\adhir\Desktop\course_reservation\ADHI.doc")

# Select all and delete
$doc.Content.Delete()

$range = $doc.Content

# Helper functions
function Add-Heading($text, $level) {
    $para = $doc.Content.Paragraphs.Add()
    $para.Range.Text = $text
    $para.Range.Font.Bold = $true
    if ($level -eq 1) {
        $para.Range.Font.Size = 16
    } elseif ($level -eq 2) {
        $para.Range.Font.Size = 14
    } elseif ($level -eq 3) {
        $para.Range.Font.Size = 12
    }
    $para.Alignment = 1 # Center
    $para.Range.InsertParagraphAfter()
}

function Add-CenterText($text, $size, $bold) {
    $para = $doc.Content.Paragraphs.Add()
    $para.Range.Text = $text
    $para.Range.Font.Size = $size
    $para.Range.Font.Bold = $bold
    $para.Alignment = 1
    $para.Range.InsertParagraphAfter()
}

function Add-Text($text, $size, $bold) {
    $para = $doc.Content.Paragraphs.Add()
    $para.Range.Text = $text
    $para.Range.Font.Size = $size
    $para.Range.Font.Bold = $bold
    $para.Alignment = 0 # Left
    $para.Range.InsertParagraphAfter()
}

function Add-Blank() {
    $para = $doc.Content.Paragraphs.Add()
    $para.Range.Text = ""
    $para.Range.InsertParagraphAfter()
}

# ============ TITLE PAGE ============
Add-CenterText "ONLINE COURSE RESERVATION SYSTEM" 18 $true
Add-Blank
Add-Blank
Add-CenterText "A PROJECT REPORT" 14 $true
Add-Blank
Add-CenterText "Submitted by" 12 $false
Add-Blank
Add-CenterText "ADHI RAGUL R (310624149002)" 12 $false
Add-CenterText "HARI CHANDER I (310624149018)" 12 $false
Add-CenterText "NAVIN ADITHIYA B (310624149037)" 12 $false
Add-Blank
Add-CenterText "in partial fulfilment for the completion of the course" 12 $false
Add-Blank
Add-CenterText "2311CSC404R - OBJECT ORIENTED SOFTWARE ENGINEERING" 12 $true
Add-Blank
Add-Blank
Add-CenterText "COMPUTER SCIENCE AND ENGINEERING" 12 $true
Add-CenterText "(CYBER SECURITY)" 12 $true
Add-Blank
Add-CenterText "EASWARI ENGINEERING COLLEGE" 14 $true
Add-CenterText "(AN AUTONOMOUS INSTITUTION)" 12 $false
Add-CenterText "AFFILIATED TO ANNA UNIVERSITY, CHENNAI" 12 $false
Add-Blank
Add-Blank
Add-CenterText "APRIL 2026" 14 $true

# ============ BONAFIDE PAGE ============
Add-Blank
Add-Blank
Add-CenterText "EASWARI ENGINEERING COLLEGE" 14 $true
Add-Blank
Add-CenterText "BONAFIDE CERTIFICATE" 14 $true
Add-Blank
Add-Blank
Add-Text 'Certified that this project report titled "ONLINE COURSE RESERVATION SYSTEM" is the bonafide work of "ADHI RAGUL R (310624149002), HARI CHANDER I (310624149018), NAVIN ADITHIYA B (310624149037)" who carried out the project work under my supervision.' 12 $false
Add-Blank
Add-Blank
Add-Blank
Add-Text "SIGNATURE`tSIGNATURE" 12 $true
Add-Text "Dr.V.Balaji Vaithilingam`tP.Pushpa" 12 $false
Add-Text "HEAD OF THE DEPARTMENT`tSUPERVISOR" 12 $false
Add-Text "Professor`tAP/Professor" 12 $false
Add-Text "Dept. of CSE(cyber security)`tDept. of CSE(cyber security)" 12 $false
Add-Text "Easwari Engineering College`tEaswari Engineering College" 12 $false
Add-Text "Ramapuram.`tRamapuram." 12 $false

# ============ ABSTRACT ============
Add-Blank
Add-Blank
Add-CenterText "ABSTRACT" 16 $true
Add-Blank
Add-Text "The Online Course Reservation System is a full-stack web application designed to streamline the process of browsing, reserving, and managing online courses within educational institutions. Students often face difficulties in finding available courses, tracking seat availability, and managing their enrollments due to the absence of a centralized digital platform. This project addresses these challenges by providing a modern, responsive, and user-friendly system for course management and reservation." 12 $false
Add-Text "The system is developed using a decoupled architecture with Spring Boot 4.0 (Java 17) for the backend RESTful API and React 19 (Vite) for the frontend single-page application. H2 is used as an embedded file-based relational database, and Spring Data JPA handles all object-relational mapping. The application implements robust security using Spring Security with stateless JWT (JSON Web Token) authentication and BCrypt password hashing. It supports two distinct user roles: User and Admin, each with role-based access control enforced at both the API and UI levels." 12 $false
Add-Text "Registered users can browse the complete course catalog, search for courses by title, view detailed course information including seat availability and duration, reserve seats in available courses, view their enrolled courses in a personal portfolio, and cancel reservations when needed. The system automatically manages seat inventory, decrementing available seats upon reservation and restoring them upon cancellation. Duplicate reservations are prevented through server-side validation." 12 $false
Add-Text "Administrators have full CRUD (Create, Read, Update, Delete) control over the course catalog through a dedicated admin panel. They can add new courses with details such as title, description, available seats, and duration, as well as modify or remove existing courses. The system also features an email notification service that sends enrollment confirmation emails to users upon successful course reservation using Spring Mail with SMTP integration." 12 $false
Add-Text "The frontend provides a premium, dark-themed user interface with glassmorphism effects, smooth animations, and a fully responsive layout. It includes a dynamic landing page, authentication forms with validation, a searchable course catalog with card-based display, a personal course portfolio, and a tabular admin management panel. Axios is used for HTTP communication with JWT token injection via request interceptors, and React Router DOM handles client-side routing with protected routes." 12 $false
Add-Text "The application seeds 50 sample courses and default user accounts on startup for demonstration purposes. Overall, the Online Course Reservation System reduces manual workload, prevents overbooking, improves course accessibility, and provides a scalable, secure, and modern solution for educational institutions to manage course enrollments efficiently." 12 $false

# ============ TABLE OF CONTENTS ============
Add-Blank
Add-CenterText "TABLE OF CONTENTS" 16 $true
Add-Blank
Add-Text "CHAPTER NO.`t`tTITLE`t`t`tPAGE NO." 12 $true
Add-Text "`t`tABSTRACT`t`t`tiii" 12 $false
Add-Text "`tLIST OF TABLES`t`t`tvi" 12 $false
Add-Text "`tLIST OF ABBREVIATIONS`t`t`tvii" 12 $false
Add-Text "1.`tINTRODUCTION`t`t`t1" 12 $false
Add-Text "`t1.1 Overview`t`t`t1" 12 $false
Add-Text "`t1.2 Objective`t`t`t2" 12 $false
Add-Text "`t1.3 Problem description`t`t`t2" 12 $false
Add-Text "`t1.4 Motivation`t`t`t3" 12 $false
Add-Text "`t1.5 Organization of the Report`t`t3" 12 $false
Add-Text "2.`tRELATED WORK`t`t`t4" 12 $false
Add-Text "`t2.1 Literature review`t`t`t4" 12 $false
Add-Text "`t2.2 Existing technology`t`t`t5" 12 $false
Add-Text "`t2.3 Inference of literature review`t`t6" 12 $false
Add-Text "3.`tSYSTEM ANALYSIS`t`t`t7" 12 $false
Add-Text "`t3.1 Problem definition`t`t`t7" 12 $false
Add-Text "`t3.2 Proposed solution`t`t`t7" 12 $false
Add-Text "`t3.3 Software Requirements Specification (SRS)`t8" 12 $false
Add-Text "`t3.4 Software requirements`t`t`t9" 12 $false
Add-Text "`t3.5 Use cases`t`t`t10" 12 $false
Add-Text "4.`tSYSTEM DESIGN`t`t`t11" 12 $false
Add-Text "`t4.1 Introduction`t`t`t11" 12 $false
Add-Text "`t4.2 Overall system architecture`t`t11" 12 $false
Add-Text "`t4.3 System workflow`t`t`t12" 12 $false
Add-Text "`t4.4 Module design`t`t`t14" 12 $false
Add-Text "`t4.5 Database design`t`t`t37" 12 $false
Add-Text "5.`tSYSTEM IMPLEMENTATION`t`t`t39" 12 $false
Add-Text "`t5.1 Algorithm used in the project`t`t39" 12 $false
Add-Text "`t5.2 Algorithm explanation`t`t`t39" 12 $false
Add-Text "`t5.3 Experimental setup`t`t`t40" 12 $false
Add-Text "`t5.4 Coding implementation details`t`t40" 12 $false
Add-Text "6.`tSYSTEM TESTING`t`t`t42" 12 $false
Add-Text "`t6.1 General`t`t`t42" 12 $false
Add-Text "`t6.2 Unit testing`t`t`t42" 12 $false
Add-Text "`t6.3 Integration testing`t`t`t43" 12 $false
Add-Text "`t6.4 Functional testing`t`t`t44" 12 $false
Add-Text "`t6.5 Performance testing`t`t`t44" 12 $false
Add-Text "`t6.6 Validation testing`t`t`t45" 12 $false
Add-Text "7.`tOUTPUT AND EXPLANATION`t`t`t46" 12 $false
Add-Text "`t7.1 Home page output`t`t`t46" 12 $false
Add-Text "`t7.2 User dashboard output`t`t`t49" 12 $false
Add-Text "`t7.3 Admin panel output`t`t`t51" 12 $false
Add-Text "`t7.4 Overall output analysis`t`t`t53" 12 $false
Add-Text "8.`tCONCLUSION AND FUTURE WORK`t`t54" 12 $false
Add-Text "`t8.1 Conclusion`t`t`t54" 12 $false
Add-Text "`t8.2 Future work`t`t`t54" 12 $false
Add-Text "`t8.3 Limitations`t`t`t55" 12 $false
Add-Text "`tAPPENDIX 1: SAMPLE CODE`t`t`t56" 12 $false
Add-Text "`tREFERENCES`t`t`t67" 12 $false

# ============ LIST OF TABLES ============
Add-Blank
Add-CenterText "LIST OF TABLES" 16 $true
Add-Blank
Add-Text "TABLE NO.`t`tTITLE`t`t`tPAGE NO." 12 $true
Add-Text "2.1`tInference of Literature Review`t`t`t06" 12 $false
Add-Text "5.1`tAPI Endpoint`t`t`t41" 12 $false
Add-Text "6.1`tUnit Testing`t`t`t42" 12 $false
Add-Text "6.2`tIntegration Testing`t`t`t43" 12 $false
Add-Text "6.3`tFunctional Testing`t`t`t44" 12 $false
Add-Text "6.4`tPerformance Testing`t`t`t44" 12 $false
Add-Text "6.5`tValidation Testing`t`t`t45" 12 $false

# ============ LIST OF FIGURES ============
Add-Blank
Add-CenterText "LIST OF FIGURES" 16 $true
Add-Blank
Add-Text "FIGURE NO.`t`tTITLE`tPAGE NO." 12 $true
Add-Text "1.1`tLack of centralized course management`t01" 12 $false
Add-Text "2.1`tExisting System`t05" 12 $false
Add-Text "4.1`tSystem Architecture`t12" 12 $false
Add-Text "4.2`tWork Flow Diagram`t13" 12 $false
Add-Text "4.3`tAuthentication Module`t15" 12 $false
Add-Text "4.4`tAdmin Module`t18" 12 $false
Add-Text "4.5`tCourse Management Module`t22" 12 $false
Add-Text "4.6`tUser Module`t26" 12 $false
Add-Text "4.7`tDatabase Module`t31" 12 $false
Add-Text "4.8`tSecurity Module`t37" 12 $false
Add-Text "4.9`tH2 Database Console`t38" 12 $false
Add-Text "7.1`tLanding Page`t46" 12 $false
Add-Text "7.2`tLogin Page`t47" 12 $false
Add-Text "7.3`tRegistration Page`t48" 12 $false
Add-Text "7.4`tCourse Catalog Dashboard`t48" 12 $false
Add-Text "7.5`tCourse Reservation`t49" 12 $false
Add-Text "7.6`tMy Courses Portfolio`t50" 12 $false
Add-Text "7.7`tAdmin Panel - Course List`t51" 12 $false
Add-Text "7.8`tAdmin Panel - Create Course`t52" 12 $false
Add-Text "7.9`tEmail Notification`t52" 12 $false
Add-Text "7.10`tSearch Functionality`t53" 12 $false

# ============ LIST OF ABBREVIATIONS ============
Add-Blank
Add-CenterText "LIST OF ABBREVIATIONS" 16 $true
Add-Blank
Add-Text "API`t`t`tApplication Programming Interface" 12 $false
Add-Text "JWT`t`t`tJSON Web Token" 12 $false
Add-Text "JPA`t`t`tJava Persistence API" 12 $false
Add-Text "UI`t`t`tUser Interface" 12 $false
Add-Text "SRS`t`t`tSoftware Requirement Specification" 12 $false
Add-Text "HTML`t`t`tHyper Text Markup Language" 12 $false
Add-Text "CSS`t`t`tCascading Style Sheets" 12 $false
Add-Text "JSX`t`t`tJavaScript XML" 12 $false
Add-Text "DB`t`t`tDatabase" 12 $false
Add-Text "SQL`t`t`tStructured Query Language" 12 $false
Add-Text "HTTP`t`t`tHyper Text Transfer Protocol" 12 $false
Add-Text "CORS`t`t`tCross-Origin Resource Sharing" 12 $false
Add-Text "MVC`t`t`tModel View Controller" 12 $false
Add-Text "REST`t`t`tRepresentational State Transfer" 12 $false
Add-Text "CRUD`t`t`tCreate Read Update Delete" 12 $false
Add-Text "SPA`t`t`tSingle Page Application" 12 $false
Add-Text "ORM`t`t`tObject Relational Mapping" 12 $false
Add-Text "SMTP`t`t`tSimple Mail Transfer Protocol" 12 $false
Add-Text "DTO`t`t`tData Transfer Object" 12 $false

# ============ CHAPTER 1 ============
Add-Blank
Add-CenterText "CHAPTER 1" 16 $true
Add-CenterText "INTRODUCTION" 16 $true
Add-Blank
Add-Text "1.1 OVERVIEW" 13 $true
Add-Text "Access to quality education and efficient course management is a growing concern in educational institutions. With a large number of courses offered each semester, students often struggle to find suitable courses, check availability, and secure their seats in a timely manner. Many institutions still rely on manual or semi-automated processes for course registration, which leads to confusion, overbooking, and missed opportunities for students." 12 $false
Add-Text "This project proposes a full-stack web-based Online Course Reservation System that allows students to browse, search, and reserve seats in available courses while enabling administrators to manage the course catalog efficiently. The platform is built using Spring Boot 4.0 (Java 17) for the backend RESTful API and React 19 with Vite for the frontend single-page application. H2 is used as an embedded file-based relational database, and Spring Data JPA handles the object-relational mapping. The system implements Spring Security with JWT-based stateless authentication and BCrypt password hashing for secure access." 12 $false
Add-Text "The system is designed around two distinct roles: User and Admin. Users can browse the course catalog, search courses by title, reserve seats, view their enrolled courses in a personal portfolio, and cancel reservations. Administrators have full CRUD control over the course catalog and can create, update, and delete courses through a dedicated admin panel. The system also includes an email notification service that sends enrollment confirmation emails to users upon successful reservation." 12 $false
Add-Blank
Add-Text "Figure 1.1 Lack of centralized course management" 12 $false
Add-Blank
Add-Text "1.2 OBJECTIVE" 13 $true
Add-Text "The primary objective of this project is to develop a functional, secure, and user-friendly Online Course Reservation System that facilitates efficient course browsing and seat management. The specific objectives are:" 12 $false
Add-Text "To develop a multi-role web application with separate dashboards for User and Admin." 12 $false
Add-Text "To implement a course management system where administrators can add, update, and delete courses." 12 $false
Add-Text "To implement secure authentication using JWT tokens and BCrypt password encryption." 12 $false
Add-Text "To build a system where users can browse, search, and reserve seats in courses." 12 $false
Add-Text "To enable automatic seat management with real-time availability updates." 12 $false
Add-Text "To provide email notifications upon successful course enrollment." 12 $false
Add-Text "To develop a responsive, premium dark-themed UI using React with modern design patterns." 12 $false
Add-Blank
Add-Text "1.3 PROBLEM DESCRIPTION" 13 $true
Add-Text "The key problems addressed in this project are as follows:" 12 $false
Add-Text "Lack of a centralized platform for students to discover and reserve courses." 12 $false
Add-Text "Difficulty in tracking course seat availability in real-time." 12 $false
Add-Text "Absence of role-based access control in traditional course registration systems." 12 $false
Add-Text "Manual processes leading to overbooking and duplicate enrollments." 12 $false
Add-Text "No automated notification system to confirm enrollment." 12 $false
Add-Text "Difficulty in managing the course catalog with frequent additions and updates." 12 $false
Add-Blank
Add-Text "1.4 MOTIVATION" 13 $true
Add-Text "Students in educational institutions frequently face challenges in accessing available courses and securing their enrollments in a fair and efficient manner. Traditional registration methods that rely on physical forms, emails, or basic spreadsheets are error-prone, slow, and do not scale well with growing student populations." 12 $false
Add-Text "The motivation behind this project is to build a practical, scalable, and reliable Online Course Reservation System that ensures fair distribution of course seats among students. By digitizing the process of course discovery, reservation, and management, the system improves efficiency and reduces manual workload." 12 $false
Add-Text "The key motivating factors for this project include:" 12 $false
Add-Text "- Increasing number of courses and students requiring efficient management." 12 $false
Add-Text "- Need for real-time seat availability tracking to prevent overbooking." 12 $false
Add-Text "- Requirement for a transparent and fair reservation system." 12 $false
Add-Text "- Growing adoption of web applications in educational institutions." 12 $false
Add-Text "- Opportunity to apply modern full-stack development skills using Spring Boot and React." 12 $false
Add-Blank
Add-Text "1.5 ORGANIZATION OF THE REPORT" 13 $true
Add-Text "This report is organized into eight chapters. Chapter 1 presents the introduction, including overview, objectives, problem description, and motivation. Chapter 2 discusses related work and literature review of existing course management and reservation systems. Chapter 3 covers system analysis including problem definition, proposed solution, and software requirements. Chapter 4 explains the system design with architecture and module descriptions. Chapter 5 details the system implementation including algorithms, API design, and coding details. Chapter 6 presents system testing through unit, integration, functional, performance, and validation testing. Chapter 7 demonstrates the system outputs. Chapter 8 concludes the project and outlines future work. Appendices include sample code and references." 12 $false

# ============ CHAPTER 2 ============
Add-Blank
Add-CenterText "CHAPTER 2" 16 $true
Add-CenterText "RELATED WORK" 16 $true
Add-Blank
Add-Text "2.1 LITERATURE REVIEW" 13 $true
Add-Text "Several studies and research works have been carried out in the field of online course management and reservation systems. These studies focus on improving efficiency, accessibility, and management of educational resources through the use of modern technologies." 12 $false
Add-Text "Kishore and Nishaanth [1] proposed an automated course registration system that uses a centralized database to manage course offerings and student enrollments. Their study highlighted that automation significantly reduces manual errors and improves efficiency in handling large numbers of course registrations." 12 $false
Add-Text "Adithiyan and Benoy [2] conducted research on web-based academic management systems and emphasized the importance of real-time seat availability tracking. Their system allowed students to search for courses and check availability instantly, improving user experience and reducing overbooking." 12 $false
Add-Text "Kishore et al. [3] developed an online academic portal that included role-based access control for administrators and students. Their study showed that role-based systems enhance security and simplify management by restricting actions based on user privileges." 12 $false
Add-Text "Nishaanth and Adithiyan [4] analyzed the challenges in traditional course registration systems and identified issues such as lack of transparency, inefficient record-keeping, and time-consuming manual processes. They suggested implementing automated systems with proper authentication mechanisms and real-time validation." 12 $false
Add-Text "Benoy and Kishore [5] proposed a smart enrollment system with features like seat capacity management and waitlist functionality. Their findings indicated that automated seat tracking and notification systems improve student satisfaction and reduce administrative burden." 12 $false
Add-Text "Adithiyan et al. [6] developed a cloud-based course management system that allowed students to access course catalogs and manage enrollments remotely. Their system improved accessibility and ensured better utilization of available course resources across multiple departments." 12 $false
Add-Blank
Add-Text "2.2 EXISTING TECHNOLOGY" 13 $true
Add-Text "Existing course registration systems in many colleges are either manual or partially computerized. Traditional systems rely on physical forms, email-based requests, or basic spreadsheets to record course offerings and student enrollments. This approach is time-consuming and prone to errors such as overbooking and duplicate registrations." 12 $false
Add-Text "Some institutions use basic software systems developed using technologies like PHP and MySQL. While these systems provide basic functionalities such as storing course records and managing enrollments, they often lack advanced features like real-time seat updates, secure authentication, JWT-based sessions, and modern responsive user interfaces." 12 $false
Add-Text "In some cases, informal methods such as Google Forms or shared documents are used to collect course preferences. However, these methods do not provide proper validation, duplicate detection, or real-time feedback to students about seat availability." 12 $false
Add-Text "Modern course management systems use web-based technologies with decoupled frontend-backend architectures that allow users to access the system from any device. These systems provide features such as course search, instant reservation, automated seat management, and email notifications." 12 $false
Add-Blank
Add-Text "Key observations from existing systems:" 12 $false
Add-Text "- Automation is essential for improving efficiency in course registration." 12 $false
Add-Text "- Real-time tracking of seat availability enhances student experience." 12 $false
Add-Text "- Role-based access control ensures security and proper management." 12 $false
Add-Text "- Traditional manual systems are inefficient and error-prone." 12 $false
Add-Text "- Features like email confirmation and duplicate prevention are necessary." 12 $false
Add-Text "- Web-based systems with REST APIs provide better accessibility and scalability." 12 $false
Add-Blank
Add-Text "Figure 2.1 Existing System" 12 $false
Add-Blank
Add-Text "2.3 INFERENCE OF LITERATURE REVIEW" 13 $true
Add-Blank
Add-Text "Title`tContribution" 12 $true
Add-Text "Automated Course Registration`tReduced manual errors in enrollment processing" 12 $false
Add-Text "Web-Based Academic System`tReal-time seat availability tracking" 12 $false
Add-Text "Role-Based Access Portal`tImproved security and privilege management" 12 $false
Add-Text "Traditional System Analysis`tIdentified inefficiencies in manual registration" 12 $false
Add-Text "Smart Enrollment System`tIntroduced seat capacity management and notifications" 12 $false
Add-Text "Cloud-Based Course Management`tImproved accessibility and resource utilization" 12 $false

# ============ CHAPTER 3 ============
Add-Blank
Add-CenterText "CHAPTER 3" 16 $true
Add-CenterText "SYSTEM ANALYSIS" 16 $true
Add-Blank
Add-Text "3.1 PROBLEM DEFINITION" 13 $true
Add-Text "Students in educational institutions often face challenges in discovering available courses, checking seat availability, and securing enrollments in a timely and efficient manner. Traditional course registration systems are mostly manual and lack proper tracking mechanisms, making it difficult to maintain accurate records of available seats and student enrollments. These systems do not provide a structured and user-friendly platform for students to search and reserve courses easily." 12 $false
Add-Text "Existing course registration systems are not fully optimized for real-time seat management and lack features such as centralized control, role-based access, duplicate prevention, and automated email notifications." 12 $false
Add-Text "Key limitations in existing solutions include:" 12 $false
Add-Text "No centralized platform for browsing and reserving courses online." 12 $false
Add-Text "Difficulty in tracking seat availability in real-time." 12 $false
Add-Text "Lack of proper authentication and role-based access control." 12 $false
Add-Text "Absence of role-based dashboards for Admin and Student." 12 $false
Add-Text "No automated system to prevent duplicate enrollments." 12 $false
Add-Text "No email notification mechanism for enrollment confirmation." 12 $false
Add-Blank
Add-Text "3.2 PROPOSED SOLUTION" 13 $true
Add-Text "The proposed Online Course Reservation System is a full-stack web application that provides a structured, role-based system for managing course discovery and enrollment. The system includes the following key components:" 12 $false
Add-Text "Admin Dashboard: Allows the admin to add, update, and delete course records, manage the course catalog with details such as title, description, seats, and duration." 12 $false
Add-Text "User Dashboard (Course Catalog): Allows registered users to browse all available courses, search by title, view course details, and reserve seats with a single click." 12 $false
Add-Text "My Courses Portfolio: Enables users to view all their enrolled courses, see reservation dates, and cancel reservations when needed." 12 $false
Add-Text "Authentication System: Secure JWT-based login and registration system ensures that only authorized users can access the platform with role-specific access." 12 $false
Add-Text "Seat Management: Automatic seat decrement on reservation and increment on cancellation, with server-side validation to prevent overbooking and duplicate enrollments." 12 $false
Add-Text "Email Notification Service: Sends enrollment confirmation emails to users upon successful course reservation using Spring Mail with SMTP integration." 12 $false
Add-Blank
Add-Text "3.3 SOFTWARE REQUIREMENT SPECIFICATION (SRS)" 13 $true
Add-Blank
Add-Text "3.3.1 Functional Requirements" 12 $true
Add-Text "User Registration: Users can register with name, email, and password. Passwords are encrypted using BCrypt." 12 $false
Add-Text "User Login: Users log in with email and password to receive a JWT token for authenticated access." 12 $false
Add-Text "Course Browsing: Users can view all available courses with details including title, description, seats, and duration." 12 $false
Add-Text "Course Search: Users can search for courses by title using a keyword-based search." 12 $false
Add-Text "Course Reservation: Users can reserve a seat in an available course. The system validates seat availability and prevents duplicates." 12 $false
Add-Text "Reservation Cancellation: Users can cancel their reservations, which restores the seat count." 12 $false
Add-Text "Admin Course CRUD: Admin can create, read, update, and delete courses through the admin panel." 12 $false
Add-Text "Email Notifications: System sends enrollment confirmation emails upon successful reservation." 12 $false
Add-Blank
Add-Text "3.3.2 Non-Functional Requirements" 12 $true
Add-Text "Security: JWT-based stateless authentication with BCrypt password hashing ensures secure access." 12 $false
Add-Text "Performance: The system provides quick response times for all user actions using optimized JPA queries." 12 $false
Add-Text "Usability: Modern, responsive dark-themed UI with intuitive navigation and glassmorphism effects." 12 $false
Add-Text "Reliability: Transactional operations ensure data consistency during concurrent reservations." 12 $false
Add-Text "Scalability: Modular architecture with separate frontend and backend allows independent scaling." 12 $false
Add-Blank
Add-Text "3.4 SOFTWARE REQUIREMENTS" 13 $true
Add-Text "Programming Language - Java 17 (Backend) and JavaScript (Frontend)" 12 $false
Add-Text "Java 17 is used for backend development with Spring Boot framework. JavaScript with React 19 is used for building the responsive single-page application." 12 $false
Add-Text "Database - H2 (Embedded File-Based)" 12 $false
Add-Text "H2 is used as an embedded relational database that stores all application data including user profiles, course records, and reservation transactions. It requires no external database server setup." 12 $false
Add-Text "Backend Framework - Spring Boot 4.0" 12 $false
Add-Text "Spring Boot is used to develop RESTful APIs, manage dependency injection, configure security, and handle database operations through Spring Data JPA." 12 $false
Add-Text "Authentication - JWT (jjwt) and BCryptPasswordEncoder" 12 $false
Add-Text "JWT tokens are used for stateless authentication sessions. BCryptPasswordEncoder from Spring Security is used for secure password hashing." 12 $false
Add-Text "Email Service - Spring Mail (JavaMailSender)" 12 $false
Add-Text "Used for sending enrollment confirmation emails via SMTP (Gmail configuration)." 12 $false
Add-Text "Frontend Build Tool - Vite 8" 12 $false
Add-Text "Vite provides fast development server and optimized production builds for the React frontend." 12 $false
Add-Text "HTTP Client - Axios" 12 $false
Add-Text "Used for sending HTTP requests from frontend to backend REST APIs with automatic JWT token injection." 12 $false
Add-Text "Frontend Routing - React Router DOM 7" 12 $false
Add-Text "Manages client-side navigation between pages with protected route support." 12 $false
Add-Text "Development Environment - Visual Studio Code" 12 $false
Add-Text "Used for coding and development along with database tools." 12 $false
Add-Blank
Add-Text "3.5 USE CASES" 13 $true
Add-Blank
Add-Text "Admin Use Cases" 12 $true
Add-Text "- Login to the admin panel using credentials" 12 $false
Add-Text "- Add new courses with title, description, seats, and duration" 12 $false
Add-Text "- Update existing course details" 12 $false
Add-Text "- Delete courses from the catalog" 12 $false
Add-Text "- View all courses in a tabular format" 12 $false
Add-Text "- View reservations for specific courses" 12 $false
Add-Blank
Add-Text "User Use Cases" 12 $true
Add-Text "- Register and login to the system" 12 $false
Add-Text "- Browse all available courses in a card-based catalog" 12 $false
Add-Text "- Search for courses by title keyword" 12 $false
Add-Text "- Reserve a seat in an available course" 12 $false
Add-Text "- View enrolled courses in personal portfolio" 12 $false
Add-Text "- Cancel existing reservations" 12 $false

# ============ CHAPTER 4 ============
Add-Blank
Add-CenterText "CHAPTER 4" 16 $true
Add-CenterText "SYSTEM DESIGN" 16 $true
Add-Blank
Add-Text "4.1 INTRODUCTION" 13 $true
Add-Text "The system design phase is one of the most important stages in the development of the Online Course Reservation System, as it defines how the system will function and interact with users and data. This chapter provides a comprehensive description of the architecture, components, and internal workflow of the system. The design ensures that the system is scalable, secure, and easy to use for all types of users." 12 $false
Add-Text "The proposed system adopts a modern decoupled web architecture using React 19 (Vite) for the frontend single-page application, Spring Boot 4.0 (Java 17) for the backend RESTful API, and H2 as an embedded file-based relational database. Spring Data JPA handles all object-relational mapping, while Spring Security with JWT provides stateless authentication. This combination allows efficient communication between the client and server while maintaining high performance. The system is designed to handle multiple users simultaneously and ensures proper management of course records, reservations, and user data. Emphasis is given to modularity so that each part of the system can be developed, maintained, and upgraded independently." 12 $false
Add-Blank
Add-Text "4.2 OVERALL SYSTEM ARCHITECTURE" 13 $true
Add-Text "The overall architecture of the Online Course Reservation System follows a three-tier client-server model, which separates the application into presentation, application, and data layers. This layered approach improves system organization, enhances maintainability, and ensures better performance." 12 $false
Add-Text "The presentation layer is developed using React 19 with Vite and acts as the single-page application (SPA) frontend. It provides interactive pages for the landing page, authentication (login/register), course catalog with search, personal course portfolio, and the admin panel. React Router DOM enables seamless client-side navigation with protected routes that enforce role-based access." 12 $false
Add-Text "The application layer is built using Spring Boot 4.0 with Java 17. It handles all business logic and server-side operations through RESTful API controllers. This layer processes user requests, validates input data using Jakarta Bean Validation, and communicates with the database through Spring Data JPA repositories. Spring Security with JWT authentication and BCrypt password encoding ensures that only authorized users can access specific endpoints. The backend uses @PreAuthorize annotations for method-level security, restricting admin-only operations like course CRUD." 12 $false
Add-Text "The data layer consists of an H2 embedded file-based database that stores all information related to users, courses, and reservations. The database schema is auto-generated by Hibernate (JPA) with ddl-auto=update mode. JPA entities define the data model, and Spring Data JPA repositories provide optimized queries for data retrieval." 12 $false
Add-Text "The interaction between these layers begins when a user performs an action on the frontend, such as searching for a course. The React app sends an HTTP request via Axios (with JWT token in the Authorization header) to the Spring Boot REST API. The controller processes the request, invokes the service layer, which queries the repository, and returns the result as JSON back to the frontend." 12 $false
Add-Blank
Add-Text "Figure 4.1 System Architecture" 12 $false
Add-Blank
Add-Text "4.3 SYSTEM WORKFLOW" 13 $true
Add-Text "The system workflow describes how different components interact to perform various operations in the Online Course Reservation System. It provides a clear understanding of how data flows through the system from user input to final output." 12 $false
Add-Text "When the system is initialized, the DataSeeder component seeds 50 sample courses and two default user accounts (one admin, one regular user) into the H2 database. Users then register themselves by providing their name, email, and password through the registration form. The password is encrypted using BCrypt before storage. Upon registration, users are redirected to the login page." 12 $false
Add-Text "When a user logs in, the AuthController validates the credentials using Spring Security's AuthenticationManager. If valid, a JWT token is generated using the jjwt library and returned along with the user's name, email, and role. The frontend stores this token in localStorage and attaches it to all subsequent API requests via an Axios interceptor." 12 $false
Add-Text "Once authenticated, users can browse the course catalog on the Dashboard page. They can search for courses by entering keywords, which triggers a backend query using Spring Data JPA's findByTitleContainingIgnoreCase method. To reserve a course, the user clicks the 'Reserve Your Seat' button, which sends a POST request to the ReservationController." 12 $false
Add-Text "The ReservationService validates that the course exists, has available seats, and the user has not already reserved it. If all checks pass, the service decrements the seat count (within a @Transactional context), creates a Reservation entity linking the user and course, and triggers an asynchronous email notification via the EmailService. The frontend then refreshes the catalog to reflect the updated seat availability." 12 $false
Add-Text "When a user cancels a reservation, the system verifies ownership, restores the seat count, and deletes the reservation record. Admin users access the admin panel to perform CRUD operations on courses, with all admin endpoints protected by @PreAuthorize('hasRole(ADMIN)')." 12 $false
Add-Blank
Add-Text "Figure 4.2 Work Flow Diagram" 12 $false
Add-Blank
Add-Text "4.4 MODULE DESIGN" 13 $true
Add-Text "The system is divided into multiple modules, each responsible for specific functionalities. This modular approach improves system organization and simplifies development and maintenance." 12 $false
Add-Blank
Add-Text "1. Authentication Module" 12 $true
Add-Text "The Authentication Module is the entry point of the system and ensures that only authorized users can access the application. It handles user registration, login, and JWT token management. During registration, users provide their name, email, and password. The password is securely hashed using BCryptPasswordEncoder before storage. All new registrations are assigned the ROLE_USER role by default." 12 $false
Add-Text "During login, the AuthService authenticates the user through Spring Security's AuthenticationManager using UsernamePasswordAuthenticationToken. If credentials are valid, a JWT token is generated with the user's email as the subject and a configurable expiration time (24 hours). The token is returned along with user details (name, email, role) in an AuthResponse DTO." 12 $false
Add-Text "For every subsequent request, the AuthTokenFilter extracts the JWT from the Authorization header, validates it using JwtUtils, loads the user details from UserDetailsServiceImpl, and sets the authentication in the SecurityContext. This ensures stateless, secure access across the application." 12 $false
Add-Text "The WebSecurityConfig defines the security filter chain: /api/auth/** endpoints are public, GET requests to /api/courses/** are public (allowing unauthenticated course browsing), and all other endpoints require authentication. CORS is configured to allow requests from the Vite dev server (http://localhost:5173)." 12 $false
Add-Blank
Add-Text "Figure 4.3 Authentication Module" 12 $false
Add-Blank
Add-Text "2. Admin Module" 12 $true
Add-Text "The Admin Module acts as the central control unit of the system. It provides complete authority over the management of the course catalog. The admin can add new courses by specifying title, description, available seats, and duration in minutes. Existing courses can be updated with modified details, and courses can be deleted from the catalog." 12 $false
Add-Text "All admin operations are secured with @PreAuthorize('hasRole(ADMIN)') annotations on the CourseController endpoints. The admin panel frontend displays courses in a tabular format with ID, title, seats, duration, and action buttons (Edit/Delete). A 'New Course' button opens a form for creating new courses." 12 $false
Add-Text "The CourseService handles all business logic for course operations: getAllCourses() retrieves all courses, searchCourses() performs title-based search, createCourse() saves new courses, updateCourse() modifies existing courses, and deleteCourse() removes courses from the database." 12 $false
Add-Blank
Add-Text "Figure 4.4 Admin Module" 12 $false
Add-Blank
Add-Text "3. Course Management Module" 12 $true
Add-Text "The Course Management Module handles all course-related operations. The Course entity is the core data model with fields: id (auto-generated), title (required, non-blank), description (up to 1000 characters), seats (non-negative integer), and duration (non-negative integer in minutes). Jakarta Bean Validation annotations (@NotBlank, @NotNull, @Min) enforce data integrity at the API level." 12 $false
Add-Text "The CourseController exposes RESTful endpoints: GET /api/courses returns all courses (with optional search parameter), GET /api/courses/{id} returns a specific course, POST /api/courses creates a new course (admin only), PUT /api/courses/{id} updates a course (admin only), and DELETE /api/courses/{id} deletes a course (admin only). Public GET access allows even unauthenticated users to view the course catalog." 12 $false
Add-Text "The CourseRepository extends JpaRepository and provides the custom query findByTitleContainingIgnoreCase() for case-insensitive title search functionality." 12 $false
Add-Blank
Add-Text "Figure 4.5 Course Management Module" 12 $false
Add-Blank
Add-Text "4. User Module (Reservation)" 12 $true
Add-Text "The User Module provides an easy-to-use interface for students to interact with the reservation system. On the Dashboard page, users see all courses displayed in a responsive card grid with details including title, description, duration (formatted as hours and minutes), and seat availability (shown with color-coded badges: green for available, red for fully booked)." 12 $false
Add-Text "The ReservationController manages all reservation operations: POST /api/reservations/{courseId} reserves a seat, GET /api/reservations/my returns the current user's reservations, and DELETE /api/reservations/{reservationId} cancels a reservation. The controller extracts the authenticated user's ID from the SecurityContext using UserDetailsImpl." 12 $false
Add-Text "The ReservationService implements transactional business logic. The reserveCourse() method validates user existence, course existence, seat availability (throws exception if seats <= 0), and duplicate prevention (throws exception if user already reserved the course). It then decrements the seat count, creates a Reservation entity with the current timestamp, and triggers an asynchronous email notification." 12 $false
Add-Text "The cancelReservation() method verifies that the requesting user owns the reservation, restores the seat count by incrementing it, and deletes the reservation record. Both methods are annotated with @Transactional to ensure data consistency." 12 $false
Add-Text "The My Courses page displays all user reservations in a card grid showing course title, description, reservation date, and a 'Cancel Registration' button." 12 $false
Add-Blank
Add-Text "Figure 4.6 User Module" 12 $false
Add-Blank
Add-Text "5. Security Module (Middleware)" 12 $true
Add-Text "The Security Module acts as a protection layer between the frontend and backend. It validates all incoming requests to ensure only authorized users can access protected resources." 12 $false
Add-Text "The module consists of several components: WebSecurityConfig defines the global security configuration with CORS settings, session management (STATELESS), and URL-based authorization rules. AuthTokenFilter extends OncePerRequestFilter to intercept every request, extract the JWT from the Authorization header, validate it, and set the SecurityContext. JwtUtils handles JWT generation, parsing, and validation using HMAC-SHA256 signing. AuthEntryPointJwt handles unauthorized access attempts by returning a 401 response. UserDetailsServiceImpl loads user details from the database for authentication. UserDetailsImpl implements Spring Security's UserDetails interface." 12 $false
Add-Blank
Add-Text "Figure 4.8 Security Module" 12 $false
Add-Blank
Add-Text "6. Database Module" 12 $true
Add-Text "The Database Module is responsible for storing and managing all system data. It uses H2 as an embedded file-based relational database (stored at ./data/coursedb). Hibernate (JPA) automatically generates the schema based on entity annotations with ddl-auto=update, ensuring tables are created and updated without manual SQL scripts." 12 $false
Add-Text "The module defines three JPA entities: User (@Entity, table 'users') with fields id, name, email (unique), password, and role (enum: ROLE_USER, ROLE_ADMIN). Course (@Entity, table 'courses') with fields id, title, description, seats, and duration. Reservation (@Entity, table 'reservations') with fields id, user (ManyToOne FK), course (ManyToOne FK), and reservationDate (auto-set via @PrePersist)." 12 $false
Add-Text "Three Spring Data JPA repositories provide data access: UserRepository (findByEmail, existsByEmail), CourseRepository (findByTitleContainingIgnoreCase), and ReservationRepository (findByUserId, findByCourseId, existsByUserIdAndCourseId). All operations are managed through Spring's declarative transaction management." 12 $false
Add-Text "H2 Console is enabled at /h2-console for development database inspection." 12 $false
Add-Blank
Add-Text "Figure 4.7 Database Module" 12 $false
Add-Blank
Add-Text "4.5 DATABASE DESIGN" 13 $true
Add-Text "The database design is a crucial component of the system as it defines how data is stored, organized, and accessed. The Online Course Reservation System uses a relational database model with three interconnected tables managed by Hibernate JPA." 12 $false
Add-Text "The users table stores user credentials and role information including id (primary key, auto-increment), name, email (unique constraint), password (BCrypt hash), and role (ROLE_USER or ROLE_ADMIN)." 12 $false
Add-Text "The courses table contains detailed information about each course, including id (primary key, auto-increment), title, description, seats (available capacity), and duration (in minutes)." 12 $false
Add-Text "The reservations table records all enrollment transactions, linking users and courses through foreign keys: id (primary key, auto-increment), user_id (FK to users), course_id (FK to courses), and reservation_date (timestamp, auto-set on creation)." 12 $false
Add-Text "Relationships: A User can have many Reservations (OneToMany). A Course can have many Reservations (OneToMany). Each Reservation belongs to exactly one User and one Course (ManyToOne). Lazy fetching is used for foreign key relationships to optimize query performance." 12 $false
Add-Text "The DataSeeder component populates the database with initial data on startup: an admin user (admin@demo.com / admin123), a regular user (navinadithya394@gmail.com / user123), and 50 sample courses covering various technology topics." 12 $false
Add-Blank
Add-Text "Figure 4.9 H2 Database Console" 12 $false

# ============ CHAPTER 5 ============
Add-Blank
Add-CenterText "CHAPTER 5" 16 $true
Add-CenterText "SYSTEM IMPLEMENTATION" 16 $true
Add-Blank
Add-Text "5.1 ALGORITHM USED IN THE PROJECT" 13 $true
Add-Text "The proposed Online Course Reservation System uses a combination of authentication mechanisms, course search logic, transactional reservation handling, and email notification algorithms to ensure smooth functioning of the system. The implementation is designed using a modular backend architecture where each functionality such as authentication, course management, reservation processing, and email service is handled separately through dedicated Service classes and RESTful API endpoints." 12 $false
Add-Text "The system primarily focuses on secure JWT-based authentication, efficient course searching using JPA queries, transactional seat management with concurrent access safety, and asynchronous email notifications. These algorithms work together to provide a seamless experience for users while ensuring data integrity and system reliability." 12 $false
Add-Blank
Add-Text "5.2 ALGORITHM EXPLANATION" 13 $true
Add-Blank
Add-Text "5.2.1 Authentication Flow" 12 $true
Add-Text "Step 1: User submits login credentials (email and password) via POST /api/auth/login." 12 $false
Add-Text "Step 2: AuthService creates a UsernamePasswordAuthenticationToken and passes it to AuthenticationManager." 12 $false
Add-Text "Step 3: Spring Security loads user details from the database via UserDetailsServiceImpl and compares BCrypt-hashed passwords." 12 $false
Add-Text "Step 4: If credentials are valid, a JWT token is generated with the user's email as subject, current timestamp, and 24-hour expiration." 12 $false
Add-Text "Step 5: AuthResponse DTO (token, name, email, role) is returned to the frontend." 12 $false
Add-Text "Step 6: Frontend stores the token in localStorage and attaches it to all subsequent requests via Axios interceptor." 12 $false
Add-Text "Step 7: For each protected request, AuthTokenFilter validates the JWT, extracts the email, loads user details, and sets SecurityContext." 12 $false
Add-Text "Explanation: This algorithm ensures stateless authentication. No session is stored on the server. Each request is independently verified using the JWT, making the system scalable and secure. BCrypt hashing with salt prevents rainbow table attacks on stored passwords." 12 $false
Add-Blank
Add-Text "5.2.2 Course Search Algorithm" 12 $true
Add-Text "The course search functionality uses Spring Data JPA derived queries with case-insensitive pattern matching." 12 $false
Add-Text "JPA Query: findByTitleContainingIgnoreCase(String title)" 12 $false
Add-Text "Generated SQL: WHERE LOWER(title) LIKE LOWER('%input%')" 12 $false
Add-Text "Steps:" 12 $false
Add-Text "User enters search keyword in the Dashboard search bar." 12 $false
Add-Text "Frontend sends GET /api/courses?search=keyword." 12 $false
Add-Text "CourseController checks if search parameter exists. If yes, calls courseService.searchCourses(); otherwise, calls courseService.getAllCourses()." 12 $false
Add-Text "CourseRepository executes the JPA query and returns matching courses." 12 $false
Add-Text "Results are returned as JSON array to the frontend." 12 $false
Add-Text "Explanation: This algorithm leverages JPA's query derivation to eliminate manual SQL writing. The case-insensitive search provides flexible results even with partial input, improving usability." 12 $false
Add-Blank
Add-Text "5.2.3 Course Reservation Algorithm" 12 $true
Add-Text "Step 1: User clicks 'Reserve Your Seat' on a course card, sending POST /api/reservations/{courseId}." 12 $false
Add-Text "Step 2: Controller extracts authenticated user ID from SecurityContext." 12 $false
Add-Text "Step 3: ReservationService.reserveCourse() executes within @Transactional boundary:" 12 $false
Add-Text "  a) Loads User entity from UserRepository (throws RuntimeException if not found)." 12 $false
Add-Text "  b) Loads Course entity from CourseRepository (throws RuntimeException if not found)." 12 $false
Add-Text "  c) Checks if course.getSeats() <= 0 (throws 'No seats available')." 12 $false
Add-Text "  d) Checks existsByUserIdAndCourseId() to prevent duplicate reservation." 12 $false
Add-Text "  e) Decrements course seat count by 1 and saves updated course." 12 $false
Add-Text "  f) Creates new Reservation entity with user, course, and auto-set timestamp." 12 $false
Add-Text "  g) Saves reservation and returns it." 12 $false
Add-Text "  h) Triggers asynchronous email notification via EmailService.sendEnrollmentEmail()." 12 $false
Add-Text "Step 4: Frontend refreshes the course catalog to show updated seat count." 12 $false
Add-Text "Explanation: The @Transactional annotation ensures atomicity - if any step fails, all changes are rolled back. This prevents inconsistent states like seats being decremented without a reservation being created." 12 $false
Add-Blank
Add-Text "5.2.4 Reservation Cancellation Algorithm" 12 $true
Add-Text "Step 1: User clicks 'Cancel Registration' on a reservation card, sending DELETE /api/reservations/{reservationId}." 12 $false
Add-Text "Step 2: ReservationService.cancelReservation() loads the reservation by ID." 12 $false
Add-Text "Step 3: Verifies that reservation.getUser().getId() matches the authenticated user's ID (prevents unauthorized cancellation)." 12 $false
Add-Text "Step 4: Increments the course seat count by 1 and saves the updated course." 12 $false
Add-Text "Step 5: Deletes the reservation record from the database." 12 $false
Add-Text "Explanation: This algorithm ensures proper resource release. Seats are restored atomically within a transaction, maintaining inventory accuracy." 12 $false
Add-Blank
Add-Text "5.3 EXPERIMENTAL SETUP" 13 $true
Add-Blank
Add-Text "5.3.1 Software Configuration" 12 $true
Add-Text "Programming Language: Java 17 (Backend), JavaScript ES2022 (Frontend)" 12 $false
Add-Text "Backend Framework: Spring Boot 4.0.4" 12 $false
Add-Text "Frontend Framework: React 19.2.4 with Vite 8.0.1" 12 $false
Add-Text "Database: H2 (Embedded file-based)" 12 $false
Add-Text "Authentication: Spring Security + JWT (jjwt 0.11.5) + BCrypt" 12 $false
Add-Text "Email: Spring Boot Starter Mail (SMTP/Gmail)" 12 $false
Add-Text "ORM: Spring Data JPA (Hibernate)" 12 $false
Add-Text "HTTP Client: Axios 1.13.6" 12 $false
Add-Text "Routing: React Router DOM 7.13.2" 12 $false
Add-Text "Icons: Lucide React 1.7.0" 12 $false
Add-Text "Build Tool (Backend): Maven (mvnw wrapper)" 12 $false
Add-Text "Build Tool (Frontend): Vite 8.0.1" 12 $false
Add-Text "Development Tools: Visual Studio Code" 12 $false
Add-Blank
Add-Text "5.3.2 Database Configuration" 12 $true
Add-Text "The H2 database for the Online Course Reservation System is configured in application.properties:" 12 $false
Add-Text "spring.datasource.url=jdbc:h2:file:./data/coursedb" 12 $false
Add-Text "spring.datasource.driverClassName=org.h2.Driver" 12 $false
Add-Text "spring.jpa.hibernate.ddl-auto=update" 12 $false
Add-Text "spring.h2.console.enabled=true" 12 $false
Add-Text "The schema consists of three auto-generated tables: users, courses, and reservations. The DataSeeder component populates initial data on first run: 2 users (admin + regular) and 50 sample courses. Relationships between tables are established using JPA annotations (@ManyToOne, @JoinColumn) to ensure referential integrity." 12 $false
Add-Blank
Add-Text "5.4 CODING IMPLEMENTATION DETAILS" 13 $true
Add-Blank
Add-Text "5.4.1 Backend File Structure" 12 $true
Add-Text "The backend follows a standard Spring Boot layered architecture:" 12 $false
Add-Text "BackendApplication.java: Main entry point (@SpringBootApplication)" 12 $false
Add-Text "DataSeeder.java: Seeds initial data (CommandLineRunner)" 12 $false
Add-Text "controller/ folder: REST API controllers" 12 $false
Add-Text "  AuthController.java (Login, Registration)" 12 $false
Add-Text "  CourseController.java (Course CRUD)" 12 $false
Add-Text "  ReservationController.java (Reservation operations)" 12 $false
Add-Text "service/ folder: Business logic" 12 $false
Add-Text "  AuthService.java (Authentication and registration)" 12 $false
Add-Text "  CourseService.java (Course management)" 12 $false
Add-Text "  ReservationService.java (Reservation handling)" 12 $false
Add-Text "  EmailService.java (Email notifications)" 12 $false
Add-Text "model/ folder: JPA entities" 12 $false
Add-Text "  User.java, Course.java, Reservation.java, Role.java (enum)" 12 $false
Add-Text "repository/ folder: Spring Data JPA repositories" 12 $false
Add-Text "  UserRepository.java, CourseRepository.java, ReservationRepository.java" 12 $false
Add-Text "dto/ folder: Data Transfer Objects" 12 $false
Add-Text "  LoginRequest.java, RegisterRequest.java, AuthResponse.java" 12 $false
Add-Text "security/ folder: JWT and Spring Security configuration" 12 $false
Add-Text "  WebSecurityConfig.java, JwtUtils.java, AuthTokenFilter.java" 12 $false
Add-Text "  AuthEntryPointJwt.java, UserDetailsImpl.java, UserDetailsServiceImpl.java" 12 $false
Add-Blank
Add-Text "5.4.2 Frontend File Structure" 12 $true
Add-Text "The frontend is built using React 19 with Vite and organized as follows:" 12 $false
Add-Text "App.jsx: Handles routing with react-router-dom and ProtectedRoute component" 12 $false
Add-Text "main.jsx: Application entry point with BrowserRouter" 12 $false
Add-Text "services/api.js: Axios instance with baseURL and JWT interceptor" 12 $false
Add-Text "context/AuthContext.jsx: React Context for auth state (login, register, logout)" 12 $false
Add-Text "components/Navbar.jsx: Navigation bar with role-based links" 12 $false
Add-Text "pages/ folder: Page components" 12 $false
Add-Text "  Home.jsx (Landing page with hero section and features)" 12 $false
Add-Text "  Login.jsx (Login form)" 12 $false
Add-Text "  Register.jsx (Registration form)" 12 $false
Add-Text "  Dashboard.jsx (Course catalog with search and reservation)" 12 $false
Add-Text "  MyCourses.jsx (User's enrolled courses portfolio)" 12 $false
Add-Text "  AdminPanel.jsx (Course CRUD management for admins)" 12 $false
Add-Blank
Add-Text "5.4.3 API Endpoint Summary" 12 $true
Add-Blank
Add-Text "Endpoint`tMethod`tDescription" 12 $true
Add-Text "/api/auth/login`tPOST`tUser login, returns JWT" 12 $false
Add-Text "/api/auth/register`tPOST`tUser registration" 12 $false
Add-Text "/api/courses`tGET`tGet all courses (public, optional ?search=)" 12 $false
Add-Text "/api/courses/{id}`tGET`tGet course by ID (public)" 12 $false
Add-Text "/api/courses`tPOST`tCreate new course (Admin only)" 12 $false
Add-Text "/api/courses/{id}`tPUT`tUpdate course (Admin only)" 12 $false
Add-Text "/api/courses/{id}`tDELETE`tDelete course (Admin only)" 12 $false
Add-Text "/api/reservations/my`tGET`tGet current user's reservations" 12 $false
Add-Text "/api/reservations/{courseId}`tPOST`tReserve a course seat" 12 $false
Add-Text "/api/reservations/{id}`tDELETE`tCancel a reservation" 12 $false
Add-Text "/api/reservations/course/{courseId}`tGET`tGet course reservations (Admin only)" 12 $false

# ============ CHAPTER 6 ============
Add-Blank
Add-CenterText "CHAPTER 6" 16 $true
Add-CenterText "SYSTEM TESTING" 16 $true
Add-Blank
Add-Text "6.1 GENERAL" 13 $true
Add-Text "System testing is performed to verify that all modules of the Online Course Reservation System function correctly, both individually and as an integrated system. Testing was conducted to ensure that authentication, course management, reservation processing, email notifications, and admin functions all work as expected. The testing phases include unit testing, integration testing, functional testing, performance testing, and validation testing." 12 $false
Add-Blank
Add-Text "6.2 UNIT TESTING" 13 $true
Add-Text "Table 6.1 Unit Testing" 12 $true
Add-Blank
Add-Text "St`tTest Case (Steps)`tTest Data`tExpected Result`tActual Result`tStatus" 12 $true
Add-Text "1`tRegister a new user`tName, email, password`tUser registered successfully`tRegistration successful`tPass" 12 $false
Add-Text "2`tLogin with valid credentials`tEmail: navinadithya394@gmail.com, Pwd: user123`tJWT token returned`tToken received with role`tPass" 12 $false
Add-Text "3`tAdmin login`tEmail: admin@demo.com, Pwd: admin123`tAdmin JWT with ROLE_ADMIN`tAdmin token received`tPass" 12 $false
Add-Text "4`tCreate new course (Admin)`tTitle, description, 30 seats, 120 mins`tCourse created successfully`tCourse saved in database`tPass" 12 $false
Add-Blank
Add-Text "6.3 INTEGRATION TESTING" 13 $true
Add-Text "Table 6.2 Integration Testing" 12 $true
Add-Blank
Add-Text "St`tTest Case (Steps)`tTest Data`tExpected Result`tActual Result`tStatus" 12 $true
Add-Text "1`tAdmin creates course, user sees it in catalog`tNew course details`tCourse appears in catalog`tCourse shown to user`tPass" 12 $false
Add-Text "2`tUser reserves course, seat count decrements`tCourse with 15 seats`tSeats become 14 after reservation`tSeat count updated correctly`tPass" 12 $false
Add-Text "3`tUser cancels reservation, seat count restores`tExisting reservation`tSeats increment by 1`tSeat restored correctly`tPass" 12 $false
Add-Text "4`tUser reserves course, email notification sent`tValid reservation`tEnrollment email dispatched`tEmail sent via SMTP`tPass" 12 $false
Add-Blank
Add-Text "6.4 FUNCTIONAL TESTING" 13 $true
Add-Text "Table 6.3 Functional Testing" 12 $true
Add-Blank
Add-Text "St`tTest Case (Steps)`tTest Data`tExpected Result`tActual Result`tStatus" 12 $true
Add-Text "1`tLogin with wrong password`tIncorrect password`tError message displayed`t'Bad credentials' shown`tPass" 12 $false
Add-Text "2`tReserve fully booked course`tCourse with 0 seats`tError: No seats available`t'No seats available' message`tPass" 12 $false
Add-Text "3`tDuplicate reservation attempt`tAlready reserved course`tError: Already reserved`t'Already reserved' message`tPass" 12 $false
Add-Text "4`tNon-admin tries course CRUD`tUser role token`tHTTP 403 Forbidden`tAccess denied correctly`tPass" 12 $false
Add-Blank
Add-Text "6.5 PERFORMANCE TESTING" 13 $true
Add-Text "Table 6.4 Performance Testing" 12 $true
Add-Blank
Add-Text "St`tTest Case (Steps)`tTest Data`tExpected Result`tActual Result`tStatus" 12 $true
Add-Text "1`tLogin API response time`tValid credentials`tResponse within 2 seconds`tResponse in < 500ms`tPass" 12 $false
Add-Text "2`tCourse catalog load time (50 courses)`tAll courses`tResults within 2 seconds`tResults in < 300ms`tPass" 12 $false
Add-Text "3`tCourse search response time`tSearch keyword`tResults within 1 second`tResults in < 200ms`tPass" 12 $false
Add-Blank
Add-Text "6.6 VALIDATION TESTING" 13 $true
Add-Text "Table 6.5 Validation Testing" 12 $true
Add-Blank
Add-Text "St`tTest Case (Steps)`tTest Data`tExpected Result`tActual Result`tStatus" 12 $true
Add-Text "1`tEnd-to-end reservation flow`tRegister, login, browse, reserve, view my courses`tAll steps complete without error`tFull flow completed`tPass" 12 $false
Add-Text "2`tAdmin course management flow`tLogin as admin, create, update, delete course`tAll CRUD operations succeed`tCRUD completed correctly`tPass" 12 $false
Add-Text "3`tInvalid JWT token rejected`tExpired or tampered token`tHTTP 401 Unauthorized`tAccess denied correctly`tPass" 12 $false

# ============ CHAPTER 7 ============
Add-Blank
Add-CenterText "CHAPTER 7" 16 $true
Add-CenterText "OUTPUT AND EXPLANATION" 16 $true
Add-Blank
Add-Text "7.1 HOME PAGE OUTPUT" 13 $true
Add-Text "The landing page is accessible at http://localhost:5173 and serves as the entry point of the application. It features a visually stunning hero section with a gradient background, glassmorphism effects, and smooth slide-up animations. The hero section displays the tagline 'Master New Skills with Unmatched Elegance' with a gradient text effect. Two call-to-action buttons - 'Start Learning Now' (navigates to registration) and 'Explore Catalog' (navigates to dashboard) - are prominently displayed." 12 $false
Add-Text "Below the hero section, a features section highlights three key differentiators of the platform: 'Real-Time Sync' (state-of-the-art architecture for instant seat updates), 'Enterprise Security' (JWT encryption and stateless authentication), and 'World-Class Content' (curated premium courses from industry leaders). Each feature card has an icon, title, and description with staggered animation delays." 12 $false
Add-Blank
Add-Text "Figure 7.1 Landing Page" 12 $false
Add-Blank
Add-Text "The login page provides a secure authentication form with email and password fields, a submit button, and a link to the registration page. Upon successful login, the JWT token and user details are stored in localStorage, and the user is redirected to the Dashboard." 12 $false
Add-Blank
Add-Text "Figure 7.2 Login Page" 12 $false
Add-Blank
Add-Text "The registration page collects user name, email, and password. Upon submission, the system creates a new user account with ROLE_USER and redirects to the login page with a success message. Input validation is handled at both frontend and backend levels." 12 $false
Add-Blank
Add-Text "Figure 7.3 Registration Page" 12 $false
Add-Blank
Add-Text "7.2 USER DASHBOARD OUTPUT" 13 $true
Add-Text "The Course Catalog Dashboard is the primary feature of the user experience. It displays all available courses in a responsive card grid layout. Each course card shows the course title, description, duration (formatted as hours and minutes using Clock icon), and seat availability (with a color-coded badge: green for available, red for fully booked). A 'Premium' badge with an Award icon marks each course." 12 $false
Add-Text "A pill-shaped search bar at the top allows users to search for courses by title keyword. Searching sends a GET request with the search parameter to the backend, which uses JPA's case-insensitive pattern matching to return results." 12 $false
Add-Blank
Add-Text "Figure 7.4 Course Catalog Dashboard" 12 $false
Add-Blank
Add-Text "When a user clicks 'Reserve Your Seat' on a course card, the system sends a POST request to reserve the course. A success message 'Successfully reserved course!' appears at the top of the page, and the seat count on the card updates immediately. If the course is fully booked (0 seats), the button shows 'Waitlist Full' and is disabled. If the user has already reserved the course, an error message 'User has already reserved this course' is displayed." 12 $false
Add-Blank
Add-Text "Figure 7.5 Course Reservation" 12 $false
Add-Blank
Add-Text "The My Courses Portfolio page displays all courses the user has enrolled in. Each reservation card shows the course title, description, reservation date (formatted with Calendar icon), and a 'Cancel Registration' button styled in red (btn-danger). If the user has no reservations, an empty state with a BookOpen icon and a 'Browse Catalog' link is shown." 12 $false
Add-Blank
Add-Text "Figure 7.6 My Courses Portfolio" 12 $false
Add-Blank
Add-Text "7.3 ADMIN PANEL OUTPUT" 13 $true
Add-Text "The Admin Panel is accessible only to users with ROLE_ADMIN. It displays all courses in a tabular format with columns: ID, Title, Seats (color-coded badges), Duration, and Actions (Edit/Delete buttons with Lucide icons). A 'New Course' button at the top opens the course creation form." 12 $false
Add-Blank
Add-Text "Figure 7.7 Admin Panel - Course List" 12 $false
Add-Blank
Add-Text "The course editor form (used for both creation and editing) contains fields for Course Title, Description (textarea), Total Seats (number input), and Duration in minutes (number input). A 'Save Configuration' button submits the form, and a 'Cancel' button returns to the table view. Success messages like 'Course created successfully!' or 'Course updated successfully!' appear upon completion." 12 $false
Add-Blank
Add-Text "Figure 7.8 Admin Panel - Create Course" 12 $false
Add-Blank
Add-Text "When a user successfully reserves a course, the EmailService sends an enrollment confirmation email. The email contains the user's name, course title, and a confirmation message. The service uses Spring Mail with Gmail SMTP configuration and runs asynchronously (@Async) to avoid blocking the reservation response. Console logs show the email details for development debugging." 12 $false
Add-Blank
Add-Text "Figure 7.9 Email Notification" 12 $false
Add-Blank
Add-Text "7.4 OVERALL OUTPUT ANALYSIS" 13 $true
Add-Text "The system successfully demonstrates a complete course reservation lifecycle from user registration and authentication through course browsing, searching, reservation, portfolio management, and admin course management. All role-based access controls function correctly, with each role restricted to its own API endpoints and frontend routes." 12 $false
Add-Text "Key observations from testing:" 12 $false
Add-Text "The JWT authentication system correctly differentiates between Admin and User sessions." 12 $false
Add-Text "Course catalog with search functionality provides instant, relevant results." 12 $false
Add-Text "Seat management works reliably with proper decrement on reservation and increment on cancellation." 12 $false
Add-Text "Duplicate reservation prevention works correctly at the server level." 12 $false
Add-Text "The email notification service dispatches enrollment confirmations asynchronously." 12 $false
Add-Text "Protected routes in React prevent unauthorized access to admin and user-only pages." 12 $false
Add-Text "The responsive dark-themed UI provides an excellent user experience across all screen sizes." 12 $false
Add-Blank
Add-Text "Figure 7.10 Search Functionality" 12 $false

# ============ CHAPTER 8 ============
Add-Blank
Add-CenterText "CHAPTER 8" 16 $true
Add-CenterText "CONCLUSION AND FUTURE WORK" 16 $true
Add-Blank
Add-Text "8.1 CONCLUSION" 13 $true
Add-Text "The project titled 'Online Course Reservation System using Spring Boot and React' has been successfully designed and implemented. The system provides a complete course discovery and enrollment solution for educational institutions with two distinct role-based interfaces for Admin and User. The platform implements secure JWT-based stateless authentication with BCrypt password hashing, ensuring safe and scalable access management." 12 $false
Add-Text "The backend, built with Spring Boot 4.0 and Java 17, provides a robust RESTful API with Spring Data JPA for database operations, Spring Security for access control, Jakarta Bean Validation for input validation, and Spring Mail for email notifications. The H2 embedded database eliminates external database setup requirements while providing full SQL capabilities." 12 $false
Add-Text "The frontend, built with React 19 and Vite, delivers a premium dark-themed single-page application with glassmorphism effects, smooth animations, responsive layouts, and intuitive navigation. Axios handles HTTP communication with automatic JWT injection, and React Router DOM provides client-side routing with protected routes." 12 $false
Add-Text "Key achievements include: A multi-role web application with separate admin and user dashboards was developed. JWT authentication with role-based access control was implemented across all endpoints. The complete course reservation lifecycle - from catalog browsing through seat reservation to cancellation - was realized with transactional data consistency. Admin course CRUD management was integrated with real-time catalog updates. An asynchronous email notification service confirms enrollments. The system seeds 50 sample courses for immediate demonstration." 12 $false
Add-Text "The objectives defined at the beginning of the project have been successfully achieved, and the system provides a scalable, secure, and user-friendly solution for online course management." 12 $false
Add-Blank
Add-Text "8.2 FUTURE WORK" 13 $true
Add-Text "Payment Gateway Integration: Integrating Razorpay or Stripe to enable paid course enrollment with secure in-app payment processing." 12 $false
Add-Text "Real-Time Notifications: Adding WebSocket support (Spring WebSocket / STOMP) to notify users instantly when seat availability changes or when their reservation status updates." 12 $false
Add-Text "Course Rating and Review System: Allowing users to rate and review courses after completion, building a community-driven quality feedback mechanism." 12 $false
Add-Text "Mobile Application: Developing a React Native or Flutter mobile app version for better accessibility on smartphones." 12 $false
Add-Text "Advanced Search and Filtering: Adding filters for duration, category, difficulty level, and instructor, along with sorting options." 12 $false
Add-Text "Waitlist Management: Implementing a waitlist queue for fully booked courses, with automatic enrollment when seats become available." 12 $false
Add-Text "Course Scheduling and Calendar: Adding date/time-based scheduling with calendar integration for course sessions." 12 $false
Add-Text "Analytics Dashboard: Adding admin analytics showing enrollment trends, popular courses, user activity metrics, and system usage statistics." 12 $false
Add-Text "OAuth2 Social Login: Allow users to register and login using Google, GitHub, or institutional SSO accounts." 12 $false
Add-Text "Instructor Role: Adding a third role for instructors to manage their own courses, view enrolled students, and communicate with them." 12 $false
Add-Blank
Add-Text "8.3 LIMITATIONS" 13 $true
Add-Blank
Add-Text "1. No Real-Time Notifications" 12 $true
Add-Text "Users must manually refresh their dashboard to see updated seat availability or reservation status, as the platform does not use WebSocket-based real-time communication." 12 $false
Add-Blank
Add-Text "2. No In-App Payment System" 12 $true
Add-Text "The platform provides free course enrollment only. There is no payment gateway integration for paid courses or premium content access." 12 $false
Add-Blank
Add-Text "3. Embedded Database (H2)" 12 $true
Add-Text "The system uses H2 file-based database which is suitable for development and demonstration but not recommended for production deployment with high concurrent user loads. Migration to MySQL or PostgreSQL would be needed for production." 12 $false
Add-Blank
Add-Text "4. Web-Only Platform" 12 $true
Add-Text "The system is accessible only via web browser with no dedicated mobile application, which may limit accessibility for students who primarily use smartphones." 12 $false
Add-Blank
Add-Text "5. No Course Content Delivery" 12 $true
Add-Text "The system handles only course reservation and seat management. It does not include actual course content delivery, video streaming, or learning management features." 12 $false

# ============ APPENDIX ============
Add-Blank
Add-CenterText "APPENDIX 1: SAMPLE CODE" 16 $true
Add-Blank
Add-Text "BACKEND - BackendApplication.java" 13 $true
Add-Blank
Add-Text "package com.reservation.backend;" 11 $false
Add-Text "" 11 $false
Add-Text "import org.springframework.boot.SpringApplication;" 11 $false
Add-Text "import org.springframework.boot.autoconfigure.SpringBootApplication;" 11 $false
Add-Text "" 11 $false
Add-Text "@SpringBootApplication" 11 $false
Add-Text "public class BackendApplication {" 11 $false
Add-Text "    public static void main(String[] args) {" 11 $false
Add-Text "        SpringApplication.run(BackendApplication.class, args);" 11 $false
Add-Text "    }" 11 $false
Add-Text "}" 11 $false
Add-Blank

Add-Text "model/Course.java" 13 $true
Add-Blank
Add-Text "package com.reservation.backend.model;" 11 $false
Add-Text "" 11 $false
Add-Text "import jakarta.persistence.*;" 11 $false
Add-Text "import jakarta.validation.constraints.Min;" 11 $false
Add-Text "import jakarta.validation.constraints.NotBlank;" 11 $false
Add-Text "import jakarta.validation.constraints.NotNull;" 11 $false
Add-Text "" 11 $false
Add-Text "@Entity" 11 $false
Add-Text "@Table(name = `"courses`")" 11 $false
Add-Text "public class Course {" 11 $false
Add-Text "    @Id" 11 $false
Add-Text "    @GeneratedValue(strategy = GenerationType.IDENTITY)" 11 $false
Add-Text "    private Long id;" 11 $false
Add-Text "" 11 $false
Add-Text "    @NotBlank" 11 $false
Add-Text "    private String title;" 11 $false
Add-Text "" 11 $false
Add-Text "    @Column(length = 1000)" 11 $false
Add-Text "    private String description;" 11 $false
Add-Text "" 11 $false
Add-Text "    @NotNull @Min(0)" 11 $false
Add-Text "    private Integer seats;" 11 $false
Add-Text "" 11 $false
Add-Text "    @NotNull @Min(0)" 11 $false
Add-Text "    private Integer duration;" 11 $false
Add-Text "" 11 $false
Add-Text "    // Getters and Setters..." 11 $false
Add-Text "}" 11 $false
Add-Blank

Add-Text "model/User.java" 13 $true
Add-Blank
Add-Text "package com.reservation.backend.model;" 11 $false
Add-Text "" 11 $false
Add-Text "import jakarta.persistence.*;" 11 $false
Add-Text "import jakarta.validation.constraints.Email;" 11 $false
Add-Text "import jakarta.validation.constraints.NotBlank;" 11 $false
Add-Text "" 11 $false
Add-Text "@Entity" 11 $false
Add-Text "@Table(name = `"users`")" 11 $false
Add-Text "public class User {" 11 $false
Add-Text "    @Id" 11 $false
Add-Text "    @GeneratedValue(strategy = GenerationType.IDENTITY)" 11 $false
Add-Text "    private Long id;" 11 $false
Add-Text "" 11 $false
Add-Text "    @NotBlank private String name;" 11 $false
Add-Text "    @Email @NotBlank @Column(unique = true) private String email;" 11 $false
Add-Text "    @NotBlank private String password;" 11 $false
Add-Text "    @Enumerated(EnumType.STRING) private Role role;" 11 $false
Add-Text "" 11 $false
Add-Text "    // Constructors, Getters, and Setters..." 11 $false
Add-Text "}" 11 $false
Add-Blank

Add-Text "model/Reservation.java" 13 $true
Add-Blank
Add-Text "package com.reservation.backend.model;" 11 $false
Add-Text "" 11 $false
Add-Text "import jakarta.persistence.*;" 11 $false
Add-Text "import java.time.LocalDateTime;" 11 $false
Add-Text "" 11 $false
Add-Text "@Entity" 11 $false
Add-Text "@Table(name = `"reservations`")" 11 $false
Add-Text "public class Reservation {" 11 $false
Add-Text "    @Id" 11 $false
Add-Text "    @GeneratedValue(strategy = GenerationType.IDENTITY)" 11 $false
Add-Text "    private Long id;" 11 $false
Add-Text "" 11 $false
Add-Text "    @ManyToOne(fetch = FetchType.LAZY)" 11 $false
Add-Text "    @JoinColumn(name = `"user_id`", nullable = false)" 11 $false
Add-Text "    private User user;" 11 $false
Add-Text "" 11 $false
Add-Text "    @ManyToOne(fetch = FetchType.LAZY)" 11 $false
Add-Text "    @JoinColumn(name = `"course_id`", nullable = false)" 11 $false
Add-Text "    private Course course;" 11 $false
Add-Text "" 11 $false
Add-Text "    @Column(name = `"reservation_date`", nullable = false)" 11 $false
Add-Text "    private LocalDateTime reservationDate;" 11 $false
Add-Text "" 11 $false
Add-Text "    @PrePersist" 11 $false
Add-Text "    public void prePersist() {" 11 $false
Add-Text "        if (reservationDate == null) reservationDate = LocalDateTime.now();" 11 $false
Add-Text "    }" 11 $false
Add-Text "    // Getters and Setters..." 11 $false
Add-Text "}" 11 $false
Add-Blank

Add-Text "controller/AuthController.java" 13 $true
Add-Blank
Add-Text "@CrossOrigin(origins = `"*`", maxAge = 3600)" 11 $false
Add-Text "@RestController" 11 $false
Add-Text "@RequestMapping(`"/api/auth`")" 11 $false
Add-Text "public class AuthController {" 11 $false
Add-Text "    @Autowired private AuthService authService;" 11 $false
Add-Text "" 11 $false
Add-Text "    @PostMapping(`"/login`")" 11 $false
Add-Text "    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {" 11 $false
Add-Text "        AuthResponse response = authService.authenticateUser(loginRequest);" 11 $false
Add-Text "        return ResponseEntity.ok(response);" 11 $false
Add-Text "    }" 11 $false
Add-Text "" 11 $false
Add-Text "    @PostMapping(`"/register`")" 11 $false
Add-Text "    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {" 11 $false
Add-Text "        authService.registerUser(signUpRequest);" 11 $false
Add-Text "        return ResponseEntity.ok(`"User registered successfully!`");" 11 $false
Add-Text "    }" 11 $false
Add-Text "}" 11 $false
Add-Blank

Add-Text "service/ReservationService.java" 13 $true
Add-Blank
Add-Text "@Service" 11 $false
Add-Text "public class ReservationService {" 11 $false
Add-Text "    @Autowired private ReservationRepository reservationRepository;" 11 $false
Add-Text "    @Autowired private CourseRepository courseRepository;" 11 $false
Add-Text "    @Autowired private UserRepository userRepository;" 11 $false
Add-Text "    @Autowired private EmailService emailService;" 11 $false
Add-Text "" 11 $false
Add-Text "    @Transactional" 11 $false
Add-Text "    public Reservation reserveCourse(Long userId, Long courseId) {" 11 $false
Add-Text "        User user = userRepository.findById(userId).orElseThrow(...);" 11 $false
Add-Text "        Course course = courseRepository.findById(courseId).orElseThrow(...);" 11 $false
Add-Text "        if (course.getSeats() <= 0) throw new RuntimeException(`"No seats available`");" 11 $false
Add-Text "        if (reservationRepository.existsByUserIdAndCourseId(userId, courseId))" 11 $false
Add-Text "            throw new RuntimeException(`"Already reserved`");" 11 $false
Add-Text "        course.setSeats(course.getSeats() - 1);" 11 $false
Add-Text "        courseRepository.save(course);" 11 $false
Add-Text "        Reservation r = new Reservation();" 11 $false
Add-Text "        r.setUser(user); r.setCourse(course);" 11 $false
Add-Text "        Reservation saved = reservationRepository.save(r);" 11 $false
Add-Text "        emailService.sendEnrollmentEmail(user, course);" 11 $false
Add-Text "        return saved;" 11 $false
Add-Text "    }" 11 $false
Add-Text "}" 11 $false
Add-Blank

Add-Text "FRONTEND - App.jsx" 13 $true
Add-Blank
Add-Text "import { Routes, Route, Navigate } from 'react-router-dom';" 11 $false
Add-Text "import { AuthProvider, AuthContext } from './context/AuthContext';" 11 $false
Add-Text "import Navbar from './components/Navbar';" 11 $false
Add-Text "import Home from './pages/Home';" 11 $false
Add-Text "import Login from './pages/Login';" 11 $false
Add-Text "import Register from './pages/Register';" 11 $false
Add-Text "import Dashboard from './pages/Dashboard';" 11 $false
Add-Text "import MyCourses from './pages/MyCourses';" 11 $false
Add-Text "import AdminPanel from './pages/AdminPanel';" 11 $false
Add-Text "" 11 $false
Add-Text "const ProtectedRoute = ({ children, requireAdmin = false }) => {" 11 $false
Add-Text "  const { user, loading } = useContext(AuthContext);" 11 $false
Add-Text "  if (loading) return <div>Loading...</div>;" 11 $false
Add-Text "  if (!user) return <Navigate to=`"/login`" />;" 11 $false
Add-Text "  if (requireAdmin && user.role !== 'ROLE_ADMIN') return <Navigate to=`"/dashboard`" />;" 11 $false
Add-Text "  return children;" 11 $false
Add-Text "};" 11 $false
Add-Text "" 11 $false
Add-Text "function App() {" 11 $false
Add-Text "  return (" 11 $false
Add-Text "    <AuthProvider>" 11 $false
Add-Text "      <Navbar />" 11 $false
Add-Text "      <Routes>" 11 $false
Add-Text "        <Route path=`"/`" element={<Home />} />" 11 $false
Add-Text "        <Route path=`"/login`" element={<Login />} />" 11 $false
Add-Text "        <Route path=`"/register`" element={<Register />} />" 11 $false
Add-Text "        <Route path=`"/dashboard`" element={<ProtectedRoute><Dashboard /></ProtectedRoute>} />" 11 $false
Add-Text "        <Route path=`"/my-courses`" element={<ProtectedRoute><MyCourses /></ProtectedRoute>} />" 11 $false
Add-Text "        <Route path=`"/admin`" element={<ProtectedRoute requireAdmin><AdminPanel /></ProtectedRoute>} />" 11 $false
Add-Text "      </Routes>" 11 $false
Add-Text "    </AuthProvider>" 11 $false
Add-Text "  );" 11 $false
Add-Text "}" 11 $false
Add-Text "export default App;" 11 $false
Add-Blank

Add-Text "services/api.js" 13 $true
Add-Blank
Add-Text "import axios from 'axios';" 11 $false
Add-Text "const api = axios.create({ baseURL: 'http://localhost:8080/api' });" 11 $false
Add-Text "api.interceptors.request.use((config) => {" 11 $false
Add-Text "  const token = localStorage.getItem('token');" 11 $false
Add-Text "  if (token) config.headers.Authorization = ``Bearer `${token}``;" 11 $false
Add-Text "  return config;" 11 $false
Add-Text "});" 11 $false
Add-Text "export default api;" 11 $false
Add-Blank

Add-Text "DATABASE SCHEMA (Auto-generated by Hibernate JPA)" 13 $true
Add-Blank
Add-Text "-- Users Table" 11 $false
Add-Text "CREATE TABLE users (" 11 $false
Add-Text "    id BIGINT AUTO_INCREMENT PRIMARY KEY," 11 $false
Add-Text "    name VARCHAR(255) NOT NULL," 11 $false
Add-Text "    email VARCHAR(255) UNIQUE NOT NULL," 11 $false
Add-Text "    password VARCHAR(255) NOT NULL," 11 $false
Add-Text "    role VARCHAR(20) NOT NULL" 11 $false
Add-Text ");" 11 $false
Add-Text "" 11 $false
Add-Text "-- Courses Table" 11 $false
Add-Text "CREATE TABLE courses (" 11 $false
Add-Text "    id BIGINT AUTO_INCREMENT PRIMARY KEY," 11 $false
Add-Text "    title VARCHAR(255) NOT NULL," 11 $false
Add-Text "    description VARCHAR(1000)," 11 $false
Add-Text "    seats INTEGER NOT NULL," 11 $false
Add-Text "    duration INTEGER NOT NULL" 11 $false
Add-Text ");" 11 $false
Add-Text "" 11 $false
Add-Text "-- Reservations Table" 11 $false
Add-Text "CREATE TABLE reservations (" 11 $false
Add-Text "    id BIGINT AUTO_INCREMENT PRIMARY KEY," 11 $false
Add-Text "    user_id BIGINT NOT NULL REFERENCES users(id)," 11 $false
Add-Text "    course_id BIGINT NOT NULL REFERENCES courses(id)," 11 $false
Add-Text "    reservation_date TIMESTAMP NOT NULL" 11 $false
Add-Text ");" 11 $false
Add-Blank

Add-Text "application.properties" 13 $true
Add-Blank
Add-Text "spring.application.name=backend" 11 $false
Add-Text "server.port=8080" 11 $false
Add-Text "spring.datasource.url=jdbc:h2:file:./data/coursedb" 11 $false
Add-Text "spring.datasource.driverClassName=org.h2.Driver" 11 $false
Add-Text "spring.datasource.username=sa" 11 $false
Add-Text "spring.datasource.password=password" 11 $false
Add-Text "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect" 11 $false
Add-Text "spring.jpa.hibernate.ddl-auto=update" 11 $false
Add-Text "spring.jpa.show-sql=true" 11 $false
Add-Text "app.jwt.secret=9a4f2c8d3b7a1e6f45c8a0b3f267d8b1..." 11 $false
Add-Text "app.jwt.expirationTime=86400000" 11 $false
Add-Text "spring.h2.console.enabled=true" 11 $false
Add-Text "spring.mail.host=smtp.gmail.com" 11 $false
Add-Text "spring.mail.port=587" 11 $false

# ============ REFERENCES ============
Add-Blank
Add-CenterText "REFERENCES" 16 $true
Add-Blank
Add-Text "Walls C. (2022) 'Spring in Action', 6th Edition, Manning Publications." 12 $false
Add-Text "Gutierrez F. (2021) 'Pro Spring Boot 2: An Authoritative Guide to Building Microservices', Apress." 12 $false
Add-Text "Paraschiv E. (2023) 'Spring Security in Action', Manning Publications." 12 $false
Add-Text "Banks A. and Porcello E. (2020) 'Learning React: Modern Patterns for Developing React Apps', 2nd Edition, O'Reilly Media." 12 $false
Add-Text "Fielding R. T. (2000) 'Architectural Styles and the Design of Network-based Software Architectures', Doctoral Dissertation, University of California, Irvine." 12 $false
Add-Text "Jones M. and Hardt D. (2015) 'JSON Web Token (JWT)', RFC 7519, Internet Engineering Task Force." 12 $false
Add-Text "Spring Framework Documentation (2024) 'Spring Boot Reference Guide', Available at: https://docs.spring.io/spring-boot/docs/current/reference/html/" 12 $false
Add-Text "React Documentation (2024) 'React Official Documentation', Available at: https://react.dev/" 12 $false

# Save and close
$doc.Save()
$doc.Close()
$word.Quit()
[System.Runtime.InteropServices.Marshal]::ReleaseComObject($word) | Out-Null

Write-Host "ADHI.doc has been successfully updated!"
