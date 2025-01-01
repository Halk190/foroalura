# Foro Alura

![Foro Alura](https://via.placeholder.com/1200x400?text=Foro+Alura) <!-- Reemplaza este enlace con tu imagen -->

---

## 📖 Índice

1. [Descripción del Proyecto](#-descripción-del-proyecto)  
2. [Características](#-características)  
3. [Tecnologías Utilizadas](#-tecnologías-utilizadas)  
4. [Instalación](#-instalación)  
5. [Uso](#-uso)  
6. [Insignias](#-insignias)  

---

## ✨ Descripción del Proyecto

**Foro Alura** es una API que permite la gestión de tópicos (temas de discusión), usuarios y cursos dentro de un entorno educativo. Este sistema incluye funcionalidades avanzadas de seguridad y autorización, garantizando que solo los usuarios autorizados puedan realizar acciones sensibles como la creación, modificación o eliminación de datos.  

---

## 🔍 Características

### Tópicos
- **Crear un tópico**: Cualquier usuario puede iniciar un nuevo tema de discusión.  
- **Listar tópicos**: Los tópicos existentes pueden visualizarse paginados.  
- **Buscar por curso**: Se pueden buscar tópicos asociados a un curso específico.  
- **Buscar por ID**: Consulta un tópico específico mediante su identificador único.  
- **Actualizar tópicos**:  
  - Los administradores pueden actualizar cualquier tópico.  
  - Los estudiantes solo pueden actualizar los tópicos que han creado.  
- **Eliminar tópicos**:  
  - Los administradores pueden eliminar cualquier tópico.  
  - Los estudiantes solo pueden eliminar los tópicos que hayan creado.  

### Cursos
- **Crear un curso**: Solo los administradores tienen permiso para crear nuevos cursos.  

### Usuarios
- **Registrar un usuario**: Permite registrar nuevos usuarios con perfil de "Estudiante" por defecto.  
- **Crear un administrador**: Pasando una clave especial, se puede registrar un usuario con perfil de "Administrador".  

### Seguridad
- **Roles**:
  - **Administrador**: Puede gestionar cursos, así como actualizar y eliminar cualquier tópico.  
  - **Estudiante**: Puede crear tópicos, además de actualizar y eliminar únicamente aquellos que haya creado.  
- **Autenticación y autorización**: Implementadas utilizando **Spring Security** y **JWT**, garantizando un acceso seguro.  

---

## 🛠 Tecnologías Utilizadas

Las tecnologías empleadas en este proyecto son:  

- **Java 17** ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)  
- **Spring Boot** ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)  
- **PostgreSQL** ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)  
- **GitHub** ![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)  

---

## 📥 Instalación

Sigue los pasos a continuación para ejecutar el proyecto en tu máquina local:  

1. **Clona el repositorio**:  
   ```bash
   git clone https://github.com/tu-usuario/foro-alura.git
   cd foro-alura

2. ***Configurar la base de datos***

- Asegúrate de tener **PostgreSQL** instalado y ejecutándose en tu sistema.
- Crea una base de datos llamada `foro_alura` (puedes cambiar el nombre si lo deseas, pero asegúrate de reflejar el cambio en la configuración).
- Configura las variables de entorno necesarias para que el proyecto pueda conectarse a la base de datos:

| Variable de Entorno   | Descripción                          | Ejemplo              |
|-----------------------|--------------------------------------|----------------------|
| `DB_HOST`            | Host de la base de datos            | `localhost:5432`     |
| `DB_USER`            | Usuario de la base de datos         | `postgres`           |
| `DB_PASSWORD`        | Contraseña del usuario de la BD     | `tu_contraseña`      |
| `JWT_SECRET`         | Clave secreta para generar JWT      | `123456`             |
| `ADMIN_KEY`          | Clave para crear usuarios admin     | `1425`               |

---

### 3. Configurar el archivo `application.properties`

El proyecto utiliza las variables de entorno configuradas anteriormente. A continuación, te mostramos cómo luce el archivo `application.properties` que se encuentra en `src/main/resources/`:

```properties
spring.application.name=foroalura

# Configuración del datasource
spring.datasource.url=jdbc:postgresql://${DB_HOST}/foroalura
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
springdoc.api-docs.path=/api-docs

# Configuración de JPA/Hibernate
spring.jpa.database=postgresql
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.data.jpa.repositories.enabled=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Configuración de Flyway
spring.flyway.enabled=true
spring.flyway.url=jdbc:postgresql://${DB_HOST}/foroalura
spring.flyway.user=${DB_USER}
spring.flyway.password=${DB_PASSWORD}
spring.flyway.repair=true

# Secret para JWT
foroalura.security.secret=${JWT_SECRET:123456}

# Clave para administrador
ADMIN_KEY=1425
