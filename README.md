# BarberBook
BarberBook es una aplicación web para la gestión de reservas en barberías. Permite a los clientes reservar citas en línea y facilita la administración de servicios, horarios y empleados.


Integrantes
Sergio Rudin De La O
Montserrat Smith Arroyo
Randall Corrales
Marycruz Vargas Castro
Funcionalidades principales
Registro e inicio de sesión
Reserva de citas
Gestión de servicios
Gestión de empleados
Consulta de horarios
Administración de reservas
Reportes y estadísticas
Tecnologías propuestas
HTML
CSS
JavaScript

Estrategia de ramas
main
develop
feature-usuarios
feature-reservas
feature-servicios
feature-empleados
Estado del proyecto

Fase de análisis y diseño.

Avance 2

Funcionalidades implementadas (Avance 2)

Actualmente el sistema permite:

- Registro de clientes.
- Inicio de sesión.
- CRUD de servicios.
- Consulta de servicios disponibles.
- Consulta de horarios disponibles.
- Registro de reservas.
- Confirmación de reserva.
- Persistencia de datos mediante MySQL y JPA.

Estado del avance

Actualmente el proyecto cuenta con la siguiente estructura:

- Arquitectura MVC.
- Capas Controller, Service, Repository y Domain.
- Integración con MySQL mediante Hibernate/JPA.
- Interfaz desarrollada con Thymeleaf y Bootstrap.
- CRUD funcional para la entidad Servicio.
- Flujo funcional de registro, inicio de sesión y reserva de citas.

Estructura del proyecto

```
src
├── controller
├── dao
├── domain
├── service
├── templates
└── resources

Ejecución

1. Clonar el repositorio.
2. Crear la base de datos `barberbook`.
3. Configurar el usuario y contraseña de MySQL en `application.properties`.
4. Ejecutar el proyecto desde NetBeans o mediante Maven.
5. Acceder desde el navegador a:

```
http://localhost:73