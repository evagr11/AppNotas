package com.example.appnotas.Model;

/**
 * Una nota con id, título, descripción y color.
 *
 * <p>Cada nota se guarda en su propio fichero {@code notaN.not}; el formato de
 * la línea lo da {@link #toString()}.</p>
 */
public class Nota {

    private int id;
    private String titulo, descripcion;
    private ColorNota color;

    /**
     * @param id          identificador de la nota (el número de su fichero)
     * @param titulo      título de la nota
     * @param descripcion texto de la nota, puede tener varias líneas
     * @param color       color de la nota
     */
    public Nota(int id, String titulo, String descripcion, ColorNota color) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.color = color;
    }

    /**
     * @return el identificador de la nota
     */
    public int getId() {
        return id;
    }

    /**
     * @return el título
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return la descripción
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return el color de la nota
     */
    public ColorNota getColor() {
        return color;
    }

    /**
     * @param id nuevo identificador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param titulo nuevo título
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @param descripcion nueva descripción
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @param color nuevo color
     */
    public void setColor(ColorNota color) {
        this.color = color;
    }

    // Formato del fichero: titulo | descripcion | color | id (todo en una línea).
    // Los saltos de línea se guardan como "\\n" para que una descripción de varias
    // líneas no rompa la lectura, y la barra "|" se cambia por "/" porque es el separador.
    /**
     * Devuelve la nota en una sola línea con el formato
     * {@code titulo | descripcion | color | id}, que es lo que se escribe en el fichero.
     *
     * @return la línea que se guarda en el fichero de la nota
     */
    @Override
    public String toString() {
        return limpiar(titulo) + " | " +
                limpiar(descripcion) + " | " +
                color + " | " +
                id;
    }

    /**
     * Prepara un texto para guardarlo en una línea: cambia {@code |} por {@code /}
     * y los saltos de línea por {@code \n}.
     *
     * @param texto texto original
     * @return texto sin separadores ni saltos de línea
     */
    private static String limpiar(String texto) {
        return texto.replace("|", "/").replace("\r", "").replace("\n", "\\n");
    }
}
