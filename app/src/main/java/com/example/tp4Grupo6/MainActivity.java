package com.example.tp4Grupo6;

import android.os.Bundle;

import com.example.myapplication.Fragment_listado;
import com.example.myapplication.Fragment_alta_nvo;
import com.example.myapplication.Fragment_modificacion;
import com.example.tp4Grupo6.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction transaction;
    Fragment fragmentalta, fragmentmodificacion,fragmentlista;


private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentalta= new Fragment_alta_nvo();
        fragmentmodificacion=new Fragment_modificacion();
        fragmentlista= new Fragment_listado();

      //no me toma el contenedor!!!!!!!!!!!!!!!!!!!
     //   getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments)


     binding = ActivityMainBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());



        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}