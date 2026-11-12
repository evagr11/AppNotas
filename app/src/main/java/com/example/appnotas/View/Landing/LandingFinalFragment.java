package com.example.appnotas.View.Landing;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.appnotas.R;
import com.example.appnotas.View.GeneralActivity;

/**
 * Pantalla de registro completado; a los 3 segundos abre las notas.
 */
public class LandingFinalFragment extends Fragment {

    /**
     * Constructor vacío que necesita Android para crear el Fragment.
     */
    public LandingFinalFragment() {
        // Required empty public constructor
    }

    /**
     * Infla la pantalla y programa el paso a {@code GeneralActivity}.
     *
     * @param inflater           inflador de vistas
     * @param container          contenedor del Fragment
     * @param savedInstanceState estado guardado
     * @return la vista del Fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_landing_final, container, false);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(getActivity(), GeneralActivity.class);
            startActivity(intent);
            getActivity().finish();
        }, 3000);

        return rootView;
    }
}