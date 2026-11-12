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

/**
 * Guarda y lee los datos del usuario de la bienvenida.
 *
 * <p>Lo comparten los tres Fragments de la bienvenida. Los datos se escriben en
 * {@code USUARIO/datos.cas} con un desplazamiento de caracteres para que no se
 * lean a simple vista (no es un cifrado seguro).</p>
 */
public class LandingViewModel {
    private Usuario usuario = new Usuario();

    /**
     * @param nombre nombre escrito en el primer paso
     */
    public void setNombre(String nombre) {
        usuario.setNombre(nombre);
    }
    /**
     * @param contrasena contraseña escrita en el segundo paso
     */
    public void setContrasena(String contrasena) {
        usuario.setContrasena(contrasena);
    }
    /**
     * @param fecha año de nacimiento escrito en el último paso
     */
    public void setFechaNacimiento(String fecha) {
        usuario.setFechaNacimiento(fecha);
    }

    /**
     * Desplaza cada carácter una posición hacia delante.
     *
     * @param texto texto original
     * @return texto desplazado
     */
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

    /**
     * Escribe el nombre, la contraseña y la fecha, desplazados, en {@code USUARIO/datos.cas}.
     *
     * @param context contexto para llegar a la carpeta privada de la app
     */
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

            Log.d("LandingViewModel", "Usuario guardado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lee {@code datos.cas} y devuelve un usuario con el nombre ya descifrado.
     *
     * @param context contexto para llegar a la carpeta privada de la app
     * @return el usuario leído (vacío si no hay fichero)
     */
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

    /**
     * Deshace {@link #cifrar(String)}: desplaza cada carácter una posición hacia atrás.
     *
     * @param texto texto desplazado
     * @return texto original
     */
    private String descifrar(String texto) {
        char[] letras = texto.toCharArray();
        String resultado = "";
        for (int i = 0; i < letras.length; i++) {
            char original = (char)(letras[i] - 1);
            resultado += original;
        }
        return resultado;
    }

    // Cambia solo el nombre: la contraseña y la fecha se quedan como estaban en datos.cas
    /**
     * Cambia solo el nombre en {@code datos.cas}, sin tocar la contraseña ni la fecha.
     *
     * @param context     contexto para llegar a la carpeta privada de la app
     * @param nuevoNombre nombre nuevo
     * @return {@code true} si se ha guardado bien
     */
    public boolean cambiarNombre(Context context, String nuevoNombre) {
        File archivo = new File(new File(context.getFilesDir(), "USUARIO"), "datos.cas");
        StringBuilder datos = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Nombre:")) {
                    datos.append("Nombre: ").append(cifrar(nuevoNombre)).append("\n");
                } else {
                    datos.append(linea).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(datos.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
