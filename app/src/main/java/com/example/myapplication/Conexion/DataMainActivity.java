package com.example.myapplication.Conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.adapter.ProductoAdapter;
import com.example.myapplication.entidad.Categoria;
import com.example.myapplication.entidad.Producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.Statement;
import java.util.ArrayList;


public class DataMainActivity extends AsyncTask<String, Void, String> {

    private ListView lvProductos;
    private Context context;
    private String accion = null;
    private EditText txtId;
    private Producto producto;
    private int maximo;
    private Categoria categoria;
    private Spinner spinnerCat;
    private static String result2;
    private static final ArrayList<Producto> listaProductos = new ArrayList<Producto>();
    private static final ArrayList<String> listaCategorias = new ArrayList<String>();

    //Recibe por constructor el textview
    //Constructor
    public DataMainActivity(String accion, Producto producto) {
        this.accion = accion;
        this.producto = producto;
    }

    public DataMainActivity(String accion, ListView lv, Context ct) {
        this.accion = accion;
        lvProductos = lv;
        context = ct;
    }

    public DataMainActivity(String accion, Spinner spinnerCat, Context ct) {
        this.accion = accion;
        this.spinnerCat = spinnerCat;
        this.context = ct;
    }

    public DataMainActivity(String accion, EditText txtId, Context ct) {
        this.accion = accion;
        this.txtId = txtId;
        this.context = ct;
    }

    @Override
    protected String doInBackground(String... urls) {
        listaProductos.clear();
        String response = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataBD.urlMySQL, DataBD.user, DataBD.pass);
            Statement st = con.createStatement();
            ResultSet rs;
            result2 = " ";
            switch (accion) {
                case "selectProducto":
                    rs = st.executeQuery("SELECT a.id, a.nombre, a.stock, a.idCategoria, c.descripcion FROM articulo a, categoria c where a.idCategoria = c.id order by a.nombre");
                    Producto producto;
                    Categoria categoria;
                    while (rs.next()) {
                        producto = new Producto();
                        categoria = new Categoria();
                        producto.setId(rs.getInt("id"));
                        producto.setNombre(rs.getString("nombre"));
                        producto.setStock(rs.getInt("stock"));
                        producto.setIdCategoria(rs.getInt("idCategoria"));
                        categoria.setDescripcion(rs.getString("descripcion"));
                        producto.setCategoria(categoria);
                        listaProductos.add(producto);
                    }
                    response = "Conexion exitosa";
                    rs.close();
                    break;

                case "traeMax":
                    rs = st.executeQuery("SELECT max(id) +1 as 'maxi' FROM articulo");
                    while (rs.next()) {
                        maximo = rs.getInt("maxi");
                    }
                    response = "Conexion exitosa";
                    rs.close();
                    break;

                case "insertProducto":
                    st.executeUpdate("INSERT INTO articulo(nombre,stock,idCategoria) VALUES('" +
                            this.producto.getNombre() + "'," + this.producto.getStock() + "," + this.producto.getIdCategoria() + ")");
                    response = "Producto insertado";
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
                    rs.close();
                    break;
                case "updateProducto":
                    st.executeUpdate("UPDATE articulo SET " +
                            "nombre = '" + this.producto.getNombre() + "', " +
                            "stock = " + this.producto.getStock() + ", " +
                            "idCategoria = " + this.producto.getIdCategoria() +
                            " WHERE id = " + this.producto.getId());
                    response = "Producto modificado";
                    break;

                default:

                    break;
            }
            st.close();
            con.close();
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
                case "selectProducto":
                    ProductoAdapter adapter = new ProductoAdapter(context, listaProductos);
                    adapter.notifyDataSetChanged();
                    lvProductos.setAdapter(adapter);
                    break;
                case "selectCategorias":
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listaCategorias);
                    spinnerCat.setAdapter(adapter2);
                    break;
                case "traeMax":

                    txtId.setText(String.valueOf(maximo), TextView.BufferType.EDITABLE);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}

