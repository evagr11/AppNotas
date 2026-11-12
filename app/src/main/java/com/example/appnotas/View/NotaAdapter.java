package com.example.appnotas.View;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.appnotas.Model.ColorNota;
import com.example.appnotas.Model.Nota;
import com.example.appnotas.R;

import java.util.List;

/**
 * Adaptador de la lista que pinta cada nota con su título, su descripción y su fondo de color.
 */
public class NotaAdapter extends ArrayAdapter<Nota> {

    /**
     * @param context contexto de la lista
     * @param notas   notas que se muestran
     */
    public NotaAdapter(Context context, List<Nota> notas) {
        super(context, 0, notas);
    }

    /**
     * Infla {@code item_nota} y lo rellena con la nota de esa posición.
     *
     * @param position    posición de la nota
     * @param convertView vista reciclada (no se usa)
     * @param parent      lista padre
     * @return la vista de la nota
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_nota, parent, false);

        Nota nota = getItem(position);

        TextView titulo = itemView.findViewById(R.id.Titulo);
        TextView descripcion = itemView.findViewById(R.id.Descripcion);

        titulo.setText(nota.getTitulo());
        descripcion.setText(nota.getDescripcion());

        ConstraintLayout fondo = itemView.findViewById(R.id.itemNotaLayout);

        fondo.setBackgroundResource(fondoDe(nota.getColor()));

        return itemView;
    }

    // Fondo redondeado que corresponde a cada color (también lo usa la nota aleatoria)
    /**
     * Devuelve el fondo redondeado de un color. También lo usa la nota aleatoria.
     *
     * @param color color de la nota
     * @return id del drawable del fondo
     */
    public static int fondoDe(ColorNota color) {
        switch (color) {
            case Color2: return R.drawable.rectangulo_fondo_lista2;
            case Color3: return R.drawable.rectangulo_fondo_lista3;
            case Color4: return R.drawable.rectangulo_fondo_lista4;
            case Color5: return R.drawable.rectangulo_fondo_lista5;
            case Color6: return R.drawable.rectangulo_fondo_lista6;
            case Color7: return R.drawable.rectangulo_fondo_lista7;
            default: return R.drawable.rectangulo_fondo_lista;
        }
    }

}
