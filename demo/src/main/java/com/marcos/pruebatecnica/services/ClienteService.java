package com.marcos.pruebatecnica.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.marcos.pruebatecnica.models.BBDD;
import com.marcos.pruebatecnica.models.Cliente;

public class ClienteService {
    public static boolean existeCliente(String idCliente) {
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

    public static List<Cliente> obtenerClientes() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> listaClientes = new ArrayList<Cliente>();

        try {
            Connection conn = BBDD.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("id"), rs.getString("nombre"));
                listaClientes.add(cliente);
            }

            rs.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error al mostrar productos: " + e.getMessage());
            e.printStackTrace();
        }

        return listaClientes;
    }
}
