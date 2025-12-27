package com.marcos.pruebatecnica.services;

import com.marcos.pruebatecnica.models.BBDD;
import com.marcos.pruebatecnica.models.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoService {

    public static void insertarProducto(Producto producto) {
        if (!existeCliente(producto.getId_cliente())) {
            System.out.println("Ese cliente no existe...");
            return;
        }

        String sql = "INSERT INTO producto (id_cliente, nombre) VALUES (?, ?)";
        try {
            Connection conn = BBDD.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, producto.getId_cliente());
            statement.setString(2, producto.getNombre());

            statement.executeUpdate();
            statement.close();
            conn.close();

            System.out.println("Producto añadido correctamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar el producto");
            e.printStackTrace();
        }
    }

    private static boolean existeCliente(String idCliente) {
        String sql = "SELECT COUNT(*) FROM cliente WHERE id = ?";
        boolean existe = false;

        try {
            Connection conn = BBDD.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, idCliente);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }

            rs.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error al comprobar si existe el cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

        return existe;
    }
}