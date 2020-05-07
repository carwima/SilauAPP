package com.example.silauapp.Rest;


import com.example.silauapp.Model.HomeStats;
import com.example.silauapp.Model.Pelanggan.GetPelanggan;
import com.example.silauapp.Model.Pelanggan.Pelanggan;
import com.example.silauapp.Model.Pelanggan.PostPutDelPelanggan;
import com.example.silauapp.Model.paketlaundry.GetPaket;
import com.example.silauapp.Model.paketlaundry.Paket;
import com.example.silauapp.Model.paketlaundry.PostPutDelPaket;
import com.example.silauapp.Model.transaksi.GetTrans;
import com.example.silauapp.Model.transaksi.PostPutDelTrans;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

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

    @GET("paketid")
    Call<Paket> getPaketID(@Query("id") String id);

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

    @GET("transaksi")
    Call<GetTrans> getTrans();

    @GET("transaksiID")
    Call<GetTrans> getTransID(@Query("id") String id);

    @FormUrlEncoded
    @POST("transaksi")
    Call<PostPutDelTrans> postTrans(@Field("id_pelanggan") String id_pelanggan,
                                    @Field("id_paket") String id_paket,
                                    @Field("id_admin") String id_admin,
                                    @Field("tgl_masuk") String tgl_masuk,
                                    @Field("berat") String berat,
                                    @Field("status_bayar") String status_bayar);

    @FormUrlEncoded
    @PUT("transaksi")
    Call<PostPutDelTrans> putTrans( @Field("id") String id);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "transaksi", hasBody = true)
    Call<PostPutDelTrans> deleteTrans(@Field("id") String id);


    @GET("pendapatan")
    Call<HomeStats> getPendapatan();
    @GET("harian1")
    Call<HomeStats> getHarian1();
    @GET("harian2")
    Call<HomeStats> getHarian2();
    @GET("belumdiambil")
    Call<HomeStats> getBelumdiambil(@Query("id") String id);
    @GET("total")
    Call<HomeStats> getTotal(@Query("id") String id);
}