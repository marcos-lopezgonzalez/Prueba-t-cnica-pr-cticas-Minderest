package com.marcos.pruebatecnica;

import java.util.List;
import java.util.Scanner;

import com.marcos.pruebatecnica.models.Cliente;
import com.marcos.pruebatecnica.models.Producto;
import com.marcos.pruebatecnica.services.ClienteService;
import com.marcos.pruebatecnica.services.ProductoService;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String input;
        int opcion = 0;

        do {
            mostrarMenu();
            input = scanner.nextLine();

            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\nPor favor, introduce un número válido.");
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    insertarProducto();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    mostrarClientes();
                    break;
                case 5:
                    mostrarProductos();
                    break;
                case 6:
                    System.out.println("\n¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nOpción no válida. Intente de nuevo.");
            }

            if (opcion != 6) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }

        } while (opcion != 6);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\nGestor de productos");
        System.out.println("-------------------");
        System.out.println("1. Insertar nuevo producto");
        System.out.println("2. Buscar productos equivalentes");
        System.out.println("3. Establecer relación de equivalencia");
        System.out.println("4. Mostrar clientes");
        System.out.println("5. Mostrar productos");
        System.out.println("6. Salir");
        System.out.print("\nSeleccione una opción: ");
    }

    private static void insertarProducto() {
        System.out.println("\n\n\n\n\n");
        System.out.println("Creación de producto");
        System.out.println("----------------------");

        System.out.print("ID del cliente: ");
        String idCliente = scanner.nextLine();

        System.out.print("Nombre del producto: ");
        String nombreProducto = scanner.nextLine();

        Producto nuevoProducto = new Producto(nombreProducto, idCliente);

        ProductoService.insertarProducto(nuevoProducto);
    }

    private static void mostrarClientes() {
        List<Cliente> listaClientes = ClienteService.obtenerClientes();

        System.out.println("\nLista de clientes");
        System.out.println("------------------");
        for (int i = 0; i < listaClientes.size(); i++) {
            System.out.println("ID: " + listaClientes.get(i).getId() +
                    " || NOMBRE: " + listaClientes.get(i).getNombre());
        }
    }

    private static void mostrarProductos() {
        List<Producto> listaProductos = ProductoService.obtenerProductos();

        System.out.println("\nLista de productos");
        System.out.println("------------------");
        for (int i = 0; i < listaProductos.size(); i++) {
            System.out.println("ID CLIENTE: " + listaProductos.get(i).getId_cliente() +
                    " || NOMBRE: " + listaProductos.get(i).getNombre());
        }
    }
}