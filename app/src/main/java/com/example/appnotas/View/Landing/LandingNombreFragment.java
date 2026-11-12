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

/**
 * Primer paso del registro: pide el nombre.
 */
public class LandingNombreFragment extends Fragment {

    private LandingViewModel viewModel = new LandingViewModel();

    /**
     * Constructor vacío; crea el {@link com.example.appnotas.ViewModel.LandingViewModel} que se pasa a los siguientes pasos.
     */
    public LandingNombreFragment() {
        // Required empty public constructor
    }

    /**
     * Comprueba que el nombre no esté vacío y pasa a {@link LandingContrasenaFragment}.
     *
     * @param inflater           inflador de vistas
     * @param container          contenedor del Fragment
     * @param savedInstanceState estado guardado
     * @return la vista del Fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_landing_nombre, container, false);

        Button btnNombre = (Button) rootView.findViewById(R.id.btnNombre);
        EditText editNombre = (EditText) rootView.findViewById(R.id.editNombre);

        btnNombre.setOnClickListener(v ->{
            String nombre = editNombre.getText().toString();
            if (nombre.isEmpty()) {
                Toast.makeText(getContext(), "El nombre es obligatorio", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.setNombre(nombre);

                LandingContrasenaFragment contrasenaFragment = new LandingContrasenaFragment(viewModel);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerLanding, contrasenaFragment);
                transaction.commit();
            }
        });

        return rootView;
    }
}