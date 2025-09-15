-- Tablas auxiliares Habitación
CREATE TABLE "categorias" (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE NOT NULL,
    descripcion TEXT
);

CREATE TABLE "tipos" (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE NOT NULL,
    descripcion TEXT
);

CREATE TABLE "estados" (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE NOT NULL,
    descripcion TEXT
);

CREATE TABLE "habitaciones"(
    id SERIAL NOT NULL,
    accesible BOOLEAN NOT NULL,
    disponible BOOLEAN NOT NULL,
    categoria_id INTEGER NOT NULL,
    tipo_id INTEGER NOT NULL,
    estado_id INTEGER NOT NULL,
    precio INTEGER NOT NULL,
    num_habitacion INTEGER NOT NULL,
    observaciones VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE (num_habitacion)
);

CREATE TABLE "salas"(
    id SERIAL NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    estado_id INTEGER NOT NULL,
    capacidad INTEGER NOT NULL,
    precio BIGINT NOT NULL,
    equipamiento VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE "direcciones"(
    id SERIAL NOT NULL,
    pais VARCHAR(50) NOT NULL,
    departamento VARCHAR(50) NOT NULL,
    ciudad VARCHAR(50) NOT NULL,
    calle VARCHAR(50) NOT NULL,
    num_puerta VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE "personas"(
    id SERIAL NOT NULL,
    id_direccion INTEGER NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE "empresas"(
    id SERIAL NOT NULL,
    id_persona INTEGER NOT NULL,
    rut VARCHAR(12) NOT NULL,
    nom_fantasia VARCHAR(100) NOT NULL,
    razon_social VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (rut)
);

CREATE TABLE "roles"(
    id SERIAL NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE "usuarios" (
    id SERIAL PRIMARY KEY,
    id_persona INTEGER NOT NULL,
    ci VARCHAR(12) NOT NULL UNIQUE,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    id_rol INTEGER NOT NULL,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasenia TEXT NOT NULL
);

CREATE TABLE "huespedes"(
    id SERIAL NOT NULL,
    id_persona INTEGER NOT NULL,
    ci VARCHAR(8) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (ci)
);

CREATE TABLE "reservas"(
    id SERIAL NOT NULL,
    usuario_id INTEGER NOT NULL,
    huesped_id INTEGER,
    fecha_ingreso TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    fecha_salida TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    empresa_id INTEGER,
    PRIMARY KEY (id)
);

CREATE TABLE "res_habitaciones"(
    id SERIAL NOT NULL,
    reserva_id INTEGER NOT NULL,
    habitacion_id INTEGER,
    PRIMARY KEY (id)
);

CREATE TABLE "res_salas"(
    id SERIAL NOT NULL,
    reserva_id INTEGER NOT NULL,
    sala_id INTEGER NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE "ocupaciones"(
    id SERIAL NOT NULL,
    reserva_id INTEGER NOT NULL,
    habitacion_id INTEGER NOT NULL,
    huesped_id INTEGER NOT NULL,
    fecha_ingreso TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    fecha_fin TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    PRIMARY KEY (id)
);

-- === CLAVES FORÁNEAS ===

ALTER TABLE "habitaciones"
    ADD CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES "categorias"(id);
ALTER TABLE "habitaciones"
    ADD CONSTRAINT fk_tipo FOREIGN KEY (tipo_id) REFERENCES "tipos"(id);
ALTER TABLE "habitaciones"
    ADD CONSTRAINT fk_estado_habitacion FOREIGN KEY (estado_id) REFERENCES "estados"(id);

ALTER TABLE "salas"
    ADD CONSTRAINT fk_estado_sala FOREIGN KEY (estado_id) REFERENCES "estados"(id);

ALTER TABLE "personas"
    ADD CONSTRAINT personas_id_direccion_foreign FOREIGN KEY(id_direccion) REFERENCES "direcciones"(id);

ALTER TABLE "empresas"
    ADD CONSTRAINT empresas_id_persona_foreign FOREIGN KEY(id_persona) REFERENCES "personas"(id);

ALTER TABLE "usuarios"
    ADD CONSTRAINT usuarios_id_persona_foreign FOREIGN KEY(id_persona) REFERENCES "personas"(id);
ALTER TABLE "usuarios"
    ADD CONSTRAINT usuarios_id_rol_foreign FOREIGN KEY(id_rol) REFERENCES "roles"(id);
    
ALTER TABLE "huespedes"
    ADD CONSTRAINT huespedes_id_persona_foreign FOREIGN KEY(id_persona) REFERENCES "personas"(id);

ALTER TABLE "reservas"
    ADD CONSTRAINT reservas_usuario_id_foreign FOREIGN KEY(usuario_id) REFERENCES "usuarios"(id);
ALTER TABLE "reservas"
    ADD CONSTRAINT reservas_huesped_id_foreign FOREIGN KEY(huesped_id) REFERENCES "huespedes"(id);
ALTER TABLE "reservas"
    ADD CONSTRAINT reservas_empresa_id_foreign FOREIGN KEY(empresa_id) REFERENCES "empresas"(id);

ALTER TABLE "res_habitaciones"
    ADD CONSTRAINT res_habitaciones_reserva_id_foreign FOREIGN KEY(reserva_id) REFERENCES "reservas"(id);
ALTER TABLE "res_habitaciones"
    ADD CONSTRAINT res_habitaciones_habitacion_id_foreign FOREIGN KEY(habitacion_id) REFERENCES "habitaciones"(id);

ALTER TABLE "res_salas"
    ADD CONSTRAINT res_salas_reserva_id_foreign FOREIGN KEY(reserva_id) REFERENCES "reservas"(id);
ALTER TABLE "res_salas"
    ADD CONSTRAINT res_salas_sala_id_foreign FOREIGN KEY(sala_id) REFERENCES "salas"(id);

ALTER TABLE "ocupaciones"
    ADD CONSTRAINT ocupaciones_reserva_id_foreign FOREIGN KEY(reserva_id) REFERENCES "reservas"(id);
ALTER TABLE "ocupaciones"
    ADD CONSTRAINT ocupaciones_habitacion_id_foreign FOREIGN KEY(habitacion_id) REFERENCES "habitaciones"(id);
ALTER TABLE "ocupaciones"
    ADD CONSTRAINT ocupaciones_huesped_id_foreign FOREIGN KEY(huesped_id) REFERENCES "huespedes"(id);

ALTER TABLE huespedes ADD CONSTRAINT unique_ci UNIQUE (ci);
ALTER TABLE empresas ADD CONSTRAINT unique_rut UNIQUE (rut);