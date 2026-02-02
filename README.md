# 🛡️ Spring Boot OAuth2 Client Application

This is a comprehensive **OAuth2 Client** implementation built with **Spring Boot 3.5**. The application acts as a secure gateway that manages user authentication via a custom **Authorization Server** and consumes protected APIs from a **Resource Server**.

---

## 🚀 Key Features

### 🔐 Advanced Authentication
- **Full OIDC Integration:** Implements OpenID Connect for standardized user identity management.
- **Secure Authorization Code Flow:** Handles the complex exchange of authorization codes for Access and ID tokens.
- **Automated Token Management:** Background handling of token storage, expiration, and relay.
- **Custom Session Handling:** Configured with a unique `CLIENTSESSIONID` to prevent session interference in local microservices environments.

### 🛠️ Technical Excellence
- **Reactive WebClient:** Uses a non-blocking `WebClient` with `ServletOAuth2AuthorizedClientExchangeFilterFunction` for automatic Bearer token injection.
- **Service-Oriented Architecture:** Business logic is decoupled from Controllers into dedicated Service classes.
- **Modern Security Configuration:** Utilizes the latest Spring Security 6.x lambda DSL for clean and readable code.



---

## 🏗️ System Architecture & Workflow

The Client App manages the "Three-Legged" OAuth2 flow to ensure maximum security:

1. **The Challenge:** When a user visits a protected URL, the app checks for a valid session.
2. **The Redirection:** If unauthenticated, the user is sent to the **Authorization Server (Port 9000)**.
3. **The Grant:** After login, the Auth Server sends a `code` back to the Client's redirect URI.
4. **The Exchange:** The Client App performs a server-to-server POST request to trade the `code` for a **JWT Access Token**.
5. **The Relay:** The `WebClient` fetches data from the **Resource Server (Port 8081)** by attaching the JWT in the `Authorization` header.



---

## ⚙️ Configuration Details (`application.properties`)

The application is configured to discover the Authorization Server dynamically:

| Property | Value | Description |
| :--- | :--- | :--- |
| `registration-id` | `client-app` | Unique identifier for this client. |
| `client-id` | `client-app` | Matches the ID stored in the Auth Server Database. |
| `issuer-uri` | `http://127.0.0.1:9000` | Used to fetch the JWK set and OpenID configuration. |
| `scope` | `openid, read` | Permissions requested from the user. |

---

## 🔌 API Endpoints

### 🟢 Public Endpoints
- `GET /` : Landing page with basic project information.

### 🔴 Protected Endpoints (Login Required)
- `GET /user` : Returns the logged-in user's profile and claims.
- `GET /fetch-resource` : Communicates with the Resource Server (8081) and returns secure data.



---

## ▶️ Execution Guide

### 1. Startup Order
To ensure the system connects correctly, follow this order:
1. Start **Authorization Server** (Port 9000).
2. Start **Resource Server** (Port 8081).
3. Start this **Client App** (Port 8080).

### 2. Command Line
```bash
mvn clean spring-boot:run