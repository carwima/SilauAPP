package com.example.silauapp.Model.transaksi;

import com.google.gson.annotations.SerializedName;

public class Trans {
    @SerializedName("id")
    private String id;
    @SerializedName("id_pelanggan")
    private String id_pelanggan;
    @SerializedName("id_paket")
    private String id_paket;
    @SerializedName("id_admin")
    private String id_admin;
    @SerializedName("tgl_masuk")
    private String tgl_masuk;
    @SerializedName("berat")
    private String berat;
    @SerializedName("status_bayar")
    private String status_bayar;

    public Trans(String id, String id_pelanggan, String id_paket, String id_admin, String tgl_masuk, String berat, String status_bayar) {
        this.id = id;
        this.id_pelanggan = id_pelanggan;
        this.id_paket = id_paket;
        this.id_admin = id_admin;
        this.tgl_masuk = tgl_masuk;
        this.berat = berat;
        this.status_bayar = status_bayar;
    }

    public String getId() {
        return id;
    }
    public String getIdPelanggan() {
        return id_pelanggan;
    }
    public String getIdAdmin() {
        return id_admin;
    }
    public String getIdPaket() {
        return id_paket;
    }
    public String getTglMasuk() {
        return tgl_masuk;
    }
    public String getBerat() {
        return berat;
    }
    public String getStatusBayar() {
        return status_bayar;
    }
}
