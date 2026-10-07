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

The suite includes unit tests for auctions, authentication and currency conversion, plus `MockMvc` tests for REST routes, parameters, JSON credentials and response codes. Integration tests also start the complete application on a random local port and verify OpenAPI, Swagger UI and the login/bid/logout workflow with the initialized data. A concurrency regression test checks that bids waiting for an article lock are rejected if the auction closes.

The [CI workflow](.github/workflows/ci.yml) runs the test suite for pushes to `master` and pull requests. The `master` branch requires the `test` check to pass before changes are integrated.

### Classroom notes and dependency review

Stable versions checked on **8 October 2026**:

| Component | Version | Official reference |
|:--|:--|:--|
| Spring Boot | 4.1.1 | [Releases](https://github.com/spring-projects/spring-boot/releases) |
| springdoc OpenAPI | 3.1.1 (Spring Boot 4 compatible branch) | [Documentation](https://springdoc.org/) |
| Spring dependency management plugin | 1.1.7 | [Releases](https://github.com/spring-gradle-plugins/dependency-management-plugin/releases) |
| Apache Commons Codec | 1.22.1 (explicit override) | [Release history](https://commons.apache.org/proper/commons-codec/changes.html) |
| Gradle wrapper | 9.8.0 | [Releases](https://gradle.org/releases/) |
| CI checkout / Java setup | 7.0.1 / 6.0.1 | [Checkout](https://github.com/actions/checkout/releases), [Java setup](https://github.com/actions/setup-java/releases) |

Java 21 remains the classroom requirement. Spring Boot manages the remaining library versions as a compatible set; they are not individually pinned to their newest upstream release. See [Spring Boot dependency management](https://docs.spring.io/spring-boot/gradle-plugin/managing-dependencies.html).

For Swagger/Postman login, `password` must contain the lowercase hexadecimal SHA-1 hash of the original password, as sent by the clients. For example, use email `batman@dc.com` and `DigestUtils.sha1Hex("Batm@n123!")`. The server hashes this value again before comparing it with the stored value. Pass the returned token as a **plain text body** when bidding or logging out. Article IDs run from **0 to 11**, and demo auctions end on 31 December of the year the server starts.

V1 stores all data and sessions in memory: restarting resets them. EUR/USD/GBP rates are fixed teaching values. The SHA-1 login scheme, non-expiring sessions and `double` monetary amounts are educational simplifications, not a production authentication or monetary model. The clients default to V2 at port 8082; configure their server URL to `http://localhost:8081` when using V1.

### License and authorship

This project is licensed under the [MIT License](LICENSE).

Faculty of Engineering, University of Deusto — Academic year 2026–27.

### AI assistance and review disclosure

The application code was initially generated with ChatGPT 4o (OpenAI) and adapted using GitHub Copilot. The codebase and documentation were reviewed and updated with assistance from Claude Opus 4.8 (Anthropic) in July 2026 and ChatGPT-6.1 Sol (OpenAI) in October 2026.

All automated tests and the continuous integration workflow were entirely generated with ChatGPT-6.1 (OpenAI) in September and October 2026.

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

La batería incluye pruebas unitarias de subastas, autenticación y conversión de moneda, además de pruebas `MockMvc` de rutas REST, parámetros, credenciales JSON y códigos de respuesta. Las pruebas de integración arrancan también la aplicación completa en un puerto local aleatorio y verifican OpenAPI, Swagger UI y el flujo de login/puja/logout con los datos iniciales. Una prueba de regresión de concurrencia comprueba que se rechazan las pujas que esperan el bloqueo de un artículo si la subasta cierra durante la espera.

El [flujo de CI](.github/workflows/ci.yml) ejecuta las pruebas en cada cambio a `master` y en cada pull request. La rama `master` requiere que la comprobación `test` sea correcta antes de integrar cambios.

### Notas para clase y revisión de dependencias

Versiones estables comprobadas el **8 de octubre de 2026**:

| Componente | Versión | Referencia oficial |
|:--|:--|:--|
| Spring Boot | 4.1.1 | [Versiones](https://github.com/spring-projects/spring-boot/releases) |
| springdoc OpenAPI | 3.1.1 (rama compatible con Spring Boot 4) | [Documentación](https://springdoc.org/) |
| Plugin de gestión de dependencias Spring | 1.1.7 | [Versiones](https://github.com/spring-gradle-plugins/dependency-management-plugin/releases) |
| Apache Commons Codec | 1.22.1 (versión explícita) | [Historial](https://commons.apache.org/proper/commons-codec/changes.html) |
| Wrapper de Gradle | 9.8.0 | [Versiones](https://gradle.org/releases/) |
| CI checkout / configuración Java | 7.0.1 / 6.0.1 | [Checkout](https://github.com/actions/checkout/releases), [Java setup](https://github.com/actions/setup-java/releases) |

Se mantiene Java 21 como requisito docente. Spring Boot gestiona las versiones del resto de librerías como un conjunto compatible; no se fija individualmente cada una a su última versión publicada. Véase la [gestión de dependencias de Spring Boot](https://docs.spring.io/spring-boot/gradle-plugin/managing-dependencies.html).

Para el login desde Swagger/Postman, `password` debe contener el hash SHA-1 hexadecimal en minúsculas de la contraseña original, como lo envían los clientes. Por ejemplo, utiliza el correo `batman@dc.com` y `DigestUtils.sha1Hex("Batm@n123!")`. El servidor vuelve a aplicar el hash antes de compararlo con el valor almacenado. Envía el token recibido como **cuerpo de texto plano** para pujar o cerrar sesión. Los identificadores de artículos van del **0 al 11** y las subastas de ejemplo cierran el 31 de diciembre del año de arranque.

V1 conserva los datos y las sesiones en memoria: se reinician al arrancar de nuevo. Los tipos EUR/USD/GBP son valores docentes fijos. El login con SHA-1, las sesiones sin caducidad y los importes con `double` son simplificaciones educativas, no un modelo de autenticación o de dinero para producción. Los clientes apuntan por defecto a V2 en el puerto 8082; configura su URL como `http://localhost:8081` para utilizarlos con V1.

### Licencia y autoría

Este proyecto se distribuye bajo la [licencia MIT](LICENSE).

Facultad de Ingeniería, Universidad de Deusto — Curso académico 2026–27.

### Declaración sobre asistencia de IA y revisión

El código de la aplicación se generó inicialmente con ChatGPT 4o (OpenAI) y se adaptó utilizando GitHub Copilot. El código y la documentación se revisaron y actualizaron con asistencia de Claude Opus 4.8 (Anthropic) en julio de 2026 y de ChatGPT-6.1 Sol (OpenAI) en octubre de 2026.

Todas las pruebas automáticas y el flujo de integración continua se generaron íntegramente con ChatGPT-6.1 (OpenAI) en septiembre y octubre de 2026.
