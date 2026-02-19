package service;

import dao.StudentDAO;
import model.Student;

import java.sql.SQLException;

public class StudentService {

    private final StudentDAO studentDAO = new StudentDAO();

    public Student register(String name, String email, String password) throws SQLException {
        if (studentDAO.emailExists(email)) {
            throw new SQLException("An account with this email already exists.");
        }
        Student student = new Student(0, name, email, password);
        return studentDAO.registerStudent(student);
    }

    public Student login(String email, String password) throws SQLException {
        return studentDAO.login(email, password);
    }
}
