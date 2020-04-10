package com.aj.toinshop.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aj.toinshop.Authentication;
import com.aj.toinshop.OwnerSes;
import com.aj.toinshop.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private OwnerSes ownerSes;
    private TextView name,owner_name,email,mobile,address;
    Button logout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ownerSes = new OwnerSes(getContext());
        name = root.findViewById(R.id.name);
        owner_name = root.findViewById(R.id.owner_name);
        email = root.findViewById(R.id.email);
        mobile = root.findViewById(R.id.mobile);
        address = root.findViewById(R.id.address);
        logout = root.findViewById(R.id.logout);

        name.setText(ownerSes.getName());
        owner_name.setText(ownerSes.getOwner_name());
        email.setText(ownerSes.getEmail());
        mobile.setText(ownerSes.getMobile());
        address.setText(ownerSes.getAddress());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ownerSes.removeUser();
                Intent i = new Intent(getActivity(), Authentication.class);
                startActivity(i);
                getActivity().finish();
                getActivity().overridePendingTransition(0,0);
            }
        });



        return root;
    }
}