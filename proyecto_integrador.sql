-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-05-2025 a las 17:41:31
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--  
CREATE DATABASE IF NOT EXISTS proyecto_integrador;
USE `proyecto_integrador`;
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ayuda`
--

CREATE TABLE `ayuda` (
  `titulo` varchar(100) NOT NULL,
  `contenido` varchar(999) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ayuda`
--

INSERT INTO `ayuda` (`titulo`, `contenido`) VALUES
('Olvido de contraseña', 'Mucha informacion util para resolver dudas'),
('Registro', 'Mucha informacion util para resolver dudas'),
('Reportar incidencia', 'Mucha informacion util para resolver dudas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoritos`
--

CREATE TABLE `favoritos` (
  `incidencias_id_incidencia` int(11) NOT NULL,
  `usuario_correo` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `favoritos`
--

INSERT INTO `favoritos` (`incidencias_id_incidencia`, `usuario_correo`) VALUES
(1, 'user1@gmail.com'),
(2, 'user2@gmail.com'),
(3, 'user3@gmail.com'),
(4, 'user4@gmail.com'),
(5, 'user5@gmail.com'),
(6, 'user6@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `incidencias`
--

DROP TABLE IF EXISTS incidencias;

CREATE TABLE incidencias (
  id_incidencia INT NOT NULL AUTO_INCREMENT,
  estado VARCHAR(100) NOT NULL,
  edificio VARCHAR(100) DEFAULT NULL,
  foto LONGBLOB DEFAULT NULL,
  piso VARCHAR(10) DEFAULT NULL,
  descripcion VARCHAR(500) NOT NULL,
  aula VARCHAR(10) DEFAULT NULL,
  justificacion VARCHAR(500) DEFAULT NULL,
  fecha DATETIME NOT NULL,
  campus VARCHAR(100) NOT NULL,
  ranking INT NOT NULL,
  resuelto INT NOT NULL,
  correo VARCHAR(200) NOT NULL,
  usuario_correo VARCHAR(200) DEFAULT NULL,
  PRIMARY KEY (id_incidencia)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


--
-- Volcado de datos para la tabla `incidencias`
--

INSERT INTO `incidencias` (`id_incidencia`, `estado`, `edificio`, `foto`, `piso`, `descripcion`, `aula`, `justificacion`, `fecha`, `campus`, `ranking`, `resuelto`, `correo`, `usuario_correo`) VALUES
(1, 'Abierto', 'Edificio A', NULL, '1', 'Descripción 1', 'A1', 'Justificación 1', '2025-05-09 16:04:43', 'Campus1', 5, 0, 'user1@gmail.com', 'user1@gmail.com'),
(2, 'Cerrado', 'Edificio B', NULL, '2', 'Descripción 2', 'B1', 'Justificación 2', '2025-05-09 16:04:43', 'Campus1', 4, 1, 'user2@gmail.com', 'user2@gmail.com'),
(3, 'En Proceso', 'Edificio C', NULL, '3', 'Descripción 3', 'C1', 'Justificación 3', '2025-05-09 16:04:43', 'Campus2', 3, 0, 'user3@gmail.com', 'user3@gmail.com'),
(4, 'Abierto', 'Edificio A', NULL, '4', 'Descripción 4', 'A1', 'Justificación 4', '2025-05-09 16:04:43', 'Campus1', 6, 0, 'user4@gmail.com', 'user4@gmail.com'),
(5, 'Cerrado', 'Edificio B', NULL, '5', 'Descripción 5', 'B1', 'Justificación 5', '2025-05-09 16:04:43', 'Campus1', 1, 1, 'user5@gmail.com', 'user5@gmail.com'),
(6, 'En Proceso', 'Edificio C', NULL, '6', 'Descripción 6', 'C1', 'Justificación 6', '2025-05-09 16:04:43', 'Campus2', 2, 0, 'user6@gmail.com', 'user6@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notificar`
--

CREATE TABLE `notificar` (
  `incidencias_id_incidencia` int(11) NOT NULL,
  `usuario_correo` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `notificar`
--

INSERT INTO `notificar` (`incidencias_id_incidencia`, `usuario_correo`) VALUES
(1, 'user1@gmail.com'),
(2, 'user2@gmail.com'),
(3, 'user3@gmail.com'),
(4, 'user4@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas_seguridad`
--

CREATE TABLE `preguntas_seguridad` (
  `id_pregunta` int(11) NOT NULL,
  `texto_seguridad` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `preguntas_seguridad`
--

INSERT INTO `preguntas_seguridad` (`id_pregunta`, `texto_seguridad`) VALUES
(1, '¿Cuál es tu mascota favorita?'),
(2, '¿Cuál es tu color favorito?'),
(3, '¿Cuál es tu comida favorita?'),
(4, '¿Cuál es tu marca de coches favorita?'),
(5, '¿Cuál es tu deporte favorito?'),
(6, '¿Cuál es tu equipo de futbol favorito?');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rellenar`
--

CREATE TABLE `rellenar` (
  `usuario_correo` varchar(200) NOT NULL,
  `seguridad_id_pregunta` int(11) NOT NULL,
  `respuesta` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rellenar`
--

INSERT INTO `rellenar` (`usuario_correo`, `seguridad_id_pregunta`, `respuesta`) VALUES
('user1@gmail.com', 1, 'Perro'),
('user2@gmail.com', 2, 'Azul'),
('user3@gmail.com', 3, 'Pizza'),
('user4@gmail.com', 4, 'Audi'),
('user5@gmail.com', 5, 'Boxeo'),
('user6@gmail.com', 6, 'Deportivo la Coruña');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `USR` varchar(100) NOT NULL,
  `PWD` varchar(100) DEFAULT NULL,
  `ROL` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--
  INSERT INTO `users` (`USR`, `PWD`, `ROL`) VALUES
  ('adminNuevo@gmail.com', SHA2('1234', 256), 'Admin'),
  ('user10@gmail.com', SHA2('contrasena10', 256), 'Usuario'),
  ('user1@gmail.com', SHA2('contrasena1', 256), 'Admin'),
  ('user2@gmail.com', SHA2('contrasena2', 256), 'Usuario'),
  ('user3@gmail.com', SHA2('contrasena3', 256), 'Admin'),
  ('user4@gmail.com', SHA2('contrasena4', 256), 'Usuario'),
  ('userNuevo@gmail.com', SHA2('1234', 256), 'Usuario'),
  ('usrXD@gmail.com', SHA2('1234', 256), 'Usuario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `correo` varchar(200) NOT NULL,
  `nickname` varchar(100) NOT NULL,
  `admin` char(1) NOT NULL,
  `campus` varchar(100) DEFAULT NULL,
  `contraseña` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`correo`, `nickname`, `admin`, `campus`, `contraseña`) VALUES
('user10@gmail.com', 'User10', 'N', 'Campus3', 'contrasena10'),
('user1@gmail.com', 'User1', 'N', 'Campus1', 'contrasena1'),
('user2@gmail.com', 'User2', 'N', 'Campus1', 'contrasena2'),
('user3@gmail.com', 'User3', 'N', 'Campus2', 'contrasena3'),
('user4@gmail.com', 'User4', 'N', 'Campus2', 'contrasena4'),
('user5@gmail.com', 'User5', 'N', 'Campus3', 'contrasena5'),
('user6@gmail.com', 'User6', 'N', 'Campus1', 'contrasena6'),
('user7@gmail.com', 'User7', 'N', 'Campus1', 'contrasena7'),
('user8@gmail.com', 'User8', 'N', 'Campus2', 'contrasena8'),
('user9@gmail.com', 'User9', 'N', 'Campus2', 'contrasena9');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ayuda`
--
ALTER TABLE `ayuda`
  ADD PRIMARY KEY (`titulo`);

--
-- Indices de la tabla `favoritos`
--
ALTER TABLE `favoritos`
  ADD PRIMARY KEY (`incidencias_id_incidencia`,`usuario_correo`),
  ADD KEY `favoritos_usuario_fk` (`usuario_correo`);

--
-- Indices de la tabla `incidencias`
--

--
-- Indices de la tabla `notificar`
--
ALTER TABLE `notificar`
  ADD PRIMARY KEY (`incidencias_id_incidencia`,`usuario_correo`),
  ADD KEY `notificar_usuario_fk` (`usuario_correo`);

--
-- Indices de la tabla `preguntas_seguridad`
--
ALTER TABLE `preguntas_seguridad`
  ADD PRIMARY KEY (`id_pregunta`);

--
-- Indices de la tabla `rellenar`
--
ALTER TABLE `rellenar`
  ADD PRIMARY KEY (`usuario_correo`,`seguridad_id_pregunta`),
  ADD KEY `rellenar_preguntas_seguridad_fk` (`seguridad_id_pregunta`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`USR`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`correo`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `favoritos`
--
ALTER TABLE `favoritos`
  ADD CONSTRAINT `favoritos_incidencias_fk` FOREIGN KEY (`incidencias_id_incidencia`) REFERENCES `incidencias` (`id_incidencia`),
  ADD CONSTRAINT `favoritos_usuario_fk` FOREIGN KEY (`usuario_correo`) REFERENCES `usuario` (`correo`);

--
-- Filtros para la tabla `incidencias`
--
ALTER TABLE `incidencias`
  ADD CONSTRAINT `incidencias_usuario_fk` FOREIGN KEY (`usuario_correo`) REFERENCES `usuario` (`correo`);

--
-- Filtros para la tabla `notificar`
--
ALTER TABLE `notificar`
  ADD CONSTRAINT `notificar_incidencias_fk` FOREIGN KEY (`incidencias_id_incidencia`) REFERENCES `incidencias` (`id_incidencia`),
  ADD CONSTRAINT `notificar_usuario_fk` FOREIGN KEY (`usuario_correo`) REFERENCES `usuario` (`correo`);

--
-- Filtros para la tabla `rellenar`
--
ALTER TABLE `rellenar`
  ADD CONSTRAINT `rellenar_preguntas_seguridad_fk` FOREIGN KEY (`seguridad_id_pregunta`) REFERENCES `preguntas_seguridad` (`id_pregunta`),
  ADD CONSTRAINT `rellenar_usuario_fk` FOREIGN KEY (`usuario_correo`) REFERENCES `usuario` (`correo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
