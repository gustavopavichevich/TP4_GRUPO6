package com.example.myapplication.Conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataMainActivity {
    public class DataMainActivity extends AsyncTask<String, Void, String> {


        private ListView lvarticulo;
        private Context context;

        private static String result2;
        private static ArrayList<Articulo> listaArticulos = new ArrayList<Articulo>();

        //Recibe por constructor el textview
        //Constructor
        public DataMainActivity(ListView lv, Context ct)
        {
            lvarticulo = lv;
            context = ct;

        }

        @Override
        protected String doInBackground(String... urls) {
            String response = "";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(DataBD.urlMySQL, DataBD.user, DataBD.pass);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM cliente");
                result2 = " ";

                Articulo articulo;
                while(rs.next()) {
                    articulo = new Articulo();
                    articulo.setId(rs.getInt("id"));
                    articulo.setNombre(rs.getString("nombre"));
                    articulo.setStock(rs.getInt("stock"));
                    articulo.setIdcategoria(rs.getInt("idcategoria"));
                    listaArticulos.add(articulo);
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
            ArticuloAdapter adapter = new ArticuloAdapter(context, listaArticulos);
            lvarticulo.setAdapter(adapter);
        }
    }
}
