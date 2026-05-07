package com.example.java.portfolio.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Service layer for portfolio-related operations
 */
@Service
public class PortfolioService {

    /**
     * Store contact submissions (in memory for demo)
     * In production, this would be saved to a database
     */
    private List<ContactSubmission> contactSubmissions = new ArrayList<>();

    /**
     * Handle contact form submission
     */
    public void saveContactSubmission(String name, String email, String subject, String message) {
        ContactSubmission submission = new ContactSubmission();
        submission.setId(UUID.randomUUID().toString());
        submission.setName(name);
        submission.setEmail(email);
        submission.setSubject(subject);
        submission.setMessage(message);
        submission.setSubmittedAt(LocalDateTime.now());
        submission.setRead(false);

        contactSubmissions.add(submission);
        
        // Log the submission
        System.out.println("[PORTFOLIO] New contact submission:");
        System.out.println("ID: " + submission.getId());
        System.out.println("From: " + name + " (" + email + ")");
        System.out.println("Subject: " + subject);
        System.out.println("Time: " + submission.getSubmittedAt());
    }

    /**
     * Get all contact submissions
     */
    public List<ContactSubmission> getAllContactSubmissions() {
        return new ArrayList<>(contactSubmissions);
    }

    /**
     * Get unread contact submissions
     */
    public List<ContactSubmission> getUnreadSubmissions() {
        return contactSubmissions.stream()
                .filter(s -> !s.isRead())
                .toList();
    }

    /**
     * Mark submission as read
     */
    public void markAsRead(String submissionId) {
        contactSubmissions.stream()
                .filter(s -> s.getId().equals(submissionId))
                .findFirst()
                .ifPresent(s -> s.setRead(true));
    }

    /**
     * Get portfolio statistics
     */
    public PortfolioStatistics getPortfolioStatistics() {
        return new PortfolioStatistics(
            10,  // Total projects
            7,   // Languages/skills
            5,   // Certifications
            2024 // Year started
        );
    }

    /**
     * Get skill proficiency
     */
    public int getSkillProficiency(String skillName) {
        Map<String, Integer> skillMap = new HashMap<>();
        skillMap.put("Python", 95);
        skillMap.put("JavaScript", 85);
        skillMap.put("React", 80);
        skillMap.put("HTML/CSS", 90);
        skillMap.put("Java", 82);
        skillMap.put("SQL", 88);
        skillMap.put("Web Dev", 87);
        skillMap.put("Git/GitHub", 92);
        
        return skillMap.getOrDefault(skillName, 0);
    }

    // Inner classes

    /**
     * Contact submission entity
     */
    public static class ContactSubmission {
        private String id;
        private String name;
        private String email;
        private String subject;
        private String message;
        private LocalDateTime submittedAt;
        private boolean read;

        // Getters and Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public LocalDateTime getSubmittedAt() {
            return submittedAt;
        }

        public void setSubmittedAt(LocalDateTime submittedAt) {
            this.submittedAt = submittedAt;
        }

        public boolean isRead() {
            return read;
        }

        public void setRead(boolean read) {
            this.read = read;
        }
    }

    /**
     * Portfolio statistics
     */
    public static class PortfolioStatistics {
        private int projects;
        private int skills;
        private int certifications;
        private int yearStarted;

        public PortfolioStatistics(int projects, int skills, int certifications, int yearStarted) {
            this.projects = projects;
            this.skills = skills;
            this.certifications = certifications;
            this.yearStarted = yearStarted;
        }

        public int getProjects() {
            return projects;
        }

        public int getSkills() {
            return skills;
        }

        public int getCertifications() {
            return certifications;
        }

        public int getYearStarted() {
            return yearStarted;
        }

        public int getExperienceYears() {
            return 2024 - yearStarted;
        }
    }
}
