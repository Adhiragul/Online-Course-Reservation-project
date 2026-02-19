package model;

import java.sql.Timestamp;

public class Reservation {
    private int id;
    private int studentId;
    private int courseId;
    private Timestamp reservationDate;
    private String courseTitle;    // for display purposes
    private String studentName;   // for display purposes

    public Reservation() {}

    public Reservation(int id, int studentId, int courseId, Timestamp reservationDate) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.reservationDate = reservationDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public Timestamp getReservationDate() { return reservationDate; }
    public void setReservationDate(Timestamp reservationDate) { this.reservationDate = reservationDate; }

    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    @Override
    public String toString() {
        return String.format("| %-4d | %-30s | %-20s |",
                id, courseTitle != null ? courseTitle : "Course #" + courseId,
                reservationDate != null ? reservationDate.toString().substring(0, 19) : "N/A");
    }
}
