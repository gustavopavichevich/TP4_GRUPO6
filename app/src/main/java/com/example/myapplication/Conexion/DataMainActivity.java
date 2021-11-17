package com.example.myapplication.Conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.myapplication.AltaFragment;
import com.example.myapplication.ListadoFragment;
import com.example.myapplication.adapter.ArticuloAdapter;

import com.example.myapplication.adapter.CategoriaAdapter;
import com.example.myapplication.entidad.Articulo;
import com.example.myapplication.entidad.Categoria;
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

    private final Articulo articulo = new Articulo();
    private final Categoria categoria = new Categoria();
    private Spinner spinner;
    private static String result2;
    private final List<Articulo> listaArticulos = new ArrayList<Articulo>();
    private final List<Categoria> listaCategorias = new ArrayList<Categoria>();


    //Recibe por constructor el textview
    //Constructor
    public DataMainActivity(String accion, Spinner sp , Context ct) {
        this.accion = accion;
        spinner = sp;
        context = ct;
    }

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

                        art = new Articulo();
                        art.setId(rs.getInt("id"));
                        art.setNombre(rs.getString("nombre"));
                        art.setStock(rs.getInt("stock"));
                        art.setCategoria(rs.getInt("idCategoria"));
                        listaArticulos.add(art);
                    }
                    response = "Conexion exitosa";
                    break;
                case "selectIDart":
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
                case "selectTodasCategorías":
                    rs = st.executeQuery("SELECT * FROM categoria");
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
                case "select":
                    ArticuloAdapter adapter = new ArticuloAdapter(context, listaArticulos);

                    //lvArticulo.setAdapter(adapter);


                    ListadoFragment flistado = new ListadoFragment();
                    Bundle bundle = new Bundle();

                    bundle.putSerializable("ListadoArticulos", adapter);
                    flistado.setArguments(bundle);
                    flistado.onStart();


                    break;

                case "selectTodasCategorías":
                    CategoriaAdapter adapterCate = new CategoriaAdapter(context, listaCategorias);
                    spinner.setAdapter(adapterCate);
                    //lvArticulo.setAdapter(adapter);

                    //Se lo envio al activity
                  AltaFragment falta = new AltaFragment();
                    Bundle bundle2 = new Bundle();
                    bundle2.putSerializable("ListadoCategorias", adapterCate);
                    falta.setArguments(bundle2);

                    /*ListadoFragment flistado = new ListadoFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ListadoArticulos", adapter);
                    flistado.setArguments(bundle);*/
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

