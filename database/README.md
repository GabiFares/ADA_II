# Base de Datos ADA Hotel

## Estructura de inicialización automática

Los archivos en la carpeta `init/` se ejecutan automáticamente cuando se inicializa la base de datos PostgreSQL en Docker.

### Orden de ejecución:

- `01-ddl.sql` - Creación de tablas y estructura de la base de datos
- `02-data.sql` - Datos de inicialización y usuarios de prueba

### Usuarios por defecto:

- **Usuario:** admin
- **Contraseña:** admin123
- **Rol:** Administrador

### Para reinicializar la base de datos:

```bash
# Detener y eliminar volúmenes
docker-compose down -v

# Reconstruir y ejecutar
docker-compose up --build
```

### Para consultar usuarios desde terminal:

```bash
docker exec ada-db psql -U postgres -d ada -c "SELECT id, ci, nombre, apellido, nombre_usuario FROM usuarios;"
```
