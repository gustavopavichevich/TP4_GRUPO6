package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

        TextView nombreLT = (TextView) item.findViewById(R.id.nombreLT);
        TextView stockLT = (TextView) item.findViewById(R.id.stockLT);

        nombreLT.setText(getItem(position).getNombre()+"");
        stockLT.setText(getItem(position).getStock());

        nombreLT.setText(getItem(position).getNombre()+"");
        stockLT.setText(getItem(position).getStock()+"");

        return item;
    }
}