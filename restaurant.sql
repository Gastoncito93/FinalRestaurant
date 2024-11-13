-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-11-2024 a las 23:53:52
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
-- Base de datos: `restaurant`
--
CREATE DATABASE IF NOT EXISTS `restaurant` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `restaurant`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mesa`
--

DROP TABLE IF EXISTS `mesa`;
CREATE TABLE `mesa` (
  `id_mesa` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1,
  `id_reserva` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `mesa`
--

INSERT INTO `mesa` (`id_mesa`, `numero`, `capacidad`, `estado`, `id_reserva`) VALUES
(20, 4, 2, 1, NULL),
(21, 5, 4, 1, NULL),
(23, 6, 8, 1, NULL),
(26, 3, 2, 1, NULL),
(27, 2, 8, 0, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mesero`
--

DROP TABLE IF EXISTS `mesero`;
CREATE TABLE `mesero` (
  `id_mesero` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1,
  `usuario` varchar(30) NOT NULL,
  `contraseña` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `mesero`
--

INSERT INTO `mesero` (`id_mesero`, `nombre`, `apellido`, `dni`, `estado`, `usuario`, `contraseña`) VALUES
(21, 'Carlos', 'Pérez', '12345678', 0, 'carlos123', 'password'),
(22, 'Ana', 'García', '87654321', 1, 'ana123', 'password'),
(24, 'Yaciel', 'Muñoz', '46408906', 1, 'Zombers007', '39394014'),
(27, 'Angel', 'Rubin', '36333655', 1, 'cartonero322', '4556532'),
(29, 'Adolfo', 'Tripler', '40482367', 1, 'Kerosene', '1945555'),
(31, 'Pedro', 'Perez', '22333333', 1, 'Pedro333', '23332243');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

DROP TABLE IF EXISTS `pedido`;
CREATE TABLE `pedido` (
  `id_pedido` int(11) NOT NULL,
  `id_mesa` int(11) DEFAULT NULL,
  `id_mesero` int(11) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id_pedido`, `id_mesa`, `id_mesero`, `fecha`, `estado`) VALUES
(37, 20, 22, '2024-11-11 18:55:20', 1),
(39, 26, 22, '2024-11-12 01:26:48', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido_producto`
--

DROP TABLE IF EXISTS `pedido_producto`;
CREATE TABLE `pedido_producto` (
  `id_pedido` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `pedido_producto`
--

INSERT INTO `pedido_producto` (`id_pedido`, `id_producto`, `cantidad`, `precio`, `estado`) VALUES
(37, 55, 2, 1000.00, 1),
(37, 61, 2, 200.00, 1),
(39, 49, 2, 160.00, 1),
(37, 47, 3, 300.00, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre`, `cantidad`, `precio`, `tipo`, `estado`) VALUES
(45, 'Paco', 50, 160.00, 'Perfumería', 1),
(46, 'Shampoo', 5, 250.75, 'Higiene', 1),
(47, 'Jabón', 50, 100.00, 'Higiene', 1),
(48, 'Perfume', 6, 500.00, 'Perfumería', 1),
(49, 'Desodorante', 2, 80.00, 'Higiene', 1),
(55, 'Pizza', 20, 500.00, 'Comida', 1),
(61, 'Gaseosa', 44, 100.00, 'Bebida', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

DROP TABLE IF EXISTS `reserva`;
CREATE TABLE `reserva` (
  `id_reserva` int(11) NOT NULL,
  `id_mesa` int(11) NOT NULL,
  `nombre_cliente` varchar(50) NOT NULL,
  `dni_cliente` varchar(20) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id_reserva`, `id_mesa`, `nombre_cliente`, `dni_cliente`, `fecha`, `hora`, `estado`) VALUES
(16, 20, 'alguien', '12121212', '2024-11-11', '22:00:00', 1),
(17, 23, 'alguien nuevo', '55511133', '2024-11-13', '20:00:00', 1),
(18, 21, 'Chino Mandarin', '12332134', '2024-11-13', '11:00:00', 1),
(19, 20, 'Un Tipo Comun', '44444445', '2024-12-12', '00:00:00', 1),
(20, 20, 'Un Tipo Comun', '', '2024-12-15', '00:00:00', 1),
(21, 23, 'arjona ricardo', '12345678', '2024-12-01', '22:00:00', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `mesa`
--
ALTER TABLE `mesa`
  ADD PRIMARY KEY (`id_mesa`),
  ADD UNIQUE KEY `UC_numero` (`numero`),
  ADD KEY `id_reserva` (`id_reserva`);

--
-- Indices de la tabla `mesero`
--
ALTER TABLE `mesero`
  ADD PRIMARY KEY (`id_mesero`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `id_mesa` (`id_mesa`),
  ADD KEY `id_mesero` (`id_mesero`);

--
-- Indices de la tabla `pedido_producto`
--
ALTER TABLE `pedido_producto`
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `id_pedido` (`id_pedido`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id_reserva`),
  ADD KEY `id_mesa` (`id_mesa`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `mesa`
--
ALTER TABLE `mesa`
  MODIFY `id_mesa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `mesero`
--
ALTER TABLE `mesero`
  MODIFY `id_mesero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id_reserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `mesa`
--
ALTER TABLE `mesa`
  ADD CONSTRAINT `mesa_ibfk_1` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id_reserva`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_mesa`) REFERENCES `mesa` (`id_mesa`),
  ADD CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`id_mesero`) REFERENCES `mesero` (`id_mesero`);

--
-- Filtros para la tabla `pedido_producto`
--
ALTER TABLE `pedido_producto`
  ADD CONSTRAINT `pedido_producto_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`),
  ADD CONSTRAINT `pedido_producto_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`id_mesa`) REFERENCES `mesa` (`id_mesa`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
