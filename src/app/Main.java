package app;

import model.Course;
import model.Reservation;
import model.Student;
import service.CourseService;
import service.ReservationService;
import service.StudentService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final ReservationService reservationService = new ReservationService();

    private static Student loggedInStudent = null;

    public static void main(String[] args) {
        printBanner();

        while (true) {
            if (loggedInStudent == null) {
                showGuestMenu();
            } else {
                showStudentMenu();
            }
        }
    }

    // ─────────────────────────────────────────────────
    // BANNER
    // ─────────────────────────────────────────────────
    private static void printBanner() {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║       ONLINE COURSE RESERVATION SYSTEM               ║");
        System.out.println("║       ─────────────────────────────────               ║");
        System.out.println("║       Browse Courses • Reserve Seats • Learn          ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.println();
    }

    // ─────────────────────────────────────────────────
    // GUEST MENU
    // ─────────────────────────────────────────────────
    private static void showGuestMenu() {
        System.out.println("┌───────────── MAIN MENU ─────────────┐");
        System.out.println("│  1. Register                        │");
        System.out.println("│  2. Login                           │");
        System.out.println("│  3. View Available Courses          │");
        System.out.println("│  4. Exit                            │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("  Choose an option: ");

        int choice = readInt();
        switch (choice) {
            case 1:
                handleRegister();
                break;
            case 2:
                handleLogin();
                break;
            case 3:
                handleViewCourses();
                break;
            case 4:
                System.out.println("\n  Thank you for using the system. Goodbye!");
                System.exit(0);
            default:
                System.out.println("  ⚠ Invalid option. Please try again.\n");
        }
    }

    // ─────────────────────────────────────────────────
    // STUDENT MENU (after login)
    // ─────────────────────────────────────────────────
    private static void showStudentMenu() {
        System.out.println("┌───────── STUDENT DASHBOARD ─────────┐");
        System.out.println("│  Welcome, " + padRight(loggedInStudent.getName(), 25) + "│");
        System.out.println("│                                     │");
        System.out.println("│  1. View Available Courses          │");
        System.out.println("│  2. Reserve a Course                │");
        System.out.println("│  3. My Reservations                 │");
        System.out.println("│  4. Cancel a Reservation            │");
        System.out.println("│  5. Logout                          │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("  Choose an option: ");

        int choice = readInt();
        switch (choice) {
            case 1:
                handleViewCourses();
                break;
            case 2:
                handleReserveCourse();
                break;
            case 3:
                handleMyReservations();
                break;
            case 4:
                handleCancelReservation();
                break;
            case 5:
                System.out.println("  Logged out successfully.\n");
                loggedInStudent = null;
                break;
            default:
                System.out.println("  ⚠ Invalid option. Please try again.\n");
        }
    }

    // ─────────────────────────────────────────────────
    // REGISTER
    // ─────────────────────────────────────────────────
    private static void handleRegister() {
        System.out.println("\n  ── Register a New Account ──");
        System.out.print("  Full Name : ");
        String name = scanner.nextLine().trim();
        System.out.print("  Email     : ");
        String email = scanner.nextLine().trim();
        System.out.print("  Password  : ");
        String password = scanner.nextLine().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("  ⚠ All fields are required.\n");
            return;
        }

        try {
            Student student = studentService.register(name, email, password);
            System.out.println("  ✓ Registration successful! Your Student ID is: " + student.getId());
            System.out.println("    You can now log in.\n");
        } catch (SQLException e) {
            System.out.println("  ✗ Registration failed: " + e.getMessage() + "\n");
        }
    }

    // ─────────────────────────────────────────────────
    // LOGIN
    // ─────────────────────────────────────────────────
    private static void handleLogin() {
        System.out.println("\n  ── Login ──");
        System.out.print("  Email    : ");
        String email = scanner.nextLine().trim();
        System.out.print("  Password : ");
        String password = scanner.nextLine().trim();

        try {
            Student student = studentService.login(email, password);
            if (student != null) {
                loggedInStudent = student;
                System.out.println("  ✓ Welcome back, " + student.getName() + "!\n");
            } else {
                System.out.println("  ✗ Invalid email or password.\n");
            }
        } catch (SQLException e) {
            System.out.println("  ✗ Login error: " + e.getMessage() + "\n");
        }
    }

    // ─────────────────────────────────────────────────
    // VIEW COURSES
    // ─────────────────────────────────────────────────
    private static void handleViewCourses() {
        System.out.println("\n  ── Available Courses ──");
        try {
            List<Course> courses = courseService.listAllCourses();
            if (courses.isEmpty()) {
                System.out.println("  No courses available at the moment.\n");
                return;
            }

            System.out.println("  " + "-".repeat(112));
            System.out.printf("  | %-4s | %-30s | %-20s | %-8s | %-8s | %-10s |%n",
                    "ID", "Title", "Instructor", "Capacity", "Enrolled", "Available");
            System.out.println("  " + "-".repeat(112));

            for (Course c : courses) {
                System.out.println("  " + c.toString());
            }
            System.out.println("  " + "-".repeat(112));
            System.out.println();

        } catch (SQLException e) {
            System.out.println("  ✗ Error fetching courses: " + e.getMessage() + "\n");
        }
    }

    // ─────────────────────────────────────────────────
    // RESERVE A COURSE
    // ─────────────────────────────────────────────────
    private static void handleReserveCourse() {
        handleViewCourses();
        System.out.print("  Enter Course ID to reserve: ");
        int courseId = readInt();

        try {
            Reservation r = reservationService.reserveCourse(loggedInStudent.getId(), courseId);
            System.out.println("  ✓ Reservation successful!");
            System.out.println("    Reservation ID : " + r.getId());
            System.out.println("    Course         : " + r.getCourseTitle() + "\n");
        } catch (SQLException e) {
            System.out.println("  ✗ Reservation failed: " + e.getMessage() + "\n");
        }
    }

    // ─────────────────────────────────────────────────
    // MY RESERVATIONS
    // ─────────────────────────────────────────────────
    private static void handleMyReservations() {
        System.out.println("\n  ── My Reservations ──");
        try {
            List<Reservation> reservations = reservationService.getMyReservations(loggedInStudent.getId());
            if (reservations.isEmpty()) {
                System.out.println("  You have no reservations yet.\n");
                return;
            }

            System.out.println("  " + "-".repeat(66));
            System.out.printf("  | %-4s | %-30s | %-20s |%n", "ID", "Course", "Reserved On");
            System.out.println("  " + "-".repeat(66));
            for (Reservation r : reservations) {
                System.out.println("  " + r.toString());
            }
            System.out.println("  " + "-".repeat(66));
            System.out.println();

        } catch (SQLException e) {
            System.out.println("  ✗ Error fetching reservations: " + e.getMessage() + "\n");
        }
    }

    // ─────────────────────────────────────────────────
    // CANCEL RESERVATION
    // ─────────────────────────────────────────────────
    private static void handleCancelReservation() {
        handleMyReservations();
        System.out.print("  Enter Reservation ID to cancel: ");
        int reservationId = readInt();

        try {
            reservationService.cancelReservation(reservationId, loggedInStudent.getId());
            System.out.println("  ✓ Reservation #" + reservationId + " cancelled successfully.\n");
        } catch (SQLException e) {
            System.out.println("  ✗ Cancellation failed: " + e.getMessage() + "\n");
        }
    }

    // ─────────────────────────────────────────────────
    // HELPERS
    // ─────────────────────────────────────────────────
    private static int readInt() {
        try {
            int value = Integer.parseInt(scanner.nextLine().trim());
            return value;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static String padRight(String text, int length) {
        if (text.length() >= length)
            return text.substring(0, length);
        return text + " ".repeat(length - text.length());
    }
}
