package com.example.appnotas.View;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.appnotas.Model.Nota;
import com.example.appnotas.R;

import java.util.List;

public class NotaAdapter extends ArrayAdapter<Nota> {

    public NotaAdapter(Context context, List<Nota> notas) {
        super(context, 0, notas);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_nota, parent, false);

        Nota nota = getItem(position);

        TextView titulo = itemView.findViewById(R.id.Titulo);
        TextView descripcion = itemView.findViewById(R.id.Descripcion);

        titulo.setText(nota.getTitulo());
        descripcion.setText(nota.getDescripcion());

        ConstraintLayout fondo = itemView.findViewById(R.id.itemNotaLayout);

        switch (nota.getColor()) {
            case Color1:
                fondo.setBackgroundResource(R.drawable.rectangulo_fondo_lista);
                break;
            case Color2:
                fondo.setBackgroundResource(R.drawable.rectangulo_fondo_lista2);
                break;
            case Color3:
                fondo.setBackgroundResource(R.drawable.rectangulo_fondo_lista3);
                break;
            case Color4:
                fondo.setBackgroundResource(R.drawable.rectangulo_fondo_lista4);
                break;
            case Color5:
                fondo.setBackgroundResource(R.drawable.rectangulo_fondo_lista5);
                break;
            case Color6:
                fondo.setBackgroundResource(R.drawable.rectangulo_fondo_lista6);
                break;
            case Color7:
                fondo.setBackgroundResource(R.drawable.rectangulo_fondo_lista7);
                break;
        }



        return itemView;
    }
}
