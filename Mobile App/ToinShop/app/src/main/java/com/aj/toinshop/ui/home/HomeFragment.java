package com.aj.toinshop.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aj.toinshop.ApiClient;
import com.aj.toinshop.ApiInterface;
import com.aj.toinshop.Authentication;
import com.aj.toinshop.Home;
import com.aj.toinshop.Modal.Mask;
import com.aj.toinshop.Modal.OwnerModal;
import com.aj.toinshop.Modal.Sanitizer;
import com.aj.toinshop.OwnerSes;
import com.aj.toinshop.R;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private TextView mcount,scount;
    String m,s;
    AlertDialog dialog;
    private OwnerSes ownerSes;
    private CardView mcv,scv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ownerSes = new OwnerSes(getContext());
        mcount = root.findViewById(R.id.mcount);
        scount = root.findViewById(R.id.scount);
        mcv = root.findViewById(R.id.cardView2);
        scv = root.findViewById(R.id.cardView3);

        mcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMask();
            }
        });
        scv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSani();
            }
        });

        performLogin(ownerSes.getUname(),ownerSes.getPassword());

        return root;
    }

    private void performLogin(final String Name, final String Pass){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<OwnerModal> call = apiInterface.performLogin(Name,Pass);
        call.enqueue(new Callback<OwnerModal>() {
            @Override
            public void onResponse(Call<OwnerModal> call, Response<OwnerModal> response) {

                try {
                    if (response.body().getResponse().equals("1")) {

                        m = response.body().getMcount();
                        s = response.body().getScount();
                        mcount.setText(m);
                        scount.setText(s);

                    }else{
                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<OwnerModal> call, Throwable t) {

                Log.e("auth",t.toString());
                Toast.makeText(getContext(), "Something went wrong try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateMask(){
        android.app.AlertDialog.Builder malert = new AlertDialog.Builder(getContext());
        final View v1 = getLayoutInflater().inflate(R.layout.fragment_update_pop, null);
        TextView header = v1.findViewById(R.id.header);
        Button btn = v1.findViewById(R.id.button);
        ImageView iv = v1.findViewById(R.id.close);
        final TextInputLayout til = v1.findViewById(R.id.textInputLayout);
        til.getEditText().setText(m);
        til.getEditText().setHint("Mask Count");
        header.setText("Update Mask Count");
        malert.setView(v1);
        dialog = malert.create();
        dialog.setCanceledOnTouchOutside(false);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                Call<Mask> call = apiInterface.updateM(ownerSes.getId(),til.getEditText().getText().toString());
                call.enqueue(new Callback<Mask>() {
                    @Override
                    public void onResponse(Call<Mask> call, Response<Mask> response) {
                        try {
                            if (response.body().getResponse().equals("1")) {
                                Toast.makeText(getContext(), "Count update successfully !", Toast.LENGTH_SHORT).show();
                                performLogin(ownerSes.getUname(),ownerSes.getPassword());
                                mcount.setText(m);
                                dialog.dismiss();

                            }else{
                                Toast.makeText(getContext(), "Error while updating data", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            Toast.makeText(getContext(), "Something went wrong !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Mask> call, Throwable t) {
                        Toast.makeText(getContext(), "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void updateSani(){
        android.app.AlertDialog.Builder malert = new AlertDialog.Builder(getContext());
        final View v1 = getLayoutInflater().inflate(R.layout.fragment_update_pop, null);

        TextView header = v1.findViewById(R.id.header);
        Button btn = v1.findViewById(R.id.button);
        ImageView iv = v1.findViewById(R.id.close);
        final TextInputLayout til = v1.findViewById(R.id.textInputLayout);
        header.setText("Update Sanitizer Count");
        til.getEditText().setText(s);
        til.getEditText().setHint("Sanitizer Count");

        malert.setView(v1);
        dialog = malert.create();
        dialog.setCanceledOnTouchOutside(false);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                Call<Sanitizer> call = apiInterface.updateS(ownerSes.getId(),til.getEditText().getText().toString());
                call.enqueue(new Callback<Sanitizer>() {
                    @Override
                    public void onResponse(Call<Sanitizer> call, Response<Sanitizer> response) {
                        try {
                            if (response.body().getResponse().equals("1")) {
                                Toast.makeText(getContext(), "Count update successfully !", Toast.LENGTH_SHORT).show();
                                performLogin(ownerSes.getUname(),ownerSes.getPassword());
                                dialog.dismiss();
                                scount.setText(s);
                            }else{
                                Toast.makeText(getContext(), "Error while updating data", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            Toast.makeText(getContext(), "Something went wrong !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Sanitizer> call, Throwable t) {
                        Toast.makeText(getContext(), "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}