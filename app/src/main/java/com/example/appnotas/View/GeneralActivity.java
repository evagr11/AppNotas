package com.example.appnotas.View;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appnotas.Model.ColorNota;
import com.example.appnotas.Model.Nota;
import com.example.appnotas.Model.Usuario;
import com.example.appnotas.R;
import com.example.appnotas.ViewModel.LandingViewModel;
import com.google.android.material.chip.Chip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class GeneralActivity extends AppCompatActivity {

    private Chip chip1, chip2, chip3, chip4, chip5, chip6, chip7;
    private ListView listView;
    private TextView noNotas, saludo;
    private ArrayList<Nota> listaNotas = new ArrayList<>();
    private ArrayList<Nota> listaFiltrada = new ArrayList<>();
    private NotaAdapter adapter;

    private Button button;

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

        noNotas = findViewById(R.id.textView3);
        saludo = findViewById(R.id.textView5);
        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);

        adapter = new NotaAdapter(this, listaFiltrada);
        listView.setAdapter(adapter);

        button.setOnClickListener(v ->{
            startActivity(new Intent(GeneralActivity.this, CrearNotasActivity.class));
            finish();
        });

        LandingViewModel viewModel = new LandingViewModel();
        Usuario usuario = viewModel.leerUsuario(this);

        saludo.setText("Hola, " + usuario.getNombre());

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
    }

    private void edicion() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Nota seleccionada = listaFiltrada.get(position);

            Intent intent = new Intent(GeneralActivity.this, CrearNotasActivity.class);
            intent.putExtra("id", seleccionada.getId());
            intent.putExtra("titulo", seleccionada.getTitulo());
            intent.putExtra("descripcion", seleccionada.getDescripcion());
            intent.putExtra("color", seleccionada.getColor().name());
            startActivity(intent);
            finish();
        });
    }

    private void cargarNotasDesdeArchivos() {
        listaNotas.clear();
        listaFiltrada.clear();

        File carpeta = new File(getFilesDir(), "USUARIO");
        File[] archivos = carpeta.listFiles();

        if (archivos != null) {
            for (int i = 0; i < archivos.length; i++) {
                File archivo = archivos[i];
                try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                    String linea = br.readLine();

                    String[] partes = linea.split("\\|");

                    String titulo = partes[0].trim();
                    String descripcion = partes[1].trim();
                    String colorStr = partes[2].trim();
                    int id = Integer.parseInt(partes[3].trim());

                    ColorNota color;

                    switch (colorStr) {
                        case "Color1": color = ColorNota.Color1;
                            break;
                        case "Color2": color = ColorNota.Color2;
                            break;
                        case "Color3": color = ColorNota.Color3;
                            break;
                        case "Color4": color = ColorNota.Color4;
                            break;
                        case "Color5": color = ColorNota.Color5;
                            break;
                        case "Color6": color = ColorNota.Color6;
                            break;
                        case "Color7": color = ColorNota.Color7;
                            break;
                        case "Color8": color = ColorNota.Color8;
                            break;
                        default: throw new Exception("");
                    }

                    listaNotas.add(new Nota(id, titulo, descripcion, color));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Collections.reverse(listaNotas);
            listaFiltrada.addAll(listaNotas);
            adapter.notifyDataSetChanged();
        }
    }


    private void filtrar() {

        boolean filtroColor1 = chip1.isChecked();
        boolean filtroColor2 = chip2.isChecked();
        boolean filtroColor3 = chip3.isChecked();
        boolean filtroColor4 = chip4.isChecked();
        boolean filtroColor5 = chip5.isChecked();
        boolean filtroColor6 = chip6.isChecked();
        boolean filtroColor7 = chip7.isChecked();

        boolean ninguno = !filtroColor1 && !filtroColor2 && !filtroColor3 && !filtroColor4 && !filtroColor5 && !filtroColor6 && !filtroColor7;
        boolean todos = filtroColor1 && filtroColor2 && filtroColor3 && filtroColor4 && filtroColor5 && filtroColor6 && filtroColor7;

        listaFiltrada.clear();

        if (ninguno || todos) {
            listaFiltrada.addAll(listaNotas);
        } else {
            for (int i = 0; i < listaNotas.size(); i++) {
                ColorNota color = listaNotas.get(i).getColor();

                if ((filtroColor1 && color == ColorNota.Color1) ||
                        (filtroColor2 && color == ColorNota.Color2) ||
                        (filtroColor3 && color == ColorNota.Color3) ||
                        (filtroColor4 && color == ColorNota.Color4) ||
                        (filtroColor5 && color == ColorNota.Color5) ||
                        (filtroColor6 && color == ColorNota.Color6) ||
                        (filtroColor7 && color == ColorNota.Color7)) {

                    listaFiltrada.add(listaNotas.get(i));
                }
            }

        }

        if(listaFiltrada.isEmpty()){
            noNotas.setVisibility(View.VISIBLE);
        } else{
            noNotas.setVisibility(View.GONE);
        }

        adapter.notifyDataSetChanged();
    }


}