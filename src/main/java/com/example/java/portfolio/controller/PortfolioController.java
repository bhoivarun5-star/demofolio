package com.example.java.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to handle portfolio API endpoints
 */
@Controller
public class PortfolioController {

    /**
     * Health check endpoint
     */
    @GetMapping("/api/health")
    @ResponseBody
    public HealthResponse health() {
        return new HealthResponse("Portfolio service is running!", "UP");
    }

    /**
     * Contact form submission endpoint
     */
    @PostMapping("/api/contact")
    @ResponseBody
    public ContactResponse submitContact(@RequestBody ContactRequest request) {
        // In a real application, you would save this to a database or send an email
        System.out.println("Received contact form submission:");
        System.out.println("Name: " + request.getName());
        System.out.println("Email: " + request.getEmail());
        System.out.println("Subject: " + request.getSubject());
        System.out.println("Message: " + request.getMessage());

        return new ContactResponse("success", "Thank you for your message! I'll get back to you soon.");
    }

    /**
     * Get portfolio information
     */
    @GetMapping("/api/portfolio-info")
    @ResponseBody
    public PortfolioInfo getPortfolioInfo() {
        return new PortfolioInfo(
            "Varun Kishor Bhoi",
            "MCA Student | Python Developer | Web Developer",
            "I craft elegant solutions through code and design. Passionate about building scalable applications and solving complex problems.",
            "India"
        );
    }

    // Inner classes for request/response DTOs

    public static class ContactRequest {
        private String name;
        private String email;
        private String subject;
        private String message;

        // Getters and Setters
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
    }

    public static class ContactResponse {
        private String status;
        private String message;

        public ContactResponse(String status, String message) {
            this.status = status;
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class HealthResponse {
        private String message;
        private String status;

        public HealthResponse(String message, String status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class PortfolioInfo {
        private String name;
        private String role;
        private String description;
        private String location;

        public PortfolioInfo(String name, String role, String description, String location) {
            this.name = name;
            this.role = role;
            this.description = description;
            this.location = location;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
