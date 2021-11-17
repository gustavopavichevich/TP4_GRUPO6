package com.example.myapplication.Conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.ListadoFragment;
import com.example.myapplication.adapter.ArticuloAdapter;
import com.example.myapplication.entidad.Articulo;
import com.example.myapplication.entidad.Categoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DataMainActivity extends AsyncTask<String, Void, String> {

    private ListView lvArticulo = null;
    private Context context = null;
    private String accion = null;
    private Articulo articulo = new Articulo();
    private Categoria categoria = new Categoria();

    private static String result2;
    private static ArrayList<Articulo> listaArticulos = new ArrayList<Articulo>();

    //Recibe por constructor el textview
    //Constructor
    public DataMainActivity(String accion, ListView lv, Context ct) {
        this.accion = accion;
        lvArticulo = lv;
        context = ct;
    }

    public DataMainActivity(String accion, int id, Articulo articulo, Context ct) {
        this.accion = accion;
        this.articulo.setId(id);
        context = ct;
    }

    public DataMainActivity(String accion, int id, Categoria categoria, Context ct) {
        this.accion = accion;
        this.categoria.setId(id);
        context = ct;
    }

    @Override
    protected String doInBackground(String... urls) {
        String response = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataBD.urlMySQL, DataBD.user, DataBD.pass);
            Statement st = con.createStatement();
            ResultSet rs;
            result2 = " ";
            Articulo art;
            Categoria cat;
            switch (accion) {
                case "selectArticulos":
                    rs = st.executeQuery("SELECT * FROM articulo");
                    while (rs.next()) {
                        art = new Articulo();
                        art.setId(rs.getInt("id"));
                        art.setNombre(rs.getString("nombre"));
                        art.setStock(rs.getInt("stock"));
                        art.setCategoria(rs.getInt("idCategoria"));
                        listaArticulos.add(art);
                    }
                    response = "Conexion exitosa";
                    break;
                case "selectIdArt":
                    rs = st.executeQuery("SELECT * FROM articulo where id = " + articulo.getId());
                    while (rs.next()) {
                        art = new Articulo();
                        art.setId(rs.getInt("id"));
                        art.setNombre(rs.getString("nombre"));
                        art.setStock(rs.getInt("stock"));
                        art.setCategoria(rs.getInt("idCategoria"));
                    }
                    response = "Conexion exitosa";
                    break;
                case "selectIdCategoría":
                    rs = st.executeQuery("SELECT * FROM categoria where id = " + categoria.getId());
                    while (rs.next()) {
                        cat = new Categoria();
                        cat.setId(rs.getInt("id"));
                        cat.setDescripcion(rs.getString("descripcion"));
                    }
                    response = "Conexion exitosa";
                    break;
                case "selectCategorías":
                    rs = st.executeQuery("SELECT * FROM categoria where id = " + categoria.getId());
                    while (rs.next()) {
                        cat = new Categoria();
                        cat.setId(rs.getInt("id"));
                        cat.setDescripcion(rs.getString("descripcion"));
                    }
                    response = "Conexion exitosa";
                    break;
                default:
                    break;
            }
        } catch (SQLDataException e) {
            e.printStackTrace();
            result2 = "Error al ejecutar sentencia SQL";
        } catch (Exception e) {
            e.printStackTrace();
            result2 = "Conexion no exitosa";
        }
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        try {
            switch (accion) {
                case "selectArticulos":
                    ArticuloAdapter adapter = new ArticuloAdapter(context, listaArticulos);
                    lvArticulo.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}

