CREATE DATABASE EmpresaLeiloesBD;

USE EmpresaLeiloesBD;

CREATE TABLE produtos (
  id bigint(20) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nome text DEFAULT NULL,
  valor int(11) DEFAULT NULL,
  status text DEFAULT NULL
);

INSERT INTO produtos (id, nome, valor, status) VALUES
(2, 'PS4', 1500, 'Vendido'),
(3, 'Xbox 360', 800, 'Vendido'),
(4, 'Iphone 12', 4800, 'Vendido'),
(5, 'PS2', 400, 'A Venda');