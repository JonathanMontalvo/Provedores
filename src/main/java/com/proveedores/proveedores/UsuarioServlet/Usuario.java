package com.proveedores.proveedores.UsuarioServlet;

public class Usuario {
    private String nombre;
    private String apellido_Paterno;
    private String apellido_Materno;
    private String correo;
    private String telefono;
    private String password;
    private String rol;
    private int status; // Nuevo campo

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellido_Paterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellido_Paterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellido_Materno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellido_Materno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
