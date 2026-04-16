package com.reservation.backend.controller;

import com.reservation.backend.dto.AdminAnalyticsResponse;
import com.reservation.backend.service.AdminAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminAnalyticsService adminAnalyticsService;

    @GetMapping("/analytics")
    @PreAuthorize("hasRole('ADMIN')")
    public AdminAnalyticsResponse getAnalytics() {
        logger.info(">>> ADMIN ANALYTICS ENDPOINT CALLED");
        try {
            AdminAnalyticsResponse response = adminAnalyticsService.getDashboardSummary();
            logger.info(">>> Analytics response: Users={}, Reservations={}, Courses={}", 
                response.getTotalUsers(), response.getTotalReservations(), response.getTotalCourses());
            return response;
        } catch (Exception e) {
            logger.error(">>> Error in analytics endpoint:", e);
            throw e;
        }
    }
}