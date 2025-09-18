📞 Telecom Billing System










A microservices-based Telecom Billing System built with Java, Spring Boot, Spring Cloud, and MySQL. The system simulates telecom operations like customer management, plan management, and billing with secure authentication and service discovery.

🚀 Features

👤 Customer Management

💳 Billing & Payment Handling

📋 Plan Management

🔐 Authentication (JWT)

🌐 API Gateway (single entry point)

📡 Eureka Service Discovery

🏗️ Architecture
API Gateway → [ Customer Service | Billing Service | Plan Service | Auth Service ]
                       │
                    MySQL DB
          + Eureka Server for discovery

⚙️ Tech Stack

Java 17

Spring Boot, Spring Cloud

MySQL

Netflix Eureka

Spring Cloud Gateway

Maven

Docker (optional)

🛠️ Getting Started
Prerequisites

JDK 17+

Maven 3.8+

MySQL 8+

Run Locally

Clone the repo:

git clone https://github.com/your-username/telecom-billing-system.git
cd telecom-billing-system


Configure MySQL databases in each service’s application.properties.

Start Eureka Server:

cd eureka-server
mvn spring-boot:run


Start each microservice (Customer, Billing, Plan, Auth) and then the API Gateway:

cd customer-service && mvn spring-boot:run

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
