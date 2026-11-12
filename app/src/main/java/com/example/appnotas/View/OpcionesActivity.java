package com.example.appnotas.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appnotas.R;
import com.example.appnotas.View.Landing.LandingActivity;
import com.example.appnotas.ViewModel.LandingViewModel;
import com.example.appnotas.ViewModel.NotaManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * Pantalla de opciones (diseño de Figma): cambiar el nombre, eliminar todas las notas
 * y reiniciar la app. Las dos últimas piden confirmación porque no se pueden deshacer.
 */
public class OpcionesActivity extends AppCompatActivity {

    /**
     * Conecta las tres opciones y el botón de volver.
     *
     * @param savedInstanceState estado guardado de la Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_opciones);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView titulo = findViewById(R.id.txtTituloPantalla);
        titulo.setText(R.string.menu_opciones);
        findViewById(R.id.btnHome).setOnClickListener(v -> volverAInicio());

        findViewById(R.id.btnCambiarNombre).setOnClickListener(v -> dialogoCambiarNombre());
        findViewById(R.id.btnEliminarNotas).setOnClickListener(v -> dialogoEliminarNotas());
        findViewById(R.id.btnReiniciar).setOnClickListener(v -> dialogoReiniciar());
    }

    /**
     * Vuelve a la pantalla principal.
     */
    private void volverAInicio() {
        startActivity(new Intent(OpcionesActivity.this, GeneralActivity.class));
        finish();
    }

    /**
     * Diálogo para escribir un nombre nuevo; no se cierra si se deja vacío.
     */
    private void dialogoCambiarNombre() {
        View vista = getLayoutInflater().inflate(R.layout.dialogo_nombre, null);
        EditText editNombre = vista.findViewById(R.id.editNuevoNombre);

        AlertDialog dialogo = new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.dialogo_nombre_titulo)
                .setView(vista)
                .setNegativeButton(R.string.cancelar, null)
                .setPositiveButton(R.string.guardar, null)
                .create();

        // El botón Guardar se configura aquí para que el diálogo no se cierre si el nombre está vacío
        dialogo.setOnShowListener(d -> dialogo.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            String nombre = editNombre.getText().toString().trim();
            if (nombre.isEmpty()) {
                editNombre.setError(getString(R.string.dialogo_nombre_vacio));
                return;
            }
            boolean ok = new LandingViewModel().cambiarNombre(this, nombre);
            dialogo.dismiss();
            if (ok) {
                avisoCorrecto(R.string.dialogo_nombre_ok);
            }
        }));
        dialogo.show();
    }

    /**
     * Pide confirmación y borra todas las notas.
     */
    private void dialogoEliminarNotas() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.opciones_eliminar_notas)
                .setMessage(R.string.dialogo_eliminar_mensaje)
                .setNegativeButton(R.string.cancelar, null)
                .setPositiveButton(R.string.eliminar, (d, w) -> {
                    new NotaManager(this).borrarTodasLasNotas();
                    avisoCorrecto(R.string.dialogo_eliminar_ok);
                })
                .show();
    }

    /**
     * Pide confirmación antes de reiniciar la app.
     */
    private void dialogoReiniciar() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.opciones_reiniciar)
                .setMessage(R.string.dialogo_reiniciar_mensaje)
                .setNegativeButton(R.string.cancelar, null)
                .setPositiveButton(R.string.reiniciar, (d, w) -> reiniciarApp())
                .show();
    }

    // Borra notas, usuario y preferencias, y vuelve a la bienvenida como la primera vez
    /**
     * Borra notas, usuario y preferencias, y vuelve a la bienvenida como la primera vez.
     */
    private void reiniciarApp() {
        new NotaManager(this).borrarTodo();
        getSharedPreferences(LandingActivity.PREFS_APP, Context.MODE_PRIVATE).edit().clear().apply();

        Toast.makeText(this, R.string.dialogo_reiniciar_ok, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(OpcionesActivity.this, LandingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    // Aviso con la tortuga, como en el diseño de Figma
    /**
     * Enseña un aviso con la tortuga, como en el diseño de Figma.
     *
     * @param mensaje id del texto del aviso
     */
    private void avisoCorrecto(int mensaje) {
        View vista = getLayoutInflater().inflate(R.layout.dialogo_aviso, null);
        TextView texto = vista.findViewById(R.id.txtAviso);
        texto.setText(mensaje);

        new MaterialAlertDialogBuilder(this)
                .setView(vista)
                .setPositiveButton(R.string.aceptar, null)
                .show();
    }
}
