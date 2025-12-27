DROP DATABASE IF EXISTS venta_productos;
CREATE DATABASE venta_productos CHARACTER SET utf8mb4;
USE venta_productos;

CREATE TABLE cliente (
    id VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE producto (
    id_cliente VARCHAR(50) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_cliente, nombre),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE equivalencia_producto (
    id_cliente_1 VARCHAR(50) NOT NULL,
    nombre_producto_1 VARCHAR(100) NOT NULL,
    id_cliente_2 VARCHAR(50) NOT NULL,
    nombre_producto_2 VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_cliente_1, nombre_producto_1, id_cliente_2, nombre_producto_2),
    FOREIGN KEY (id_cliente_1, nombre_producto_1) REFERENCES producto(id_cliente, nombre) ON DELETE CASCADE,
    FOREIGN KEY (id_cliente_2, nombre_producto_2) REFERENCES producto(id_cliente, nombre) ON DELETE CASCADE
);

INSERT INTO cliente (id, nombre) VALUES
    ('C001', 'Worten'),
    ('C002', 'MediaMarkt'),
    ('C003', 'Amazon'),
    ('C004', 'El Corte Inglés'),
    ('C005', 'PcComponentes'),
    ('C006', 'Fnac'),
    ('C007', 'Carrefour'),
    ('C008', 'Alcampo'),
    ('C009', 'Tienda9'),
    ('C010', 'Tienda10');