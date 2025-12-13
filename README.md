# Sistema de Gestión de Docentes

## 📋 Índice
- [Introducción](#introducción)
- [Descripción del Proyecto](#descripción-del-proyecto)
- [Funcionalidades](#funcionalidades)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Requisitos Previos](#requisitos-previos)
- [Instalación y Configuración](#instalación-y-configuración)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Modelo de Datos](#modelo-de-datos)
- [API REST - Endpoints](#api-rest---endpoints)
- [Ejemplos de Uso](#ejemplos-de-uso)
- [Autor](#autor)

---

## 🎯 Introducción

Este proyecto implementa un **Sistema de Gestión de Docentes** desarrollado como parte de la práctica del primer trimestre de la asignatura **Desarrollo Web en Entorno Servidor**.

El sistema permite la gestión automatizada de docentes de un centro educativo, incluyendo la administración de sus datos personales, departamentos y solicitudes de días de asuntos propios.

---

## 📖 Descripción del Proyecto

La aplicación web proporciona una API REST para gestionar:

- **Docentes**: Información personal, departamento y rol
- **Departamentos**: Organización de docentes por áreas
- **Asuntos Propios**: Gestión de solicitudes de días personales con sistema de validación

El sistema automatiza procesos que anteriormente se realizaban manualmente, mejorando la eficiencia administrativa del centro educativo.

---

## ⚡ Funcionalidades

### 1. Gestión de Docentes
- Consulta de docentes por ID
- Listado de docentes ordenados por apellidos
- Búsqueda de docentes por departamento
- Información completa: nombre, apellidos, email, siglas, departamento y rol

### 2. Gestión de Departamentos
- Conteo de profesores por código de departamento
- Información de departamentos: nombre, código y teléfono

### 3. Gestión de Asuntos Propios
- **UC2 - Solicitar día propio**: Validación automática de solicitudes
  - Verifica que la fecha sea válida (no puede ser pasada)
  - Comprueba que no haya solicitudes duplicadas
  - Almacena en estado pendiente de validación

- **UC5 - Validar días propios**: Aprobación o rechazo de solicitudes
  - Confirma o deniega días solicitados
  - Registra fecha de tramitación

- **UC6 - Consultar días propios**: Listado completo de solicitudes
  - Muestra días aceptados y rechazados
  - Incluye información del docente y departamento

- **Consultas avanzadas**:
  - Días propios pendientes de disfrutar (validados con fecha >= hoy)
  - Docente que más días propios ha disfrutado

---

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java 21**
- **Spring Boot 3.4.0**
  - Spring Web
  - Spring Data JPA
  - Spring Boot DevTools
- **Hibernate** (ORM)
- **Lombok** (Reducción de código boilerplate)

### Base de Datos
- **MySQL 8.x** (compatible con 5.5.5)

### Herramientas de Desarrollo
- **Maven** (Gestión de dependencias)
- **Eclipse IDE**
- **Postman** (Pruebas de API)
- **Git** (Control de versiones)

---

## 📦 Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- Java JDK 21 o superior
- Maven 3.6+
- MySQL (XAMPP recomendado)
- Eclipse IDE 
- Postman (opcional, para probar la API)

---

## 🚀 Instalación y Configuración

### 1. Clonar el repositorio
```bash
git clone <url-del-repositorio>
cd GestorProfesores
```

### 2. Configurar la Base de Datos

La aplicación creará automáticamente la base de datos `gestor_profesores` si no existe.

También puedes crearla manualmente:
```sql
CREATE DATABASE gestor_profesores;
```

### 3. Configurar `application.properties`

El archivo está configurado para conectarse a MySQL local:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestor_profesores?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
```

Si tu configuración es diferente, modifica estos valores en `src/main/resources/application.properties`.

### 4. Ejecutar la aplicación

**Desde Eclipse:**
```
Click derecho en AlbavgDocentesApplication.java
→ Run As → Java Application
```

**Desde terminal:**
```bash
./mvnw spring-boot:run
```

La aplicación se iniciará en: `http://localhost:8080`

Las tablas de la base de datos se crearán automáticamente gracias a `spring.jpa.hibernate.ddl-auto=update`.

### 5. Cargar datos iniciales

**Ejecutar script SQL en phpMyAdmin:**

1. Asegúrate de que XAMPP está iniciado con MySQL activo
2. Abre phpMyAdmin (http://localhost/phpmyadmin)
3. Selecciona la base de datos `gestor_profesores`
4. Ve a la pestaña SQL
5. Copia y ejecuta el contenido de `src/main/resources/departamentos_docentes.sql`

Esto insertará:
- **8 departamentos**: IFC, ELE, FME, IMA, QUI, ADG, COM, EOC
- **15 docentes** distribuidos en los diferentes departamentos

### 6. Verificar funcionamiento

Abre Postman y prueba:
```
GET http://localhost:8080/docentes/ordenados
```

---

## 📁 Estructura del Proyecto

```
GestorProfesores/
│
├── src/
│   ├── main/
│   │   ├── java/com/albavg/gestiondocentes/
│   │   │   ├── controlador/          # Controladores REST
│   │   │   │   ├── DocenteController.java
│   │   │   │   ├── DepartamentoController.java
│   │   │   │   └── AsuntoPropioController.java
│   │   │   │
│   │   │   ├── servicio/              # Lógica de negocio
│   │   │   │   ├── DocenteService.java
│   │   │   │   ├── DepartamentoService.java
│   │   │   │   └── AsuntoPropioService.java
│   │   │   │
│   │   │   ├── repositorio/           # Acceso a datos
│   │   │   │   ├── DocenteRepository.java
│   │   │   │   ├── DepartamentoRepository.java
│   │   │   │   └── AsuntoPropioRepository.java
│   │   │   │
│   │   │   ├── modelo/                # Entidades JPA
│   │   │   │   ├── Docente.java
│   │   │   │   ├── Departamento.java
│   │   │   │   ├── AsuntoPropio.java
│   │   │   │   └── Rol.java
│   │   │   │
│   │   │   ├── dto/                   # Data Transfer Objects
│   │   │   │   ├── DocenteDTO.java
│   │   │   │   └── AsuntoPropioDetalleDTO.java
│   │   │   │
│   │   │   └── AlbavgDocentesApplication.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties       # Configuración
│   │       └── departamentos_docentes.sql  # Datos iniciales
│   │
│   └── test/                          # Tests unitarios
│
├── pom.xml                            # Dependencias Maven
└── README.md                          # Documentación
```

---

## 🗄️ Modelo de Datos

### Diagrama Entidad-Relación

```
┌─────────────┐         ┌──────────────┐
│ Departamento│◄────────│   Docente    │
│             │ 1     N │              │
│ - id        │         │ - id         │
│ - nombre    │         │ - nombre     │
│ - codigo    │         │ - apellidos  │
│ - telefono  │         │ - email      │
└─────────────┘         │ - siglas     │
                        └──────┬───────┘
       ┌────────────┐          │
       │    Rol     │◄─────────┘
       │            │ 1     N
       │ - id       │
       │ - nombre   │
       │ - orden    │
       └────────────┘

                        ┌──────────────┐
                        │   Docente    │
                        └──────┬───────┘
                               │ 1
                               │
                               │ N
                        ┌──────┴───────────┐
                        │  AsuntoPropio    │
                        │                  │
                        │ - id             │
                        │ - diaSolicitado  │
                        │ - fechaTramitacion│
                        │ - descripcion    │
                        │ - aprobado       │
                        └──────────────────┘
```

### Entidades Principales

#### Docente
- **id**: Identificador único (Long)
- **nombre**: Nombre del docente (String, obligatorio)
- **apellidos**: Apellidos del docente (String, obligatorio)
- **email**: Correo electrónico (String, único, obligatorio)
- **siglas**: Iniciales del docente (String, único, obligatorio)
- **departamento**: Relación ManyToOne con Departamento
- **rol**: Relación ManyToOne con Rol (opcional)

#### Departamento
- **id**: Identificador único (Long)
- **nombre**: Nombre del departamento (String, obligatorio)
- **codigo**: Código único del departamento (String, único, obligatorio)
- **telefono**: Teléfono de contacto (String)

#### AsuntoPropio
- **id**: Identificador único (Long)
- **diaSolicitado**: Fecha solicitada (LocalDate, obligatorio)
- **fechaTramitacion**: Fecha de aprobación/rechazo (LocalDate)
- **descripcion**: Motivo de la solicitud (String)
- **aprobado**: Estado de aprobación (Boolean, por defecto false)
- **docente**: Relación ManyToOne con Docente

#### Rol
- **id**: Identificador único (Long)
- **nombre**: Nombre del rol (String, obligatorio)
- **orden**: Prioridad del rol (Integer, obligatorio)

---

## 🌐 API REST - Endpoints

### Base URL
```
http://localhost:8080
```

### Docentes

#### 1. Obtener docente por ID
```http
GET /docentes/{id}
```
**Parámetros:**
- `id` (path): ID del docente

**Ejemplo:**
```
GET /docentes/1
```

**Respuesta (200 OK):**
```json
{
    "id": 1,
    "nombre": "Sergio",
    "apellidos": "Martínez López",
    "email": "sergioml@educastur.org",
    "siglas": "MLSe",
    "departamento": {
        "id": 1,
        "nombre": "Informática y Comunicaciones",
        "codigo": "IFC",
        "telefono": "984100101"
    },
    "rol": null
}
```

---

#### 2. Obtener docentes ordenados por apellidos
```http
GET /docentes/ordenados
```

**Respuesta (200 OK):**
```json
[
    {
        "id": 3,
        "nombre": "David",
        "apellidos": "Álvarez Rubio",
        "email": "davidar@educastur.org",
        "siglas": "ARDa",
        "departamento": {
            "id": 1,
            "nombre": "Informática y Comunicaciones",
            "codigo": "IFC",
            "telefono": "984100101"
        },
        "rol": null
    },
    {
        "id": 6,
        "nombre": "Javier",
        "apellidos": "Campos Rubio",
        "email": "javiercr@educastur.org",
        "siglas": "CRJa",
        "departamento": {
            "id": 3,
            "nombre": "Fabricación Mecánica",
            "codigo": "FME",
            "telefono": "984100103"
        },
        "rol": null
    }
]
```

---

#### 3. Obtener docentes por departamento
```http
GET /docentes/departamento/{nombre}
```

**Parámetros:**
- `nombre` (path): Nombre del departamento (se debe codificar en URL si contiene espacios o tildes)

**Ejemplo:**
```
GET /docentes/departamento/Qu%C3%ADmica
```

**Nota:** En Postman puedes escribir directamente "Química" y se codificará automáticamente.

**Respuesta (200 OK):**
```json
[
    {
        "id": 11,
        "nombre": "Noelia",
        "apellidos": "Lago Souto",
        "email": "noelials@educastur.org",
        "siglas": "LSNo",
        "departamento": {
            "id": 5,
            "nombre": "Química",
            "codigo": "QUI",
            "telefono": "984100105"
        },
        "rol": null
    },
    {
        "id": 10,
        "nombre": "Lucía",
        "apellidos": "Ortega Rivas",
        "email": "luciaor@educastur.org",
        "siglas": "ORLu",
        "departamento": {
            "id": 5,
            "nombre": "Química",
            "codigo": "QUI",
            "telefono": "984100105"
        },
        "rol": null
    },
    {
        "id": 9,
        "nombre": "Daniel",
        "apellidos": "Santos Vega",
        "email": "danielsv@educastur.org",
        "siglas": "SVDa",
        "departamento": {
            "id": 5,
            "nombre": "Química",
            "codigo": "QUI",
            "telefono": "984100105"
        },
        "rol": null
    }
]
```

---

### Departamentos

#### 4. Contar profesores por código de departamento
```http
GET /departamentos/contar/{codigo}
```

**Parámetros:**
- `codigo` (path): Código del departamento

**Ejemplo:**
```
GET /departamentos/contar/IFC
```

**Respuesta (200 OK):**
```json
3
```

---

### Asuntos Propios

#### 5. UC2 - Solicitar día propio
```http
POST /asuntospropios/solicitar?docenteId={id}&fecha={fecha}&descripcion={texto}
```

**Parámetros:**
- `docenteId` (query, obligatorio): ID del docente
- `fecha` (query, obligatorio): Fecha solicitada (formato: YYYY-MM-DD)
- `descripcion` (query, opcional): Motivo de la solicitud

**Ejemplo:**
```
POST /asuntospropios/solicitar?docenteId=1&fecha=2025-12-20&descripcion=Asunto personal
```

**Respuesta (200 OK):**
```json
true
```

**Validaciones:**
- El docente debe existir
- La fecha debe ser futura (>= hoy)
- No puede haber solicitudes duplicadas para la misma fecha

**Respuesta en caso de error:**
```json
false
```

---

#### 6. UC5 - Validar día propio
```http
PUT /asuntospropios/validar/{id}?aceptado={boolean}
```

**Parámetros:**
- `id` (path): ID del asunto propio
- `aceptado` (query): true para aprobar, false para rechazar

**Ejemplo:**
```
PUT /asuntospropios/validar/1?aceptado=true
```

**Respuesta (200 OK):**
```json
true
```

**Funcionalidad:**
- Actualiza el estado de aprobación
- Registra la fecha de tramitación (fecha actual)
- Devuelve true si se validó correctamente, false si no existe el asunto

---

#### 7. UC6 - Consultar días propios
```http
GET /asuntospropios/consultar/{docenteId}
```

**Parámetros:**
- `docenteId` (path): ID del docente

**Ejemplo:**
```
GET /asuntospropios/consultar/1
```

**Respuesta (200 OK):**
```json
[
    {
        "id": 1,
        "diaSolicitado": "2025-12-20",
        "fechaTramitacion": "2025-12-13",
        "descripcion": "Asunto personal",
        "aprobado": true,
        "docente": {
            "id": 1,
            "nombre": "Sergio",
            "apellidos": "Martínez López",
            "email": "sergioml@educastur.org",
            "siglas": "MLSe",
            "departamento": {
                "id": 1,
                "nombre": "Informática y Comunicaciones",
                "codigo": "IFC",
                "telefono": "984100101"
            },
            "rol": null
        }
    }
]
```

---

#### 8. Obtener días pendientes de disfrutar
```http
GET /asuntospropios/pendientes/{docenteId}
```

**Descripción:** Devuelve asuntos propios aprobados con fecha >= hoy

**Parámetros:**
- `docenteId` (path): ID del docente

**Ejemplo:**
```
GET /asuntospropios/pendientes/1
```

**Respuesta (200 OK):**
```json
[
    {
        "id": 1,
        "diaSolicitado": "2025-12-20",
        "fechaTramitacion": "2025-12-13",
        "descripcion": "Asunto personal",
        "aprobado": true,
        "docente": {
            "id": 1,
            "nombre": "Sergio",
            "apellidos": "Martínez López",
            "email": "sergioml@educastur.org",
            "siglas": "MLSe",
            "departamento": {...},
            "rol": null
        }
    }
]
```

---

#### 9. Obtener docente con más días disfrutados
```http
GET /asuntospropios/docente-mas-dias
```

**Descripción:** Devuelve el docente que más días propios ha disfrutado (aprobados con fecha < hoy)

**Respuesta (200 OK):**
```json
{
    "id": 1,
    "nombre": "Sergio",
    "apellidos": "Martínez López",
    "email": "sergioml@educastur.org",
    "siglas": "MLSe",
    "departamento": {
        "id": 1,
        "nombre": "Informática y Comunicaciones",
        "codigo": "IFC",
        "telefono": "984100101"
    },
    "rol": null
}
```

**Respuesta si no hay datos:**
```json
null
```

---

## 📝 Ejemplos de Uso

### Flujo completo: Solicitar y validar un día propio

**Prerequisito:** Tener al menos un docente y un departamento en la base de datos.

**1. Solicitar día propio**
```http
POST http://localhost:8080/asuntospropios/solicitar?docenteId=1&fecha=2026-02-15&descripcion=Trámites personales

Respuesta: true
```

**2. Consultar solicitudes del docente**
```http
GET http://localhost:8080/asuntospropios/consultar/1

Respuesta: [...asuntos propios incluyendo el nuevo]
```

**3. Validar la solicitud (usar el ID del asunto creado)**
```http
PUT http://localhost:8080/asuntospropios/validar/1?aceptado=true

Respuesta: true
```

**4. Verificar días pendientes**
```http
GET http://localhost:8080/asuntospropios/pendientes/1

Respuesta: [...incluye el día aprobado]
```

**5. Consultar docente con más días disfrutados**
```http
GET http://localhost:8080/asuntospropios/docente-mas-dias

Respuesta: {...información del docente}
```

---

## 👨‍💻 Autor

**Alba Raquel Velasco González**

- Proyecto: Gestión de Docentes
- Asignatura: Desarrollo Web en Entorno Servidor
- Fecha: Diciembre 2025

---

## 📄 Licencia

Este proyecto es de uso académico para la asignatura de Desarrollo Web en Entorno Servidor.

---
