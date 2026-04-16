package com.reservation.backend.controller;

import com.reservation.backend.model.Reservation;
import com.reservation.backend.dto.ReservationResponse;
import com.reservation.backend.security.UserDetailsImpl;
import com.reservation.backend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/my")
    public List<ReservationResponse> getMyReservations() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        return reservationService.getUserReservations(userDetails.getId());
    }

    @PostMapping("/{courseId}")
    public ResponseEntity<?> reserveCourse(@PathVariable Long courseId) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

            Reservation reservation = reservationService.reserveCourse(userDetails.getId(), courseId);
            return ResponseEntity.ok(reservation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long reservationId) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

            reservationService.cancelReservation(reservationId, userDetails.getId());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/course/{courseId}")
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    public List<ReservationResponse> getCourseReservations(@PathVariable Long courseId) {
        return reservationService.getCourseReservations(courseId);
    }
}
