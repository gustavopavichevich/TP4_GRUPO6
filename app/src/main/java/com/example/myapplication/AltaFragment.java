package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Conexion.DataArticuloActivity;
import com.example.myapplication.Conexion.DataMainActivity;
import com.example.myapplication.entidad.Articulo;

public class AltaFragment extends Fragment {
    private Button btnAgregar;
    private EditText txtId, txtNombre, txtStock, txtIdCategoria;
    private Spinner spinnerCat;
    private View view;

    public static AltaFragment newInstance() {
        return new AltaFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_alta, container, false);
        txtId = (EditText) view.findViewById(R.id.txtID);
        txtNombre = (EditText) view.findViewById(R.id.txtNombre);
        txtStock = (EditText)view.findViewById(R.id.txtStock);

        spinnerCat = (Spinner) view.findViewById(R.id.spinnerCategoria);
        btnAgregar = (Button) view.findViewById(R.id.btnModificar);
        cargarSpinner();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtId.getText().toString().isEmpty() || txtNombre.getText().toString().isEmpty() || txtStock.getText().toString().isEmpty()){

                    Toast.makeText(getActivity(), "Faltan completar campos", Toast.LENGTH_SHORT).show();
                    return;

                }

                try {
                    Integer id = Integer.parseInt(txtId.getText().toString());

                    Articulo art = new DataArticuloActivity(id).execute().get();
                    if (art != null) {
                        Toast.makeText(getActivity(), "El ID ingresado ya existe", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Articulo _art = new Articulo(id,txtNombre.getText().toString(),
                            Integer.parseInt(txtStock.getText().toString()), spinnerCat.getSelectedItemPosition() +1,null);

                    DataMainActivity InsertArticulo = new DataMainActivity("insertArticulo",_art);
                    String resultado = InsertArticulo.execute().get();

                    Toast.makeText(getActivity(), resultado, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error al insertar", Toast.LENGTH_SHORT).show();
                }
            }

        });
        return view;
    }

    public void cargarSpinner() {
        DataMainActivity carga = new DataMainActivity("selectCategorias",spinnerCat,getActivity());
        carga.execute();
    }
}