package com.example.silauapp.Model.transaksi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTrans {
    @SerializedName("recordset")
    List<Trans> getListDataTrans;
    public List<Trans> getListDataTrans() {
        return getListDataTrans;
    }
}
