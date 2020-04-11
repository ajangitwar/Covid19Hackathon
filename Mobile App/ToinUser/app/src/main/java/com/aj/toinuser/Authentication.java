package com.aj.toinuser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.aj.toinuser.Modal.UserModal;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Authentication extends AppCompatActivity {

    private TextInputLayout uname,upass;
    private Button LogBtn,ForgBtn;
    private TextView textReg;
    UserSes userSes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        userSes = new UserSes(Authentication.this);
        LogBtn = findViewById(R.id.logbtn);
        ForgBtn = findViewById(R.id.logbtn2);
        textReg = findViewById(R.id.reg_text);
        uname = findViewById(R.id.uname);
        upass = findViewById(R.id.password);

        LogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = uname.getEditText().getText().toString();
                String pass = upass.getEditText().getText().toString();

                performLogin(name,pass);
            }
        });
        ForgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Authentication.this, Forgot.class);
                startActivity(i);
                finish();
            }
        });
        textReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Authentication.this, RegisterUser.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void performLogin(final String Name, final String Pass){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<UserModal> call = apiInterface.performLogin(Name,Pass);
        call.enqueue(new Callback<UserModal>() {
            @Override
            public void onResponse(Call<UserModal> call, Response<UserModal> response) {
                try {
                    if (response.body().getResponse().equals("1")) {

                        userSes.setName(response.body().getName());
                        userSes.setAddress(response.body().getAddress());
                        userSes.setEmail(response.body().getEmail());
                        userSes.setMobile(response.body().getMobile());

                        userSes.setUname(Name);
                        userSes.setPassword(Pass);

                        Intent i = new Intent(Authentication.this, Listing.class);
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
            public void onFailure(Call<UserModal> call, Throwable t) {

                Log.e("auth",t.toString());
                Toast.makeText(Authentication.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
