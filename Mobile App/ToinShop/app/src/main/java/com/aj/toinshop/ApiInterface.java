package com.aj.toinshop;

import com.aj.toinshop.Modal.ForgRes;
import com.aj.toinshop.Modal.Mask;
import com.aj.toinshop.Modal.OwnerModal;
import com.aj.toinshop.Modal.PassChangeRes;
import com.aj.toinshop.Modal.RegRes;
import com.aj.toinshop.Modal.Sanitizer;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {


    @POST("owner_login.php")
    @FormUrlEncoded
    Call<OwnerModal> performLogin(@Field("username") String username, @Field("password") String password);

    @POST("updateM.php")
    @FormUrlEncoded
    Call<Mask> updateM(@Field("id") String id, @Field("mask_count") String mast_count);

    @POST("updateS.php")
    @FormUrlEncoded
    Call<Sanitizer> updateS(@Field("id") String id, @Field("sanitize_count") String sanitize_count);

    @POST("registerOwner.php")
    @FormUrlEncoded
    Call<RegRes> performReg(@Field("name") String name,@Field("owner_name") String owner_name,@Field("location") String location,
                            @Field("email") String email,@Field("mobile") String mobile,@Field("username") String username,
                            @Field("password") String password,@Field("address") String address);

    @POST("ForgotOwner.php")
    @FormUrlEncoded
    Call<ForgRes> ForgPass(@Field("owner_name") String owner_name, @Field("mobile") String mobile);

    @POST("UpdatePassOwner.php")
    @FormUrlEncoded
    Call<PassChangeRes> PassConf(@Field("id") String id, @Field("password") String password);

}
