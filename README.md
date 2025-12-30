# Backend – Spring Boot Application

## 📌 Overview
이 프로젝트는 **Spring Boot 기반 백엔드 서버**입니다.  
React 프런트엔드와 연동되며, ChatGPT(OpenAI), NATS, Redis 등 외부 시스템과 통합되어 동작합니다.


## Prerequisite
Redis
역할: 세션 저장소 (Spring Session)
Port: 6379

NATS
역할: 메시징 / 이벤트 처리 (JetStream)
Port: 4222

OpenAI (ChatGPT)
OpenAI API 연동 필요


---

## 🛠 Tech Stack
- Java 21
- Spring Boot 3.3.5
- Spring Security + OAuth2 Client
- MyBatis
- MySQL
- Redis (Session Store)
- NATS (JetStream)
- Spring AI (OpenAI)
- Swagger (OpenAPI)
- Gradle
- Checkstyle / JaCoCo

---

## cors 설정 

애플리케이션을 클라이언트와 바로 연동하지 않고 프록시 서버를 
거쳐서 연동한다면 
config/SecurityConfig.java의 cors 설정을 주석 처리
할수도 있습니다. 

## 📂 Project Structure
```text
src/main/java
├── config        # Security, Redis, NATS 설정
├── controller    # REST API Controller
├── service       # 비즈니스 로직
├── mapper        # MyBatis Mapper
├── domain        # DTO / Domain
└── Application.java

## Build & Start 

./gradlew clean build

./gradlew bootRun

## Test

./gradlew test

cat build/reports/jacoco/test/html/index.html

