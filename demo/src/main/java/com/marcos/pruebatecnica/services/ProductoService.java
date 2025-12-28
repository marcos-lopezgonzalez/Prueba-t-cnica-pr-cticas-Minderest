package com.marcos.pruebatecnica.services;

import com.marcos.pruebatecnica.models.BBDD;
import com.marcos.pruebatecnica.models.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    public static void insertarProducto(Producto producto) {
        if (!ClienteService.existeCliente(producto.getId_cliente())) {
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
            System.out.println("Error al insertar el producto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Producto> obtenerProductos() {
        String sql = "SELECT * FROM producto";
        List<Producto> listaProductos = new ArrayList<Producto>();

        try {
            Connection conn = BBDD.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto(rs.getString("nombre"), rs.getString("id_cliente"));
                listaProductos.add(producto);
            }

            rs.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error al mostrar productos: " + e.getMessage());
            e.printStackTrace();
        }

        return listaProductos;
    }
}