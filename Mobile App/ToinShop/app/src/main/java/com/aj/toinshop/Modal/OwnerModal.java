package com.aj.toinshop.Modal;

public class OwnerModal
{

    private String Id;

    private String Response;

    private String Name;

    private String Owner_Name;

    private String Email;

    private String Mobile;

    private String Address;

    private String Location;

    private String Mcount;

    private String Scount;

    public String getId() {
        return this.Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setResponse(String Response){
        this.Response = Response;
    }
    public String getResponse(){
        return this.Response;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public String getName(){
        return this.Name;
    }
    public void setOwner_Name(String Owner_Name){
        this.Owner_Name = Owner_Name;
    }
    public String getOwner_Name(){
        return this.Owner_Name;
    }
    public void setEmail(String Email){
        this.Email = Email;
    }
    public String getEmail(){
        return this.Email;
    }
    public void setMobile(String Mobile){
        this.Mobile = Mobile;
    }
    public String getMobile(){
        return this.Mobile;
    }
    public void setAddress(String Address){
        this.Address = Address;
    }
    public String getAddress(){
        return this.Address;
    }
    public void setLocation(String Location){
        this.Location = Location;
    }
    public String getLocation(){
        return this.Location;
    }
    public void setMcount(String Mcount){
        this.Mcount = Mcount;
    }
    public String getMcount(){
        return this.Mcount;
    }
    public void setScount(String Scount){
        this.Scount = Scount;
    }
    public String getScount() {
        return this.Scount;
    }
}
