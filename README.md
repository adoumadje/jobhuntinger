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
cd jobhuntinger
```

---

## 🚀 Run the project (Production mode)

Move to the production folder:

```bash
cd prod
```

Start all services:

```bash
docker compose up -d
```

---

## 🌐 Access the application

Once everything is running:

- Frontend: http://localhost:8180  
- Backend API: http://localhost:8080  
- Database: localhost:5433  

---

## 🧩 Services Overview

| Service   | Description            | Port  |
|----------|------------------------|------|
| frontend | Angular + Nginx UI     | 8180 |
| backend  | Spring Boot API        | 8080 |
| postgres | PostgreSQL database    | 5433 |

---

## 🛑 Stop the application

Stop containers:

```bash
docker compose down
```

Remove everything (⚠ deletes database data):

```bash
docker compose down -v
```

---

## ⚙️ Architecture Notes

- Frontend is served via **Nginx**
- Backend is accessible internally via Docker network
- API requests go through `/api` proxy (no direct backend calls from frontend)
- PostgreSQL runs inside Docker with persistent volume

---

## 🧠 Important Notes

- Do NOT use `backend:8080` in frontend code
- All API calls must go through `/api`
- Backend is not required to be exposed to the browser
- Database data is persisted using Docker volumes

---

## 📄 License

This project is for educational/personal use.
