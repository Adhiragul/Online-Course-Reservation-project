package com.reservation.backend.service;

import com.reservation.backend.model.Course;
import com.reservation.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Value("${spring.mail.username:admin@coursereserve.com}")
    private String fromEmail;

    @Async
    public void sendLoginEmail(User user) {
        try {
            System.out.println("\n=========================================================");
            System.out.println(">>> PREPARING TO SEND LOGIN EMAIL...");
            System.out.println("To: " + user.getEmail());
            System.out.println("Subject: CourseReserve Login Confirmation");
            System.out.println("Body:");
            System.out.println("Dear " + user.getName() + ",\n");
            System.out.println("You just signed in to CourseReserve successfully.\n");
            System.out.println("Best regards,\nCourseReserve Team");
            System.out.println("=========================================================\n");

            if (mailSender != null) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(fromEmail);
                message.setTo(user.getEmail());
                message.setSubject("CourseReserve Login Confirmation");
                message.setText("Dear " + user.getName() + ",\n\n" +
                        "You just signed in to CourseReserve successfully.\n\n" +
                        "Best regards,\nCourseReserve Team");

                mailSender.send(message);
                System.out.println("Login email genuinely dispatched via SMTP Server.");
            } else {
                System.out.println("(Simulated Login Email: Provide SMTP credentials in application.properties for real delivery)");
            }
        } catch (Exception e) {
            System.err.println("Failed to send login email: " + e.getMessage());
        }
    }

    @Async
    public void sendEnrollmentEmail(User user, Course course) {
        try {
            System.out.println("\n=========================================================");
            System.out.println(">>> PREPARING TO SEND ACKNOWLEDGEMENT EMAIL...");
            System.out.println("To: " + user.getEmail());
            System.out.println("Subject: Course Enrollment Confirmation - " + course.getTitle());
            System.out.println("Body:");
            System.out.println("Dear " + user.getName() + ",\n");
            System.out.println("Thank you for enrolling in '" + course.getTitle() + "'.");
            System.out.println("Your seat has been successfully reserved. We look forward to seeing you in class!\n");
            System.out.println("Best regards,\nCourseReserve Team");
            System.out.println("=========================================================\n");

            if (mailSender != null) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(fromEmail);
                message.setTo(user.getEmail());
                message.setSubject("Course Enrollment Confirmation - " + course.getTitle());
                message.setText("Dear " + user.getName() + ",\n\n" +
                        "Thank you for enrolling in '" + course.getTitle() + "'.\n" +
                        "Your seat has been successfully reserved. We look forward to seeing you in class!\n\n" +
                        "Best regards,\nCourseReserve Team");

                mailSender.send(message);
                System.out.println("Email genuinely dispatched via SMTP Server.");
            } else {
                System.out.println(
                        "(Simulated Email: Provide SMTP credentials in application.properties for real delivery)");
            }
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}
