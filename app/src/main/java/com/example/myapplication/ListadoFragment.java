package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Conexion.DataMainActivity;

public class ListadoFragment extends Fragment {
    private View view;
    private ListView lvArticulos;

    public static ListadoFragment newInstance(String param1, String param2) {
        return new ListadoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado, container, false);
    }
    public void Connect() {
        DataMainActivity task = new DataMainActivity("selectArticulos", lvArticulos, getActivity());
        task.execute();
    }

}