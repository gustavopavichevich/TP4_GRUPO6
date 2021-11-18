package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Conexion.DataMainActivity;

public class FragmentListado extends Fragment {
    private View view;
    private ListView lvProductos;
    public static final String titulo = "Listado";

    public static FragmentListado newInstance() {
        return new FragmentListado();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listado, container, false);
        lvProductos = (ListView) view.findViewById(R.id.lvProductos);
        Connect();
        return view;
    }

    public void Connect() {
        DataMainActivity task = new DataMainActivity("selectProducto", lvProductos, getActivity());
        task.execute();
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