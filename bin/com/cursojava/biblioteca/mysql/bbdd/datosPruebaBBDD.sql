-- biblioteca
INSERT INTO biblioteca2.biblioteca (nombre) VALUES ('Biblioteca Viewnext.');

-- bibliotecario
INSERT INTO biblioteca2.persona (dni, nombre, apellidos, direccion, email) VALUES ("000002340T", "Pedro", "Rosa", "dir", "email");
INSERT INTO biblioteca2.bibliotecario (nss, dni, id_biblioteca) VALUES ("w6q5e4fqw56e4f", (SELECT dni FROM persona WHERE nombre = 'Pedro'), (SELECT id_biblioteca FROM biblioteca2.biblioteca));

-- Tipos de usuarios
INSERT INTO biblioteca2.tipousuario (tipo) VALUES ("OCASIONAL");
INSERT INTO biblioteca2.tipousuario (tipo) VALUES ("SOCIO");

-- Usuario ocasional
INSERT INTO biblioteca2.persona (dni, nombre, apellidos, direccion, email) VALUES ("00000000T", "Rosa", "Garcia", "", "");
INSERT INTO biblioteca2.usuario (dni_persona, limite_prestamos, tipo_usuario) VALUES ("00000000T", 2, (SELECT tipo FROM biblioteca2.tipousuario WHERE id_tipo = 1));

-- Usuario socio
INSERT INTO biblioteca2.persona (dni, nombre, apellidos, direccion, email) VALUES ("44485125S", "Pepe", "Dominguez", "", "");
INSERT INTO biblioteca2.usuario (dni_persona, limite_prestamos, tipo_usuario) VALUES ("44485125S", 20, (SELECT tipo FROM biblioteca2.tipousuario WHERE id_tipo = 2));

-- Tipos documentos
INSERT INTO biblioteca2.tipodocumento (tipo) VALUES ("LIBRO");
INSERT INTO biblioteca2.tipodocumento (tipo) VALUES ("REVISTA");

-- Revista
INSERT INTO biblioteca2.documento (autor, titulo, disponible, anho_publicacion, num_paginas, tipo) VALUES ("autor1", "libro1", true, null, 2, (SELECT tipo FROM biblioteca2.tipodocumento WHERE id_tipo = 2));

-- Libro
INSERT INTO biblioteca2.documento (autor, titulo, disponible, anho_publicacion, num_paginas, tipo) VALUES ("autor2", "revista1", true, null, 20, (SELECT tipo FROM biblioteca2.tipodocumento WHERE id_tipo = 1));