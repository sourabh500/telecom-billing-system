# 📞 Telecom Billing System  

[![Java](https://img.shields.io/badge/Java-17-red?logo=java&logoColor=white)](https://www.oracle.com/java/)  
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green?logo=springboot)](https://spring.io/projects/spring-boot)  
[![MySQL](https://img.shields.io/badge/MySQL-8-blue?logo=mysql)](https://www.mysql.com/)  
[![Maven](https://img.shields.io/badge/Maven-3.8+-orange?logo=apache-maven)](https://maven.apache.org/)  
[![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?logo=docker&logoColor=white)](https://www.docker.com/)  

A **microservices-based Telecom Billing System** built with **Java, Spring Boot, Spring Cloud, and MySQL**. The system simulates telecom operations like customer management, plan management, and billing with secure authentication and service discovery.  

---

## 🚀 Features  

- 👤 Customer Management  
- 💳 Billing & Payment Handling  
- 📋 Plan Management  
- 🔐 Authentication (JWT)  
- 🌐 API Gateway (single entry point)  
- 📡 Eureka Service Discovery  

---

## 🏗️ Architecture  

API Gateway → [ Customer Service | Billing Service | Plan Service | Auth Service ]
│
MySQL DB
+ Eureka Server for discovery

---

## ⚙️ Tech Stack  

- Java 17  
- Spring Boot, Spring Cloud  
- MySQL  
- Netflix Eureka  
- Spring Cloud Gateway  
- Maven  
- Docker (optional)  

---

## 🛠️ Getting Started  

### Prerequisites  
- JDK 17+  
- Maven 3.8+  
- MySQL 8+  

### Run Locally  

1. Clone the repo:  
   ```bash
   git clone https://github.com/your-username/telecom-billing-system.git
   cd telecom-billing-system
2. Configure MySQL databases in each service’s `application.properties`.  

3. Start Eureka Server:  
   ```bash
   cd eureka-server
   mvn spring-boot:run

4. Start each microservice (Customer, Billing, Plan, Auth) and then the API Gateway:  
   ```bash
   cd customer-service && mvn spring-boot:run
   cd billing-service && mvn spring-boot:run
   cd plan-service && mvn spring-boot:run
   cd auth-service && mvn spring-boot:run
   cd api-gateway && mvn spring-boot:run

📡 API Access

Eureka Dashboard: http://localhost:8761/

Gateway Endpoint Example:
GET http://localhost:8080/customer-service/customers/1

📑 Roadmap

 Reporting & analytics

 Online payments

 Notifications (Kafka/RabbitMQ)

 Cloud deployment with CI/CD

 👨‍💻 Author

Sourabh
Backend Developer | Java | Spring Boot | Microservices

   
   
   
