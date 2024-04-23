DROP DATABASE IF EXISTS biblioteca2;
CREATE DATABASE biblioteca2;
USE biblioteca2;

CREATE TABLE IF NOT EXISTS Persona (
	dni VARCHAR(20) PRIMARY KEY NOT NULL,
	nombre VARCHAR(100),
	apellidos VARCHAR(100),
	direccion VARCHAR(255),
	email VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Documento (
    id_documento INT PRIMARY KEY AUTO_INCREMENT,
    cod VARCHAR(255),
	autor VARCHAR(255),
	titulo VARCHAR(255),
	disponible BOOLEAN,
	anho_publicacion DATE,
	num_paginas INT,
	tipo VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Prestamo (
    id_prestamo INT AUTO_INCREMENT PRIMARY KEY,
    fecha_prestamo DATETIME,
    fecha_devolucion DATETIME,
    id_documento INT,
    dni_usuario VARCHAR(20),
    dias_prestamo INT,
    FOREIGN KEY (id_documento) REFERENCES Documento(id_documento),
    FOREIGN KEY (dni_usuario) REFERENCES Persona(dni)
);

CREATE TABLE IF NOT EXISTS Bibliotecario (
    id_bibliotecario INT AUTO_INCREMENT PRIMARY KEY,
    nss VARCHAR(20),
    dni VARCHAR(20),
    id_biblioteca INT,
    FOREIGN KEY (dni) REFERENCES Persona(dni)
);

CREATE TABLE IF NOT EXISTS Usuario(
	id_socio BIGINT AUTO_INCREMENT PRIMARY KEY,
    limite_prestamos INT,
    dni_persona VARCHAR(20),
    tipo_usuario VARCHAR(50),
    FOREIGN KEY (dni_persona) REFERENCES Persona(dni)
);

CREATE TABLE IF NOT EXISTS Biblioteca (
    id_biblioteca INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    dni_bibliotecario VARCHAR (255),
    FOREIGN KEY (dni_bibliotecario) REFERENCES Persona(dni)
);

CREATE TABLE IF NOT EXISTS PrestamoBiblioteca (
    id_prestamo INT,
    dni_usuario VARCHAR(20),
    id_biblioteca INT,
    PRIMARY KEY (id_prestamo, dni_usuario, id_biblioteca),
    FOREIGN KEY (id_prestamo) REFERENCES Prestamo(id_prestamo),
    FOREIGN KEY (dni_usuario) REFERENCES Persona(dni),
    FOREIGN KEY (id_biblioteca) REFERENCES Biblioteca(id_biblioteca)
);

CREATE TABLE IF NOT EXISTS TipoUsuario (
	id_tipo INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS TipoDocumento (
	id_tipo INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(20)
);