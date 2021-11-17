package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entidad.Articulo;
import com.example.myapplication.entidad.Categoria;

import java.io.Serializable;
import java.util.List;

public class CategoriaAdapter extends ArrayAdapter<Categoria> implements Serializable {

    private Context context;

    public CategoriaAdapter(Context context, List<Categoria> categorias) {
        super(context, R.layout.fragment_alta, categorias);
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.fragment_alta, null);
        Spinner spinner = item.findViewById(R.id.s_categoria);
      //  spinner.setAdapter(getItem(position).ge);

     /*   TextView tvNombre = (TextView) item.findViewById(R.id.nombreLT);
        TextView tvStock = (TextView) item.findViewById(R.id.stockLT);

        tvNombre.setText(getItem(position).getNombre()+"");
        tvStock.setText(getItem(position).getStock());*/

        return item;
    }
}