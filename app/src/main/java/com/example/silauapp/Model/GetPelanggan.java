package com.example.silauapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPelanggan {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Pelanggan> listDataKontak;
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
    public List<Pelanggan> getListDataKontak() {
        return listDataKontak;
    }
    public void setListDataKontak(List<Pelanggan> listDataKontak) {
        this.listDataKontak = listDataKontak;
    }
}
