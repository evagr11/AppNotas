package com.example.appnotas.ViewModel;

import android.content.Context;

import com.example.appnotas.Model.ColorNota;
import com.example.appnotas.Model.Nota;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class NotaManager {

    private Context ct;

    public NotaManager(Context ct){
        this.ct = ct;
    }

    public void guardarNota(Nota nota){
        try{
            File carpeta = new File(ct.getFilesDir(), "USUARIO");

            int id = obtenerSiguienteId(carpeta);
            nota = new Nota(id, nota.getTitulo(), nota.getDescripcion(), nota.getColor());

            String nombreArchivo = ("nota" + id + ".not");
            File archivoNota = new File(carpeta, nombreArchivo);

            FileWriter writer = new FileWriter(archivoNota);
            writer.write(nota.toString());
            writer.close();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    public void actualizarNota(Nota nota){
        try{
            File carpeta = new File(ct.getFilesDir(), "USUARIO");

            String nombreArchivo = "nota" + nota.getId() + ".not";
            File archivoNota = new File(carpeta, nombreArchivo);

            FileWriter writer = new FileWriter(archivoNota);
            writer.write(nota.toString());
            writer.close();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    // El id de una nota nueva es el mayor id que ya exista más uno (nota1.not, nota2.not...)
    private int obtenerSiguienteId(File carpeta){
        if (!carpeta.exists()) carpeta.mkdir();
        File[] archivos = carpeta.listFiles((dir, nombre) -> nombre.startsWith("nota") && nombre.endsWith(".not"));
        int mayor = 0;
        if (archivos != null) {
            for (File archivo : archivos) {
                String numero = archivo.getName().replace("nota", "").replace(".not", "");
                try {
                    mayor = Math.max(mayor, Integer.parseInt(numero));
                } catch (NumberFormatException e) {
                    // nombre inesperado: se ignora
                }
            }
        }
        return mayor + 1;
    }

    // Lee todas las notas guardadas (ficheros .not), de la más nueva a la más antigua
    public ArrayList<Nota> leerNotas() {
        ArrayList<Nota> notas = new ArrayList<>();
        File carpeta = new File(ct.getFilesDir(), "USUARIO");
        // Solo los ficheros de notas (.not); en la carpeta también está datos.cas con el usuario
        File[] archivos = carpeta.listFiles((dir, nombre) -> nombre.endsWith(".not"));
        if (archivos == null) return notas;

        for (File archivo : archivos) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea = br.readLine();
                if (linea == null) continue;
                String[] partes = linea.split("\\|");

                String titulo = partes[0].trim();
                String descripcion = partes[1].trim().replace("\\n", "\n");
                ColorNota color = ColorNota.valueOf(partes[2].trim());
                int id = Integer.parseInt(partes[3].trim());

                notas.add(new Nota(id, titulo, descripcion, color));
            } catch (Exception e) {
                // Fichero con un formato inesperado: se salta
                e.printStackTrace();
            }
        }

        // Ordena por id de mayor a menor para que la última nota salga arriba
        Collections.sort(notas, (a, b) -> Integer.compare(b.getId(), a.getId()));
        return notas;
    }

    // Borra todas las notas, pero deja los datos del usuario (datos.cas)
    public void borrarTodasLasNotas() {
        File carpeta = new File(ct.getFilesDir(), "USUARIO");
        File[] archivos = carpeta.listFiles((dir, nombre) -> nombre.endsWith(".not"));
        if (archivos == null) return;
        for (File archivo : archivos) {
            archivo.delete();
        }
    }

    // Borra las notas y los datos del usuario: la app vuelve a su estado inicial
    public void borrarTodo() {
        File carpeta = new File(ct.getFilesDir(), "USUARIO");
        File[] archivos = carpeta.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                archivo.delete();
            }
        }
        carpeta.delete();
    }
}
