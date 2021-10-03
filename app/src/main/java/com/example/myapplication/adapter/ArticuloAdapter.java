package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.Conexion.Articulo;
import com.example.myapplication.R;

import java.util.List;



public class ArticuloAdapter extends ArrayAdapter<Articulo> {

    public ArticuloAdapter(Context context, List<Articulo> objetos) {
        super(context, R.layout.list_template, objetos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_template, null);

        TextView tvid = (TextView) item.findViewById(R.id.idC);
        TextView tvdni = (TextView) item.findViewById(R.id.dni);
        TextView tvnombre = (TextView) item.findViewById(R.id.nombre);
        TextView tvapellido = (TextView) item.findViewById(R.id.apellido);

        tvid.setText(getItem(position).getId()+"");
        tvdni.setText(getItem(position).getDni()+"");
        tvnombre.setText(getItem(position).getNombre());
        tvapellido.setText(getItem(position).getApellido());

        return item;
    }
}