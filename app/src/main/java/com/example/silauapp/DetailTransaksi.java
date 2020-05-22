package com.example.silauapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.silauapp.Model.paketlaundry.Paket;
import com.example.silauapp.Model.transaksi.PostPutDelTrans;
import com.example.silauapp.Rest.ApiClient;
import com.example.silauapp.Rest.ApiInterface;
import com.example.silauapp.ui.laundry.LaundryFragment;
import com.example.silauapp.ui.transaction.TransactionFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTransaksi extends AppCompatActivity {
    TextView tvid, tvidpelanggan,tvtanggal, tvharga, tvidpaket,tvstatus;
    ApiInterface mApiInterface;
    Button btBack, btnUpdate, btDel;
    public static DetailPaket mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);
        tvid =  findViewById(R.id.tv_id);
        tvidpelanggan = findViewById(R.id.tv_idpelanggan);
        tvtanggal = findViewById(R.id.tv_tanggal);
        tvidpaket = findViewById(R.id.tv_idpaket);
        tvharga = findViewById(R.id.tv_harga);
        tvstatus = findViewById(R.id.tv_status);
        btnUpdate =  findViewById(R.id.btnUpdate);
        final Intent mIntent = getIntent();
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Paket> paketCall  = mApiInterface.getPaketID(mIntent.getStringExtra("IdPaket"));
        paketCall.enqueue(new Callback<Paket>() {
            @Override
            public void onResponse(Call<Paket> call, Response<Paket> response) {
                String harga = (Integer.parseInt(response.body().getHarga()) * Integer.parseInt(mIntent.getStringExtra("Harga")))+" ";
                //String harga = mIntent.getStringExtra("Harga");
                tvid.setText(mIntent.getStringExtra("Id"));
                tvidpelanggan.setText(mIntent.getStringExtra("IdPelanggan"));
                tvtanggal.setText(mIntent.getStringExtra("Tanggal"));
                tvidpaket.setText(mIntent.getStringExtra("IdPaket"));
                tvharga.setText(harga+"");
            }

            @Override
            public void onFailure(Call<Paket> call, Throwable t) {
                Log.e("ERROR ON Retrofit Get", t.toString());
            }
        });
        if(mIntent.getStringExtra("Status").equals("0"))
            tvstatus.setText("Belum Selesai");
        else if(mIntent.getStringExtra("Status").equals("1")) {
            tvstatus.setText("Selesai");
            btnUpdate.setVisibility(View.GONE);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelTrans> updatePaketCall = mApiInterface.putTrans(
                        mIntent.getStringExtra("Id"));
                updatePaketCall.enqueue(new Callback<PostPutDelTrans>() {
                    @Override
                    public void onResponse(Call<PostPutDelTrans> call, Response<PostPutDelTrans> response) {
                        TransactionFragment.ma.refresh(mIntent.getStringExtra("Level"), mIntent.getStringExtra("User"));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDelTrans> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btBack =  findViewById(R.id.btBackGo);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btDel =  findViewById(R.id.btnDelete);
        btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelTrans> updatePaketCall = mApiInterface.deleteTrans(
                        mIntent.getStringExtra("Id"));
                updatePaketCall.enqueue(new Callback<PostPutDelTrans>() {
                    @Override
                    public void onResponse(Call<PostPutDelTrans> call, Response<PostPutDelTrans> response) {
                        TransactionFragment.ma.refresh(mIntent.getStringExtra("Level"), mIntent.getStringExtra("User"));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDelTrans> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
                finish();
                finish();
            }
        });
        if(mIntent.getStringExtra("Level").equals("pelanggan")){
            btDel.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
        }
    }
}
