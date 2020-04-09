package com.example.silauapp.Model.Pelanggan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPelanggan {
    @SerializedName("recordset")
    List<Pelanggan> listDataKontak;
    public List<Pelanggan> getListDataKontak() {
        return listDataKontak;
    }
}
