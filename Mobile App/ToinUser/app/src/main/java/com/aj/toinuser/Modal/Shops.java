package com.aj.toinuser.Modal;

public class Shops
{
    private int id;

    private String name;

    private String owner_name;

    private String address;

    private String email;

    private String mobile;

    private String location;

    private String mask_count;

    private String sanitize_count;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setOwner_name(String owner_name){
        this.owner_name = owner_name;
    }
    public String getOwner_name(){
        return this.owner_name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    public String getMobile(){
        return this.mobile;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return this.location;
    }
    public void setMask_count(String mask_count){
        this.mask_count = mask_count;
    }
    public String getMask_count(){
        return this.mask_count;
    }
    public void setSanitize_count(String sanitize_count){
        this.sanitize_count = sanitize_count;
    }
    public String getSanitize_count(){
        return this.sanitize_count;
    }
}
