package com.example.silauapp.Rest;


import com.example.silauapp.Model.GetPelanggan;
import com.example.silauapp.Model.PostPutDelPelanggan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {
    @GET("kontak_android")
    Call<GetPelanggan> getKontak();
    @FormUrlEncoded
    @POST("kontak")
    Call<PostPutDelPelanggan> postKontak(@Field("nama") String nama,
                                         @Field("nomor") String nomor);
    @FormUrlEncoded
    @PUT("kontak")
    Call<PostPutDelPelanggan> putKontak(@Field("id") String id,
                                     @Field("nama") String nama,
                                     @Field("nomor") String nomor);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "kontak", hasBody = true)
    Call<PostPutDelPelanggan> deleteKontak(@Field("id") String id);
}