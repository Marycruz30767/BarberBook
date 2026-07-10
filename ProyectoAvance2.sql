CREATE DATABASE barberbook;
USE barberbook;

CREATE TABLE rol (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL
);

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    contrasena VARCHAR(100) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    id_rol INT NOT NULL,
    FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);

CREATE TABLE empleado (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    especialidad VARCHAR(100),
    activo BOOLEAN DEFAULT TRUE,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE servicio (
    id_servicio INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    descripcion VARCHAR(255),
    duracion INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE horario (
    id_horario INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    disponible BOOLEAN DEFAULT TRUE,
    id_empleado INT NOT NULL,
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado)
);

CREATE TABLE reserva (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    estado VARCHAR(30) NOT NULL,
    id_usuario INT NOT NULL,
    id_empleado INT NOT NULL,
    id_servicio INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_servicio) REFERENCES servicio(id_servicio)
);

CREATE TABLE pago (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    monto DECIMAL(10,2) NOT NULL,
    metodo_pago VARCHAR(50),
    estado VARCHAR(30),
    id_reserva INT NOT NULL,
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva)
);

CREATE TABLE bitacora_reserva (
    id_bitacora INT AUTO_INCREMENT PRIMARY KEY,
    accion VARCHAR(100) NOT NULL,
    fecha_accion DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_reserva INT NOT NULL,
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva)
);


INSERT INTO rol (nombre) VALUES
('ADMIN'),
('CLIENTE'),
('BARBERO');

INSERT INTO usuario (nombre, apellido, correo, telefono, contrasena, id_rol) VALUES
('Admin', 'Principal', 'admin@barberbook.com', '88888888', '123', 1),
('Sergio', 'Rudin', 'cliente@barberbook.com', '88887777', '123', 2),
('Carlos', 'Barbero', 'barbero@barberbook.com', '88886666', '123', 3);

INSERT INTO empleado (especialidad, activo, id_usuario) VALUES
('Cortes clásicos y barba', TRUE, 3);

INSERT INTO servicio (nombre, descripcion, duracion, precio, activo) VALUES
('Corte básico', 'Corte de cabello tradicional', 30, 3500, TRUE),
('Corte y barba', 'Corte de cabello más arreglo de barba', 45, 5000, TRUE),
('Barba', 'Perfilado y arreglo de barba', 25, 2500, TRUE);

INSERT INTO horario (fecha, hora, disponible, id_empleado) VALUES
('2026-07-10', '09:00:00', TRUE, 1),
('2026-07-10', '10:00:00', TRUE, 1),
('2026-07-10', '11:00:00', TRUE, 1);

