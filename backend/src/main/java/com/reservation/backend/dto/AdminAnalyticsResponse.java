package com.reservation.backend.dto;

import java.util.List;

public class AdminAnalyticsResponse {
    private long totalUsers;
    private long totalAdmins;
    private long totalStudents;
    private long totalCourses;
    private long totalReservations;
    private long seatsRemaining;
    private List<UserProgress> userProgress;
    private List<CourseProgress> courseProgress;
    private List<RecentActivity> recentActivity;

    public AdminAnalyticsResponse(long totalUsers, long totalAdmins, long totalStudents, long totalCourses,
            long totalReservations, long seatsRemaining, List<UserProgress> userProgress,
            List<CourseProgress> courseProgress, List<RecentActivity> recentActivity) {
        this.totalUsers = totalUsers;
        this.totalAdmins = totalAdmins;
        this.totalStudents = totalStudents;
        this.totalCourses = totalCourses;
        this.totalReservations = totalReservations;
        this.seatsRemaining = seatsRemaining;
        this.userProgress = userProgress;
        this.courseProgress = courseProgress;
        this.recentActivity = recentActivity;
    }

    public long getTotalUsers() { return totalUsers; }
    public long getTotalAdmins() { return totalAdmins; }
    public long getTotalStudents() { return totalStudents; }
    public long getTotalCourses() { return totalCourses; }
    public long getTotalReservations() { return totalReservations; }
    public long getSeatsRemaining() { return seatsRemaining; }
    public List<UserProgress> getUserProgress() { return userProgress; }
    public List<CourseProgress> getCourseProgress() { return courseProgress; }
    public List<RecentActivity> getRecentActivity() { return recentActivity; }

    public static class UserProgress {
        private Long id;
        private String name;
        private String email;
        private String role;
        private long reservations;
        private double progressPercent;

        public UserProgress(Long id, String name, String email, String role, long reservations, double progressPercent) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.role = role;
            this.reservations = reservations;
            this.progressPercent = progressPercent;
        }

        public Long getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getRole() { return role; }
        public long getReservations() { return reservations; }
        public double getProgressPercent() { return progressPercent; }
    }

    public static class CourseProgress {
        private Long id;
        private String title;
        private long reservations;
        private int seatsLeft;
        private String hiringTrend;
        private double fillRate;

        public CourseProgress(Long id, String title, long reservations, int seatsLeft, String hiringTrend, double fillRate) {
            this.id = id;
            this.title = title;
            this.reservations = reservations;
            this.seatsLeft = seatsLeft;
            this.hiringTrend = hiringTrend;
            this.fillRate = fillRate;
        }

        public Long getId() { return id; }
        public String getTitle() { return title; }
        public long getReservations() { return reservations; }
        public int getSeatsLeft() { return seatsLeft; }
        public String getHiringTrend() { return hiringTrend; }
        public double getFillRate() { return fillRate; }
    }

    public static class RecentActivity {
        private Long id;
        private String userName;
        private String courseTitle;
        private String reservationDate;

        public RecentActivity(Long id, String userName, String courseTitle, String reservationDate) {
            this.id = id;
            this.userName = userName;
            this.courseTitle = courseTitle;
            this.reservationDate = reservationDate;
        }

        public Long getId() { return id; }
        public String getUserName() { return userName; }
        public String getCourseTitle() { return courseTitle; }
        public String getReservationDate() { return reservationDate; }
    }
}