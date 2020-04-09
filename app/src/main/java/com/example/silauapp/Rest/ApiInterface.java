package com.example.silauapp.Rest;


import com.example.silauapp.Model.Pelanggan.GetPelanggan;
import com.example.silauapp.Model.Pelanggan.Pelanggan;
import com.example.silauapp.Model.Pelanggan.PostPutDelPelanggan;
import com.example.silauapp.Model.paketlaundry.GetPaket;
import com.example.silauapp.Model.paketlaundry.PostPutDelPaket;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {
    @GET("pelanggan")
    Call<GetPelanggan> getKontak();
    @FormUrlEncoded
    @POST("pelanggan")
    Call<PostPutDelPelanggan> postKontak(@Field("nama") String nama,
                                         @Field("nomor") String nomor,
                                         @Field("username") String username,
                                         @Field("passwd") String passwd);
    @FormUrlEncoded
    @PUT("pelanggan")
    Call<PostPutDelPelanggan> putKontak(
                                     @Field("id") String id,
                                     @Field("nama") String nama,
                                     @Field("nomor") String nomor);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "pelanggan", hasBody = true)
    Call<PostPutDelPelanggan> deleteKontak(@Field("id") String id);

    @GET("paket")
    Call<GetPaket> getPaket();

    @FormUrlEncoded
    @POST("paket")
    Call<PostPutDelPaket> postPaket(@Field("namapaket") String namapaket,
                                     @Field("harga") String harga,
                                     @Field("deskripsi") String deskripsi);

    @FormUrlEncoded
    @PUT("paket")
    Call<PostPutDelPaket> putPaket( @Field("id") String id,
                                    @Field("namapaket") String namapaket,
                                    @Field("harga") String harga,
                                    @Field("deskripsi") String deskripsi);
    @FormUrlEncoded
    @POST("login")
    Call<Pelanggan> getLogin(@Field("username") String username,
                             @Field("passwd") String passwd);

    @FormUrlEncoded
    @POST("loginadm")
    Call<Pelanggan> getAdmin(@Field("username") String username,
                             @Field("passwd") String passwd);
}