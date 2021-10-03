package com.example.myapplication.Conexion;

import android.widget.SpinnerAdapter;

public class Articulo {

    int Id;
    String nombre;
    int stock;
    String categoria;

    public SpinnerAdapter getcategoria() {
        return categoria;
    }

    public void setcategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void Articulo() {

    }

}
