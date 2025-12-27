package com.marcos.pruebatecnica.models;

public class Producto {
    private String nombre;
    private String id_cliente;

    public Producto(String nombre, String id_cliente) {
        this.nombre = nombre;
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }
}
