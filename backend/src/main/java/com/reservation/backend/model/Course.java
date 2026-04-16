package com.reservation.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(length = 800)
    private String scope;

    @Column(length = 500)
    private String jobRoles;

    @Column(length = 600)
    private String hiringCompanies;

    @Column(length = 3000)
    private String hiringCompanyProfiles;

    @Column(length = 40)
    private String hiringTrend;

    @NotNull
    @Min(0)
    private Integer seats;

    @NotNull
    @Min(0)
    private Integer duration; // in minutes

    public Course() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getScope() { return scope; }
    public void setScope(String scope) { this.scope = scope; }

    public String getJobRoles() { return jobRoles; }
    public void setJobRoles(String jobRoles) { this.jobRoles = jobRoles; }

    public String getHiringCompanies() { return hiringCompanies; }
    public void setHiringCompanies(String hiringCompanies) { this.hiringCompanies = hiringCompanies; }

    public String getHiringCompanyProfiles() { return hiringCompanyProfiles; }
    public void setHiringCompanyProfiles(String hiringCompanyProfiles) { this.hiringCompanyProfiles = hiringCompanyProfiles; }

    public String getHiringTrend() { return hiringTrend; }
    public void setHiringTrend(String hiringTrend) { this.hiringTrend = hiringTrend; }

    public Integer getSeats() { return seats; }
    public void setSeats(Integer seats) { this.seats = seats; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }
}
