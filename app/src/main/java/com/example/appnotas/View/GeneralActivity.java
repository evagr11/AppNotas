package com.example.appnotas.View;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appnotas.R;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class GeneralActivity extends AppCompatActivity {

    private Chip chip1, chip2, chip3, chip4, chip5, chip6, chip7, chip8;
    private ListView listView;
    private TextView noNotas;
    private ArrayList<Nota> listaNotas = new ArrayList<>();
    private ArrayList<Nota> listaFiltrada = new ArrayList<>();
    private NotaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_general);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        chip1 = findViewById(R.id.chip1);
        chip2 = findViewById(R.id.chip2);
        chip3 = findViewById(R.id.chip3);
        chip4 = findViewById(R.id.chip4);
        chip5 = findViewById(R.id.chip5);
        chip6 = findViewById(R.id.chip6);
        chip7 = findViewById(R.id.chip7);
        chip8 = findViewById(R.id.chip8);
        noNotas = findViewById(R.id.textView3);
        listView = findViewById(R.id.listView);

        adapter = new NotaAdapter(getContext(), listaFiltrada);
        listView.setAdapter(adapter);

        cargarNotasDesdeArchivos();

        filtrado();

        edicion();

    }

    private void filtrado(){
        chip1.setOnCheckedChangeListener((b, c) -> filtrar());
        chip2.setOnCheckedChangeListener((b, c) -> filtrar());
        chip3.setOnCheckedChangeListener((b, c) -> filtrar());
        chip4.setOnCheckedChangeListener((b, c) -> filtrar());
        chip5.setOnCheckedChangeListener((b, c) -> filtrar());
        chip6.setOnCheckedChangeListener((b, c) -> filtrar());
        chip7.setOnCheckedChangeListener((b, c) -> filtrar());
        chip8.setOnCheckedChangeListener((b, c) -> filtrar());
    }
}