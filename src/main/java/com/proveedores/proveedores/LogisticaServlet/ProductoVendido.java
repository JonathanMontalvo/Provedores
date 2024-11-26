package com.proveedores.proveedores.LogisticaServlet;

import org.bson.types.ObjectId;

public class ProductoVendido {
    private ObjectId id_Producto;
    private int cantidad;
    private double total;

    public ProductoVendido() {
    }

    public ObjectId getIdProducto() {
        return id_Producto;
    }

    public void setIdProducto(ObjectId id_Producto) {
        this.id_Producto = id_Producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

