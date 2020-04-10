package com.aj.toinshop;

import com.aj.toinshop.Modal.Mask;
import com.aj.toinshop.Modal.OwnerModal;
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

}
