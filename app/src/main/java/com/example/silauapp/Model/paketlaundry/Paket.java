package com.example.silauapp.Model.paketlaundry;

import com.google.gson.annotations.SerializedName;

public class Paket {
    @SerializedName("id")
    private String id;
    @SerializedName("namapaket")
    private String namapaket;
    @SerializedName("harga")
    private String harga;
    @SerializedName("deskripsi")
    private String deskripsi;

    public Paket(String id, String namapaket, String harga, String deskripsi) {
        this.id = id;
        this.namapaket = namapaket;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    public String getId() {
        return id;
    }
    public String getNamapaket() {
        return namapaket;
    }
    public String getDeskripsi() {
        return deskripsi;
    }
    public String getHarga() {
        return harga;
    }
}
