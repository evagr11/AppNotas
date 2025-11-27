package com.example.appnotas.View.Landing;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.appnotas.R;

public class LandingContrasenaFragment extends Fragment {

    public LandingContrasenaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_landing_contrasena, container, false);

        Button btnContrasena = (Button) rootView.findViewById(R.id.btnContrasena);

        btnContrasena.setOnClickListener(v ->{
            LandingFechaFragment fechaFragment = new LandingFechaFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainerLanding, fechaFragment);
            transaction.commit();
        });

        return rootView;
    }
}