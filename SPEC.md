# chatbot project SPEC.md

## 1. 개요

Chatbot 서비스를 구현 계획 

---

## 2. Tech Stack

### Frontend
- React.js
  - 사용자 채팅 UI 제공
  - API 연동을 통한 메시지 송수신 처리

### Backend
- Spring Boot
  - REST API 제공
  - 비즈니스 로직 및 메시지 처리 담당
  - NATS JetStream 연동

### Data / Cache
- MySQL
  - 사용자, 대화, 메타데이터 등 핵심 데이터 영속화
- Redis
  - 세션, 캐시, 임시 상태 관리

### Messaging
- NATS JetStream
  - 서비스 간 비동기 메시지 처리
  - Publish / Subscribe 기반 이벤트 전달

### Deployment / Infrastructure
- Kubernetes
  - 애플리케이션 배포 및 운영 환경
- QEMU 기반 로컬 환경
  - 로컬에서 Kubernetes 환경을 구성하여 배포

---

## 3. Architecture

- React 클라이언트에서 Spring Boot API 호출
- API 서버는 MySQL 및 Redis를 사용해 데이터와 상태 관리
- 비동기 작업 및 이벤트 전달은 NATS JetStream을 통해 처리

---

## 4. Why NATS JetStream

Kafka 대신 NATS JetStream은 Kafka보다 운영 복잡성이 낮음 
NATs는  클라이언트로부터 전달된 데이터를 publish 하고,
필요한 서비스가 subscribe하는 용도로 충분한 기능을 제공 

---

## 5. Kubernetes & Local Setup

Kubernetes는 배포 환경 운영 실험을 위해 사용  
QEMU 기반 로컬 환경에서 노드와 네트워크를 구성하여
배포, 재시작, 장애 상황을 가정 및 테스트  
