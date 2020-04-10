package com.aj.toinuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.aj.toinuser.Modal.Result;
import com.aj.toinuser.Modal.Shops;
import com.aj.toinuser.Modal.UserModal;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Listing extends AppCompatActivity {

    private AlertDialog dialog;
    private TextView name,address,email,mobile,emptyview;
    private Button btn;
    private ImageView iv;
    private UserSes userSes;
    private List<Shops> list;
    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        getSupportActionBar().setTitle("Toin");
        getSupportActionBar().setSubtitle("Healthcare & Medication");
        userSes = new UserSes(Listing.this);
        recyclerView = findViewById(R.id.recyclerview);
        emptyview = findViewById(R.id.textView);
        if(userSes.getLocation().equals("")){
            selectLoc();
        }
        getShopList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //and this to handle actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            android.app.AlertDialog.Builder malert = new AlertDialog.Builder(Listing.this);
            final View v1 = getLayoutInflater().inflate(R.layout.fragment_profile, null);
            name = v1.findViewById(R.id.name);
            address = v1.findViewById(R.id.address);
            mobile = v1.findViewById(R.id.mobile);
            email = v1.findViewById(R.id.email);
            btn = v1.findViewById(R.id.logout);
            iv = v1.findViewById(R.id.close);

            name.setText(userSes.getName());
            address.setText(userSes.getAddress());
            email.setText(userSes.getEmail());
            mobile.setText(userSes.getMobile());

            malert.setView(v1);
            dialog = malert.create();

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userSes.removeUser();
                    Intent i = new Intent(Listing.this,Authentication.class);
                    startActivity(i);
                    finish();
                }
            });
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
            return true;
        }else if (id == R.id.location){
            selectLoc();
        }
        return super.onOptionsItemSelected(item);
    }

    public void getShopList(){

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Result> call = apiInterface.getShopList(userSes.getLocation());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                try {
                    if (response.body().getResponse().equals("1")){
                        list = new ArrayList<>();
                        list = response.body().getResult();
                        setAdapter(list);
                        recyclerView.setVisibility(View.VISIBLE);
                        emptyview.setVisibility(View.INVISIBLE);
//                        Toast.makeText(Listing.this, "success", Toast.LENGTH_SHORT).show();

                    }else  if (response.body().getResponse().equals("0")){

                        recyclerView.setVisibility(View.INVISIBLE);
                        emptyview.setVisibility(View.VISIBLE);
                        emptyview.setText("No records found for location : "+userSes.getLocation());
//                        Toast.makeText(Listing.this, "No records found for location "+userSes.getLocation(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("fail",t.toString());
                Toast.makeText(Listing.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setAdapter(List<Shops> list){

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Listing.this);
        recyclerView.setLayoutManager(layoutManager);

        dataAdapter = new DataAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(dataAdapter);
    }

    public void selectLoc(){
        android.app.AlertDialog.Builder malert = new AlertDialog.Builder(Listing.this);
        final View v1 = getLayoutInflater().inflate(R.layout.fragment_update_pop, null);

        Button btn = v1.findViewById(R.id.button);
        ImageView iv = v1.findViewById(R.id.close);
        final TextInputLayout til = v1.findViewById(R.id.textInputLayout);

        til.getEditText().setHint("Enter Location");

        malert.setView(v1);
        dialog = malert.create();
        dialog.setCanceledOnTouchOutside(false);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSes.setLocation(til.getEditText().getText().toString());
                getShopList();

                dialog.dismiss();
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
