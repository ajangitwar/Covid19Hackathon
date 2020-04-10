package com.aj.toinuser;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends BottomSheetDialogFragment {

    private TextView name,address,email,mobile;
    Button btn;
    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

//        getActivity().getActionBar().hide();


        return view;
    }

}
