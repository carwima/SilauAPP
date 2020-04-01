package com.example.silauapp.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDelPelanggan {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Pelanggan mKontak;
    @SerializedName("message")
    String message;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Pelanggan getKontak() {
        return mKontak;
    }
    public void setKontak(Pelanggan Kontak) {
        mKontak = Kontak;
    }
}
