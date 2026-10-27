package com.example.appnotas.View.Landing;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appnotas.R;
import com.example.appnotas.ViewModel.LandingViewModel;

public class LandingFechaFragment extends Fragment {

    private LandingViewModel viewModel;

    public LandingFechaFragment(LandingViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_landing_fecha, container, false);

        Button btnFecha = (Button) rootView.findViewById(R.id.btnFecha);
        EditText editFecha = (EditText) rootView.findViewById(R.id.editFecha);

        btnFecha.setOnClickListener(v ->{
            String fecha = editFecha.getText().toString();

            if (fecha.isEmpty()) {
                Toast.makeText(getContext(), "El año de nacimiento es obligatorio", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.setFechaNacimiento(fecha);
                viewModel.guardarUsuario(getContext());
                // A partir de ahora la app entra directamente a las notas
                getContext().getSharedPreferences(LandingActivity.PREFS_APP, Context.MODE_PRIVATE)
                        .edit().putBoolean(LandingActivity.KEY_PRIMERA_VEZ, false).apply();

                LandingFinalFragment finalFragment = new LandingFinalFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerLanding, finalFragment);
                transaction.commit();
            }
        });

        return rootView;
    }
}