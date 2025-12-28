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

    public static void establecerRelacionEquivalencia(Producto producto1, Producto producto2) {
        if (existeRelacionEquivalencia(producto1, producto2)) {
            System.out.println("Ya existe una relación de equivalencia entre estos dos productos.");
            return;
        }

        String sql = "INSERT INTO equivalencia_producto (id_cliente_1, nombre_producto_1, id_cliente_2, nombre_producto_2) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = BBDD.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, producto1.getId_cliente());
            statement.setString(2, producto1.getNombre());
            statement.setString(3, producto2.getId_cliente());
            statement.setString(4, producto2.getNombre());

            statement.executeUpdate();
            statement.close();
            conn.close();

            System.out.println("Relación de equivalencia establecida correctamente");

        } catch (SQLException e) {
            System.out.println("Error al establecer la relación de equivalencia: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static boolean existeRelacionEquivalencia(Producto producto1, Producto producto2) {
        String sql = "SELECT COUNT(*) FROM equivalencia_producto WHERE " +
                "(id_cliente_1 = ? AND nombre_producto_1 = ? AND id_cliente_2 = ? AND nombre_producto_2 = ?) OR " +
                "(id_cliente_1 = ? AND nombre_producto_1 = ? AND id_cliente_2 = ? AND nombre_producto_2 = ?)";
        boolean existe = false;

        try {
            Connection conn = BBDD.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, producto1.getId_cliente());
            statement.setString(2, producto1.getNombre());
            statement.setString(3, producto2.getId_cliente());
            statement.setString(4, producto2.getNombre());

            statement.setString(5, producto2.getId_cliente());
            statement.setString(6, producto2.getNombre());
            statement.setString(7, producto1.getId_cliente());
            statement.setString(8, producto1.getNombre());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }

            rs.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error al comprobar si existe la relación: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

        return existe;
    }
}