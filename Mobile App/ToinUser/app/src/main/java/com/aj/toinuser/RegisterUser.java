package com.aj.toinuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aj.toinuser.Modal.RegRes;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUser extends AppCompatActivity {

    private TextInputLayout name,email,mobile,address,uname,upass,cpass;
    TextView text;
    private Button RegBtn;
    private long backPressedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        uname = findViewById(R.id.username);
        upass = findViewById(R.id.password);
        cpass = findViewById(R.id.repassword);
        address = findViewById(R.id.address);
        RegBtn = findViewById(R.id.regbtn);
        text = findViewById(R.id.textview1);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterUser.this,Authentication.class));
                finish();
            }
        });
        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getEditText().getText().toString();
                String mail = email.getEditText().getText().toString();
                String mob = mobile.getEditText().getText().toString();
                String add = address.getEditText().getText().toString();
                String username = uname.getEditText().getText().toString();
                String userpass = upass.getEditText().getText().toString();
                String conpass = cpass.getEditText().getText().toString();

                if (userpass.equals(conpass)){
                    performRegister(Name,mail,mob,username,userpass,add);
                }else {
                    cpass.getEditText().setError("Password Does not match");
                    upass.getEditText().setError("Password Does not match");
                }
            }
        });
    }

    private void performRegister(String Name, String mail, String mob,
                                 String username, String userpass, String add) {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<RegRes> call = apiInterface.performReg(Name,mail,mob,username,userpass,add);
        call.enqueue(new Callback<RegRes>() {
            @Override
            public void onResponse(Call<RegRes> call, Response<RegRes> response) {
                try {
                    if (response.body().getResponse().equals("1")){
                        Toast.makeText(RegisterUser.this, "User Registered Successfully !", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterUser.this,Authentication.class));
                        finish();
                    }else if (response.body().getResponse().equals("0")){
                        Toast.makeText(RegisterUser.this, "User already exist !", Toast.LENGTH_SHORT).show();
                        name.getEditText().setText("");
                        email.getEditText().setText("");
                        mobile.getEditText().setText("");
                        address.getEditText().setText("");
                        uname.getEditText().setText("");
                        upass.getEditText().setText("");
                        cpass.getEditText().setText("");
                    }
                }catch (Exception e){
                    Toast.makeText(RegisterUser.this, "Something went wrong !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegRes> call, Throwable t) {
                Toast.makeText(RegisterUser.this, t.toString(), Toast.LENGTH_SHORT).show();

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
