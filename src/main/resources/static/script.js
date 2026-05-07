/* ========================================
   SMOOTH SCROLL & NAVIGATION
   ======================================== */

document.addEventListener('DOMContentLoaded', function() {
    initializeNavigation();
    initializeScrollToTop();
    initializeScrollAnimations();
    initializeFormHandler();
    initializeTypingAnimation();
});

// Navigation functionality
function initializeNavigation() {
    const hamburger = document.querySelector('.hamburger');
    const navMenu = document.querySelector('.nav-menu');
    const navLinks = document.querySelectorAll('.nav-link');

    // Toggle mobile menu
    hamburger.addEventListener('click', () => {
        navMenu.classList.toggle('active');
        hamburger.classList.toggle('active');
    });

    // Close menu when clicking on a link
    navLinks.forEach(link => {
        link.addEventListener('click', () => {
            navMenu.classList.remove('active');
            hamburger.classList.remove('active');
            
            // Remove active class from all links
            navLinks.forEach(l => l.classList.remove('active'));
            
            // Add active class to clicked link
            link.classList.add('active');
        });
    });

    // Update active link on scroll
    window.addEventListener('scroll', updateActiveLink);
}

function updateActiveLink() {
    const sections = document.querySelectorAll('section');
    const navLinks = document.querySelectorAll('.nav-link');
    
    let current = '';
    sections.forEach(section => {
        const sectionTop = section.offsetTop;
        const sectionHeight = section.clientHeight;
        
        if (pageYOffset >= sectionTop - 200) {
            current = section.getAttribute('id');
        }
    });

    navLinks.forEach(link => {
        link.classList.remove('active');
        if (link.getAttribute('href') === '#' + current) {
            link.classList.add('active');
        }
    });
}

/* ========================================
   SCROLL TO TOP BUTTON
   ======================================== */

function initializeScrollToTop() {
    const scrollToTopBtn = document.getElementById('scrollToTop');

    window.addEventListener('scroll', () => {
        if (window.pageYOffset > 300) {
            scrollToTopBtn.classList.add('show');
        } else {
            scrollToTopBtn.classList.remove('show');
        }
    });

    scrollToTopBtn.addEventListener('click', () => {
        window.scrollTo({
            top: 0,
            behavior: 'smooth'
        });
    });
}

/* ========================================
   SCROLL ANIMATIONS
   ======================================== */

function initializeScrollAnimations() {
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -100px 0px'
    };

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.style.animation = 'fadeInUp 0.8s ease forwards';
                observer.unobserve(entry.target);
            }
        });
    }, observerOptions);

    // Observe skill cards, project cards, and certification cards
    document.querySelectorAll('.skill-card, .project-card, .cert-card, .stat-card').forEach(element => {
        observer.observe(element);
    });

    // Animate progress bars when skills section comes into view
    const skillsSection = document.querySelector('.skills');
    const progressBars = document.querySelectorAll('.progress');
    
    const skillsObserver = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                progressBars.forEach(bar => {
                    const width = bar.style.width;
                    bar.style.width = '0';
                    setTimeout(() => {
                        bar.style.width = width;
                    }, 100);
                });
                skillsObserver.unobserve(skillsSection);
            }
        });
    }, observerOptions);

    if (skillsSection) {
        skillsObserver.observe(skillsSection);
    }
}

/* ========================================
   FORM HANDLER
   ======================================== */

function initializeFormHandler() {
    const contactForm = document.getElementById('contactForm');
    
    if (contactForm) {
        contactForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            // Get form values
            const formData = new FormData(this);
            const name = this.querySelector('input[type="text"]').value;
            const email = this.querySelector('input[type="email"]').value;
            const subject = this.querySelectorAll('input[type="text"]')[1].value;
            const message = this.querySelector('textarea').value;

            // Show success message
            showNotification('Message sent successfully! I\'ll get back to you soon.', 'success');
            
            // Reset form
            this.reset();
            
            // In a real scenario, you would send this data to a server
            console.log('Form Data:', {
                name,
                email,
                subject,
                message
            });
        });
    }
}

// Show notification
function showNotification(message, type = 'success') {
    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.textContent = message;
    notification.style.cssText = `
        position: fixed;
        top: 100px;
        right: 20px;
        background: ${type === 'success' ? 'rgba(0, 217, 255, 0.2)' : 'rgba(255, 6, 110, 0.2)'};
        border: 1px solid ${type === 'success' ? '#00d9ff' : '#ff006e'};
        color: ${type === 'success' ? '#00d9ff' : '#ff006e'};
        padding: 1rem 1.5rem;
        border-radius: 8px;
        z-index: 10000;
        animation: slideInRight 0.3s ease;
        font-weight: 500;
        max-width: 300px;
        word-wrap: break-word;
    `;
    
    document.body.appendChild(notification);
    
    setTimeout(() => {
        notification.style.animation = 'slideOutRight 0.3s ease';
        setTimeout(() => notification.remove(), 300);
    }, 3000);
}

/* ========================================
   TYPING ANIMATION
   ======================================== */

function initializeTypingAnimation() {
    const typingElement = document.querySelector('.typing');
    
    if (typingElement) {
        const text = typingElement.textContent;
        typingElement.textContent = '';
        
        let index = 0;
        const speed = 50; // milliseconds per character

        function type() {
            if (index < text.length) {
                typingElement.textContent += text.charAt(index);
                index++;
                setTimeout(type, speed);
            } else {
                // Add cursor blink animation after typing is complete
                typingElement.style.borderRight = '2px solid #00d9ff';
                typingElement.style.animation = 'blink 0.7s infinite';
            }
        }

        // Start typing after a short delay
        setTimeout(type, 500);
    }
}

/* ========================================
   ADD CSS ANIMATIONS DYNAMICALLY
   ======================================== */

const style = document.createElement('style');
style.textContent = `
    @keyframes slideOutRight {
        from {
            opacity: 1;
            transform: translateX(0);
        }
        to {
            opacity: 0;
            transform: translateX(100%);
        }
    }

    @keyframes blink {
        0%, 49% {
            border-right-color: #00d9ff;
        }
        50%, 100% {
            border-right-color: transparent;
        }
    }

    @keyframes slideInRight {
        from {
            opacity: 0;
            transform: translateX(100%);
        }
        to {
            opacity: 1;
            transform: translateX(0);
        }
    }
`;
document.head.appendChild(style);

/* ========================================
   SMOOTH PARALLAX EFFECT
   ======================================== */

window.addEventListener('scroll', () => {
    const scrolled = window.pageYOffset;
    const hero = document.querySelector('.hero');
    
    if (hero) {
        hero.style.backgroundPosition = `0px ${scrolled * 0.5}px`;
    }
});

/* ========================================
   MOUSE HOVER EFFECTS FOR CARDS
   ======================================== */

document.addEventListener('DOMContentLoaded', () => {
    const cards = document.querySelectorAll('.project-card, .skill-card, .cert-card');
    
    cards.forEach(card => {
        card.addEventListener('mouseenter', (e) => {
            const rect = card.getBoundingClientRect();
            const x = e.clientX - rect.left;
            const y = e.clientY - rect.top;

            card.style.setProperty('--mouse-x', `${x}px`);
            card.style.setProperty('--mouse-y', `${y}px`);
        });
    });
});

/* ========================================
   LAZY LOAD IMAGES (Future Enhancement)
   ======================================== */

function lazyLoadImages() {
    const images = document.querySelectorAll('img[data-src]');
    
    const imageObserver = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                const img = entry.target;
                img.src = img.dataset.src;
                img.removeAttribute('data-src');
                imageObserver.unobserve(img);
            }
        });
    });

    images.forEach(img => imageObserver.observe(img));
}

/* ========================================
   PERFORMANCE OPTIMIZATIONS
   ======================================== */

// Debounce function for scroll events
function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

// Throttle function for frequent events
function throttle(func, limit) {
    let inThrottle;
    return function(...args) {
        if (!inThrottle) {
            func.apply(this, args);
            inThrottle = true;
            setTimeout(() => inThrottle = false, limit);
        }
    };
}

/* ========================================
   ENHANCED KEYBOARD NAVIGATION
   ======================================== */

document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape') {
        // Close mobile menu on Escape
        const navMenu = document.querySelector('.nav-menu');
        const hamburger = document.querySelector('.hamburger');
        
        if (navMenu && navMenu.classList.contains('active')) {
            navMenu.classList.remove('active');
            hamburger.classList.remove('active');
        }
    }

    // Skip to main content on Tab+Alt
    if (e.altKey && e.key === 'm') {
        document.querySelector('#home').focus();
    }
});

/* ========================================
   CURSOR TRACKING FOR ENHANCED UX
   ======================================== */

document.addEventListener('mousemove', (e) => {
    const mouseX = e.clientX / window.innerWidth;
    const mouseY = e.clientY / window.innerHeight;

    // Create subtle parallax effect with hero content
    const heroText = document.querySelector('.hero-text');
    const heroImage = document.querySelector('.hero-image');

    if (heroText && heroImage) {
        heroText.style.transform = `translateX(${mouseX * 5}px)`;
        heroImage.style.transform = `translateX(${-mouseX * 5}px)`;
    }
});

/* ========================================
   RESUME DOWNLOAD
   ======================================== */

// Handle resume download
document.querySelectorAll('[download]').forEach(link => {
    link.addEventListener('click', (e) => {
        // Replace with actual resume path
        e.target.href = 'resume.pdf'; // Update with actual resume file
        showNotification('Resume downloading...', 'success');
    });
});

/* ========================================
   PROJECT CARD INTERACTIONS
   ======================================== */

document.querySelectorAll('.project-card').forEach(card => {
    const links = card.querySelectorAll('.project-link');
    
    links.forEach(link => {
        link.addEventListener('click', (e) => {
            // Handle link clicks
            if (link.textContent.includes('Code')) {
                // Handle GitHub link
                // e.preventDefault();
                // window.open(githubLink, '_blank');
            } else if (link.textContent.includes('Demo')) {
                // Handle demo link
                // e.preventDefault();
                // window.open(demoLink, '_blank');
            }
        });
    });
});

/* ========================================
   PRINT STYLESHEET SUPPORT
   ======================================== */

window.addEventListener('beforeprint', () => {
    document.body.style.background = 'white';
});

window.addEventListener('afterprint', () => {
    document.body.style.background = '';
});

/* ========================================
   LOCAL STORAGE FOR PREFERENCES
   ======================================== */

// Save user preferences (e.g., dark mode, theme)
function saveUserPreference(key, value) {
    localStorage.setItem(`portfolio_${key}`, JSON.stringify(value));
}

function getUserPreference(key, defaultValue) {
    const stored = localStorage.getItem(`portfolio_${key}`);
    return stored ? JSON.parse(stored) : defaultValue;
}

/* ========================================
   HANDLE CONTACT FORM WITH VALIDATION
   ======================================== */

function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}

document.getElementById('contactForm')?.addEventListener('submit', function(e) {
    e.preventDefault();
    
    const inputs = {
        name: this.querySelector('input[type="text"]').value,
        email: this.querySelector('input[type="email"]').value,
        subject: this.querySelectorAll('input[type="text"]')[1]?.value,
        message: this.querySelector('textarea').value
    };

    // Validation
    if (!inputs.name.trim()) {
        showNotification('Please enter your name', 'error');
        return;
    }

    if (!validateEmail(inputs.email)) {
        showNotification('Please enter a valid email', 'error');
        return;
    }

    if (!inputs.message.trim()) {
        showNotification('Please enter a message', 'error');
        return;
    }

    // If validation passes
    showNotification('Message sent successfully! I\'ll get back to you soon.', 'success');
    this.reset();
});

console.log('Portfolio loaded successfully! 🚀');
