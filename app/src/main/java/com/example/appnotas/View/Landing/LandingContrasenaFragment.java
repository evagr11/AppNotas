package com.example.appnotas.View.Landing;

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

public class LandingContrasenaFragment extends Fragment {

    private LandingViewModel viewModel;

    public LandingContrasenaFragment(LandingViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_landing_contrasena, container, false);

        Button btnContrasena = (Button) rootView.findViewById(R.id.btnContrasena);
        EditText editContrasena = (EditText) rootView.findViewById(R.id.editContrasena);

        btnContrasena.setOnClickListener(v ->{
            String contrasena = editContrasena.getText().toString();
            if (contrasena.isEmpty()) {
                Toast.makeText(getContext(), "El contraseña es obligatorio", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.setContrasena(contrasena);

                LandingFechaFragment fechaFragment = new LandingFechaFragment(viewModel);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerLanding, fechaFragment);
                transaction.commit();
            }
        });

        return rootView;
    }
}