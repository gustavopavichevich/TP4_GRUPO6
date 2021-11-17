package com.example.myapplication.Conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.example.myapplication.ListadoFragment;
import com.example.myapplication.adapter.ArticuloAdapter;
import com.example.myapplication.entidad.Articulos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DataMainActivity extends AsyncTask<String, Void, String> {

    private final ListView lvArticulo;
    private final Context context;
    private String accion = null;

    private static String result2;
    private List<Articulos> listaArticulos = new ArrayList<Articulos>();

    //Recibe por constructor el textview
    //Constructor
    public DataMainActivity(String accion, ListView lv, Context ct) {
        this.accion = accion;
        lvArticulo = lv;
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
            switch (accion) {
                case "select":
                    rs = st.executeQuery("SELECT * FROM articulo");
                    Articulos articulo;
                    while (rs.next()) {
                        articulo = new Articulos();
                        articulo.setId(rs.getInt("id"));
                        articulo.setNombre(rs.getString("nombre"));
                        articulo.setStock(rs.getInt("stock"));
                        articulo.setCategoria(rs.getInt("idCategoria"));
                        listaArticulos.add(articulo);
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
                case "select":
                    ArticuloAdapter adapter = new ArticuloAdapter(context, listaArticulos);
                    //lvArticulo.setAdapter(adapter);

                    //Se lo envio al activity
                    ListadoFragment flistado= new ListadoFragment();
                    Bundle bundle= new Bundle();
                    bundle.putSerializable("ListadoArticulos", adapter);
                    flistado.setArguments(bundle);
                    flistado.onStart();


                    break;
                default:
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }
}

