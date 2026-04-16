package com.reservation.backend.dto;

import java.time.LocalDateTime;

public class ReservationResponse {
    private Long id;
    private LocalDateTime reservationDate;
    private CourseSummary course;

    public ReservationResponse(Long id, LocalDateTime reservationDate, CourseSummary course) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public CourseSummary getCourse() {
        return course;
    }

    public static class CourseSummary {
        private Long id;
        private String title;
        private String description;
        private Integer duration;
        private String scope;
        private String jobRoles;
        private String hiringCompanies;
        private String hiringCompanyProfiles;
        private String hiringTrend;

        public CourseSummary(Long id, String title, String description, Integer duration, String scope, String jobRoles, String hiringCompanies, String hiringCompanyProfiles, String hiringTrend) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.duration = duration;
            this.scope = scope;
            this.jobRoles = jobRoles;
            this.hiringCompanies = hiringCompanies;
            this.hiringCompanyProfiles = hiringCompanyProfiles;
            this.hiringTrend = hiringTrend;
        }

        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public Integer getDuration() {
            return duration;
        }

        public String getScope() {
            return scope;
        }

        public String getJobRoles() {
            return jobRoles;
        }

        public String getHiringCompanies() {
            return hiringCompanies;
        }

        public String getHiringCompanyProfiles() {
            return hiringCompanyProfiles;
        }

        public String getHiringTrend() {
            return hiringTrend;
        }
    }
}
