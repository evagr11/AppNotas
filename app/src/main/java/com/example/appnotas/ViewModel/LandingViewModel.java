package com.example.appnotas.ViewModel;

import android.content.Context;
import android.util.Log;

import com.example.appnotas.Model.Usuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LandingViewModel {
    private Usuario usuario = new Usuario();

    public void setNombre(String nombre) {
        usuario.setNombre(nombre);
    }
    public void setContrasena(String contrasena) {
        usuario.setContrasena(contrasena);
    }
    public void setFechaNacimiento(String fecha) {
        usuario.setFechaNacimiento(fecha);
    }

    public String cifrar(String texto){
        char[] letras = texto.toCharArray();
        String resultado = "";
        for (int i = 0; i < texto.length(); i++){
            char letra = letras[i];
            char cifrada = (char)(letra + 1);
            resultado += cifrada;
        }
        return resultado;
    }

    public void guardarUsuario(Context context) {
        String nombreCifrado = cifrar(usuario.getNombre());
        String contrasenaCifrada = cifrar(usuario.getContrasena());
        String fechaCifrada = cifrar(usuario.getFechaNacimiento());

        String datos = "Nombre: " + nombreCifrado + "\n" +
                       "Contraseña: " + contrasenaCifrada + "\n" +
                       "Fecha: " + fechaCifrada + "\n";
        try {
            File carpeta = new File(context.getFilesDir(), "USUARIO");
            if(!carpeta.exists()){ carpeta.mkdir(); }

            File archivo = new File(carpeta, "datos.cas");

            FileWriter writer = new FileWriter(archivo);
            writer.write(datos.toString());
            writer.close();

            Log.d("LandingViewModel", datos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Usuario leerUsuario(Context context) {
        File carpeta = new File(context.getFilesDir(), "USUARIO");
        File archivo = new File(carpeta, "datos.cas");

        Usuario usuario = new Usuario();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null){
                if (linea.startsWith("Nombre:")) {
                    String nombreCifrado = linea.replace("Nombre:", "").trim();
                    usuario.setNombre(descifrar(nombreCifrado));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

    private String descifrar(String texto) {
        char[] letras = texto.toCharArray();
        String resultado = "";
        for (int i = 0; i < letras.length; i++) {
            char original = (char)(letras[i] - 1);
            resultado += original;
        }
        return resultado;
    }


}
