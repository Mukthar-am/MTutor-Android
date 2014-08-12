package com.math.tutor.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.math.tutor.R;

/**
 * Created by mukthar.ahmed on 11/08/14.
 */
public class MathsFragment extends Fragment {

    public MathsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_create, container, false);

        return rootView;
    }
}
