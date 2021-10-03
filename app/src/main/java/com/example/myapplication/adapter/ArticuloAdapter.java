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
        super(context, R.layout.fragment_listado, objetos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.fragment_listado, null);

        TextView tvid = (TextView) item.findViewById(R.id.et_id);
        TextView tvnombre = (TextView) item.findViewById(R.id.et_nombre);
        TextView tvStock = (TextView) item.findViewById(R.id.et_stock);
        Spinner tvCategoria = (Spinner) item.findViewById(R.id.s_categoria);

        tvid.setText(getItem(position).getId()+"");
        tvnombre.setText(getItem(position).getNombre()+"");
        tvStock.setText(getItem(position).getStock());
//        tvCategoria.setAdapter(getItem(position).getcategoria());

        return item;
    }
}