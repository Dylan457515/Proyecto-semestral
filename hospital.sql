-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-04-2024 a las 18:13:19
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hospital`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citas_medicas`
--

CREATE TABLE `citas_medicas` (
  `ID_Citas` int(100) NOT NULL,
  `ID_Paciente` int(100) NOT NULL,
  `ID_Doctor` int(50) NOT NULL,
  `Fecha_programada` date DEFAULT NULL,
  `Hora_programada` time DEFAULT NULL,
  `Fecha_registro` date DEFAULT NULL,
  `Num_orden` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `citas_medicas`
--

INSERT INTO `citas_medicas` (`ID_Citas`, `ID_Paciente`, `ID_Doctor`, `Fecha_programada`, `Hora_programada`, `Fecha_registro`, `Num_orden`) VALUES
(1, 20, 2, '2023-11-24', '21:31:00', '2023-11-14', 0),
(2, 1, 2, '2023-11-18', '17:18:00', '2023-11-16', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `doctores`
--

CREATE TABLE `doctores` (
  `ID_Doctor` int(50) NOT NULL,
  `ID_usuario` int(50) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Ap_Paterno` varchar(20) DEFAULT NULL,
  `Ap_Materno` varchar(20) DEFAULT NULL,
  `Direccion` varchar(30) DEFAULT NULL,
  `Espeialidad` int(20) DEFAULT NULL,
  `Telefono` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `doctores`
--

INSERT INTO `doctores` (`ID_Doctor`, `ID_usuario`, `Nombre`, `Ap_Paterno`, `Ap_Materno`, `Direccion`, `Espeialidad`, `Telefono`) VALUES
(2, 0, 'adam', 'quispe', 'castilo', 'zaef', 6, 65464),
(3, 0, 'Marcos', 'ramos', 'quisbert', 'Av.sub teniente Burgos', 6, 702342123),
(5, 0, 'Juan', 'quispe', 'castilo', 'Av.sub teniente Burgos', 6, 65464);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `formulario`
--

CREATE TABLE `formulario` (
  `ID_formulario` int(100) NOT NULL,
  `ID_Paciente` int(100) NOT NULL,
  `ID_Doctor` int(50) NOT NULL,
  `Nombre` varchar(20) DEFAULT NULL,
  `sexo` varchar(10) NOT NULL,
  `Direccion` varchar(50) DEFAULT NULL,
  `Telefono` int(10) DEFAULT NULL,
  `habitacion` int(11) DEFAULT NULL,
  `Tipo_habitacion` int(10) DEFAULT NULL,
  `Fecha_ingreso` date DEFAULT NULL,
  `Hora_ingreso` time DEFAULT NULL,
  `Motivo` text NOT NULL,
  `Diagnostico` text NOT NULL,
  `Tratamiento` text NOT NULL,
  `Notas` text DEFAULT NULL,
  `Fecha_alta` date NOT NULL,
  `Total_pagar` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `formulario`
--

INSERT INTO `formulario` (`ID_formulario`, `ID_Paciente`, `ID_Doctor`, `Nombre`, `sexo`, `Direccion`, `Telefono`, `habitacion`, `Tipo_habitacion`, `Fecha_ingreso`, `Hora_ingreso`, `Motivo`, `Diagnostico`, `Tratamiento`, `Notas`, `Fecha_alta`, `Total_pagar`) VALUES
(13, 21, 3, 'Marcos', 'masculino', 'argentina', 324245, NULL, 1, '2023-11-15', '23:44:30', 'miedo', 'decanso', 'decanso profundo', 'ayuda', '2023-12-01', 657),
(14, 20, 3, 'Marcos', 'masculino', 'argentina', 324245, 432, 1, '2023-11-15', '23:45:45', 'miedo', 'decanso', 'decanso profundo', 'ayuda', '2023-12-01', 657),
(15, 21, 5, 'Juan', 'masculino', 'argentina', 324245, 13, 1, '2023-11-16', '01:48:03', 'desmayo', 'falta de  desauono ', 'comer mas', 'sin observasiones', '2023-11-08', 4353),
(16, 21, 5, 'Juan', 'masculino', 'bolivia', 864531, 43, 1, '2023-11-16', '12:03:28', 'frio', 'fiebre', 'dormir', '', '2023-11-21', 12),
(17, 24, 5, 'Juan', 'masculino', 'jhc', 8645, 45, 2, '2023-11-16', '12:11:09', 'kgfc', 'jhvc', 'ertyk', 'ygkbjln', '2023-10-31', 465);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitacion_tipo`
--

CREATE TABLE `habitacion_tipo` (
  `Nro_habitacion` int(10) NOT NULL,
  `Tipo_de_habitacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `habitacion_tipo`
--

INSERT INTO `habitacion_tipo` (`Nro_habitacion`, `Tipo_de_habitacion`) VALUES
(1, 'Economica'),
(2, 'No Economica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `ID_Paciente` int(100) NOT NULL,
  `ID_usuario` int(50) DEFAULT NULL,
  `Nombre` varchar(20) NOT NULL,
  `sexo` varchar(10) DEFAULT NULL,
  `Ap_Paterno` varchar(20) DEFAULT NULL,
  `Ap_Materno` varchar(20) DEFAULT NULL,
  `Fecha_de_nacimiento` date DEFAULT NULL,
  `Direccion` varchar(30) DEFAULT NULL,
  `Telefono` int(10) DEFAULT NULL,
  `Reponsable` varchar(30) DEFAULT NULL,
  `Parestesco_Responsable` int(20) DEFAULT NULL,
  `Telefono_reponsable` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`ID_Paciente`, `ID_usuario`, `Nombre`, `sexo`, `Ap_Paterno`, `Ap_Materno`, `Fecha_de_nacimiento`, `Direccion`, `Telefono`, `Reponsable`, `Parestesco_Responsable`, `Telefono_reponsable`) VALUES
(1, 0, 'Pablo', 'masculino', 'García ', 'González ', '2016-10-11', 'Valentina Lucía Reynoso Orella', 1829382, 'Juan', 7, 5225012),
(5, 0, 'Dan', 'masculino', 'García ', 'Fernández ', '2004-11-18', 'Ricardo Alan Zúñiga Sarabia', 1929382, 'Juan', 6, 545365012),
(6, 0, 'Dylan', NULL, NULL, NULL, '2003-06-27', NULL, NULL, NULL, NULL, NULL),
(7, 0, 'Gabriel', NULL, NULL, NULL, '2003-11-05', NULL, NULL, NULL, NULL, NULL),
(8, 0, 'asd', NULL, NULL, NULL, '2023-11-10', NULL, NULL, NULL, NULL, NULL),
(9, 0, 'DYlan', NULL, NULL, NULL, '2023-11-11', NULL, NULL, NULL, NULL, NULL),
(10, 0, 'Daiml', NULL, NULL, NULL, '2023-11-03', NULL, NULL, NULL, NULL, NULL),
(11, 0, 'juan', NULL, NULL, NULL, '2023-11-03', NULL, NULL, NULL, NULL, NULL),
(12, 0, 'Dylan', NULL, NULL, NULL, '2023-11-12', NULL, NULL, NULL, NULL, NULL),
(13, 0, 'dailas', NULL, NULL, NULL, '2023-11-30', NULL, NULL, NULL, NULL, NULL),
(14, 0, 'Diosito', NULL, NULL, NULL, '2013-03-13', NULL, NULL, NULL, NULL, NULL),
(15, 0, 'sdas', NULL, NULL, NULL, '2023-12-01', NULL, NULL, NULL, NULL, NULL),
(16, 0, 'fea', NULL, NULL, NULL, '2023-11-04', NULL, NULL, NULL, NULL, NULL),
(19, 29, 'adam', NULL, NULL, NULL, '2023-12-23', NULL, NULL, NULL, NULL, NULL),
(20, 31, 'Marcos', NULL, NULL, NULL, '2004-04-14', NULL, NULL, NULL, NULL, NULL),
(21, 34, 'Dylan', NULL, NULL, NULL, '2023-11-25', NULL, NULL, NULL, NULL, NULL),
(22, 36, 'Juan', NULL, NULL, NULL, '2023-11-25', NULL, NULL, NULL, NULL, NULL),
(23, 37, 'Juan', NULL, NULL, NULL, '2023-11-25', NULL, NULL, NULL, NULL, NULL),
(24, 38, 'jerson', NULL, NULL, NULL, '1998-02-26', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parestesco_tipo`
--

CREATE TABLE `parestesco_tipo` (
  `ID_parentesco` int(20) NOT NULL,
  `Parestesco_Responsable` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `parestesco_tipo`
--

INSERT INTO `parestesco_tipo` (`ID_parentesco`, `Parestesco_Responsable`) VALUES
(6, 'familia'),
(7, 'amigo'),
(8, 'Hermano');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal_medico`
--

CREATE TABLE `personal_medico` (
  `ID_empleado` int(100) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Ap_Paterno` varchar(20) NOT NULL,
  `Ap_Materno` varchar(20) NOT NULL,
  `Direccion` varchar(30) NOT NULL,
  `Telefono` int(10) DEFAULT NULL,
  `rol` int(30) NOT NULL,
  `Fecha_Registro` date DEFAULT NULL,
  `Fecha_Modificasion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol_tipo`
--

CREATE TABLE `rol_tipo` (
  `Nro_rol` int(30) NOT NULL,
  `Tipo_de_rol` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol_tipo`
--

INSERT INTO `rol_tipo` (`Nro_rol`, `Tipo_de_rol`) VALUES
(3, 'Administrador'),
(5, 'Paciente'),
(6, 'Doctor'),
(7, 'Secretario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sequence`
--

CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `ID_usuario` int(50) NOT NULL,
  `Tipo_usuario` int(30) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Fecha_nacimiento` date DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `Fecha_Ingreso` date DEFAULT NULL,
  `Fecha_modificacion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`ID_usuario`, `Tipo_usuario`, `Nombre`, `Fecha_nacimiento`, `password`, `Fecha_Ingreso`, `Fecha_modificacion`) VALUES
(1, 3, 'Luis', '2013-11-13', 'admin', '2023-11-11', '2023-11-11'),
(2, 5, 'Pablo', '2008-11-19', 'asdf', '2023-11-09', '2023-11-09'),
(4, 5, 'Dan', '2023-11-22', 'dias', '2023-11-20', '2023-11-23'),
(5, 6, 'Pabl', '2008-11-19', 'admin', '2023-11-28', '2023-11-17'),
(11, 7, 'adam', '2023-11-09', 'admin', '2023-11-12', '2023-11-12'),
(12, 5, 'sdas', '2023-11-09', 'asdas', '2023-11-12', '2023-11-12'),
(16, 5, 'Dylan', '2003-06-27', 'admin', '2023-11-12', '2023-11-12'),
(17, 6, 'Gabriel', '2003-11-05', 'GHabo20000', '2023-11-13', '2023-11-13'),
(24, 5, 'Diosito', '2013-03-13', 'ayuda', '2023-11-13', '2023-11-13'),
(25, 5, 'sdas', '2023-12-01', '6546', '2023-11-13', '2023-11-13'),
(27, 5, 'dailan', '2023-11-12', 'hola', '2023-11-13', '2023-11-13'),
(28, 5, 'DON', '2023-11-09', 'juegos', '2023-11-13', '2023-11-13'),
(29, 5, 'adam', '2023-12-23', 'abesedario', '2023-11-13', '2023-11-13'),
(30, 6, 'adam', '2023-11-14', 'admin', '2023-11-14', '2023-11-14'),
(31, 5, 'Marcos', '2004-04-14', '65160863', '2023-11-14', '2023-11-14'),
(32, 6, 'Marcos', '2023-11-14', '65160863', '2023-11-14', '2023-11-14'),
(33, 7, 'dani', '2023-11-14', 'admin', '2023-11-14', '2023-11-14'),
(34, 5, 'Dylan', '2023-11-25', 'admin', '2023-11-15', '2023-11-15'),
(35, 6, 'Juan', '2023-11-16', 'admin', '2023-11-16', '2023-11-16'),
(36, 5, 'Juan', '2023-11-25', 'admin', '2023-11-16', '2023-11-16'),
(37, 5, 'Juan', '2023-11-25', 'juega', '2023-11-16', '2023-11-16'),
(38, 5, 'jerson', '1998-02-26', '123456', '2023-11-16', '2023-11-16');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `citas_medicas`
--
ALTER TABLE `citas_medicas`
  ADD PRIMARY KEY (`ID_Citas`),
  ADD KEY `ID_para_doctor` (`ID_Doctor`),
  ADD KEY `ID_Paciente` (`ID_Paciente`,`ID_Doctor`) USING BTREE;

--
-- Indices de la tabla `doctores`
--
ALTER TABLE `doctores`
  ADD PRIMARY KEY (`ID_Doctor`),
  ADD KEY `Tipo_Especialidad` (`Espeialidad`);

--
-- Indices de la tabla `formulario`
--
ALTER TABLE `formulario`
  ADD PRIMARY KEY (`ID_formulario`),
  ADD KEY `Tipo_Habitacion` (`Tipo_habitacion`),
  ADD KEY `ID_Paciente` (`ID_Paciente`),
  ADD KEY `ID_Doctor` (`ID_Doctor`);

--
-- Indices de la tabla `habitacion_tipo`
--
ALTER TABLE `habitacion_tipo`
  ADD PRIMARY KEY (`Nro_habitacion`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`ID_Paciente`),
  ADD KEY `Telefono` (`Telefono`),
  ADD KEY `Parestesco_Responsable` (`Parestesco_Responsable`) USING BTREE;

--
-- Indices de la tabla `parestesco_tipo`
--
ALTER TABLE `parestesco_tipo`
  ADD PRIMARY KEY (`ID_parentesco`);

--
-- Indices de la tabla `personal_medico`
--
ALTER TABLE `personal_medico`
  ADD PRIMARY KEY (`ID_empleado`),
  ADD KEY `rol` (`rol`) USING BTREE;

--
-- Indices de la tabla `rol_tipo`
--
ALTER TABLE `rol_tipo`
  ADD PRIMARY KEY (`Nro_rol`);

--
-- Indices de la tabla `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`SEQ_NAME`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`ID_usuario`),
  ADD KEY `Tipo_usuario` (`Tipo_usuario`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `citas_medicas`
--
ALTER TABLE `citas_medicas`
  MODIFY `ID_Citas` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `doctores`
--
ALTER TABLE `doctores`
  MODIFY `ID_Doctor` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `formulario`
--
ALTER TABLE `formulario`
  MODIFY `ID_formulario` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `habitacion_tipo`
--
ALTER TABLE `habitacion_tipo`
  MODIFY `Nro_habitacion` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `paciente`
--
ALTER TABLE `paciente`
  MODIFY `ID_Paciente` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `parestesco_tipo`
--
ALTER TABLE `parestesco_tipo`
  MODIFY `ID_parentesco` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `personal_medico`
--
ALTER TABLE `personal_medico`
  MODIFY `ID_empleado` int(100) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `rol_tipo`
--
ALTER TABLE `rol_tipo`
  MODIFY `Nro_rol` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `ID_usuario` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `citas_medicas`
--
ALTER TABLE `citas_medicas`
  ADD CONSTRAINT `ID_para_doctor` FOREIGN KEY (`ID_Doctor`) REFERENCES `doctores` (`ID_Doctor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ID_para_paciente` FOREIGN KEY (`ID_Paciente`) REFERENCES `paciente` (`ID_Paciente`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `doctores`
--
ALTER TABLE `doctores`
  ADD CONSTRAINT `Tipo_Rol1` FOREIGN KEY (`Espeialidad`) REFERENCES `rol_tipo` (`Nro_rol`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `formulario`
--
ALTER TABLE `formulario`
  ADD CONSTRAINT `ID_de_Paciente` FOREIGN KEY (`ID_Paciente`) REFERENCES `paciente` (`ID_Paciente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ID_de_doctor` FOREIGN KEY (`ID_Doctor`) REFERENCES `doctores` (`ID_Doctor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Tipo_Habitacion` FOREIGN KEY (`Tipo_habitacion`) REFERENCES `habitacion_tipo` (`Nro_habitacion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD CONSTRAINT `Tipo_Parentesco` FOREIGN KEY (`Parestesco_Responsable`) REFERENCES `parestesco_tipo` (`ID_parentesco`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `personal_medico`
--
ALTER TABLE `personal_medico`
  ADD CONSTRAINT `Tipo_Rol` FOREIGN KEY (`rol`) REFERENCES `rol_tipo` (`Nro_rol`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `Tipo_Usuario_Rol` FOREIGN KEY (`Tipo_usuario`) REFERENCES `rol_tipo` (`Nro_rol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
