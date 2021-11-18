package com.example.myapplication.Conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.myapplication.adapter.ArticuloAdapter;
import com.example.myapplication.entidad.Articulo;
import com.example.myapplication.entidad.Categoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.Statement;
import java.util.ArrayList;


public class DataMainActivity extends AsyncTask<String, Void, String> {

    private ListView lvArticulo;
    private Context context;
    private String accion = null;

    private Articulo articulo;
    private Categoria categoria;
    private Spinner spinnerCat;
    private static String result2;
    private static final ArrayList<Articulo> listaArticulos = new ArrayList<Articulo>();
    private static final ArrayList<String> listaCategorias = new ArrayList<String>();

    //Recibe por constructor el textview
    //Constructor
    public DataMainActivity(String accion, Articulo articulo) {
        this.accion = accion;
        this.articulo = articulo;
    }

    public DataMainActivity(String accion, ListView lv, Context ct) {
        this.accion = accion;
        lvArticulo = lv;
        context = ct;
    }

    public DataMainActivity(String accion, Spinner spinnerCat, Context ct) {
        this.accion = accion;
        this.spinnerCat = spinnerCat;
        this.context = ct;
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
                case "selectArticulos":
                    rs = st.executeQuery("SELECT * FROM articulo");
                    Articulo articulo;
                    while (rs.next()) {
                        articulo = new Articulo();
                        articulo.setId(rs.getInt("id"));
                        articulo.setNombre(rs.getString("nombre"));
                        articulo.setStock(rs.getInt("stock"));
                        articulo.setIdCategoria(rs.getInt("idCategoria"));
                        listaArticulos.add(articulo);
                    }
                    response = "Conexion exitosa";
                    break;
                case "selectIDart":
                    rs = st.executeQuery("SELECT * FROM articulo where id = " + this.articulo.getId());
                    while (rs.next()) {
                        this.articulo.setId(rs.getInt("id"));
                        this.articulo.setNombre(rs.getString("nombre"));
                        this.articulo.setStock(rs.getInt("stock"));
                        this.articulo.setIdCategoria(rs.getInt("idCategoria"));
                    }
                    response = "Conexion exitosa";

                    break;
                case "insertArticulo":
                    st.executeUpdate("INSERT INTO articulo(id,nombre,stock,idCategoria) VALUES(" +
                            this.articulo.getId() + ",'" + this.articulo.getNombre() + "'," + this.articulo.getStock() + "," + this.articulo.getIdCategoria() + ")");
                    break;
                case "selectIdCategoria":
                    rs = st.executeQuery("SELECT * FROM categoria where id = " + categoria.getId());
                    while (rs.next()) {
                        categoria = new Categoria();
                        categoria.setId(rs.getInt("id"));
                        categoria.setDescripcion(rs.getString("descripcion"));
                    }
                    response = "Conexion exitosa";
                    break;
                case "selectCategorias":
                    listaCategorias.clear();
                    rs = st.executeQuery("SELECT * FROM categoria");
                    while (rs.next()) {
                        categoria = new Categoria();
                        categoria.setId(rs.getInt("id"));
                        categoria.setDescripcion(rs.getString("descripcion"));
                        listaCategorias.add(categoria.getDescripcion());
                    }
                    response = "Conexion exitosa";
                    break;
                case "uptadteArticulo":
                    st.executeUpdate("UPDATE articulo SET " +
                            "nombre = '" + this.articulo.getNombre() + "', " +
                            "stock = " + this.articulo.getStock() + ", " +
                            "idCategoria = " + this.articulo.getIdCategoria() + ", " +
                            "WHERE id = " + this.articulo.getId());
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
                case "selectIDart":

                    break;
                case "selectCategorias":
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listaCategorias);
                    spinnerCat.setAdapter(adapter2);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}

