# 🚀 Deployment Guide - Varun's Portfolio

Complete guide to deploy your portfolio to various platforms.

## 📋 Pre-Deployment Checklist

- [ ] Update personal information in `index.html`
- [ ] Replace social media links
- [ ] Update resume PDF link
- [ ] Test all functionality locally
- [ ] Run: `mvn clean package -DskipTests`
- [ ] Verify JAR file created in `target/`

## 🖥️ Local Deployment

### Windows

```bash
# Build
mvn clean package

# Run
java -jar target/java-portfolio-0.0.1-SNAPSHOT.jar

# Access: http://localhost:8080
```

### Linux/Mac

```bash
# Build
./mvnw clean package

# Run
java -jar target/java-portfolio-0.0.1-SNAPSHOT.jar

# Access: http://localhost:8080
```

### Custom Port

```bash
java -jar target/java-portfolio-0.0.1-SNAPSHOT.jar --server.port=9090
```

## ☁️ Cloud Deployment Options

### 1. **Azure App Service** (Recommended for Java)

#### Prerequisites
- Azure account
- Azure CLI installed
- Resource Group created

#### Deployment Steps

```bash
# 1. Create resource group
az group create --name myResourceGroup --location eastus

# 2. Create App Service plan
az appservice plan create --name myAppServicePlan --resource-group myResourceGroup --sku FREE

# 3. Create web app
az webapp create --resource-group myResourceGroup --plan myAppServicePlan --name varun-portfolio --runtime "java|17-java17"

# 4. Deploy JAR
az webapp deployment source config-zip --resource-group myResourceGroup --name varun-portfolio --src target/java-portfolio-0.0.1-SNAPSHOT.jar

# 5. Access your portfolio
# URL: https://varun-portfolio.azurewebsites.net
```

### 2. **AWS Elastic Beanstalk**

#### Prerequisites
- AWS account
- AWS CLI installed
- EB CLI installed

#### Deployment Steps

```bash
# 1. Initialize Elastic Beanstalk
eb init -p java-17 varun-portfolio --region us-east-1

# 2. Create environment
eb create production

# 3. Deploy
eb deploy

# 4. Open application
eb open

# 5. View URL
# eb status
```

**application.properties for EB**:
```properties
server.port=5000
```

### 3. **Heroku**

#### Prerequisites
- Heroku account
- Heroku CLI installed
- Git repository

#### Deployment Steps

```bash
# 1. Login to Heroku
heroku login

# 2. Create app
heroku create varun-portfolio

# 3. Deploy
git push heroku main

# 4. View logs
heroku logs --tail

# 5. Access
# https://varun-portfolio.herokuapp.com
```

**Procfile** (create in project root):
```
web: java -Dserver.port=$PORT $JAVA_OPTS -jar target/java-portfolio-0.0.1-SNAPSHOT.jar
```

**System.properties** (create in project root):
```
java.runtime.version=17
```

### 4. **DigitalOcean App Platform**

#### Deployment Steps

1. Push code to GitHub
2. Connect DigitalOcean to GitHub
3. Create new App
4. Choose Java runtime
5. Configure:
   - Build command: `mvn package`
   - Run command: `java -jar target/java-portfolio-0.0.1-SNAPSHOT.jar`
6. Deploy

### 5. **Netlify** (Static Site Only)

If you want to host just the HTML/CSS/JS on Netlify:

```bash
# Build your project
mvn clean package

# Extract static files
mkdir netlify-deploy
cp -r src/main/resources/static/* netlify-deploy/

# Deploy to Netlify
netlify deploy --prod --dir netlify-deploy
```

### 6. **GitHub Pages** (Static Site)

```bash
# Create gh-pages branch
git checkout --orphan gh-pages

# Copy static files
mkdir -p docs
cp -r src/main/resources/static/* docs/

# Commit and push
git add docs/
git commit -m "Deploy portfolio to GitHub Pages"
git push origin gh-pages

# Enable in GitHub Settings → Pages → Source: gh-pages /docs
```

## 🐳 Docker Deployment

### Create Dockerfile

```dockerfile
# Use Java 17 base image
FROM openjdk:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy JAR file
COPY target/java-portfolio-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Build and Run Docker Image

```bash
# Build Docker image
docker build -t varun-portfolio:latest .

# Run container
docker run -p 8080:8080 varun-portfolio:latest

# Access: http://localhost:8080
```

### Docker Compose

**docker-compose.yml**:
```yaml
version: '3.8'

services:
  portfolio:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SERVER_PORT=8080
    restart: unless-stopped
```

```bash
# Start services
docker-compose up -d

# Stop services
docker-compose down
```

## 📋 Environment Configuration

### Production application.properties

```properties
spring.application.name=Varun Kishor Bhoi - Portfolio
server.port=${SERVER_PORT:8080}
server.servlet.context-path=/
spring.mvc.static-path-pattern=/static/**
spring.resources.static-locations=classpath:/static/

# Security
server.compression.enabled=true
server.error.whitelabel.enabled=false

# Logging
logging.level.root=INFO
logging.level.com.example.java.portfolio=INFO

# Mail (optional)
spring.mail.host=${MAIL_HOST}
spring.mail.port=${MAIL_PORT:587}
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_PASSWORD}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## 🔒 Security Configuration

### Production Checklist

```yaml
Security:
  - [ ] Enable HTTPS/SSL
  - [ ] Update CORS origins (not "*")
  - [ ] Set secure headers
  - [ ] Enable CSRF protection
  - [ ] Use environment variables for secrets
  - [ ] Implement rate limiting
  - [ ] Add authentication if needed
  - [ ] Configure security headers

Performance:
  - [ ] Enable caching headers
  - [ ] Minify static assets
  - [ ] Enable compression
  - [ ] Implement CDN
  - [ ] Monitor server resources

Monitoring:
  - [ ] Set up error tracking (Sentry)
  - [ ] Enable access logs
  - [ ] Monitor uptime
  - [ ] Track performance metrics
```

### Security Headers

Add to `WebConfig.java`:
```java
@Override
public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
            .allowedOrigins("yourdomain.com")  // Specific domain
            .allowedMethods("GET", "POST")
            .allowCredentials(true)
            .maxAge(3600);
}
```

## 📊 Monitoring & Logging

### Application Performance Monitoring (APM)

#### New Relic
```xml
<dependency>
    <groupId>com.newrelic.agent.java</groupId>
    <artifactId>newrelic-java</artifactId>
    <version>latest</version>
</dependency>
```

#### Datadog
```yaml
dd:
  trace:
    enabled: true
```

### Log Management

#### ELK Stack (Elasticsearch, Logstash, Kibana)

Add dependency:
```xml
<dependency>
    <groupId>net.logstash.logback</groupId>
    <artifactId>logstash-logback-encoder</artifactId>
    <version>7.0.1</version>
</dependency>
```

## 🚀 Continuous Deployment (CI/CD)

### GitHub Actions Workflow

Create `.github/workflows/deploy.yml`:
```yaml
name: Deploy Portfolio

on:
  push:
    branches: [ main ]

jobs:
  build-and-deploy:
    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v2
    
    - name: Set up Java
      uses: actions/setup-java@v2
      with:
        java-version: '17'
    
    - name: Build with Maven
      run: mvn clean package
    
    - name: Deploy to Azure
      uses: azure/webapps-deploy@v2
      with:
        app-name: 'varun-portfolio'
        publish-profile: ${{ secrets.AZURE_WEBAPP_PUBLISH_PROFILE }}
        package: 'target/java-portfolio-0.0.1-SNAPSHOT.jar'
```

### GitLab CI

Create `.gitlab-ci.yml`:
```yaml
image: maven:3.8.1-jdk-17

stages:
  - build
  - deploy

build:
  stage: build
  script:
    - mvn clean package
  artifacts:
    paths:
      - target/java-portfolio-0.0.1-SNAPSHOT.jar

deploy:
  stage: deploy
  script:
    - echo "Deploying to production..."
    - # Your deployment command here
  environment:
    name: production
```

## ✅ Post-Deployment Verification

```bash
# Test health endpoint
curl http://yourdomain.com/api/health

# Test API endpoints
curl http://yourdomain.com/api/skills
curl http://yourdomain.com/api/projects

# Check logs
tail -f application.log

# Monitor server resources
# CPU, Memory, Disk usage
```

## 🔄 Update & Maintenance

### Deploy New Version

```bash
# 1. Make changes
# 2. Build
mvn clean package

# 3. Stop old version (if applicable)
# 4. Deploy new JAR
# 5. Verify deployment

# Azure
az webapp deployment source config-zip --resource-group myResourceGroup --name varun-portfolio --src target/java-portfolio-0.0.1-SNAPSHOT.jar

# AWS EB
eb deploy

# Heroku
git push heroku main
```

### Rollback Procedure

```bash
# Azure
az webapp deployment slot swap --resource-group myResourceGroup --name varun-portfolio --slot staging

# AWS EB
eb swap

# Heroku
heroku releases:rollback
```

## 📞 Support & Troubleshooting

### Common Issues

**Port Already in Use**
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -i :8080
kill -9 <PID>
```

**Out of Memory**
```bash
# Increase heap size
java -Xmx512m -Xms256m -jar target/java-portfolio-0.0.1-SNAPSHOT.jar
```

**CORS Issues**
- Check `WebConfig.java` CORS configuration
- Verify allowed origins
- Check browser console for specific error

---

**Successfully Deployed! 🎉**

For more help, visit:
- Spring Boot Docs: https://spring.io/projects/spring-boot
- Azure Docs: https://docs.microsoft.com/azure
- AWS Docs: https://docs.aws.amazon.com
