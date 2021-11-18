package com.example.myapplication.Conexion;

import android.os.AsyncTask;

import com.example.myapplication.entidad.Producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataProductoActivity extends AsyncTask<String, Void, Producto> {

    private int id;

    private Producto producto;

    public DataProductoActivity(int id) {
        this.id = id;
    }

    @Override
    protected Producto doInBackground(String... strings) {
        producto = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataBD.urlMySQL, DataBD.user, DataBD.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM articulo WHERE id = " + id);

            while (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setStock(rs.getInt("stock"));
                producto.setIdCategoria(rs.getInt("idCategoria"));
                rs.close();
                st.close();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return producto;
    }
}
