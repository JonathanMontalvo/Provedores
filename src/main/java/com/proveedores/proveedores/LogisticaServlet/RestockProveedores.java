package com.proveedores.proveedores.LogisticaServlet;

import org.bson.types.ObjectId;

public class RestockProveedores {
    private String id;
    private ObjectId id_Inventario;
    private int cantidad;
    private double costo_Restock;
    private String fecha_Restock;

    public RestockProveedores() {
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ObjectId getIdInventario() {
        return id_Inventario;
    }

    public void setIdInventario(ObjectId id_Inventario) {
        this.id_Inventario = id_Inventario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCostoRestock() {
        return costo_Restock;
    }

    public void setCostoRestock(double costo_Restock) {
        this.costo_Restock = costo_Restock;
    }

    public String getFechaRestock() {
        return fecha_Restock;
    }

    public void setFechaRestock(String fecha_Restock) {
        this.fecha_Restock = fecha_Restock;
    }
}
