package com.example.silauapp.Model.Pelanggan;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pelanggan implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("nomor")
    private String nomor;
    @SerializedName("username")
    private String uname;
    @SerializedName("passwd")
    private String passwd;

    public Pelanggan(String id, String nama, String nomor,  String uname, String passwd) {
        this.id = id;
        this.nama = nama;
        this.nomor = nomor;
        this.uname = uname;
        this.passwd = passwd;
    }

    public String getId() {
        return id;
    }
    public String getNama() {
        return nama;
    }
    public String getNomor() {
        return nomor;
    }
    public String getUsername() {
        return uname;
    }
}
