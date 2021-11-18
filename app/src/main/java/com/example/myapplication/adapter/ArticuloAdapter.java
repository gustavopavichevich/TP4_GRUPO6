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

public class ArticuloAdapter extends ArrayAdapter<Articulo>{

    private Context context;

    public ArticuloAdapter(Context context, List<Articulo> lvArticulos) {
        super(context, R.layout.list_template, lvArticulos);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_template, null);

        TextView tvId = item.findViewById(R.id.idArticulo);
        TextView tvNombre = item.findViewById(R.id.nombreArt);
        TextView tvStock = item.findViewById(R.id.stockArt);
        TextView tvIdCategoria = item.findViewById(R.id.idCategoria);

        tvId.setText("ID: " + getItem(position).getId()+"");
        tvNombre.setText(getItem(position).getNombre());
        tvStock.setText("Stock: "+ getItem(position).getStock()+"");
        tvIdCategoria.setText("Categoría: "+ getItem(position).getIdCategoria()+"");
        return item;
    }
}