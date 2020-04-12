package com.aj.toinuser;

import com.aj.toinuser.Modal.ForgRes;
import com.aj.toinuser.Modal.PassChangeRes;
import com.aj.toinuser.Modal.RegRes;
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

    @POST("registerUser.php")
    @FormUrlEncoded
    Call<RegRes> performReg(@Field("name") String name, @Field("email") String email, @Field("mobile") String mobile,
                            @Field("username") String username, @Field("password") String password,
                            @Field("address") String address);

    @POST("ForgotUser.php")
    @FormUrlEncoded
    Call<ForgRes> ForgPass(@Field("owner_name") String owner_name, @Field("mobile") String mobile);


    @POST("UpdatePassUser.php")
    @FormUrlEncoded
    Call<PassChangeRes> PassConf(@Field("id") String id, @Field("password") String password);

}
