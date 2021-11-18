package com.example.myapplication.entidad;

import java.io.Serializable;

public class Articulo implements Serializable {

    private int id;
    private String nombre;
    private int stock;
    private int idCategoria;
    private Categoria categoria;

    public Articulo(){

    }

    public Articulo( String nombre, int stock, int idCategoria, Categoria categoria) {
     //   this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.idCategoria = idCategoria;
        this.categoria =categoria;
    }
    public Articulo( int id, String nombre, int stock, int idCategoria, Categoria categoria) {
           this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.idCategoria = idCategoria;
        this.categoria =categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getIdCategoria() { return idCategoria; }

    public void setIdCategoria(Integer idCategoria) { this.idCategoria = idCategoria; }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", stock=" + stock +
                ", idCategoria=" + idCategoria +
                ", categoria=" + categoria +
                '}';
    }

}
