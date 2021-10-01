package com.example.tp4Grupo6.conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;





/* public class DataMainActivity extends AsyncTask<String, Void, String> {


   private ListView lvClientes;
    private Context context;

    private static String result2;
    private static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

    //Recibe por constructor el textview
    //Constructor
    public DataMainActivity(ListView lv, Context ct)
    {
        lvClientes = lv;
        context = ct;

    }

    @Override
    protected String doInBackground(String... urls) {
        String response = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cliente");
            result2 = " ";

            Cliente cliente;
            while(rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setDni(rs.getInt("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellidos"));
                listaClientes.add(cliente);
            }
            response = "Conexion exitosa";
        }
        catch(Exception e) {
            e.printStackTrace();
            result2 = "Conexion no exitosa";
        }
        return response;

    }

    @Override
    protected void onPostExecute(String response) {
        ClienteAdapter adapter = new ClienteAdapter(context, listaClientes);
        lvClientes.setAdapter(adapter);
    }
}
*/
