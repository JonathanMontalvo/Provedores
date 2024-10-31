package com.proveedores.proveedores.VehiculoServlet;

public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private double capacidad;
    private String dimensiones;
    private int anio;
    private String tipo;
    private String fechaAdquisicion;
    private String estado;
    private String combustible;
    private String ultimoMantenimiento;
    private String ubicacion;

    public Vehiculo() {
    }

    // Getters y Setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(String fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public String getUltimoMantenimiento() {
        return ultimoMantenimiento;
    }

    public void setUltimoMantenimiento(String ultimoMantenimiento) {
        this.ultimoMantenimiento = ultimoMantenimiento;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
