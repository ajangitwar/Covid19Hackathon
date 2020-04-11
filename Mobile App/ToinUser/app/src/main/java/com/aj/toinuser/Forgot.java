package com.aj.toinuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Forgot extends AppCompatActivity {

    TextInputLayout email,name;
    private TextView textReg;
    private Button ForgBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ForgBtn = findViewById(R.id.logbtn2);

        email = findViewById(R.id.uname);
        name = findViewById(R.id.password);
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
                String mail = email.getEditText().getText().toString();

                Toast.makeText(Forgot.this, "Service not available for now", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
