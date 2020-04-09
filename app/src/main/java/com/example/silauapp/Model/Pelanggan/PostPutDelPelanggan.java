package com.example.silauapp.Model.Pelanggan;

import com.example.silauapp.Model.Pelanggan.Pelanggan;
import com.google.gson.annotations.SerializedName;

public class PostPutDelPelanggan {
    @SerializedName("recordset")
    Pelanggan mKontak;
    public Pelanggan getKontak() {
        return mKontak;
    }
    public void setKontak(Pelanggan Kontak) {
        mKontak = Kontak;
    }
}
