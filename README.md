# Vinicitus 🏠🚲 - Economía Circular de Barrio

**Vinicitus** es una plataforma de economía circular diseñada para conectar a vecinos y fomentar el consumo responsable mediante el alquiler y préstamo de objetos de uso ocasional. El objetivo es reducir el desperdicio y fortalecer los lazos comunitarios.

---

## 🚀 Arquitectura del Proyecto

El proyecto sigue una arquitectura de **Monolito Modular**, diseñada para facilitar una futura transición a **Microservicios**. El sistema se divide en dominios de negocio claramente separados:

### 🧩 Módulos Principales:
* **User Module:** Gestión de identidades, perfiles y coordenadas para geolocalización.
* **Catalog Module:** Gestión del inventario de objetos, categorías y disponibilidad.
* **Booking Module:** Motor de transacciones y estados de reserva.
* **Infrastructure:** Configuraciones globales, seguridad y persistencia.

---

## 📊 Modelo de Datos (ERD)

La estructura de la base de datos está optimizada con relaciones Lazy para un alto rendimiento:



* **User (1:N) Item:** Un vecino puede publicar múltiples objetos.
* **Item (1:N) Booking:** Un objeto puede tener un historial de múltiples reservas.
* **User (1:N) Booking:** Un usuario puede realizar múltiples solicitudes de alquiler (como arrendatario).

---

## 🔄 Flujo de una Reserva

El sistema gestiona el ciclo de vida de un alquiler mediante un **Enum de Estados** (`BookingStatus`):



1.  **PENDING:** El solicitante crea la reserva.
2.  **ACCEPTED/REJECTED:** El propietario decide sobre la solicitud.
3.  **ACTIVE:** El objeto ha sido entregado físicamente al vecino.
4.  **COMPLETED:** El objeto ha sido devuelto y la transacción finaliza con éxito.

---

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 4.0.5
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** PostgreSQL
* **Productividad:** Lombok, Spring DevTools
* **Documentación:** Swagger / OpenAPI (Próximamente)

---

## ⚙️ Instalación y Configuración

1.  Clonar el repositorio:
    ```bash
    git clone [https://github.com/MohamedAfallah/vinicitus.git](https://github.com/MohamedAfallah/vinicitus.git)
    ```
2.  Configurar la base de datos en `src/main/resources/application.properties`.
3.  Ejecutar la aplicación con IntelliJ IDEA o mediante terminal:
    ```bash
    ./mvnw spring-boot:run
    ```

---

## 👤 Autor
* **Mohamed Afallah** - *Desarrollador Backend* - [GitHub](https://github.com/MohamedAfallah)