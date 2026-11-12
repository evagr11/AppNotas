package com.example.appnotas.View.Landing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import com.example.appnotas.View.GeneralActivity;
import com.example.appnotas.R;

/**
 * Pantalla de bienvenida con la tortuga.
 *
 * <p>La primera vez abre el registro por pasos; las siguientes, entra
 * directamente a las notas.</p>
 */
public class LandingActivity extends AppCompatActivity {
    /**
     * Nombre del fichero de preferencias de la app.
     */
    public static final String PREFS_APP = "PreferenciasApp";
    /**
     * Clave que indica si todavía no se ha hecho el registro.
     */
    public static final String KEY_PRIMERA_VEZ = "esPrimeraVez";


    private Button btnAcceder;
    private FragmentContainerView fragmentContainerLanding;

    /**
     * Prepara el botón de acceso.
     *
     * @param savedInstanceState estado guardado de la Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_landing);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAcceder = findViewById(R.id.btnAcceder);

        fragmentContainerLanding = findViewById(R.id.fragmentContainerLanding);


        btnAcceder.setOnClickListener(v -> {
            if (esPrimeraVez()) {
                btnAcceder.setVisibility(View.GONE);
                fragmentContainerLanding.setVisibility(View.VISIBLE);

                LandingNombreFragment nombreFragment = new LandingNombreFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerLanding, nombreFragment);
                transaction.commit();
            } else {
                Intent intent = new Intent(LandingActivity.this, GeneralActivity.class);
                startActivity(intent);
                // Se cierra la bienvenida para que el botón atrás no vuelva a ella
                finish();
            }
        });
    }

    /**
     * @return {@code true} si el usuario aún no ha terminado el registro
     */
    private boolean esPrimeraVez(){
        SharedPreferences prefs = getSharedPreferences(PREFS_APP, MODE_PRIVATE);
        boolean esPrimeraVez = prefs.getBoolean(KEY_PRIMERA_VEZ, true);

        return esPrimeraVez;

    }
}