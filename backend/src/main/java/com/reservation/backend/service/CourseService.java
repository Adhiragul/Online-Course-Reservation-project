package com.reservation.backend.service;

import com.reservation.backend.model.Course;
import com.reservation.backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> searchCourses(String title) {
        return courseRepository.findByTitleContainingIgnoreCase(title);
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course createCourse(Course course) {
        applyCourseIntelligence(course);
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course courseDetails) {
        return courseRepository.findById(id).map(course -> {
            course.setTitle(courseDetails.getTitle());
            course.setDescription(courseDetails.getDescription());
            course.setScope(courseDetails.getScope());
            course.setJobRoles(courseDetails.getJobRoles());
            course.setHiringCompanies(courseDetails.getHiringCompanies());
            course.setHiringCompanyProfiles(courseDetails.getHiringCompanyProfiles());
            course.setHiringTrend(courseDetails.getHiringTrend());
            course.setSeats(courseDetails.getSeats());
            course.setDuration(courseDetails.getDuration());
            applyCourseIntelligence(course);
            return courseRepository.save(course);
        }).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found");
        }
        courseRepository.deleteById(id);
    }

    private void applyCourseIntelligence(Course course) {
        String title = course.getTitle() == null ? "" : course.getTitle().toLowerCase();

        if (isBlank(course.getScope())) {
            course.setScope(resolveScope(title));
        }

        if (isBlank(course.getJobRoles())) {
            course.setJobRoles(resolveRoles(title));
        }

        if (isBlank(course.getHiringCompanies()) && !isBlank(course.getHiringCompanyProfiles())) {
            course.setHiringCompanies(extractCompanyNames(course.getHiringCompanyProfiles()));
        }

        if (isBlank(course.getHiringCompanies())) {
            course.setHiringCompanies(resolveCompanies(title));
        }

        if (isBlank(course.getHiringCompanyProfiles())) {
            course.setHiringCompanyProfiles(buildCompanyProfiles(course.getHiringCompanies()));
        }

        if (isBlank(course.getHiringTrend())) {
            course.setHiringTrend(resolveHiringTrend(title));
        }

    }

    private String resolveScope(String title) {
        if (title.contains("react") || title.contains("frontend") || title.contains("vue") || title.contains("angular")) {
            return "Build modern frontend products with scalable UI architecture, reusable components, and production-grade performance.";
        }
        if (title.contains("node") || title.contains("api") || title.contains("graphql") || title.contains("microservices")) {
            return "Design robust backend services with scalable APIs, secure integrations, and reliable distributed architecture.";
        }
        if (title.contains("python") || title.contains("machine learning") || title.contains("data")) {
            return "Develop data-driven solutions from preparation and modeling to evaluation and production deployment.";
        }
        if (title.contains("cloud") || title.contains("aws") || title.contains("devops") || title.contains("kubernetes")) {
            return "Build cloud-native systems with automation, observability, resilient deployment pipelines, and operations best practices.";
        }
        if (title.contains("cyber") || title.contains("security")) {
            return "Apply practical security workflows for risk assessment, vulnerability analysis, and secure engineering delivery.";
        }
        if (title.contains("design") || title.contains("ux") || title.contains("figma")) {
            return "Create user-centered product experiences with clear flows, visual systems, and measurable usability outcomes.";
        }
        return "Gain applied, job-ready capabilities through project-based learning aligned to modern product and engineering expectations.";
    }

    private String resolveRoles(String title) {
        if (title.contains("react") || title.contains("frontend") || title.contains("vue") || title.contains("angular")) {
            return "Frontend Engineer, UI Engineer, Product Developer";
        }
        if (title.contains("node") || title.contains("api") || title.contains("graphql") || title.contains("microservices")) {
            return "Backend Engineer, API Developer, Platform Engineer";
        }
        if (title.contains("python") || title.contains("machine learning") || title.contains("data")) {
            return "Data Scientist, ML Engineer, Analytics Engineer";
        }
        if (title.contains("cloud") || title.contains("aws") || title.contains("devops") || title.contains("kubernetes")) {
            return "Cloud Engineer, DevOps Engineer, Site Reliability Engineer";
        }
        if (title.contains("cyber") || title.contains("security")) {
            return "Security Analyst, Security Engineer, Risk Consultant";
        }
        if (title.contains("design") || title.contains("ux") || title.contains("figma")) {
            return "UX Designer, Product Designer, Interaction Designer";
        }
        return "Software Engineer, Product Specialist, Technology Consultant";
    }

    private String resolveCompanies(String title) {
        if (title.contains("react") || title.contains("frontend") || title.contains("vue") || title.contains("angular")) {
            return "Google, Microsoft, Atlassian, Shopify";
        }
        if (title.contains("node") || title.contains("api") || title.contains("graphql") || title.contains("microservices")) {
            return "Amazon, Uber, Stripe, Flipkart";
        }
        if (title.contains("python") || title.contains("machine learning") || title.contains("data")) {
            return "NVIDIA, Databricks, Fractal, Siemens";
        }
        if (title.contains("cloud") || title.contains("aws") || title.contains("devops") || title.contains("kubernetes")) {
            return "AWS, Accenture, Infosys, VMware";
        }
        if (title.contains("cyber") || title.contains("security")) {
            return "Palo Alto Networks, CrowdStrike, Cisco, Deloitte";
        }
        if (title.contains("design") || title.contains("ux") || title.contains("figma")) {
            return "Adobe, Canva, PhonePe, CRED";
        }
        return "Google, Microsoft, Amazon, Deloitte";
    }

    private String resolveHiringTrend(String title) {
        if (title.contains("machine learning") || title.contains("cloud") || title.contains("cyber") || title.contains("ai")) {
            return "High";
        }
        if (title.contains("data") || title.contains("frontend") || title.contains("devops") || title.contains("kubernetes")) {
            return "Growing";
        }
        return "Steady";
    }

    private String buildCompanyProfiles(String companiesCsv) {
        if (isBlank(companiesCsv)) {
            return "";
        }
        return Arrays.stream(companiesCsv.split(","))
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .map(name -> {
                    String domain = companyToDomain(name);
                    return name + "|https://www." + domain + "/careers|" + domain;
                })
                .collect(Collectors.joining(";"));
    }

    private String extractCompanyNames(String profiles) {
        if (isBlank(profiles)) {
            return "";
        }
        return Arrays.stream(profiles.split(";"))
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .map(line -> line.split("\\|")[0].trim())
                .collect(Collectors.joining(", "));
    }

    private String companyToDomain(String company) {
        String normalized = company.toLowerCase().trim();
        return switch (normalized) {
            case "google" -> "google.com";
            case "microsoft" -> "microsoft.com";
            case "amazon" -> "amazon.jobs";
            case "atlassian" -> "atlassian.com";
            case "shopify" -> "shopify.com";
            case "uber" -> "uber.com";
            case "stripe" -> "stripe.com";
            case "flipkart" -> "flipkartcareers.com";
            case "nvidia" -> "nvidia.com";
            case "databricks" -> "databricks.com";
            case "aws" -> "aws.amazon.com";
            case "accenture" -> "accenture.com";
            case "infosys" -> "infosys.com";
            case "vmware" -> "vmware.com";
            case "palo alto networks" -> "paloaltonetworks.com";
            case "crowdstrike" -> "crowdstrike.com";
            case "cisco" -> "cisco.com";
            case "deloitte" -> "deloitte.com";
            case "adobe" -> "adobe.com";
            case "canva" -> "canva.com";
            case "phonepe" -> "phonepe.com";
            case "cred" -> "cred.club";
            default -> normalized.replaceAll("[^a-z0-9]", "") + ".com";
        };
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
