package com.aj.toinshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aj.toinshop.Modal.OwnerModal;
import com.aj.toinshop.Modal.RegRes;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterOwner extends AppCompatActivity {

    private TextInputLayout name,owner_name,location,email,mobile,address,uname,upass,cpass;
    TextView text;
    private Button RegBtn;
    private long backPressedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_owner);

        name = findViewById(R.id.name);
        owner_name = findViewById(R.id.owner_name);
        location = findViewById(R.id.location);
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
                startActivity(new Intent(RegisterOwner.this,Authentication.class));
                finish();
            }
        });
        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getEditText().getText().toString();
                String owner = owner_name.getEditText().getText().toString();
                String loc = location.getEditText().getText().toString();
                String mail = email.getEditText().getText().toString();
                String mob = mobile.getEditText().getText().toString();
                String add = address.getEditText().getText().toString();
                String username = uname.getEditText().getText().toString();
                String userpass = upass.getEditText().getText().toString();
                String conpass = cpass.getEditText().getText().toString();

                if (userpass.equals(conpass)){
                    performRegister(Name,owner,loc,mail,mob,username,userpass,add);
                }else {
                    cpass.getEditText().setError("Password Does not match");
                    upass.getEditText().setError("Password Does not match");
                }
            }
        });
    }

    private void performRegister(String Name, String owner, String loc, String mail, String mob,
                                 String username, String userpass, String add) {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<RegRes> call = apiInterface.performReg(Name,owner,loc,mail,mob,username,userpass,add);
        call.enqueue(new Callback<RegRes>() {
            @Override
            public void onResponse(Call<RegRes> call, Response<RegRes> response) {
                try {
                    if (response.body().getResponse().equals("1")){
                        Toast.makeText(RegisterOwner.this, "User Registered Successfully !", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterOwner.this,Authentication.class));
                        finish();
                    }else if (response.body().getResponse().equals("0")){
                        Toast.makeText(RegisterOwner.this, "User already exist !", Toast.LENGTH_SHORT).show();
                        name.getEditText().setText("");
                        owner_name.getEditText().setText("");
                        location.getEditText().setText("");
                        email.getEditText().setText("");
                        mobile.getEditText().setText("");
                        address.getEditText().setText("");
                        uname.getEditText().setText("");
                        upass.getEditText().setText("");
                        cpass.getEditText().setText("");
                    }
                }catch (Exception e){
                    Toast.makeText(RegisterOwner.this, "Something went wrong !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegRes> call, Throwable t) {
                Toast.makeText(RegisterOwner.this, t.toString(), Toast.LENGTH_SHORT).show();

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
