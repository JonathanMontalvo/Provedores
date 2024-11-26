package com.proveedores.proveedores.LogisticaServlet;

import org.bson.types.ObjectId;

public class Inventario {
    private String id;
    private ObjectId id_Producto;
    private int cantidad;
    private String ubicacion;
    private String fecha_Actualizacion;

    public Inventario() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFechaActualizacion() {
        return fecha_Actualizacion;
    }

    public void setFechaActualizacion(String fecha_Actualizacion) {
        this.fecha_Actualizacion = fecha_Actualizacion;
    }
}
