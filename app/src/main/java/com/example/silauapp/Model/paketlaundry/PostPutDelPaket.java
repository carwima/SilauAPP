package com.example.silauapp.Model.paketlaundry;

import com.google.gson.annotations.SerializedName;

public class PostPutDelPaket {
    @SerializedName("recordset")
    Paket mpaket;
    public Paket getPaket() {
        return mpaket;
    }
    public void setPaket(Paket paket) {
        mpaket = paket;
    }
}