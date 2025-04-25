# Mutual Followers – Bajaj Finserv Health Challenge (Spring Boot Application)

## 📋 Project Overview

This Spring Boot application solves the problem of finding mutual followers

---

## 🏗️ Project Structure

```
bajaj-finserv-challenge/
├── .gitattributes
├── .gitignore
├── README.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/adina/bajajfinservchallenge/
│   │   │       ├── BajajFinservChallengeApplication.java
│   │   │       ├── client/ApiClient.java
│   │   │       ├── config/AppConfig.java
│   │   │       ├── model/
│   │   │       │   ├── WebhookRequest.java
│   │   │       │   ├── WebhookResponse.java
│   │   │       │   ├── WebhookResult.java
│   │   │       ├── runner/ApplicationRunner.java
│   │   │       └── service/MutualFollowersService.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/adina/bajajfinservchallenge/
│           └── BajajFinservChallengeApplicationTests.java
└── target/
    └── (compiled classes and build artifacts)
```

---

## 🚀 How to Run the Project

### 1. Prerequisites
- Java 17+
- Maven 3.8+
- Internet connection for dependency download

### 2. Clone Repository

```bash
git clone https://github.com/rakeshreddyadina/bajaj-finserv-challenge.git
cd mutual-followers
```

### 3. Build Project

```bash
./mvnw clean install
```

### 4. Run Application

```bash
./mvnw spring-boot:run
```

Or execute `BajajFinservChallengeApplication.java` from your IDE.

---

## 🛠️ Key Components

| Module | Description |
|:-------|:------------|
| `ApiClient.java` | REST API Client to POST webhook results |
| `MutualFollowersService.java` | Main logic for finding nth-level mutual followers |
| `ApplicationRunner.java` | Application initializer and runner |
| `Webhook Models` | Data models for request and response mapping |
| `AppConfig.java` | Spring configuration setup |

---

## 📡 Core Features

- ✅ BFS (Breadth-First Search) for n-level follower traversal
- ✅ Webhook API Integration with Authorization
- ✅ Retry mechanism with exponential backoff
- ✅ Exception handling and validation
- ✅ JUnit5 unit tests

---

## 🧠 Technologies Used

- Spring Boot 3.x
- Java 17
- Maven
- JUnit5
- (Optional: Lombok)

---

## ⚙️ Configuration (application.properties)

```properties
webhook.url=https://your-webhook-url
webhook.authToken=Bearer your_auth_token
```

Configure your webhook URL and token before running the application.

---

## ✨ Author

Developed by **Adina Rakesh Reddy**  
for **Bajaj Finserv Health – SRM Programming Challenge, April 2025**.