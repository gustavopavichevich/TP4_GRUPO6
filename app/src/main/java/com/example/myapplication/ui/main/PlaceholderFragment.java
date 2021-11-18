package com.example.myapplication.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.AltaFragment;
import com.example.myapplication.ListadoFragment;
import com.example.myapplication.ModificacionFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends FragmentPagerAdapter {

    private static int cantTabs = 3;

    public PlaceholderFragment(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 1:
                fragment = new AltaFragment();
                break;
            case 2:
                fragment = new ModificacionFragment();
                break;
            case 3:
                fragment = new ListadoFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return cantTabs;
    }
}