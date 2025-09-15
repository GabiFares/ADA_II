-- Insert estados (compartidos por habitaciones y salas)
INSERT INTO "estados" (nombre, descripcion) VALUES
('en_mantenimiento', 'Fuera de servicio por tareas de mantenimiento'),
('en_limpieza', 'Pendiente de limpieza antes de uso'),
('disponible', 'Disponible para ser reservada'),
('reservado', 'Asignada a una reserva futura'),
('ocupado', 'Actualmente en uso');

-- Insert categorías de habitación
INSERT INTO "categorias" (nombre, descripcion) VALUES
('economica', 'Habitación económica con servicios básicos'),
('estandar_a', 'Estándar tipo A con mejor ubicación o servicios'),
('estandar_b', 'Estándar tipo B con menos beneficios que tipo A'),
('superior', 'Habitación superior con comodidades adicionales y más espacio');

-- Insert tipos de habitación
INSERT INTO "tipos" (nombre, descripcion) VALUES
('single', 'Una sola cama para una persona'),
('doble', 'Dos camas individuales'),
('doble_matrimonial', 'Una cama matrimonial para dos personas'),
('triple', 'Tres camas individuales'),
('triple_matrimonial', 'Una cama matrimonial y una cama individual'),
('cuadruple', 'Cuatro camas individuales'),
('matrimonial_doble_simple', 'Una cama matrimonial y dos camas simples');

-- Insert direcciones
INSERT INTO "direcciones" (pais, departamento, ciudad, calle, num_puerta)
VALUES 
('Uruguay', 'Montevideo', 'Montevideo', 'Ejemplo', '123'),
('Uruguay', 'Montevideo', 'Montevideo', 'Principal', '100'),
('Uruguay', 'Montevideo', 'Montevideo', 'Secundaria', '200'),
('Uruguay', 'Montevideo', 'Montevideo', 'Terciaria', '300'),
('Uruguay', 'Montevideo', 'Montevideo', 'Admin', '400');

-- Insert personas (para usuarios y clientes)
INSERT INTO "personas" (id_direccion, telefono, email)
VALUES 
(1, '099111111', 'cliente1@example.com'),
(2, '099222222', 'lucas@hotel.com'),
(3, '099333333', 'juana@hotel.com'),
(4, '099444444', 'gabi@hotel.com'),
(5, '099555555', 'admin@hotel.com'),
(1, '099666666', 'cliente2@example.com'),
(1, '099777777', 'cliente3@example.com'),
(1, '099888888', 'cliente4@example.com'),
(1, '099999999', 'cliente5@example.com');

-- Insert rol
INSERT INTO "roles" (nombre, descripcion)
VALUES ('Recepcionista', 'Carga reservas y gestiona huéspedes');

-- Insert usuarios con contraseñas BCrypt correctas
INSERT INTO "usuarios" (id_persona, ci, nombre, apellido, id_rol, nombre_usuario, contrasenia)
VALUES 
(2, '11111111', 'Lucas', 'García', 1, 'lucas', '$2a$10$IfVjuT/YDNgyIqbWQvFWLete0NOnJkJ7LlALDz.8D7U4MI8hMM.9a'),
(3, '22222222', 'Juana', 'Rodríguez', 1, 'juana', '$2a$10$Ip2uyp7Jpwx9rBgALGOFCO9.e9cqx2oBtGk982lOhGOdfLT8hvv5m'),
(4, '33333333', 'Gabi', 'Fernández', 1, 'gabi', '$2a$10$sSGOLsCvBclGkyvAvVr5JehFRLYPOWWf8CZs4YrT/yXNBxnLrn5gi'),
(5, '44444444', 'Admin', 'Sistema', 1, 'admin', '$2a$10$/JshPo4R/QStlA3jKo2.0eLr6CDqRYOvTGGJrWJUtAkR7RcPF6z32');

-- Insert clientes/huéspedes
INSERT INTO "huespedes" (id_persona, ci, nombre, apellido)
VALUES 
(1, '45678901', 'Ana', 'Martínez'),
(6, '56789012', 'Luis', 'Fernández'),
(7, '67890123', 'María', 'González'),
(8, '78901234', 'Carlos', 'López'),
(9, '89012345', 'Laura', 'Sánchez');

-- Insert habitaciones
INSERT INTO "habitaciones" (
    accesible, disponible, categoria_id, tipo_id, estado_id, precio, num_habitacion, observaciones
)
VALUES
(true, true, 4, 2, 3, 150, 101, 'Vista al mar'),
(false, true, 2, 1, 3, 100, 102, NULL),
(true, false, 1, 2, 1, 200, 103, 'Jacuzzi incluido'),

-- Piso 2 (habitaciones 201-210)
(true, true, 4, 3, 3, 180, 201, 'Vista panorámica al mar'),
(false, true, 2, 1, 3, 120, 202, 'Esquina con ventana grande'),
(true, true, 1, 2, 3, 140, 203, 'Vista al jardín'),
(true, false, 3, 4, 5, 200, 204, 'Reservada para evento'),
(false, true, 4, 2, 3, 170, 205, 'Balcón privado'),
(true, true, 2, 5, 3, 160, 206, 'Vista al centro'),
(false, false, 1, 1, 1, 110, 207, 'En mantenimiento - aire'),
(true, true, 3, 6, 3, 190, 208, 'Familiar amplia'),
(true, true, 4, 3, 3, 175, 209, 'Suite con jacuzzi'),
(false, true, 2, 2, 3, 130, 210, 'Vista lateral'),

-- Piso 3 (habitaciones 301-310)
(true, true, 4, 7, 3, 220, 301, 'Suite presidencial'),
(false, true, 3, 1, 3, 115, 302, 'Compacta pero cómoda'),
(true, false, 1, 4, 5, 180, 303, 'Grupo familiar'),
(true, true, 2, 3, 3, 155, 304, 'Romántica'),
(false, true, 4, 2, 3, 185, 305, 'Vista premium'),
(true, true, 1, 6, 3, 165, 306, 'Ideal para familias'),
(false, false, 3, 2, 2, 125, 307, 'Pendiente limpieza'),
(true, true, 2, 5, 3, 150, 308, 'Terraza privada'),
(true, true, 4, 1, 3, 160, 309, 'Single de lujo'),
(false, true, 1, 3, 3, 135, 310, 'Económica matrimonial'),

-- Piso 4 (habitaciones 401-410)
(true, true, 4, 7, 3, 250, 401, 'Suite ejecutiva'),
(false, true, 2, 4, 3, 145, 402, 'Triple estándar'),
(true, true, 3, 2, 3, 140, 403, 'Doble confort'),
(true, false, 1, 6, 5, 175, 404, 'Familiar en uso'),
(false, true, 4, 3, 3, 195, 405, 'Matrimonial premium'),
(true, true, 2, 1, 3, 125, 406, 'Single ejecutivo'),
(true, false, 3, 5, 5, 170, 407, 'Grupo de trabajo'),
(false, true, 1, 2, 3, 120, 408, 'Doble básica'),
(true, true, 4, 6, 3, 210, 409, 'Cuádruple de lujo'),
(false, true, 2, 3, 3, 160, 410, 'Matrimonial estándar'),

-- Piso 5 (habitaciones 501-510)
(true, true, 4, 7, 3, 280, 501, 'Suite penthouse'),
(false, true, 3, 4, 3, 155, 502, 'Triple ejecutiva'),
(true, true, 1, 1, 3, 110, 503, 'Single económica'),
(true, false, 2, 2, 5, 150, 504, 'Doble en uso'),
(false, true, 4, 3, 3, 205, 505, 'Matrimonial suite'),
(true, true, 3, 6, 3, 180, 506, 'Cuádruple familiar'),
(false, false, 1, 5, 1, 130, 507, 'Mantenimiento plomería'),
(true, true, 2, 2, 3, 145, 508, 'Doble vista mar'),
(true, true, 4, 1, 3, 175, 509, 'Single premium'),
(false, true, 1, 3, 3, 125, 510, 'Matrimonial básica');

-- Insert salas con nombres (7 salas en total)
INSERT INTO "salas" (nombre, estado_id, capacidad, precio, equipamiento)
VALUES
('Sala de Conferencias A', 3, 20, 300, 'Proyector, aire acondicionado'),
('Salón de Eventos Principal', 3, 50, 500, 'Proyector, sonido, aire acondicionado, escenario'),
('Sala de Capacitación', 3, 30, 400, 'Pizarra, proyector, aire acondicionado'),
('Salón de Fiestas', 3, 100, 800, 'Sonido profesional, iluminación, escenario, bar'),
('Sala de Reuniones Ejecutiva', 3, 15, 250, 'TV 65", aire acondicionado, sofás'),
('Salón de Catering', 3, 80, 600, 'Cocina, bar, aire acondicionado, terraza'),
('Sala de Entrenamiento', 3, 25, 350, 'Pizarra digital, aire acondicionado');

-- Insert reservas
INSERT INTO "reservas" (usuario_id, huesped_id, fecha_ingreso, fecha_salida)
VALUES 
(1, 1, '2025-05-20 14:00:00', '2025-05-22 11:00:00'),
(2, 2, '2025-05-25 15:00:00', '2025-05-27 12:00:00');

-- Asignar habitaciones a las reservas
INSERT INTO "res_habitaciones" (reserva_id, habitacion_id)
VALUES 
(1, 1),
(1, 2),
(2, 3),
(2, 4);

-- Asignar salas a las reservas
INSERT INTO "res_salas" (reserva_id, sala_id)
VALUES 
(1, 1),
(2, 2);

-- Insert ocupaciones
INSERT INTO "ocupaciones" (reserva_id, habitacion_id, huesped_id, fecha_ingreso, fecha_fin)
VALUES
(1, 1, 1, '2025-05-20 14:00:00', '2025-05-22 11:00:00'),
(1, 2, 1, '2025-05-20 14:00:00', '2025-05-22 11:00:00'),
(2, 3, 2, '2025-05-25 15:00:00', '2025-05-27 12:00:00'),
(2, 4, 2, '2025-05-25 15:00:00', '2025-05-27 12:00:00');