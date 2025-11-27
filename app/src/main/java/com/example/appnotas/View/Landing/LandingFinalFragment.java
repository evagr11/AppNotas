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

public class LandingFinalFragment extends Fragment {

    public LandingFinalFragment() {
        // Required empty public constructor
    }

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