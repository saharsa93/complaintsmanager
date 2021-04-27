package com.codewithsahar.googlemapapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterInterface {
    @FormUrlEncoded
    @POST("index.php")
    Call<ResponseBody> register(@Field("type")String type, @Field("phone")String phone, @Field("signaltype")String signaltype
            , @Field("signalstrength")String signalstrength, @Field("latitude")String latitude
            , @Field("longitude")String longitude, @Field("description")String description);
}
