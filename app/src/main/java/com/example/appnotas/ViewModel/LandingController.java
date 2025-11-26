package com.example.appnotas.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.appnotas.View.GeneralActivity;
import com.example.appnotas.View.Landing.LandingActivity;

public class LandingController {
    public static final String PREFS_APP = "PreferenciasApp";
    public static final String KEY_PRIMERA_VEZ = "esPrimeraVez";


    public Intent obtenerIntentAcceso(LandingActivity context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_APP, Context.MODE_PRIVATE);
        boolean esPrimeraVez = prefs.getBoolean(KEY_PRIMERA_VEZ, true);

        if (esPrimeraVez) {
            prefs.edit().putBoolean(KEY_PRIMERA_VEZ, false).apply();
            return new Intent(context, Info1.class);
        } else {
            return new Intent(context, GeneralActivity.class);
        }
    }
}
