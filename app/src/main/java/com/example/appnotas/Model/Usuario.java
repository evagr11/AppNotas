package com.example.appnotas.Model;

public class Usuario {
    private String nombre, contrasena, fechaNacimiento;

    public Usuario() {}

    public Usuario(String nombre, String contrasena, String fechaNacimiento) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
