package com.example.parc2_j_chavarria_f_pablu.Helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.parc2_j_chavarria_f_pablu.R;

import java.util.ArrayList;
import java.util.List;

public class NotasAdapter extends ArrayAdapter<Notas> {

    private List<Notas> notasOpciones = new ArrayList<>();
    public NotasAdapter(@NonNull Context context, @NonNull List<Notas> objects) {
        super(context, R.layout.plantilla_notas, objects);

        notasOpciones = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.plantilla_notas, null);

        ImageView imvMateria = (ImageView) item.findViewById(R.id.imvMateria);
        imvMateria.setImageResource(notasOpciones.get(position).getImagenMateria());
        TextView lblMateria = (TextView) item.findViewById(R.id.lblMateria);
        lblMateria.setText(notasOpciones.get(position).getNombreMateria());
        TextView lblDescripcion = (TextView) item.findViewById(R.id.lblDescripcionNota);
        lblDescripcion.setText(notasOpciones.get(position).getDescipcion());
        TextView lblNota = (TextView) item.findViewById(R.id.lblNota);
        lblNota.setText(notasOpciones.get(position).getNota());
        ImageView imvStatus = (ImageView) item.findViewById(R.id.imvStatus);
        imvStatus.setImageResource(notasOpciones.get(position).getImagenStatus());
        return item;
    }


}
