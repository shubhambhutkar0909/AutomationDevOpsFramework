
# 🏆 Automation + DevOps Intergated Framework

📌 Enterprise-Grade Test Automation Framework leveraging Selenium, Java, TestNG, and Docker for scalable and maintainable UI Test Automation.
⚡ Built for fast execution, parallel testing, and seamless CI/CD integration.


## 🚀 About Me

👋 Hi, I'm Shubham Bhutkar, with 8.5+ years of expertise in Functional and Automation Testing, specializing in Framework Development, CI/CD Integration, and Scalable Test Solutions.

💡 Experienced in Hybrid Test Automation Frameworks leveraging Selenium, Java, TestNG, Dockerized Selenium Grid, and Jenkins to deliver enterprise-grade automation.

🚀 Passionate about building maintainable, high-performance automation frameworks that enhance quality, efficiency, and scalability in Agile environments.


## Author
- Shubham Bhutkar
- [@Linkedin](www.linkedin.com/in/shubham-bhutkar-sdet)

✨ Why Choose This Framework?

✔ Hybrid Design: Combines Data-Driven, and Page Object Model (POM) principles

✔ Scalability: Supports parallel execution with Selenium Grid & Docker

✔ Data-Driven Testing: Uses Apache POI for Excel-based test execution

✔ Robust Reporting: Integrated Allure Reports for real-time test insights

✔ Advanced Logging: Log4j for granular debugging and tracking

✔ CI/CD Ready: Pre-configured for Jenkins, GitHub Actions, and Nexus


🛠 Pre-requisites
Before setting up and running the framework, ensure you have the following installed:

✅ Java 11

✅ Maven for dependency management

✅ Docker for containerized execution 
    (Docker Desktop can also be used for local)
✅ Docker Compose for Selenium Grid setup

✅ TestNG, Apache POI, Log4j (Included in Maven dependencies)




🛠️ Tech Stack & Infrastructure

🚀 Automation Framework Tech Stack

🔹 🖥️ Selenium WebDriver – Web UI Automation

🔹 ☕ Java (JDK 11+) – Core Programming Language

🔹 🧪 TestNG – Test Framework for Execution & Assertions

🔹 🛠️ Maven – Dependency Management & Build Automation

🔹 📊 Allure Reports – Real-time Test Execution Reporting

🔹 📜 Log4j – Centralized Logging & Debugging

🔹 📑 Apache POI – Data-Driven Testing with Excel Integration

🏗️ Infrastructure & DevOps

🔹 🐳 Dockerized Selenium Grid – Parallel Test Execution Across 

Browsers
🔹 ⚙️ Docker Compose – Manages Selenium Grid Setup

🔹 📦 Nexus Repository – Stores & Manages FAT JARs

🔹 🚀 CI/CD Pipeline – GitHub Actions & Jenkins Integration

🧩 Design Patterns & Principles

🔹 📌 Page Object Model (POM) – Enhances Maintainability & Reusability

🔹 📌 Single Responsibility Principle (SRP) – Modular & Scalable Architecture
📂 Project Structure

Hybrid-Test-Automation-Framework

│── src/main/java

│   ├── pages           # Page Object Model (POM) classes

│   ├── utils           # Utility classes (WebDriverManager, Logger, etc.)

│   ├── listeners       # Custom TestNG Listeners for logging & reporting

│   ├── utility         # Common reusable functions & helper methods

│   ├── driverFactory   # Manages WebDriver instances & driver manipulations

│

│── src/test/java

│   ├── tests           # TestNG test cases

│

│── src/test/resources

│   ├── testdata        # External test data files (CSV, Excel, JSON)

│   ├── testrunner      # TestNG suite XML for execution control

│   ├── log4j.properties # Logging configuration file

│   ├── config          # Environment-specific configuration files

│

│── reports             # Allure Reports output

│── docker-compose.yml  # Docker setup for Selenium Grid

│── Jenkinsfile         # CI/CD pipeline configuration for Jenkins

│── pom.xml             # Maven dependencies

│── README.md           # Project Documentation
📥 Installation

    1️⃣ Clone the repository:
    git clone https://github.com/shubhambhutkar0909/Automation_DevOps_Framework.git

    2️⃣ Running Test with Docker Compose:
    docker-compose up -d  
    mvn clean test 

🖥 Running Tests Locally

    mvn clean test 

📜 Logging

    📝 Logs are generated using Log4j and stored in the logs/ directory.⚙ Log levels and configurations can be modified in log4j.properties.

🚀 Integrated - GitHub Actions

    This framework includes GitHub Actions for automated test execution.

    📂 Workflow File: .github/workflows/maven.yml

    ✅ Runs tests on every push & PR

    ✅ Runs tests also gets executed at 11:30 PM IST everyday with help of Cron Job setup

    ✅ Supports execution on multiple browsers

    ✅ Reports test results in GitHub Actions

🚀 To manually trigger a GitHub Actions workflow:

    1️⃣ Navigate to the Actions tab in your repository.

    2️⃣ Select the workflow and click Run workflow

📊 Generating Reports

📍 Generate & View Allure Reports
    mvn allure:serve  

🔄 CI/CD Integration
🔹 🚀 GitHub Actions automates test execution on every commit.

🔹 🛠️ Jenkins Pipeline triggers test execution with Maven build and Selenium Grid execution.

🔹 📦 Nexus Repository stores FAT JARs for centralized access and version control.

🛡️ Best Practices & Design Principles
✅ 📌 Page Object Model (POM) enhances maintainability and modularity.

✅ 📌 Single Responsibility Principle (SRP) ensures each component has a clear purpose.

✅ 📊 Custom TestNG Listeners improve logging, retry mechanisms, and dynamic test execution.

✅ ⏳ Robust Exception Handling enhance test stability.


⭐ Like This Project? Give It a Star!

## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/shubhambhutkar0909)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](www.linkedin.com/in/shubham-bhutkar-sdet)

