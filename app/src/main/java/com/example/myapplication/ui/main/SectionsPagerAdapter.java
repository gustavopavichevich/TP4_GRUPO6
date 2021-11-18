package com.example.myapplication.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.AltaFragment;
import com.example.myapplication.ListadoFragment;
import com.example.myapplication.ModificacionFragment;
import com.example.myapplication.R;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static int cantTabs = 3;

    public SectionsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 1:
                return AltaFragment.newInstance();
            case 2:
                return ModificacionFragment.newInstance();
            case 3:
                return ListadoFragment.newInstance();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Alta";

            case 1:
                return "Modificación";

            case 2:
                return "Listado";
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return cantTabs;
    }
}