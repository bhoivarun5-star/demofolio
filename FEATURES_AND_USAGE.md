# 📖 Portfolio Website - Features & Usage Guide

## 🎯 Quick Start

### Running the Application

```bash
# 1. Navigate to the project directory
cd java-portfolio

# 2. Build the project
mvn clean package

# 3. Run the application
mvn spring-boot:run

# 4. Open your browser
# Visit: http://localhost:8080
```

## 🔥 Key Features Explained

### 1. Hero Section
**What it does**: Creates an eye-catching first impression with animated typing effect

**Customization**:
```html
<!-- Edit in index.html around line 40 -->
<span class="typing">Hey, I'm Varun Kishor Bhoi</span>
<p class="hero-subtitle">MCA Student | Python Developer | Web Developer</p>
```

**Animations**:
- Typing effect on page load
- Floating icon animation
- Smooth scroll indicator
- Parallax effect on mouse movement

### 2. About Section
**What it does**: Introduces your background, skills, and experience

**Statistics Cards**:
- Display key metrics (Projects, Languages, Certifications)
- Hover effects with color transitions
- Card elevation on hover

**Customization**:
```html
<!-- Update statistics in index.html -->
<div class="stat-content">
    <h3>Projects</h3>
    <p>10+</p>  <!-- Edit this number -->
</div>
```

### 3. Skills Section
**What it does**: Shows technical proficiency with animated progress bars

**Features**:
- 8+ skills with proficiency percentage
- Animated progress bars that fill when section comes into view
- Color-coded skill levels (Advanced/Intermediate)
- Icon representation for each skill

**Customization**:
```html
<!-- Edit skill percentage and level -->
<div class="skill-progress">
    <div class="progress-bar">
        <div class="progress" style="width: 95%"></div>  <!-- Change width -->
    </div>
    <span>95%</span>
</div>
```

**Add New Skill**:
```html
<div class="skill-card">
    <div class="skill-icon">
        <i class="fab fa-your-icon"></i>
    </div>
    <h3>Your Skill</h3>
    <div class="skill-progress">
        <div class="progress-bar">
            <div class="progress" style="width: 85%"></div>
        </div>
        <span>85%</span>
    </div>
    <p>Advanced</p>
</div>
```

### 4. Projects Section
**What it does**: Showcases your best work with detailed project cards

**Features**:
- 3 featured projects (expandable)
- Project description and technologies
- Links to GitHub code and live demo
- Hover effects with card elevation

**Customization** - Edit Project:
```html
<div class="project-card">
    <div class="project-image">
        <div class="project-placeholder">
            <i class="fas fa-icon"></i>  <!-- Change icon -->
        </div>
    </div>
    <div class="project-content">
        <h3>Your Project Title</h3>
        <p>Your project description</p>
        <div class="project-tags">
            <span class="tag">Technology 1</span>
            <span class="tag">Technology 2</span>
        </div>
        <div class="project-links">
            <a href="your-github-link" class="project-link">
                <i class="fab fa-github"></i> Code
            </a>
            <a href="your-demo-link" class="project-link">
                <i class="fas fa-external-link-alt"></i> Demo
            </a>
        </div>
    </div>
</div>
```

### 5. Certifications Section
**What it does**: Displays professional certifications and achievements

**Features**:
- Certificate cards with icons
- Issuer and date information
- Hover effects with color change
- Supports multiple certifications

**Add Certification**:
```html
<div class="cert-card">
    <div class="cert-icon">
        <i class="fas fa-certificate"></i>
    </div>
    <h3>Your Certification Title</h3>
    <p class="cert-issuer">Certificate Issuer</p>
    <p class="cert-date">2024</p>
</div>
```

### 6. Contact Form
**What it does**: Allows visitors to send you messages directly

**Features**:
- Real-time form validation
- Email validation
- Success/error notifications
- Server-side processing

**Backend Endpoint**:
```java
POST /api/contact
Content-Type: application/json

{
    "name": "John Doe",
    "email": "john@example.com",
    "subject": "Project Inquiry",
    "message": "I'd like to discuss a project..."
}
```

**Enable Email Sending**:
1. Update `pom.xml` with mail dependency
2. Configure SMTP settings in `application.properties`
3. Update `PortfolioController.java` to send emails

### 7. Navigation Bar
**What it does**: Provides smooth navigation throughout the page

**Features**:
- Fixed header with blur effect
- Active link highlighting
- Mobile hamburger menu
- Smooth scroll to sections

**Customization**:
```html
<!-- Add new navigation link -->
<li class="nav-item"><a href="#your-section" class="nav-link">Your Section</a></li>
```

### 8. Footer
**What it does**: Provides quick links and social media connections

**Social Media Links**:
```html
<a href="https://github.com/your-username" class="social-link" title="GitHub">
    <i class="fab fa-github"></i>
</a>
```

Supported platforms:
- GitHub
- LinkedIn
- Twitter
- Instagram

## 🎨 Customization Guide

### Change Color Scheme

Edit `styles.css` at the top:
```css
:root {
    --primary-color: #00d9ff;      /* Main accent color */
    --secondary-color: #ff006e;    /* Hover/emphasis color */
    --accent-color: #8338ec;       /* Secondary accent */
    --dark-bg: #0a0e27;            /* Background color */
    --text-primary: #ffffff;       /* Main text color */
    --text-secondary: #b0b0b0;     /* Muted text color */
}
```

### Adjust Animation Speed

In `styles.css`, modify animation duration:
```css
/* Change 0.8s to your desired duration */
@keyframes fadeInUp {
    animation: fadeInUp 0.8s ease;  /* Change 0.8s */
}
```

### Typing Animation Speed

In `script.js`:
```javascript
const speed = 50; // milliseconds per character - lower = faster
```

### Progress Bar Animation Duration

In `styles.css`:
```css
.progress {
    animation: growWidth 1s ease-out;  /* Change 1s for duration */
}
```

## 🔌 REST API Endpoints

### Get Skills
```bash
GET /api/skills
```

Response:
```json
[
    {
        "name": "Python",
        "proficiency": 95,
        "level": "Advanced"
    }
]
```

### Get Projects
```bash
GET /api/projects
```

### Get Certifications
```bash
GET /api/certifications
```

### Submit Contact Form
```bash
POST /api/contact
Content-Type: application/json

{
    "name": "Your Name",
    "email": "your@email.com",
    "subject": "Subject",
    "message": "Your message"
}
```

### Health Check
```bash
GET /api/health
```

## 📱 Responsive Breakpoints

The portfolio is optimized for:
- **Desktop**: 1200px and above
- **Tablet**: 768px to 1199px
- **Mobile**: Below 768px
- **Small Mobile**: 480px and below

## 🚀 Performance Tips

1. **Optimize Images**: Use WebP format for images
2. **Minify CSS/JS**: Already optimized in production build
3. **Lazy Load**: Images load on scroll (ready to implement)
4. **Caching**: Spring Boot caches static resources

## 🔐 Security Considerations

1. **Form Validation**: Always validate on both client and server
2. **CORS**: Configured in `WebConfig.java`
3. **XSS Protection**: Input is sanitized
4. **SQL Injection**: N/A (no database in demo)

## 📊 Browser DevTools Tips

### Check Animations
1. Open DevTools (F12)
2. Go to "Animations" tab
3. Reload page to see all animations

### Performance Profiling
1. Open DevTools
2. Go to "Performance" tab
3. Click record and reload
4. Check for long tasks

## 🆘 Troubleshooting

### Styles Not Applying
- Clear browser cache (Ctrl+Shift+Delete)
- Rebuild: `mvn clean package`
- Check browser console for 404 errors

### Animations Not Working
- Enable JavaScript in browser
- Check browser compatibility
- Check browser DevTools console for errors

### Form Not Submitting
- Check console for JavaScript errors
- Verify server is running
- Test with: `curl -X POST http://localhost:8080/api/contact`

## 📚 Next Steps

1. **Replace placeholder content** with your actual information
2. **Add your resume PDF** to static folder
3. **Update social media links** in footer
4. **Customize colors** to match your brand
5. **Deploy to production** on your preferred platform

## 💡 Enhancement Ideas

- Add blog section
- Implement dark/light theme toggle
- Add project filtering
- Create admin dashboard
- Integrate email sending
- Add analytics
- SEO optimization
- Performance metrics display

---

**Happy customizing! 🚀**
