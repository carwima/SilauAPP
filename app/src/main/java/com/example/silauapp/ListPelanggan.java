package com.example.silauapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.silauapp.Adapter.PelangganAdapter;
import com.example.silauapp.Model.Pelanggan.GetPelanggan;
import com.example.silauapp.Model.Pelanggan.Pelanggan;
import com.example.silauapp.Rest.ApiClient;
import com.example.silauapp.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPelanggan extends AppCompatActivity {
    Button btIns;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static ListPelanggan ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listpelanggan);

        btIns = findViewById(R.id.btIns);
        btIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListPelanggan.this, PelangganTambah.class));
            }
        });
        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma=this;
        refresh();
    }

    public void refresh() {
        Call<GetPelanggan> kontakCall = mApiInterface.getKontak();
        kontakCall.enqueue(new Callback<GetPelanggan>() {
            @Override
            public void onResponse(Call<GetPelanggan> call, Response<GetPelanggan>
                    response) {
                List<Pelanggan> KontakList = response.body().getListDataKontak();
                int a = 0;
                if (KontakList==null) a=0;
                else a = KontakList.size();
                Log.d("Retrofit Get", "Jumlah data Kontak: " + a);
                mAdapter = new PelangganAdapter(KontakList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetPelanggan> call, Throwable t) {
                Log.e("ERROR ON Retrofit Get", t.toString());
            }
        });
    }
}
