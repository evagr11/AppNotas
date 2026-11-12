package com.example.appnotas.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.appnotas.Model.ColorNota;
import com.example.appnotas.Model.Nota;
import com.example.appnotas.Model.Usuario;
import com.example.appnotas.R;
import com.example.appnotas.ViewModel.LandingViewModel;
import com.example.appnotas.ViewModel.NotaManager;
import com.google.android.material.chip.Chip;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

/**
 * Pantalla principal: saludo, lista de notas, filtro por colores y menú lateral.
 */
public class GeneralActivity extends AppCompatActivity {

    private Chip chip1, chip2, chip3, chip4, chip5, chip6, chip7;
    private ListView listView;
    private TextView noNotas, saludo;
    private ArrayList<Nota> listaNotas = new ArrayList<>();
    private ArrayList<Nota> listaFiltrada = new ArrayList<>();
    private NotaAdapter adapter;

    private DrawerLayout drawerLayout;
    private NavigationView menuLateral;
    private ImageButton btnMenu;

    /**
     * Carga el nombre del usuario y sus notas y prepara el filtro, la edición y el menú lateral.
     *
     * @param savedInstanceState estado guardado de la Activity
     */
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
        drawerLayout = findViewById(R.id.drawerLayout);
        menuLateral = findViewById(R.id.menuLateral);
        btnMenu = findViewById(R.id.btnMenu);

        adapter = new NotaAdapter(this, listaFiltrada);
        listView.setAdapter(adapter);

        LandingViewModel viewModel = new LandingViewModel();
        Usuario usuario = viewModel.leerUsuario(this);
        String textoSaludo = getString(R.string.saludo, usuario.getNombre());
        saludo.setText(textoSaludo);

        configurarMenuLateral(textoSaludo);

        cargarNotasDesdeArchivos();

        filtrado();

        edicion();
    }

    // Menú lateral (diseño de Figma): Escribir nota, Opciones y Nota aleatoria
    /**
     * Pone el saludo en la cabecera del menú lateral y abre la pantalla elegida.
     *
     * @param textoSaludo saludo con el nombre del usuario
     */
    private void configurarMenuLateral(String textoSaludo) {
        TextView saludoMenu = menuLateral.getHeaderView(0).findViewById(R.id.txtSaludoMenu);
        saludoMenu.setText(textoSaludo);

        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        menuLateral.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            int id = item.getItemId();
            if (id == R.id.menuEscribir) {
                abrir(CrearNotasActivity.class);
            } else if (id == R.id.menuOpciones) {
                abrir(OpcionesActivity.class);
            } else if (id == R.id.menuAleatoria) {
                abrir(NotaAleatoriaActivity.class);
            }
            return true;
        });

        // Si el menú está abierto, el botón atrás lo cierra en vez de salir de la app
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    finish();
                }
            }
        });
    }

    /**
     * Abre otra pantalla y cierra esta.
     *
     * @param pantalla clase de la Activity que se abre
     */
    private void abrir(Class<?> pantalla) {
        startActivity(new Intent(GeneralActivity.this, pantalla));
        finish();
    }

    /**
     * Vuelve a filtrar la lista cada vez que se marca o desmarca un Chip de color.
     */
    private void filtrado(){
        chip1.setOnCheckedChangeListener((b, c) -> filtrar());
        chip2.setOnCheckedChangeListener((b, c) -> filtrar());
        chip3.setOnCheckedChangeListener((b, c) -> filtrar());
        chip4.setOnCheckedChangeListener((b, c) -> filtrar());
        chip5.setOnCheckedChangeListener((b, c) -> filtrar());
        chip6.setOnCheckedChangeListener((b, c) -> filtrar());
        chip7.setOnCheckedChangeListener((b, c) -> filtrar());
    }

    /**
     * Al pulsar una nota, la abre en {@link CrearNotasActivity} para editarla.
     */
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

    /**
     * Lee las notas guardadas y enseña el aviso si no hay ninguna.
     */
    private void cargarNotasDesdeArchivos() {
        listaNotas.clear();
        listaFiltrada.clear();

        listaNotas.addAll(new NotaManager(this).leerNotas());
        listaFiltrada.addAll(listaNotas);
        adapter.notifyDataSetChanged();

        noNotas.setVisibility(listaFiltrada.isEmpty() ? View.VISIBLE : View.GONE);
    }

    /**
     * Deja en la lista las notas de los colores marcados.
     * Si no hay ninguno marcado, o están todos, se ven todas.
     */
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

        noNotas.setVisibility(listaFiltrada.isEmpty() ? View.VISIBLE : View.GONE);

        adapter.notifyDataSetChanged();
    }
}
