package com.example.myapplication.Conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.myapplication.AltaFragment;
import com.example.myapplication.adapter.ArticuloAdapter;
import com.example.myapplication.adapter.CategoriaAdapter;
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

    private ListView lvArticulo;
    private Context context;
    private String accion = null;

    private Articulo articulo = new Articulo();
    private Categoria categoria = new Categoria();
    private Spinner spinner;
    private static String result2;
    private List<Articulo> listaArticulos = new ArrayList<Articulo>();
    private List<Categoria> listaCategorias = new ArrayList<Categoria>();


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
        listaArticulos.clear();
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
                    Articulo articulo;
                    while (rs.next()) {

                        articulo = new Articulo();
                        articulo.setId(rs.getInt("id"));
                        articulo.setNombre(rs.getString("nombre"));
                        articulo.setStock(rs.getInt("stock"));
                        articulo.setCategoria(rs.getInt("idCategoria"));
                        listaArticulos.add(articulo);
                    }
                    response = "Conexion exitosa";
                    break;
//                case "selectIDart":
//                    rs = st.executeQuery("SELECT * FROM articulo where id = " + articulo.getId());
//                    while (rs.next()) {
//                        articulo = new Articulo();
//                        articulo.setId(rs.getInt("id"));
//                        articulo.setNombre(rs.getString("nombre"));
//                        articulo.setStock(rs.getInt("stock"));
//                        articulo.setCategoria(rs.getInt("idCategoria"));
//                    }
//                    response = "Conexion exitosa";
//                    break;
//                case "selectIdCategoría":
//                    rs = st.executeQuery("SELECT * FROM categoria where id = " + categoria.getId());
//                    while (rs.next()) {
//                        categoria = new Categoria();
//                        categoria.setId(rs.getInt("id"));
//                        categoria.setDescripcion(rs.getString("descripcion"));
//                    }
//                    response = "Conexion exitosa";
//                    break;
//                case "selectTodasCategorías":
//                    rs = st.executeQuery("SELECT * FROM categoria");
//                    while (rs.next()) {
//                        categoria = new Categoria();
//                        categoria.setId(rs.getInt("id"));
//                        categoria.setDescripcion(rs.getString("descripcion"));
//
//                    }
//                    response = "Conexion exitosa";
//                    break;

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
                    lvArticulo.setAdapter(adapter);

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

