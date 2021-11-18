package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.entidad.Producto;
import com.example.myapplication.R;

import java.util.List;

public class ProductoAdapter extends ArrayAdapter<Producto>{

    private Context context;

    public ProductoAdapter(Context context, List<Producto> lvProductos) {
        super(context, R.layout.list_template, lvProductos);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_template, null);

        TextView tvId = item.findViewById(R.id.idProducto);
        TextView tvNombre = item.findViewById(R.id.nombreArt);
        TextView tvStock = item.findViewById(R.id.stockArt);
        TextView tvDescription = item.findViewById(R.id.description);

        tvId.setText("ID: " + getItem(position).getId()+"");
        tvNombre.setText(getItem(position).getNombre());
        tvStock.setText("Stock: "+ getItem(position).getStock()+"");
        tvDescription.setText("Categoría: "+ getItem(position).getCategoria().getDescripcion()+"");
        return item;
    }

}