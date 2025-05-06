# Tutorial Ludoteca 🎮 - Arquitectura de Microservicios

Este proyecto es una aplicación distribuida basada en 🧩 microservicios utilizando el stack de Spring Boot y Spring Cloud.

## ⚙️ Microservicios incluidos

| Servicio     | Descripción                                      | Puerto típico |
|--------------|--------------------------------------------------|---------------|
| `gateway`    | API Gateway para enrutar solicitudes             | 8080          |
| `eureka`     | Service discovery con Spring Cloud Eureka        | 8761          |
| `borrow`     | Gestión de préstamos de juegos                   | 8095          |
| `customer`   | Gestión de clientes                              | 8094          |
| `game`       | Gestión de juegos                                | 8093          |
| `author`     | Gestión de autores                               | 8092          |
| `category`   | Gestión de categorías                            | 8091          |

> Cada microservicio está desacoplado y se comunica a través de **REST** usando **Feign Clients**.

## 🛠️ Tecnologías usadas

- ⚙️ Spring Boot  
- ☁️ Spring Cloud (Eureka, Gateway, Feign)  
- 🌐 REST APIs  
- 🔁 ModelMapper  
- 🧪 H2 (base de datos en memoria para desarrollo)


