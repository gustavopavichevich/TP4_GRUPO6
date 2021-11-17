package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.entidad.Articulo;
import com.example.myapplication.R;

import java.io.Serializable;
import java.util.List;

public class ArticuloAdapter extends ArrayAdapter<Articulo> implements Serializable {

    private Context context;

    public ArticuloAdapter(Context context, List<Articulo> articulos) {
        super(context, R.layout.list_template, articulos);
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.list_template, null);

        TextView tvNombre = (TextView) item.findViewById(R.id.nombreLT);
        TextView tvStock = (TextView) item.findViewById(R.id.stockLT);

        tvNombre.setText(getItem(position).getNombre()+"");
        tvStock.setText(getItem(position).getStock());

        return item;
    }
}