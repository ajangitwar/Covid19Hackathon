package com.aj.toinuser;

import com.aj.toinuser.Modal.Result;
import com.aj.toinuser.Modal.Shops;
import com.aj.toinuser.Modal.UserModal;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {


    @POST("user_login.php")
    @FormUrlEncoded
    Call<UserModal> performLogin(@Field("username") String username, @Field("password") String password);

    @POST("listing.php")
    @FormUrlEncoded
    Call<Result> getShopList(@Field("location") String location);

}
