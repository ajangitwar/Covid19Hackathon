package com.aj.toinuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aj.toinuser.Modal.PassChangeRes;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {

    TextInputLayout password,conPassword;
    Button button;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        

        Toast.makeText(this,  getIntent().getStringExtra("id"), Toast.LENGTH_SHORT).show();
        button = findViewById(R.id.logbtn);
        password = findViewById(R.id.password);
        conPassword = findViewById(R.id.conpass);
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = password.getEditText().getText().toString();
                String cpass = conPassword.getEditText().getText().toString();

                if (pass.equals(cpass)){
                    changePassword(getIntent().getStringExtra("id"),pass);
                }else {
                  
                    conPassword.getEditText().setError("Password Does not match");
                }
            }
        });
    }
    
    public void changePassword(String id ,String password){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<PassChangeRes> call = apiInterface.PassConf(id,password);
        call.enqueue(new Callback<PassChangeRes>() {
            @Override
            public void onResponse(Call<PassChangeRes> call, Response<PassChangeRes> response) {
                try {
                    if (response.body().getResponse().equals("1")){
                        Toast.makeText(ChangePassword.this, "Password Created successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ChangePassword.this,Authentication.class));
                    }else {
                        Toast.makeText(ChangePassword.this, "Password Creation Failed", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(ChangePassword.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<PassChangeRes> call, Throwable t) {
                Toast.makeText(ChangePassword.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

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
