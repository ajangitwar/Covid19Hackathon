package com.aj.toinshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aj.toinshop.Modal.OwnerModal;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.security.acl.Owner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Authentication extends AppCompatActivity {

    private TextInputLayout uname,upass;
    private Button LogBtn;
    private TextView textReg;
    OwnerSes ownerSes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        ownerSes = new OwnerSes(Authentication.this);
        LogBtn = findViewById(R.id.logbtn);
        textReg = findViewById(R.id.reg_text);
        uname = findViewById(R.id.uname);
        upass = findViewById(R.id.password);
        textReg = findViewById(R.id.reg_text);

        LogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = uname.getEditText().getText().toString();
                String pass = upass.getEditText().getText().toString();

                performLogin(name,pass);
            }
        });
        textReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Authentication.this, "Register page", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void performLogin(final String Name, final String Pass){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<OwnerModal> call = apiInterface.performLogin(Name,Pass);
        call.enqueue(new Callback<OwnerModal>() {
            @Override
            public void onResponse(Call<OwnerModal> call, Response<OwnerModal> response) {
                try {
                    if (response.body().getResponse().equals("1")) {

                        ownerSes.setId(response.body().getId());
                        ownerSes.setOwner_name(response.body().getOwner_Name());
                        ownerSes.setName(response.body().getName());
                        ownerSes.setAddress(response.body().getAddress());
                        ownerSes.setEmail(response.body().getEmail());
                        ownerSes.setMobile(response.body().getMobile());
                        ownerSes.setLocation(response.body().getLocation());
                        ownerSes.setUname(Name);
                        ownerSes.setPassword(Pass);

                        Intent i = new Intent(Authentication.this, Home.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(Authentication.this, "Incorrect Email or Password !", Toast.LENGTH_SHORT).show();
                        uname.getEditText().setText("");
                        upass.getEditText().setText("");
                    }
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<OwnerModal> call, Throwable t) {

                Log.e("auth",t.toString());
                Toast.makeText(Authentication.this, "Something went wrong try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
