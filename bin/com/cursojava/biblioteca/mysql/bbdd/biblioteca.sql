DROP DATABASE IF EXISTS biblioteca;
CREATE DATABASE biblioteca;
USE biblioteca;

-- Crear tabla de libros
CREATE TABLE documentos (
    id_documento INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    año_publicacion INT,
    tipo_documento ENUM('libro', 'revista'),
    disponible BOOLEAN DEFAULT TRUE
);
-- Crear tabla de usuarios
CREATE TABLE usuarios (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    email VARCHAR(100),
    tipo_usuario ENUM('socio', 'ocasional')
);

-- Crear tabla de préstamos
CREATE TABLE prestamos (
    id_prestamo INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    id_documento INT,
    FOREIGN KEY (id_documento) REFERENCES documentos(id_documento),
    fecha_prestamo DATE,
    fecha_devolucion DATE,
    devuelto BOOLEAN DEFAULT FALSE
);