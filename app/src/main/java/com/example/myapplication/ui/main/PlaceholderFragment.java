package com.example.myapplication.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.AltaFragment;
import com.example.myapplication.ListadoFragment;
import com.example.myapplication.ModificacionFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public PlaceholderFragment(@NonNull FragmentManager fm) {
        super(fm);
    }


    public static Fragment newInstance(int index) {
        Fragment fragment = null;
        switch (index) {
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
}