package com.proveedores.proveedores.LogisticaServlet;

import java.time.LocalDateTime;
import java.util.List;

public class HistorialVenta {
    private String id;
    private List<ProductoVendido> productos_Vendidos;
    private int status_Pago;
    private String fecha_Venta;
    private double total;

    public HistorialVenta() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProductoVendido> getProductosVendidos() {
        return productos_Vendidos;
    }

    public void setProductosVendidos(List<ProductoVendido> productos_Vendidos) {
        this.productos_Vendidos = productos_Vendidos;
    }

    public int getStatusPago() {
        return status_Pago;
    }

    public void setStatusPago(int status_Pago) {
        this.status_Pago = status_Pago;
    }

    public String getFechaVenta() {
        return fecha_Venta;
    }

    public void setFechaVenta(String fecha_Venta) {
        this.fecha_Venta = fecha_Venta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

