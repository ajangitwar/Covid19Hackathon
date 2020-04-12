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
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Authentication extends AppCompatActivity {

    private TextInputLayout uname,upass;
    private Button LogBtn,ForgBtn;
    private TextView textReg;
    OwnerSes ownerSes;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        ownerSes = new OwnerSes(Authentication.this);

        LogBtn = findViewById(R.id.logbtn);
        ForgBtn = findViewById(R.id.forgot);

        textReg = findViewById(R.id.reg_text);

        uname = findViewById(R.id.uname);
        upass = findViewById(R.id.password);


        ForgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Authentication.this,Forgot.class);
                startActivity(i);
                finish();
            }
        });
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
                Intent i = new Intent(Authentication.this,RegisterOwner.class);
                startActivity(i);
                finish();
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
                Toast.makeText(Authentication.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        }else {
            Toast.makeText(this, "Hit Back Again To Exit !", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
