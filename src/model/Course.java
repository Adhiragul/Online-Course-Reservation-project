package model;

public class Course {
    private int id;
    private String title;
    private String instructor;
    private int capacity;
    private int enrolledCount;

    public Course() {}

    public Course(int id, String title, String instructor, int capacity, int enrolledCount) {
        this.id = id;
        this.title = title;
        this.instructor = instructor;
        this.capacity = capacity;
        this.enrolledCount = enrolledCount;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public int getEnrolledCount() { return enrolledCount; }
    public void setEnrolledCount(int enrolledCount) { this.enrolledCount = enrolledCount; }

    public int getAvailableSeats() {
        return capacity - enrolledCount;
    }

    @Override
    public String toString() {
        return String.format("| %-4d | %-30s | %-20s | %-8d | %-8d | %-10d |",
                id, title, instructor, capacity, enrolledCount, getAvailableSeats());
    }
}
