package com.example.appnotas.Model;

/**
 * Datos que se piden en la bienvenida: nombre, contraseña y año de nacimiento.
 */
public class Usuario {
    private String nombre, contrasena, fechaNacimiento;

    /**
     * Crea un usuario vacío que se va rellenando paso a paso.
     */
    public Usuario() {}

    /**
     * @param nombre          nombre del usuario
     * @param contrasena      contraseña
     * @param fechaNacimiento año de nacimiento
     */
    public Usuario(String nombre, String contrasena, String fechaNacimiento) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return la contraseña
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @return el año de nacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param contrasena nueva contraseña
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @param fechaNacimiento nuevo año de nacimiento
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
