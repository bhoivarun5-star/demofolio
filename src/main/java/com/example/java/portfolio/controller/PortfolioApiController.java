package com.example.java.portfolio.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * REST API Controller for portfolio data
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PortfolioApiController {

    /**
     * Get all skills
     */
    @GetMapping("/skills")
    public List<SkillDto> getSkills() {
        List<SkillDto> skills = new ArrayList<>();
        skills.add(new SkillDto("Python", 95, "Advanced"));
        skills.add(new SkillDto("JavaScript", 85, "Advanced"));
        skills.add(new SkillDto("React", 80, "Advanced"));
        skills.add(new SkillDto("HTML/CSS", 90, "Advanced"));
        skills.add(new SkillDto("Java", 82, "Advanced"));
        skills.add(new SkillDto("SQL", 88, "Advanced"));
        skills.add(new SkillDto("Web Dev", 87, "Advanced"));
        skills.add(new SkillDto("Git/GitHub", 92, "Advanced"));
        return skills;
    }

    /**
     * Get all projects
     */
    @GetMapping("/projects")
    public List<ProjectDto> getProjects() {
        List<ProjectDto> projects = new ArrayList<>();

        ProjectDto project1 = new ProjectDto(
            1,
            "ATS Score Tracker",
            "An intelligent application that analyzes resumes against job descriptions and provides an Applicant Tracking System (ATS) score. Built with Python and advanced text analysis algorithms.",
            Arrays.asList("Python", "NLP", "Flask"),
            "https://github.com",
            "https://demo.example.com"
        );
        projects.add(project1);

        ProjectDto project2 = new ProjectDto(
            2,
            "BookSwap Hub",
            "A full-stack web application for book enthusiasts to swap, buy, and sell used books in their community. Features user authentication, book listings, and integrated messaging system.",
            Arrays.asList("React", "Node.js", "MongoDB"),
            "https://github.com",
            "https://demo.example.com"
        );
        projects.add(project2);

        ProjectDto project3 = new ProjectDto(
            3,
            "Data Dashboard",
            "Interactive data visualization dashboard that processes and displays complex datasets with real-time updates. Features multiple chart types and responsive design.",
            Arrays.asList("JavaScript", "D3.js", "API"),
            "https://github.com",
            "https://demo.example.com"
        );
        projects.add(project3);

        return projects;
    }

    /**
     * Get project by ID
     */
    @GetMapping("/projects/{id}")
    public ProjectDto getProjectById(@PathVariable int id) {
        List<ProjectDto> projects = getProjects();
        return projects.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElse(null);
    }

    /**
     * Get all certifications
     */
    @GetMapping("/certifications")
    public List<CertificationDto> getCertifications() {
        List<CertificationDto> certs = new ArrayList<>();
        certs.add(new CertificationDto(1, "Python Programming Master", "Udemy", "2024"));
        certs.add(new CertificationDto(2, "Web Development Bootcamp", "Coursera", "2024"));
        certs.add(new CertificationDto(3, "Full Stack Development", "freeCodeCamp", "2023"));
        certs.add(new CertificationDto(4, "Data Structures & Algorithms", "LeetCode", "2023"));
        certs.add(new CertificationDto(5, "Hackathon Winner", "TechFest 2024", "2024"));
        certs.add(new CertificationDto(6, "Open Source Contributor", "GitHub", "2023 - Present"));
        return certs;
    }

    /**
     * Get portfolio statistics
     */
    @GetMapping("/statistics")
    public StatisticsDto getStatistics() {
        return new StatisticsDto(10, 7, 5);
    }

    /**
     * Get contact information
     */
    @GetMapping("/contact-info")
    public ContactInfoDto getContactInfo() {
        return new ContactInfoDto(
            "varun@example.com",
            "+91 98765 43210",
            "India"
        );
    }

    // DTOs (Data Transfer Objects)

    public static class SkillDto {
        private String name;
        private int proficiency;
        private String level;

        public SkillDto(String name, int proficiency, String level) {
            this.name = name;
            this.proficiency = proficiency;
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public int getProficiency() {
            return proficiency;
        }

        public String getLevel() {
            return level;
        }
    }

    public static class ProjectDto {
        private int id;
        private String title;
        private String description;
        private List<String> technologies;
        private String codeUrl;
        private String demoUrl;

        public ProjectDto(int id, String title, String description, List<String> technologies,
                         String codeUrl, String demoUrl) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.technologies = technologies;
            this.codeUrl = codeUrl;
            this.demoUrl = demoUrl;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public List<String> getTechnologies() {
            return technologies;
        }

        public String getCodeUrl() {
            return codeUrl;
        }

        public String getDemoUrl() {
            return demoUrl;
        }
    }

    public static class CertificationDto {
        private int id;
        private String title;
        private String issuer;
        private String year;

        public CertificationDto(int id, String title, String issuer, String year) {
            this.id = id;
            this.title = title;
            this.issuer = issuer;
            this.year = year;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getIssuer() {
            return issuer;
        }

        public String getYear() {
            return year;
        }
    }

    public static class StatisticsDto {
        private int projects;
        private int languages;
        private int certifications;

        public StatisticsDto(int projects, int languages, int certifications) {
            this.projects = projects;
            this.languages = languages;
            this.certifications = certifications;
        }

        public int getProjects() {
            return projects;
        }

        public int getLanguages() {
            return languages;
        }

        public int getCertifications() {
            return certifications;
        }
    }

    public static class ContactInfoDto {
        private String email;
        private String phone;
        private String location;

        public ContactInfoDto(String email, String phone, String location) {
            this.email = email;
            this.phone = phone;
            this.location = location;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public String getLocation() {
            return location;
        }
    }
}
