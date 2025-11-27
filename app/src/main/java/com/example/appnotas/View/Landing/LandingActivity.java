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

public class LandingActivity extends AppCompatActivity {
    public static final String PREFS_APP = "PreferenciasApp";
    public static final String KEY_PRIMERA_VEZ = "esPrimeraVez";


    private Button btnAcceder;
    private FragmentContainerView fragmentContainerLanding;

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
            }
        });
    }

    private boolean esPrimeraVez(){
        SharedPreferences prefs = getSharedPreferences(PREFS_APP, MODE_PRIVATE);
        boolean esPrimeraVez = prefs.getBoolean(KEY_PRIMERA_VEZ, true);

        if(esPrimeraVez){
            //PARA PRUEBAS, DESPUES CAMBIAR "(KEY_PRIMERA_VEZ, false)"
            prefs.edit().putBoolean(KEY_PRIMERA_VEZ, true).apply();
        }
        return esPrimeraVez;

    }
}