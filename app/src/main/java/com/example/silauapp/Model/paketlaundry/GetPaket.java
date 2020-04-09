package com.example.silauapp.Model.paketlaundry;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPaket {
    @SerializedName("recordset")
    List<Paket> getListDataPaket;
    public List<Paket> getListDataPaket() {
        return getListDataPaket;
    }
}
