package com.example.silauapp.Model;

import com.google.gson.annotations.SerializedName;

public class HomeStats {
    @SerializedName("stats")
    private String stats;
    public HomeStats(String stats) {
        this.stats = stats;
    }

    public String getHomestats() {
        if(stats==null) stats = "0";
        return stats;
    }
}
