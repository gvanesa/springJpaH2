CREATE TABLE IF NOT EXISTS compras (
`idCompra` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  descripcion VARCHAR(100) NOT NULL,
  estado VARCHAR(25) NOT NULL,
  cantidad int not null
);



CREATE TABLE `productos` (
`id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
`nombre` varchar(300) NOT NULL,
`precio` int NOT NULL
);