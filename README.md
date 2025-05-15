# API RESTful de Gestión de Usuarios

## 🚀Tecnologías utilizadas
- Java 17
- Spring Boot 3.x
- Spring Web / Spring Data JPA
- H2 Database (en memoria)
- JWT (jjwt 0.11.5)
- Maven

## 📦 Endpoints disponibles

| Método | Ruta                  | Descripción                        |
|--------|-----------------------|------------------------------------|
| POST   | `/api/usuarios`       | Crear un nuevo usuario             |
| GET    | `/api/usuarios`       | Listar todos los usuarios          |
| GET    | `/api/usuarios/{id}`  | Obtener usuario por ID             |
| PUT    | `/api/usuarios/{id}`  | ACtualizar un usuario              |
| PATCH  | `/api/usuarios/{id}`  | Actualizar parcialmente un usuario |
| DELETE | `/api/usuarios/{id}`  | Eliminar un usuario                |

> Todos los endpoints aceptan y responden en formato JSON.

## ▶️ Cómo ejecutar el proyecto
1. Clona el repositorio:

`git clone https://github.com/alex000sam/PruebaAPIRestfulJava02.git`

`cd PruebaAPIRestfulJava02`

2. Abre el proyecto en el IDE de tu preferencia (Ejm: IntelliJ IDEA)
3. Ejecuta la aplicación o inicia el modo `debug`. De acuerdo a la configuración del proyecto este ejecutará automáticamente los scripts sql ubicados en /src/main/resources/ (schema.sql y data.sql). 
4. La aplicación correrá en `http://localhost:8080`
5. Se puede probar desde Postman con el archivo `API RESTful Gestión Usuarios.postman_collection.json` ubicado en la raiz.

## ▶️ Swagger
`http://localhost:8080/swagger-ui/index.html`