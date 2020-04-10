package com.aj.toinuser.Modal;

import java.util.List;

public class Result {
    private String response;

    private List<Shops> result;

    public void setResponse(String response){
        this.response = response;
    }
    public String getResponse(){
        return this.response;
    }
    public void setResult(List<Shops> result){
        this.result = result;
    }
    public List<Shops> getResult(){
        return this.result;
    }
}
