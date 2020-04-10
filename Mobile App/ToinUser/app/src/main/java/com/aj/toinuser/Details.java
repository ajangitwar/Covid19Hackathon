package com.aj.toinuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    TextView mobile,name,email,mcount,scount,owner_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mobile = findViewById(R.id.mobile);
        name = findViewById(R.id.name);
        scount = findViewById(R.id.scount);
        mcount = findViewById(R.id.mcount);
        email = findViewById(R.id.email);
        owner_name = findViewById(R.id.owner_name);



        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Details");
            Intent i = getIntent();
            owner_name.setText(i.getStringExtra("owner_name"));
            name.setText(i.getStringExtra("name"));
            email.setText(i.getStringExtra("email"));
            mobile.setText(i.getStringExtra("mobile"));
            mcount.setText(i.getStringExtra("mcount"));
            scount.setText(i.getStringExtra("scount"));
        }catch (Exception e){
            Toast.makeText(this, "Something wrong check you Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }
}
