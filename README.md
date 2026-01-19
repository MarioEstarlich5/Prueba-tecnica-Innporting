# Web App Flickr – Prueba Técnica Innporting
Aplicación completa desarrollada con Angular (frontend) y Spring Boot (backend), que permite buscar imágenes en Flickr, listarlas, ver su detalle y descargarlas. El frontend consume únicamente la API propia del backend, cumpliendo con la arquitectura solicitada.

------------------------------------------------------------
REQUISITOS PARA EJECUTAR LA APLICACIÓN
------------------------------------------------------------

Frontend:
- Node.js 18+
- Angular CLI 17+
- Navegador moderno (Chrome, Firefox, Edge)

Backend:
- Java 17+
- Maven 3.9+
- Conexión a Internet (para acceder a la API de Flickr)

Configuración necesaria:
El backend requiere configurar las credenciales de Flickr en application.properties:

flickr.api.key=aa80857dbe0f4136252b8177f378d669
flickr.api.secret=658fe2bb21dc1445
flickr.api.url=https://api.flickr.com/services/rest/

Puertos utilizados:
- Frontend: http://localhost:4200
- Backend: http://localhost:8080

El frontend debe tener acceso al backend para funcionar correctamente.

------------------------------------------------------------
TECNOLOGÍAS UTILIZADAS
------------------------------------------------------------

Frontend:
- Angular 17+
- SCSS
- HTML

Backend:
- Spring Boot 3+
- Java
- WebClient (HTTP Client)
- Arquitectura Hexagonal
- DTOs, Servicios, Controladores

Externo:
- Flickr API

------------------------------------------------------------
ESTRUCTURA GENERAL
------------------------------------------------------------

frontend/
backend/

Cada parte se ejecuta de forma independiente.

------------------------------------------------------------
FRONTEND (ANGULAR)
------------------------------------------------------------

Instalación:
cd frontend
npm install

Ejecutar:
ng serve

La aplicación estará disponible en:
http://localhost:4200

Funcionalidades implementadas:
- Buscador de imágenes por concepto
- Listado en formato grid
- Eliminación local de imágenes
- Vista de detalle con:
  - Imagen grande
  - Título
  - Autor
  - Descripción
  - Tags
  - Resolución
  - Botón de descarga
- Infinite Scroll (opcional implementado)
- Imagen estática inicial antes de buscar
- Diseño responsive con Angular Material

------------------------------------------------------------
BACKEND (SPRING BOOT)
------------------------------------------------------------

Instalación:
cd backend
./mvnw clean install

Ejecutar:
./mvnw spring-boot:run

El backend se ejecutará en:
http://localhost:8080

Arquitectura Backend:
- Controladores REST
- Servicios de dominio
- Repositorios (adaptadores a Flickr)
- DTOs para entrada/salida
- Cliente HTTP para Flickr
- Separación clara de responsabilidades
- Cumplimiento de principios SOLID
- Arquitectura Hexagonal:
  - domain (lógica)
  - application (casos de uso)
  - infrastructure (adaptadores)

------------------------------------------------------------
Cómo usar la aplicación
------------------------------------------------------------
1. Abre http://localhost:4200
2. Escribe un concepto en el buscador (ej: "mountains")
3. Explora el grid de imágenes
4. Haz clic en una imagen para ver su detalle
5. Usa el botón "Descargar" si deseas obtener la imagen
