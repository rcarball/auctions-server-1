🇪🇸 *Scroll down for the Spanish version / Descripción en castellano a continuación.*

# 🏗️ Auctions Server - Version 1

## 📘 Description

This repository contains the first version of the **Auctions Server**, part of the *Auctions Service* case study inspired by a simplified version of eBay. It provides the foundational structure of the server, implementing core backend functionality for managing auctions, bids, users, and categories.

The server follows a **client-server architecture** based on **Spring Boot**, exposing a **REST API** for the client-side application.  
Version 1 introduces essential design patterns, including:

- 🧩 **Façade**
- ⚙️ **Application Service (AppService)**
- 📦 **Data Transfer Object (DTO)**
- 🔄 **State Management**

### 🌐 REST API Endpoints

| Method | Endpoint | Description |
|:--------|:-------------------------------|:----------------------------------------------|
| **POST** | `/auth/login` | Log in to the system |
| **POST** | `/auth/logout` | Log out from the system |
| **GET**  | `/auctions/categories` | Retrieve all available categories |
| **GET**  | `/auctions/categories/{categoryName}/articles` | Get all articles in a given category |
| **GET**  | `/auctions/articles/{articleId}/details` | Retrieve detailed information of an article |
| **POST** | `/auctions/articles/{articleId}/bid` | Place a bid on an article (requires login) |

💡 Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)  
📄 OpenAPI Docs: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 📘 Descripción

Este repositorio contiene la primera versión del **Servidor de Subastas**, parte del caso práctico *Auctions Service*, inspirado en una versión simplificada de eBay. Proporciona la estructura base del servidor e implementa la funcionalidad esencial del backend para la gestión de subastas, pujas, usuarios y categorías.

El servidor utiliza una **arquitectura cliente-servidor** con **Spring Boot**, ofreciendo una **API REST** para la comunicación con el cliente.  
La versión 1 implementa los patrones de diseño:

- 🧩 **Façade**
- ⚙️ **Application Service (AppService)**
- 📦 **Data Transfer Object (DTO)**
- 🔄 **State Management**

### 🌐 Endpoints del API REST

| Método | Endpoint | Descripción |
|:--------|:-------------------------------|:----------------------------------------------|
| **POST** | `/auth/login` | Iniciar sesión |
| **POST** | `/auth/logout` | Cerrar sesión |
| **GET**  | `/auctions/categories` | Consultar todas las categorías |
| **GET**  | `/auctions/categories/{categoryName}/articles` | Obtener artículos por categoría |
| **GET**  | `/auctions/articles/{articleId}/details` | Consultar los detalles de un artículo |
| **POST** | `/auctions/articles/{articleId}/bid` | Realizar una puja (requiere login) |

💡 Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)  
📄 OpenAPI Docs: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## ✒️ Authors / Autoría

**Carballedo, R. & Cortázar, R.**  
*Faculty of Engineering – University of Deusto*

---

> 🧠 *This description was generated with the assistance of ChatGPT 5 and has been reviewed and validated to ensure accuracy and correctness.*
