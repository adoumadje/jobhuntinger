# 🚀 JobHuntinger

A full-stack job tracking application built with **Spring Boot**, **Angular**, **PostgreSQL**, and **Docker**.

---

## 🧱 Tech Stack

- Backend: Spring Boot (Java)
- Frontend: Angular + Nginx
- Database: PostgreSQL 16
- Deployment: Docker & Docker Compose

---

## 📦 Requirements

Before running this project, make sure you have installed:

- Docker → https://www.docker.com/get-started
- Docker Compose (included in Docker Desktop)

---

## 📥 Clone the repository

```bash
git clone https://github.com/adoumadje/jobhuntinger.git
cd jobhuntinger🚀 JobHuntinger

A full-stack job tracking application built with Spring Boot, Angular, PostgreSQL, and Docker.

📦 Tech Stack
Backend: Spring Boot (Java)
Frontend: Angular + Nginx
Database: PostgreSQL 16
Deployment: Docker & Docker Compose
🛠️ Requirements

Before running the project, make sure you have:

Docker installed → https://www.docker.com/get-started
Docker Compose (included in modern Docker Desktop)
📥 Clone the project
git clone https://github.com/adoumadje/jobhuntinger.git
cd jobhuntinger
🚀 Run in Production Mode

Move to the production setup folder:

cd prod

Start all services:

docker compose up -d
🌐 Access the application

After startup:

Frontend → http://localhost:8180
Backend API → http://localhost:8080
PostgreSQL → localhost:5433
📌 Services Overview
Service	Description	Port
frontend	Angular + Nginx UI	8180
backend	Spring Boot REST API	8080
postgres	Database	5433
⚙️ Environment

The application runs with Docker Compose using:

Spring profile: prod
Internal Docker networking for backend ↔ database communication
Nginx reverse proxy for frontend routing (/api → backend)
🧹 Stop the application
docker compose down

To remove volumes (⚠ deletes database data):

docker compose down -v
🧠 Notes
Backend is not directly used by the frontend via hostname (backend:8080)
All API calls go through /api via Nginx proxy
No manual database setup required (PostgreSQL auto-initialized)
