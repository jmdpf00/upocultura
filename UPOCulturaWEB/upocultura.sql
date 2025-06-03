CREATE DATABASE upocultura;
USE upocultura;

-- Tabla de usuarios
CREATE TABLE usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  contrasena VARCHAR(255),
  tipo ENUM('asistente', 'organizador', 'voluntario'),
  fecha_registro DATE
);

-- Tabla de organizadores (perfil ampliado)
CREATE TABLE organizador (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_usuario INT,
  FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

-- Tabla de eventos culturales
CREATE TABLE evento (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_organizador INT,
  titulo VARCHAR(150),
  descripcion TEXT,
  ubicacion VARCHAR(150),
  fecha_inicio DATE,
  fecha_fin DATE,
  plazas INT,
  FOREIGN KEY (id_organizador) REFERENCES organizador(id)
);

-- Tabla de inscripciones a eventos
CREATE TABLE inscripcion (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_evento INT,
  id_usuario INT,
  fecha_inscripcion DATE,
  FOREIGN KEY (id_evento) REFERENCES evento(id),
  FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

-- Tabla de solicitudes de voluntariado
CREATE TABLE solicitud_voluntariado (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_evento INT,
  id_usuario INT,
  estado ENUM('pendiente', 'aprobada', 'rechazada'),
  fecha_solicitud DATE,
  FOREIGN KEY (id_evento) REFERENCES evento(id),
  FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

-- Tabla de tareas asignadas a voluntarios
CREATE TABLE tarea_voluntario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_solicitud INT,
  descripcion VARCHAR(255),
  hora_inicio TIME,
  hora_fin TIME,
  FOREIGN KEY (id_solicitud) REFERENCES solicitud_voluntariado(id)
);

-- Tabla de publicaciones
CREATE TABLE publicacion (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_usuario INT,
  titulo VARCHAR(150),
  contenido TEXT,
  fecha_publicacion DATE,
  FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

-- Tabla de comentarios en publicaciones
CREATE TABLE comentario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_publicacion INT,
  id_usuario INT,
  contenido TEXT,
  fecha_comentario DATE,
  FOREIGN KEY (id_publicacion) REFERENCES publicacion(id),
  FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

-- Tabla de notificaciones
CREATE TABLE notificacion (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_usuario INT,
  mensaje TEXT,
  leida BOOLEAN DEFAULT FALSE,
  fecha_envio DATETIME,
  FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);


-- DATOS DE PRUEBA

USE upocultura;

-- Usuarios
INSERT INTO usuario (nombre, email, contrasena, tipo, fecha_registro) VALUES
('Ana Pérez', 'ana.perez@example.com', 'passAna123', 'asistente', '2024-01-10'),
('Luis Gómez', 'luis.gomez@example.com', 'passLuis123', 'organizador', '2023-11-05'),
('Marta Ruiz', 'marta.ruiz@example.com', 'passMarta123', 'voluntario', '2024-02-20');

-- Organizadores
INSERT INTO organizador (id_usuario, nombre_organizacion, descripcion) VALUES
(2, 'Cultura Viva', 'Organización dedicada a la promoción de eventos culturales en la ciudad.');

-- Eventos
INSERT INTO evento (id_organizador, titulo, descripcion, ubicacion, fecha_inicio, fecha_fin, plazas) VALUES
(1, 'Festival de Música Latina', 'Evento anual con los mejores artistas latinos.', 'Auditorio Central', '2024-06-15', '2024-06-17', 200),
(1, 'Exposición de Arte Moderno', 'Exposición con artistas contemporáneos.', 'Galería de Arte Municipal', '2024-07-01', '2024-07-30', 100);

-- Inscripciones
INSERT INTO inscripcion (id_evento, id_usuario, fecha_inscripcion) VALUES
(1, 1, '2024-05-01'),
(2, 1, '2024-06-01');

-- Solicitudes de voluntariado
INSERT INTO solicitud_voluntariado (id_evento, id_usuario, estado, fecha_solicitud) VALUES
(1, 3, 'pendiente', '2024-05-05');

-- Tareas para voluntarios
INSERT INTO tarea_voluntario (id_solicitud, descripcion, hora_inicio, hora_fin) VALUES
(1, 'Ayuda en logística del escenario', '15:00:00', '20:00:00');

-- Publicaciones
INSERT INTO publicacion (id_usuario, titulo, contenido, fecha_publicacion) VALUES
(1, 'Gran festival de música', 'No te pierdas el mejor festival de música latina de este año.', '2024-05-10');

-- Comentarios
INSERT INTO comentario (id_publicacion, id_usuario, contenido, fecha_comentario) VALUES
(1, 3, '¡Estoy emocionada por asistir!', '2024-05-11');

-- Notificaciones
INSERT INTO notificacion (id_usuario, mensaje, leida, fecha_envio) VALUES
(3, 'Tu solicitud de voluntariado está pendiente de revisión.', FALSE, '2024-05-06 10:00:00');
