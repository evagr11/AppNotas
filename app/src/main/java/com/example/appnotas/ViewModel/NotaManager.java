package com.example.appnotas.ViewModel;

import android.content.Context;

import com.example.appnotas.Model.Nota;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
}
