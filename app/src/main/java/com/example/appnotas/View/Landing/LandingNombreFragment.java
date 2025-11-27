package com.example.appnotas.View.Landing;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.appnotas.R;

public class LandingNombreFragment extends Fragment {

    public LandingNombreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_landing_nombre, container, false);

        Button btnNombre = (Button) rootView.findViewById(R.id.btnNombre);

        btnNombre.setOnClickListener(v ->{
            LandingContrasenaFragment contrasenaFragment = new LandingContrasenaFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainerLanding, contrasenaFragment);
            transaction.commit();
        });

        return rootView;
    }
}