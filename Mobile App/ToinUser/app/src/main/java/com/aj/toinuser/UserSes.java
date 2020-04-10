package com.aj.toinuser;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSes {

    String uname;
    String password;
    String name;
    String address;
    String mobile;
    String email;
    String location;
    private SharedPreferences sharedPreferences;
    private Context context;

    public UserSes(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
    }
    public void removeUser(){
        sharedPreferences.edit().clear().apply();
    }


    public String getName() {
        name = sharedPreferences.getString("name","");
        return name;
    }

    public void setName(String name) {
        this.name = name;
        sharedPreferences.edit().putString("name",name).apply();
    }

    public String getAddress() {
        address = sharedPreferences.getString("address","");
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        sharedPreferences.edit().putString("address",address).apply();
    }

    public String getMobile() {
        mobile = sharedPreferences.getString("mobile","");
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        sharedPreferences.edit().putString("mobile",mobile).apply();
    }

    public String getEmail() {
        email = sharedPreferences.getString("email","");
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        sharedPreferences.edit().putString("email",email).apply();
    }

    public String getLocation() {
        location = sharedPreferences.getString("location","");
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        sharedPreferences.edit().putString("location",location).apply();
    }
    public String getUname() {
        uname = sharedPreferences.getString("uname","");
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
        sharedPreferences.edit().putString("uname",uname).apply();
    }

    public String getPassword() {
        password = sharedPreferences.getString("password","");
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        sharedPreferences.edit().putString("password",password).apply();
    }
}
