🇪🇸 *Scroll down for the Spanish version / Descripción en castellano a continuación.*

# 🏗️ Auctions Server - Version 1

## 📘 Description (English)

This repository contains the first version of the **Auctions Server**, part of the *Auctions Service* case study inspired by a simplified version of eBay. It provides the foundational backend for managing **auctions, bids, users, and categories** and exposes a **REST API** built with **Spring Boot**.

Version 1 demonstrates core design patterns:
- 🧩 **Façade**
- ⚙️ **Application Service (AppService)**
- 📦 **Data Transfer Object (DTO)**
- 🔄 **State Management**

### 🌐 REST API Endpoints

| Method | Endpoint | Description |
|:------:|:-------------------------------|:----------------------------------------------|
| **POST** | `/auth/login` | Log in to the system |
| **POST** | `/auth/logout` | Log out from the system |
| **GET**  | `/auctions/categories` | Retrieve all available categories |
| **GET**  | `/auctions/categories/{categoryName}/articles` | Get all articles in a given category |
| **GET**  | `/auctions/articles/{articleId}/details` | Retrieve detailed information of an article |
| **POST** | `/auctions/articles/{articleId}/bid` | Place a bid on an article (requires login) |

📄 OpenAPI: `http://localhost:8080/v3/api-docs`  
💡 Swagger UI: `http://localhost:8080/swagger-ui/index.html`

---

## 📘 Descripción

Este repositorio contiene la primera versión del **Servidor de Subastas**, parte del caso práctico *Auctions Service*. Proporciona el backend base para **subastas, pujas, usuarios y categorías**, exponiendo una **API REST** con **Spring Boot**.

Patrones de diseño:
- 🧩 **Façade**
- ⚙️ **Application Service (AppService)**
- 📦 **Data Transfer Object (DTO)**
- 🔄 **State Management**

### 🌐 Endpoints del API REST

| Método | Endpoint | Descripción |
|:------:|:-------------------------------|:----------------------------------------------|
| **POST** | `/auth/login` | Iniciar sesión |
| **POST** | `/auth/logout` | Cerrar sesión |
| **GET**  | `/auctions/categories` | Consultar todas las categorías |
| **GET**  | `/auctions/categories/{categoryName}/articles` | Obtener artículos por categoría |
| **GET**  | `/auctions/articles/{articleId}/details` | Ver detalles de un artículo |
| **POST** | `/auctions/articles/{articleId}/bid` | Realizar una puja (requiere login) |

📄 OpenAPI: `http://localhost:8080/v3/api-docs`  
💡 Swagger UI: `http://localhost:8080/swagger-ui/index.html`

---

## ⚙️ Tech Stack & Build

### 🔧 Java & Build
- ☕ **Java**: 21  
- 🧱 **Build**: Gradle  
- 🔌 **Plugins**:
  - `org.springframework.boot` **3.5.7**
  - `io.spring.dependency-management` **1.1.6**
### 📦 Dependencies
- `org.springframework.boot:spring-boot-starter-web`
- `org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13`

---

## ✒️ Authors / Autoría

**Carballedo, R. & Cortázar, R.**  
*Faculty of Engineering – University of Deusto*

---

> 🧠 *This description was generated with the assistance of ChatGPT 5 and has been reviewed and validated to ensure accuracy and correctness.*
