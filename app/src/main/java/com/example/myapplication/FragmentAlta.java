package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Conexion.DataArticuloActivity;
import com.example.myapplication.Conexion.DataMainActivity;
import com.example.myapplication.entidad.Articulo;

public class FragmentAlta extends Fragment {
    private Button btnAgregar;
    private EditText txtId, txtNombre, txtStock;
    private Spinner spinnerCat;
    private View view;
    public static final String titulo = "Alta";

    public static FragmentAlta newInstance() {
        return new FragmentAlta();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_alta, container, false);
        txtId = (EditText) view.findViewById(R.id.txtID);
        txtNombre = (EditText) view.findViewById(R.id.txtNombre);
        txtStock = (EditText)view.findViewById(R.id.txtStock);
        spinnerCat = (Spinner) view.findViewById(R.id.spinnerCategoria);
        btnAgregar = (Button) view.findViewById(R.id.btnAgregar);
        TraerMaximo();
        cargarSpinner();
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtNombre.getText().toString().isEmpty() || txtStock.getText().toString().isEmpty()){

                    Toast.makeText(getActivity(), "Faltan completar campos", Toast.LENGTH_SHORT).show();
                    return;

                }

                try {
          //          Integer id = Integer.parseInt(txtId.getText().toString());

         /*           Articulo art = new DataArticuloActivity(id).execute().get();
                    if (art != null) {
                        Toast.makeText(getActivity(), "El ID ingresado ya existe", Toast.LENGTH_SHORT).show();
                        return;
                    }*/

                    Articulo _art = new Articulo(txtNombre.getText().toString(),
                            Integer.parseInt(txtStock.getText().toString()), spinnerCat.getSelectedItemPosition() +1,null);

                    DataMainActivity InsertArticulo = new DataMainActivity("insertArticulo",_art);
                    String resultado = InsertArticulo.execute().get();
                    TraerMaximo();
                    txtNombre.setText("", TextView.BufferType.EDITABLE);
                    txtStock.setText("",TextView.BufferType.EDITABLE);
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
    public void TraerMaximo() {
        DataMainActivity traeMax = new DataMainActivity("traeMax", txtId, getActivity());
        traeMax.execute();
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // Refresh tab data:
        if (getFragmentManager() != null) {
            getFragmentManager()
                    .beginTransaction()
                    .detach(this)
                    .attach(this)
                    .commit();
        }
    }
}