package dao;

import model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                courses.add(mapRow(rs));
            }
        }
        return courses;
    }

    public Course getCourseById(int id) throws SQLException {
        String sql = "SELECT * FROM courses WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    public void updateEnrolledCount(int courseId, int delta) throws SQLException {
        String sql = "UPDATE courses SET enrolled_count = enrolled_count + ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, delta);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        }
    }

    private Course mapRow(ResultSet rs) throws SQLException {
        return new Course(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("instructor"),
                rs.getInt("capacity"),
                rs.getInt("enrolled_count"));
    }
}
