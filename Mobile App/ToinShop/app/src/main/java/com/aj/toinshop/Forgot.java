package com.aj.toinshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aj.toinshop.Modal.ForgRes;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forgot extends AppCompatActivity {

    TextInputLayout mobile,name;
    private TextView textReg;
    private Button ForgBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ForgBtn = findViewById(R.id.forgot);

        name = findViewById(R.id.uname);
        mobile = findViewById(R.id.conpass);
        textReg = findViewById(R.id.reg_text);
        textReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Forgot.this, Authentication.class);
                startActivity(i);
                finish();
            }
        });

        ForgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = name.getEditText().getText().toString();
                String mob = mobile.getEditText().getText().toString();

               performFor(fname,mob);
            }
        });
    }

    private void performFor(String fname, String mob) {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ForgRes> call = apiInterface.ForgPass(fname,mob);

        call.enqueue(new Callback<ForgRes>() {
            @Override
            public void onResponse(Call<ForgRes> call, Response<ForgRes> response) {
                try {
                    if (response.body().getResponse().equals("1")){
                        Intent i  = new Intent(Forgot.this,ChangePassword.class);
                        i.putExtra("id",response.body().getId());
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(Forgot.this, "Details are incorrect !", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(Forgot.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ForgRes> call, Throwable t) {
                Log.e("forgot",t.toString());
                Toast.makeText(Forgot.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
