# 🏗️ Auctions Server — Version 1

[![CI](https://github.com/rcarball/auctions-server-1/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/rcarball/auctions-server-1/actions/workflows/ci.yml)

## English

### Overview

Auctions Server V1 is the foundational backend of the *Auctions Service* teaching case study, a simplified distributed auction system for third-year Computer Engineering students. It manages auctions, bids, users and categories through a Spring Boot REST API.

The version is intentionally educational rather than production-oriented. It illustrates the Façade, Application Service, Data Transfer Object and State Management patterns.

### REST API

| Method | Endpoint | Description |
|:--|:--|:--|
| POST | `/auth/login` | Log in and obtain a token |
| POST | `/auth/logout` | Log out using a token |
| GET | `/auctions/categories` | Retrieve categories |
| GET | `/auctions/categories/{categoryName}/articles` | Retrieve a category's articles |
| GET | `/auctions/articles/{articleId}/details` | Retrieve article details |
| POST | `/auctions/articles/{articleId}/bid` | Place a bid (requires login) |

- Swagger UI: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
- OpenAPI document: [http://localhost:8081/v3/api-docs](http://localhost:8081/v3/api-docs)

### Requirements and run on Windows, macOS and Linux

Requires JDK 21.

#### Windows

From PowerShell:

```powershell
.\gradlew.bat bootRun
```

#### macOS

From the repository root:

```bash
chmod +x gradlew  # only needed if the executable bit was lost, e.g. after extracting a ZIP
./gradlew bootRun
```

#### Linux

Use the same commands as macOS:

```bash
chmod +x gradlew  # only if needed
./gradlew bootRun
```

The server starts at [http://localhost:8081](http://localhost:8081). The Gradle wrapper is included, so a local Gradle installation is not required. The first run downloads its pinned Gradle version and dependencies.

In Eclipse or Spring Tool Suite on any supported operating system, import the folder as an existing Gradle project and run `AuctionsApplication`.

### Tests and continuous integration

```bash
./gradlew test
```

The suite includes unit tests for auctions, authentication and currency conversion, plus `MockMvc` tests for REST routes, parameters, JSON credentials and response codes. It validates the HTTP contract explored manually with Swagger UI or Postman without starting a network server.

The [CI workflow](.github/workflows/ci.yml) runs the test suite for pushes to `master` and pull requests. The `master` branch requires the `test` check to pass before changes are integrated.

### License and authorship

This project is licensed under the [MIT License](LICENSE).

Faculty of Engineering, University of Deusto — Academic year 2026–27.

### AI assistance and review disclosure

The initial version of this codebase was developed with partial assistance from ChatGPT (OpenAI) and GitHub Copilot.

From July to September 2026, the codebase and documentation were reviewed and audited using Claude Opus (Anthropic) and Codex (OpenAI). The resulting version was tested and refined to identify and correct issues within the scope of those verification activities.

---

## Español

### Descripción general

Auctions Server V1 es el backend inicial del caso docente *Auctions Service*, un sistema de subastas distribuido y simplificado para alumnado de tercero de Ingeniería Informática. Gestiona subastas, pujas, usuarios y categorías mediante una API REST de Spring Boot.

La versión tiene una finalidad deliberadamente educativa, no productiva. Ilustra los patrones Façade, Application Service, Data Transfer Object y State Management.

### API REST

| Método | Endpoint | Descripción |
|:--|:--|:--|
| POST | `/auth/login` | Iniciar sesión y obtener un token |
| POST | `/auth/logout` | Cerrar sesión con un token |
| GET | `/auctions/categories` | Consultar categorías |
| GET | `/auctions/categories/{categoryName}/articles` | Consultar los artículos de una categoría |
| GET | `/auctions/articles/{articleId}/details` | Consultar los detalles de un artículo |
| POST | `/auctions/articles/{articleId}/bid` | Realizar una puja (requiere sesión) |

- Swagger UI: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
- Documento OpenAPI: [http://localhost:8081/v3/api-docs](http://localhost:8081/v3/api-docs)

### Requisitos y ejecución en Windows, macOS y Linux

Requiere JDK 21.

#### Windows

Desde PowerShell:

```powershell
.\gradlew.bat bootRun
```

#### macOS

Desde la raíz del repositorio:

```bash
chmod +x gradlew  # solo si se ha perdido el permiso, por ejemplo tras extraer un ZIP
./gradlew bootRun
```

#### Linux

Utiliza los mismos comandos que en macOS:

```bash
chmod +x gradlew  # solo si fuera necesario
./gradlew bootRun
```

El servidor queda disponible en [http://localhost:8081](http://localhost:8081). Se incluye el wrapper de Gradle, por lo que no es necesario instalar Gradle localmente. La primera ejecución descarga la versión fijada de Gradle y las dependencias.

En Eclipse o Spring Tool Suite, en cualquiera de los sistemas operativos admitidos, importa la carpeta como proyecto Gradle existente y ejecuta `AuctionsApplication`.

### Pruebas e integración continua

```bash
./gradlew test
```

La batería incluye pruebas unitarias de subastas, autenticación y conversión de moneda, además de pruebas `MockMvc` de rutas REST, parámetros, credenciales JSON y códigos de respuesta. Verifica automáticamente el contrato HTTP que se explora manualmente con Swagger UI o Postman, sin iniciar un servidor de red.

El [flujo de CI](.github/workflows/ci.yml) ejecuta las pruebas en cada cambio a `master` y en cada pull request. La rama `master` requiere que la comprobación `test` sea correcta antes de integrar cambios.

### Licencia y autoría

Este proyecto se distribuye bajo la [licencia MIT](LICENSE).

Facultad de Ingeniería, Universidad de Deusto — Curso académico 2026–27.

### Declaración sobre asistencia de IA y revisión

La versión inicial de este código se desarrolló con asistencia parcial de ChatGPT (OpenAI) y GitHub Copilot.

Entre julio y septiembre de 2026, el código y la documentación se revisaron y auditaron con Claude Opus (Anthropic) y Codex (OpenAI). La versión resultante fue probada y refinada para identificar y corregir incidencias dentro del alcance de dichas actividades de verificación.
