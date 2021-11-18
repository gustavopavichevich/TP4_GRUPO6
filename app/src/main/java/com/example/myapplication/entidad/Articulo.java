package com.example.myapplication.entidad;

public class Articulo {

    private int id;
    private String nombre;
    private int stock;
    private int idCategoria;
    private Categoria categoria;

    public Articulo(){

    }

    public Articulo(int id, String nombre, int stock, int idCategoria, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.idCategoria = idCategoria;
        this.categoria =categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setCategoria(int categoria) {
        this.idCategoria = categoria;
    }
}
