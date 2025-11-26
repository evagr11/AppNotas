package com.example.appnotas.View.Landing;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appnotas.ViewModel.LandingController;
import com.example.appnotas.R;

public class LandingActivity extends AppCompatActivity {


    private LandingController controller;

    private Button btnAcceder;

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

        controller = new LandingController();

        btnAcceder.setOnClickListener(v -> {
            Intent intent = controller.obtenerIntentAcceso(this);
            startActivity(intent);
            finish();
        });
    }
}