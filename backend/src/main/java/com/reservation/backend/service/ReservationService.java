package com.reservation.backend.service;

import com.reservation.backend.model.Course;
import com.reservation.backend.model.Reservation;
import com.reservation.backend.model.User;
import com.reservation.backend.dto.ReservationResponse;
import com.reservation.backend.repository.CourseRepository;
import com.reservation.backend.repository.ReservationRepository;
import com.reservation.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private EmailService emailService;

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    public List<ReservationResponse> getUserReservations(Long userId) {
        return reservationRepository.findByUserId(userId)
                .stream()
                .sorted(Comparator.comparing(Reservation::getReservationDate).reversed())
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<ReservationResponse> getCourseReservations(Long courseId) {
        return reservationRepository.findByCourseId(courseId)
                .stream()
                .sorted(Comparator.comparing(Reservation::getReservationDate).reversed())
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public Reservation reserveCourse(Long userId, Long courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Course course = courseRepository.findWithLockById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (course.getSeats() <= 0) {
            throw new RuntimeException("No seats available for this course.");
        }

        if (reservationRepository.existsByUserIdAndCourseId(userId, courseId)) {
            throw new RuntimeException("User has already reserved this course.");
        }

        course.setSeats(course.getSeats() - 1);
        courseRepository.save(course);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setCourse(course);
        Reservation savedReservation = reservationRepository.save(reservation);

        emailService.sendEnrollmentEmail(user, course);

        return savedReservation;
    }

    @Transactional
    public void cancelReservation(Long reservationId, Long userId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (!reservation.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized to cancel this reservation");
        }

        Course course = reservation.getCourse();
        course.setSeats(course.getSeats() + 1);
        courseRepository.save(course);

        reservationRepository.delete(reservation);
    }

    private ReservationResponse toResponse(Reservation reservation) {
        Course course = reservation.getCourse();
        ReservationResponse.CourseSummary summary = new ReservationResponse.CourseSummary(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getDuration(),
                course.getScope(),
            course.getJobRoles(),
            course.getHiringCompanies(),
            course.getHiringCompanyProfiles(),
            course.getHiringTrend());

        return new ReservationResponse(reservation.getId(), reservation.getReservationDate(), summary);
    }
}
