package dao;

import model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    public Reservation createReservation(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO reservations (student_id, course_id) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, reservation.getStudentId());
            ps.setInt(2, reservation.getCourseId());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    reservation.setId(keys.getInt(1));
                }
            }
        }
        return reservation;
    }

    public List<Reservation> getReservationsByStudent(int studentId) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT r.id, r.student_id, r.course_id, r.reservation_date, c.title AS course_title "
                + "FROM reservations r "
                + "JOIN courses c ON r.course_id = c.id "
                + "WHERE r.student_id = ? "
                + "ORDER BY r.reservation_date DESC";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reservation r = new Reservation(
                            rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getInt("course_id"),
                            rs.getTimestamp("reservation_date"));
                    r.setCourseTitle(rs.getString("course_title"));
                    reservations.add(r);
                }
            }
        }
        return reservations;
    }

    public Reservation getReservationById(int id) throws SQLException {
        String sql = "SELECT r.id, r.student_id, r.course_id, r.reservation_date, c.title AS course_title "
                + "FROM reservations r "
                + "JOIN courses c ON r.course_id = c.id "
                + "WHERE r.id = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Reservation r = new Reservation(
                            rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getInt("course_id"),
                            rs.getTimestamp("reservation_date"));
                    r.setCourseTitle(rs.getString("course_title"));
                    return r;
                }
            }
        }
        return null;
    }

    public boolean deleteReservation(int id) throws SQLException {
        String sql = "DELETE FROM reservations WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean isAlreadyReserved(int studentId, int courseId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM reservations WHERE student_id = ? AND course_id = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
