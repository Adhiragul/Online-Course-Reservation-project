package com.reservation.backend.service;

import com.reservation.backend.dto.AdminAnalyticsResponse;
import com.reservation.backend.model.Course;
import com.reservation.backend.model.Reservation;
import com.reservation.backend.model.Role;
import com.reservation.backend.model.User;
import com.reservation.backend.repository.CourseRepository;
import com.reservation.backend.repository.ReservationRepository;
import com.reservation.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminAnalyticsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ReservationRepository reservationRepository;

        @Transactional(readOnly = true)
    public AdminAnalyticsResponse getDashboardSummary() {
        List<User> users = userRepository.findAll();
        List<Course> courses = courseRepository.findAll();
        List<Reservation> reservations = reservationRepository.findAllWithUserAndCourse();

        long totalUsers = users.size();
        long totalAdmins = users.stream().filter(user -> user.getRole() == Role.ROLE_ADMIN).count();
        long totalStudents = totalUsers - totalAdmins;
        long totalCourses = courses.size();
        long totalReservations = reservations.size();
        long seatsRemaining = courses.stream().mapToLong(course -> course.getSeats() == null ? 0 : course.getSeats()).sum();

        Map<Long, Long> reservationsByUser = reservations.stream()
                .collect(Collectors.groupingBy(reservation -> reservation.getUser().getId(), Collectors.counting()));

        List<AdminAnalyticsResponse.UserProgress> userProgress = users.stream()
                .sorted(Comparator.comparingLong(user -> -reservationsByUser.getOrDefault(user.getId(), 0L)))
                .map(user -> {
                    long userReservations = reservationsByUser.getOrDefault(user.getId(), 0L);
                    double progressPercent = totalCourses == 0 ? 0 : Math.min(100.0, (userReservations * 100.0) / totalCourses);
                    return new AdminAnalyticsResponse.UserProgress(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            user.getRole().name(),
                            userReservations,
                            progressPercent);
                })
                .collect(Collectors.toList());

        Map<Long, Long> reservationsByCourse = reservations.stream()
                .collect(Collectors.groupingBy(reservation -> reservation.getCourse().getId(), Collectors.counting()));

        List<AdminAnalyticsResponse.CourseProgress> courseProgress = courses.stream()
                .sorted(Comparator.comparingLong(course -> -reservationsByCourse.getOrDefault(course.getId(), 0L)))
                .map(course -> {
                    long courseReservations = reservationsByCourse.getOrDefault(course.getId(), 0L);
                    int seatsLeft = course.getSeats() == null ? 0 : course.getSeats();
                    int initialSeats = seatsLeft + (int) courseReservations;
                    double fillRate = initialSeats <= 0 ? 0 : Math.min(100.0, (courseReservations * 100.0) / initialSeats);
                    return new AdminAnalyticsResponse.CourseProgress(
                            course.getId(),
                            course.getTitle(),
                            courseReservations,
                            seatsLeft,
                            course.getHiringTrend(),
                            fillRate);
                })
                .collect(Collectors.toList());

        List<AdminAnalyticsResponse.RecentActivity> recentActivity = reservations.stream()
                .sorted(Comparator.comparing(Reservation::getReservationDate).reversed())
                .limit(8)
                .map(reservation -> new AdminAnalyticsResponse.RecentActivity(
                        reservation.getId(),
                        reservation.getUser().getName(),
                        reservation.getCourse().getTitle(),
                        reservation.getReservationDate().toString()))
                .collect(Collectors.toList());

        return new AdminAnalyticsResponse(
                totalUsers,
                totalAdmins,
                totalStudents,
                totalCourses,
                totalReservations,
                seatsRemaining,
                userProgress,
                courseProgress,
                recentActivity);
    }
}