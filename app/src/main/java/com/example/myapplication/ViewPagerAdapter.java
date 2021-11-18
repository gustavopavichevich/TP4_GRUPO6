package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static int cantTabs = 3;

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return FragmentAlta.newInstance();
            case 2:
                return FragmentModificacion.newInstance();
            case 3:
                return FragmentListado.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return cantTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return FragmentAlta.titulo;

            case 1:
                return FragmentModificacion.titulo;

            case 2:
                return FragmentListado.titulo;
        }
        return super.getPageTitle(position);
    }
}