package service;

import dao.CourseDAO;
import model.Course;

import java.sql.SQLException;
import java.util.List;

public class CourseService {

    private final CourseDAO courseDAO = new CourseDAO();

    public List<Course> listAllCourses() throws SQLException {
        return courseDAO.getAllCourses();
    }

    public Course getCourseById(int id) throws SQLException {
        return courseDAO.getCourseById(id);
    }
}
