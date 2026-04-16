package com.reservation.backend.repository;

import com.reservation.backend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @EntityGraph(attributePaths = { "course" })
    List<Reservation> findByUserId(Long userId);

    @EntityGraph(attributePaths = { "course" })
    List<Reservation> findByCourseId(Long courseId);

    @EntityGraph(attributePaths = { "user", "course" })
    @Query("select r from Reservation r")
    List<Reservation> findAllWithUserAndCourse();

    boolean existsByUserIdAndCourseId(Long userId, Long courseId);
}
