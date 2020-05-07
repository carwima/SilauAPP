package com.example.silauapp.Model.transaksi;

import com.google.gson.annotations.SerializedName;

public class PostPutDelTrans {
    @SerializedName("recordset")
    Trans mtrans;
    public Trans getTrans() {
        return mtrans;
    }
    public void setTrans(Trans trans) {
        mtrans = trans;
    }
}