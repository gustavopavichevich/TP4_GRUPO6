package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.myapplication.adapter.ArticuloAdapter;

public class AltaFragment extends Fragment {
    public static final String titulo = "ALTA";
    private View view;
    
    public static AltaFragment newInstance() {
        return new AltaFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_alta, container, false);
        Spinner spinner = view.findViewById(R.id.s_categoria);
        spinner.setAdapter(adapter);
        return inflater.inflate(R.layout.fragment_alta, container, false);
    }
}