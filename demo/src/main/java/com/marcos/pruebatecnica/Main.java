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
                    mostrarProductosEquivalentes();
                    break;
                case 3:
                    establecerRelacionEquivalencia();
                    break;
                case 4:
                    mostrarClientes();
                    break;
                case 5:
                    mostrarProductos(ProductoService.obtenerProductos());
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
        System.out.println("\nCreación de producto");
        System.out.println("----------------------");

        System.out.print("ID del cliente: ");
        String idCliente = scanner.nextLine();

        System.out.print("Nombre del producto: ");
        String nombreProducto = scanner.nextLine();

        Producto nuevoProducto = new Producto(nombreProducto, idCliente);

        ProductoService.insertarProducto(nuevoProducto);
    }

    private static void mostrarProductosEquivalentes() {
        List<Producto> listaProductos = ProductoService.obtenerProductos();

        mostrarProductos(listaProductos);
        System.out.println("\nElige 1 producto (introduce el número correspondiente):");

        System.out.print("Producto: ");
        String input1 = scanner.nextLine();

        int num;

        try {
            num = Integer.parseInt(input1);
        } catch (NumberFormatException e) {
            System.out.println("\nError: Debes introducir números válidos.");
            return;
        }

        if (num < 1 || num > listaProductos.size()) {
            System.out.println("\nError: Debes elegir un número dentro del rango de productos.");
            return;
        }

        Producto producto = listaProductos.get(num - 1);

        List<Producto> listaProductosEquivalentes = ProductoService.buscarProductosEquivalentes(producto);

        if(listaProductosEquivalentes.size() == 0) {
            System.out.println("\nNo existen productos equivalentes a este...");
        } else {
            System.out.println("\nMostrando productos equivalentes");
            mostrarProductos(listaProductosEquivalentes);
        }
    }

    private static void establecerRelacionEquivalencia() {
        List<Producto> listaProductos = ProductoService.obtenerProductos();

        if(listaProductos.size() < 2) {
            System.out.println("Error: Todavía no hay suficientes productos para esta función");
            return;
        }

        mostrarProductos(listaProductos);
        System.out.println("\nElige 2 productos (introduce el número correspondiente):");

        System.out.print("Primer producto: ");
        String input1 = scanner.nextLine();

        System.out.print("Segundo producto: ");
        String input2 = scanner.nextLine();

        int num1, num2;

        try {
            num1 = Integer.parseInt(input1);
            num2 = Integer.parseInt(input2);
        } catch (NumberFormatException e) {
            System.out.println("\nError: Debes introducir números válidos.");
            return;
        }

        if (num1 == num2) {
            System.out.println("\nError: No puedes seleccionar el mismo producto dos veces.");
            return;
        }

        if (num1 < 1 || num1 > listaProductos.size() ||
                num2 < 1 || num2 > listaProductos.size()) {
            System.out.println("\nError: Debes elegir un número dentro del rango de productos.");
            return;
        }

        Producto producto1 = listaProductos.get(num1 - 1);
        Producto producto2 = listaProductos.get(num2 - 1);

        if (producto1.getId_cliente().equals(producto2.getId_cliente())) {
            System.out.println("\nError: No se puede establecer una relación de equivalencia entre productos del mismo cliente.");
            return;
        }

        ProductoService.establecerRelacionEquivalencia(producto1, producto2);
    }

    private static void mostrarClientes() {
        List<Cliente> listaClientes = ClienteService.obtenerClientes();

        if(listaClientes.size() == 0) {
            System.out.println("Error: No existen clientes...");
            return;
        }

        System.out.println("\nLista de clientes");
        System.out.println("------------------");
        for (int i = 0; i < listaClientes.size(); i++) {
            System.out.println(i + 1 + " - " + "ID: " + listaClientes.get(i).getId() +
                    " || NOMBRE: " + listaClientes.get(i).getNombre());
        }
    }

    private static void mostrarProductos(List<Producto> listaProductos) {
        if(listaProductos.size() == 0) {
            System.out.println("Error: No existen productos...");
            return;
        }

        System.out.println("\nLista de productos");
        System.out.println("------------------");
        for (int i = 0; i < listaProductos.size(); i++) {
            System.out.println(i + 1 + " - " + "ID CLIENTE: " + listaProductos.get(i).getId_cliente() +
                    " || NOMBRE: " + listaProductos.get(i).getNombre());
        }
    }
}