package com.example.appnotas.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appnotas.Model.Nota;
import com.example.appnotas.R;
import com.example.appnotas.ViewModel.NotaManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Muestra una de tus notas elegida al azar, con su color. "Otra nota" elige otra distinta
 * y "Editar" la abre en el formulario de edición.
 */
public class NotaAleatoriaActivity extends AppCompatActivity {

    private ArrayList<Nota> notas;
    private Nota notaActual;
    private final Random random = new Random();

    private View contenedorNota, botones;
    private TextView txtTitulo, txtTexto, txtSinNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nota_aleatoria);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView titulo = findViewById(R.id.txtTituloPantalla);
        titulo.setText(R.string.menu_aleatoria);
        findViewById(R.id.btnHome).setOnClickListener(v -> {
            startActivity(new Intent(NotaAleatoriaActivity.this, GeneralActivity.class));
            finish();
        });

        contenedorNota = findViewById(R.id.contenedorNota);
        botones = findViewById(R.id.botonesAleatoria);
        txtTitulo = findViewById(R.id.txtTituloAleatoria);
        txtTexto = findViewById(R.id.txtTextoAleatoria);
        txtSinNotas = findViewById(R.id.txtSinNotas);

        findViewById(R.id.btnOtraNota).setOnClickListener(v -> mostrarNotaAleatoria());
        findViewById(R.id.btnEditarAleatoria).setOnClickListener(v -> editarNota());

        notas = new NotaManager(this).leerNotas();
        mostrarNotaAleatoria();
    }

    private void mostrarNotaAleatoria() {
        if (notas.isEmpty()) {
            contenedorNota.setVisibility(View.GONE);
            botones.setVisibility(View.GONE);
            txtSinNotas.setVisibility(View.VISIBLE);
            return;
        }

        // Si hay más de una nota, se evita repetir la que ya se está viendo
        Nota elegida;
        do {
            elegida = notas.get(random.nextInt(notas.size()));
        } while (notas.size() > 1 && elegida == notaActual);
        notaActual = elegida;

        txtTitulo.setText(notaActual.getTitulo());
        txtTexto.setText(notaActual.getDescripcion());
        contenedorNota.setBackgroundResource(NotaAdapter.fondoDe(notaActual.getColor()));
    }

    private void editarNota() {
        if (notaActual == null) return;
        Intent intent = new Intent(NotaAleatoriaActivity.this, CrearNotasActivity.class);
        intent.putExtra("id", notaActual.getId());
        intent.putExtra("titulo", notaActual.getTitulo());
        intent.putExtra("descripcion", notaActual.getDescripcion());
        intent.putExtra("color", notaActual.getColor().name());
        startActivity(intent);
        finish();
    }
}
