package com.example.silauapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.silauapp.Model.transaksi.PostPutDelTrans;
import com.example.silauapp.Rest.ApiClient;
import com.example.silauapp.Rest.ApiInterface;
import com.example.silauapp.ui.transaction.TransactionFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiTambah extends AppCompatActivity {
    EditText edtPelanggan, edtPaket, edtBerat;
    Button btInsert, btBack;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_tambah);
        final Intent i = getIntent();
        edtPelanggan = findViewById(R.id.edtNamapaket);
        edtPaket = findViewById(R.id.edtHarga);
        edtBerat = findViewById(R.id.edtDeskripsi);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btInsert =findViewById(R.id.btInserting);
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelTrans> postTrans = mApiInterface.postTrans(edtPelanggan.getText().toString(), edtPaket.getText().toString(), i.getStringExtra("id_admin"),"test", edtBerat.getText().toString(), "0");
                postTrans.enqueue(new Callback<PostPutDelTrans>() {
                    @Override
                    public void onResponse(Call<PostPutDelTrans> call, Response<PostPutDelTrans> response) {
                        TransactionFragment.ma.refresh(i.getStringExtra("id_admin"),"pekerja");
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
                ListPelanggan.ma.refresh();
                finish();
            }
        });
    }
}
