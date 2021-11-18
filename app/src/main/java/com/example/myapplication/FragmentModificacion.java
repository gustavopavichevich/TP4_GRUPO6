package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Conexion.DataProductoActivity;
import com.example.myapplication.Conexion.DataMainActivity;
import com.example.myapplication.entidad.Producto;

public class FragmentModificacion extends Fragment {
    public static final String titulo = "Modificación";

    private View view;
    private EditText txtID;
    private EditText txtStock;
    private EditText txtNombre;
    private Spinner spinnerCat;
    private Button btnBuscar;
    private Button btnModificar;

    public static FragmentModificacion newInstance() {
        return new FragmentModificacion();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_modificacion, container, false);
        txtID = view.findViewById(R.id.txtID);
        txtNombre = view.findViewById(R.id.txtNombre);
        txtStock = view.findViewById(R.id.txtStock);
        spinnerCat = view.findViewById(R.id.spinnerCategoria);
        btnBuscar = view.findViewById(R.id.btnBuscar);
        btnModificar = view.findViewById(R.id.btnModificar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (txtID.getText().toString().isEmpty()) {
                        Toast.makeText(getActivity(), "Debe ingresar un ID", Toast.LENGTH_LONG).show();
                        return;
                    }
                    txtID.clearFocus();
                    Integer id = Integer.parseInt(txtID.getText().toString());
                    Producto producto = new DataProductoActivity(id).execute().get();
                    spinnerCat.setSelection(producto.getIdCategoria()-1);
                    if (producto == null) {
                        Toast.makeText(getActivity(), "El ID ingresado no existe", Toast.LENGTH_LONG).show();
                        return;
                    }
                    cargarSpinner();
                    SetearCamposProductos(producto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtID.getText().toString().isEmpty() || txtNombre.getText().toString().isEmpty() || txtStock.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Ingrese todos los campos antes de avanzar", Toast.LENGTH_SHORT).show();


                } else {
                    Integer id = Integer.parseInt(txtID.getText().toString());
                    String nombre = txtNombre.getText().toString();
                    Integer stock = Integer.parseInt(txtStock.getText().toString());
                    Integer idCategoria = spinnerCat.getSelectedItemPosition() + 1;
                    Producto producto = new Producto(id, nombre, stock, idCategoria, null);
                    try {
                        DataMainActivity modificacion = new DataMainActivity("updateProducto", producto);
                        String resultado = modificacion.execute().get();
                        Toast.makeText(getActivity(), resultado, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "No pudimos actualizar el artículo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    private void SetearCamposProductos(Producto producto) {
        txtNombre.setText(producto.getNombre());
        txtStock.setText(producto.getStock().toString());
    }

    public void cargarSpinner() {
        DataMainActivity carga = new DataMainActivity("selectCategorias", spinnerCat, getActivity());
        carga.execute();
    }

}