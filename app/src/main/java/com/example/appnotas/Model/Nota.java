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

    // Formato del fichero: titulo | descripcion | color | id (todo en una línea).
    // Los saltos de línea se guardan como "\\n" para que una descripción de varias
    // líneas no rompa la lectura, y la barra "|" se cambia por "/" porque es el separador.
    @Override
    public String toString() {
        return limpiar(titulo) + " | " +
                limpiar(descripcion) + " | " +
                color + " | " +
                id;
    }

    private static String limpiar(String texto) {
        return texto.replace("|", "/").replace("\r", "").replace("\n", "\\n");
    }
}
