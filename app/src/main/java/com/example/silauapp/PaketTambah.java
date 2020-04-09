package com.example.silauapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.silauapp.Model.paketlaundry.PostPutDelPaket;
import com.example.silauapp.Rest.ApiClient;
import com.example.silauapp.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaketTambah extends AppCompatActivity {
    EditText edtNama, edtHarga, edtDeskripsi;
    Button btInsert, btBack;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket_tambah);
        edtNama = findViewById(R.id.edtNamapaket);
        edtHarga = findViewById(R.id.edtHarga);
        edtDeskripsi = findViewById(R.id.edtDeskripsi);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btInsert =findViewById(R.id.btInserting);
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelPaket> postPaket = mApiInterface.postPaket(edtNama.getText().toString(), edtHarga.getText().toString(),
                        edtDeskripsi.getText().toString());
                postPaket.enqueue(new Callback<PostPutDelPaket>() {
                    @Override
                    public void onResponse(Call<PostPutDelPaket> call, Response<PostPutDelPaket> response) {
                        ListPelanggan.ma.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDelPaket> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btBack = (Button) findViewById(R.id.btBackGo);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListPelanggan.ma.refresh();
                finish();
            }
        });
    }
}
