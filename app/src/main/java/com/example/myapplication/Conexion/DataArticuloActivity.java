package com.example.myapplication.Conexion;

import android.os.AsyncTask;

import com.example.myapplication.entidad.Articulo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataArticuloActivity extends AsyncTask<String, Void, Articulo> {

    private int id;

    private Articulo articulo;

    public DataArticuloActivity(int id) {
        this.id = id;
    }

    @Override
    protected Articulo doInBackground(String... strings) {
        articulo = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataBD.urlMySQL, DataBD.user, DataBD.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM articulo WHERE id = " + id);

            while (rs.next()) {
                articulo = new Articulo();
                articulo.setId(rs.getInt("id"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setStock(rs.getInt("stock"));
                articulo.setIdCategoria(rs.getInt("idCategoria"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return articulo;
    }
}
