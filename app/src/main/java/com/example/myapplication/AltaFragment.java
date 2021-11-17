package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.myapplication.adapter.ArticuloAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AltaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AltaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Spinner SpCate;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AltaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BajaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AltaFragment newInstance(String param1, String param2) {
        AltaFragment fragment = new AltaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Bundle objRecibido= getArguments();
        ArticuloAdapter adapter=null;
        if (objRecibido!=null){
            adapter= (ArticuloAdapter) objRecibido.getSerializable("ListadoCategorias");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle objRecibido= getArguments();
        ArticuloAdapter adapter=null;
        if (objRecibido!=null){
            adapter= (ArticuloAdapter) objRecibido.getSerializable("ListadoCategorias");
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alta, container, false);
        Spinner spinner = view.findViewById(R.id.s_categoria);
        spinner.setAdapter(adapter);
        return inflater.inflate(R.layout.fragment_alta, container, false);
    }
}