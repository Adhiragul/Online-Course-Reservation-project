package service;

import dao.CourseDAO;
import dao.ReservationDAO;
import model.Course;
import model.Reservation;

import java.sql.SQLException;
import java.util.List;

public class ReservationService {

    private final ReservationDAO reservationDAO = new ReservationDAO();
    private final CourseDAO courseDAO = new CourseDAO();

    /**
     * Reserve a seat in a course for the given student.
     * Checks: course exists, seats available, not already reserved.
     */
    public Reservation reserveCourse(int studentId, int courseId) throws SQLException {
        // 1. Check course exists
        Course course = courseDAO.getCourseById(courseId);
        if (course == null) {
            throw new SQLException("Course not found with ID: " + courseId);
        }

        // 2. Check seats available
        if (course.getAvailableSeats() <= 0) {
            throw new SQLException("Sorry, no seats available in \"" + course.getTitle() + "\".");
        }

        // 3. Check not already reserved
        if (reservationDAO.isAlreadyReserved(studentId, courseId)) {
            throw new SQLException("You have already reserved a seat in \"" + course.getTitle() + "\".");
        }

        // 4. Create reservation and increment enrolled count
        Reservation reservation = new Reservation();
        reservation.setStudentId(studentId);
        reservation.setCourseId(courseId);
        reservationDAO.createReservation(reservation);
        courseDAO.updateEnrolledCount(courseId, 1);

        reservation.setCourseTitle(course.getTitle());
        return reservation;
    }

    /**
     * Cancel a reservation. Only the owning student can cancel it.
     */
    public void cancelReservation(int reservationId, int studentId) throws SQLException {
        Reservation reservation = reservationDAO.getReservationById(reservationId);
        if (reservation == null) {
            throw new SQLException("Reservation not found with ID: " + reservationId);
        }
        if (reservation.getStudentId() != studentId) {
            throw new SQLException("You can only cancel your own reservations.");
        }

        reservationDAO.deleteReservation(reservationId);
        courseDAO.updateEnrolledCount(reservation.getCourseId(), -1);
    }

    /**
     * Get all reservations for a student.
     */
    public List<Reservation> getMyReservations(int studentId) throws SQLException {
        return reservationDAO.getReservationsByStudent(studentId);
    }
}
