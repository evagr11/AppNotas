package com.example.appnotas.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appnotas.Model.ColorNota;
import com.example.appnotas.Model.Nota;
import com.example.appnotas.R;
import com.example.appnotas.ViewModel.NotaManager;
import com.google.android.material.chip.Chip;

public class CrearNotasActivity extends AppCompatActivity {

    private EditText txtTituloEdit, txtDescripcionEdit;
    private Chip chip1, chip2, chip3, chip4, chip5, chip6, chip7;
    private Button btnEnviar, btnBack;
    private int notaId = 0;
    private boolean esEdicion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_notas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtTituloEdit = findViewById(R.id.txtTituloEdit);
        txtDescripcionEdit = findViewById(R.id.txtDescripcionEdit);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnBack = findViewById(R.id.btnBack);

        chip1 = findViewById(R.id.chip1);
        chip2 = findViewById(R.id.chip2);
        chip3 = findViewById(R.id.chip3);
        chip4 = findViewById(R.id.chip4);
        chip5 = findViewById(R.id.chip5);
        chip6 = findViewById(R.id.chip6);
        chip7 = findViewById(R.id.chip7);

        Intent intent = getIntent();
        if (getIntent().hasExtra("id")) {
            esEdicion = true;
            notaId = intent.getIntExtra("id", 0);
            txtTituloEdit.setText(intent.getStringExtra("titulo"));
            txtDescripcionEdit.setText(intent.getStringExtra("descripcion"));

            String colorStr = intent.getStringExtra("color");

            ColorNota color = ColorNota.valueOf(colorStr);

            switch (color) {
                case Color1: chip1.setChecked(true); break;
                case Color2: chip2.setChecked(true); break;
                case Color3: chip3.setChecked(true); break;
                case Color4: chip4.setChecked(true); break;
                case Color5: chip5.setChecked(true); break;
                case Color6: chip6.setChecked(true); break;
                case Color7: chip7.setChecked(true); break;
            }
        }

        btnEnviar.setOnClickListener(v -> guardarNota());

        btnBack.setOnClickListener(v-> {
            startActivity(new Intent(CrearNotasActivity.this, GeneralActivity.class));
            finish();
        });

    }

    private void guardarNota() {
        String titulo = txtTituloEdit.getText().toString().trim();
        String descripcion = txtDescripcionEdit.getText().toString().trim();

        if(titulo.isEmpty() && descripcion.isEmpty()){
            Toast.makeText(this, "No hay alertas disponibles aún", Toast.LENGTH_SHORT).show();
            return;
        }

        ColorNota colorSeleccionado = ColorNota.Color1;
        if (chip1.isChecked()) colorSeleccionado = ColorNota.Color1;
        else if (chip2.isChecked()) colorSeleccionado = ColorNota.Color2;
        else if (chip3.isChecked()) colorSeleccionado = ColorNota.Color3;
        else if (chip4.isChecked()) colorSeleccionado = ColorNota.Color4;
        else if (chip5.isChecked()) colorSeleccionado = ColorNota.Color5;
        else if (chip6.isChecked()) colorSeleccionado = ColorNota.Color6;
        else if (chip7.isChecked()) colorSeleccionado = ColorNota.Color7;

        NotaManager manager = new NotaManager(this);

        if (esEdicion) {
            Nota nota = new Nota(notaId, titulo, descripcion, colorSeleccionado);
            manager.actualizarNota(nota);
            Toast.makeText(this, "Nota editada correctamente", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(CrearNotasActivity.this, GeneralActivity.class));
            finish();
        } else {
            Nota nota = new Nota(0, titulo, descripcion, colorSeleccionado);
            manager.guardarNota(nota);
            Toast.makeText(this, "Nota creada", Toast.LENGTH_SHORT).show();

            // limpiar campos
            txtTituloEdit.setText("");
            txtDescripcionEdit.setText("");

            // desseleccionar chips
            chip1.setChecked(false);
            chip2.setChecked(false);
            chip3.setChecked(false);
            chip4.setChecked(false);
            chip5.setChecked(false);
            chip6.setChecked(false);
            chip7.setChecked(false);
        }
    }
}