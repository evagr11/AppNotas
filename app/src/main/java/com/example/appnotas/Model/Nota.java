package com.example.appnotas.Model;

public class Nota {

    private int id;
    private String titulo, descripcion;
    private ColorNota color;

    public Nota(int id, String titulo, String descripcion, ColorNota color) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ColorNota getColor() {
        return color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setColor(ColorNota color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return titulo + " | " +
                descripcion + " | " +
                color + " | " +
                id;
    }
}
