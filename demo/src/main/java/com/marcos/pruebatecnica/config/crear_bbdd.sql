DROP DATABASE IF EXISTS venta_productos;
CREATE DATABASE venta_productos CHARACTER SET utf8mb4;
USE venta_productos;

CREATE TABLE cliente (
    id VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE producto (
    nombre VARCHAR(100) PRIMARY KEY
);

CREATE TABLE cli_pro (
    id_cliente VARCHAR(50) NOT NULL,
    nombre_producto VARCHAR(100) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_cliente, nombre),
    UNIQUE (id_cliente, nombre_producto),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (nombre_producto) REFERENCES producto(nombre) ON DELETE CASCADE ON UPDATE CASCADE
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

INSERT INTO producto (nombre) VALUES
    ('DELL_UltraSharp_U2422H'),
    ('LOGITECH_MX_MASTER_3'),
    ('SAMSUNG_ODDYSSEY_G7'),
    ('APPLE_AIRPODS_PRO_2'),
    ('SONY_WH_1000XM5'),
    ('RAZER_DEATHADDER_V3');

INSERT INTO cli_pro (id_cliente, nombre_producto, nombre) VALUES
    ('C001', 'DELL_UltraSharp_U2422H', 'Monitor DELL UltraSharp 24 pulgadas'),
    ('C002', 'DELL_UltraSharp_U2422H', 'Pantalla UltraSharp Dell 24"'),
    ('C003', 'DELL_UltraSharp_U2422H', 'DELL UltraSharp U2422H 24" Full HD'),

    ('C004', 'LOGITECH_MX_MASTER_3', 'Ratón Logitech MX Master 3'),
    ('C005', 'LOGITECH_MX_MASTER_3', 'Logitech MX Master 3 Wireless'),

    ('C001', 'SAMSUNG_ODDYSSEY_G7', 'Monitor Gaming Samsung Odyssey G7 27"'),
    ('C006', 'SAMSUNG_ODDYSSEY_G7', 'Samsung Odyssey G7 27" Curved'),

    ('C002', 'APPLE_AIRPODS_PRO_2', 'Apple AirPods Pro 2ª Generación'),
    ('C003', 'APPLE_AIRPODS_PRO_2', 'AirPods Pro 2 con cancelación de ruido'),

    ('C007', 'SONY_WH_1000XM5', 'Sony WH-1000XM5 Auriculares Inalámbricos');