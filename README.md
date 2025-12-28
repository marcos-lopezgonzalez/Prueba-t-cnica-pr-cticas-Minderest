# Prueba técnica prácticas Minderest
## Resumen
Esta aplicación Java permite controlar productos de clientes. Facilita el listado de clientes y productos de una base de datos. Este programa permite "igualar" productos de distintos clientes, que, aunque puedan tener un nombre distinto, realmente son el mismo.
## Despliegue

1. Clonar el repositorio
```bash
    git clone https://github.com/marcos-lopezgonzalez/Prueba-t-cnica-pr-cticas-Minderest.git
```

2. Ejecutar el script SQL para crear la base de datos: `demo/src/main/java/com/marcos/pruebatecnica/config/crear_bbdd.sql`

3. Verificar que la configuración de la clase BBDD.java sea correcta

```java
    private static final String URL = "jdbc:mysql://<ip>:<puerto>/<nombre_bbdd>";
    private static final String USER = "usuario";
    private static final String PASSWORD = "password";
```