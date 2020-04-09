package com.example.silauapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.silauapp.Model.paketlaundry.PostPutDelPaket;
import com.example.silauapp.Rest.ApiClient;
import com.example.silauapp.Rest.ApiInterface;
import com.example.silauapp.ui.laundry.LaundryFragment;
import com.example.silauapp.ui.list.ListFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaketEdit extends AppCompatActivity {
    EditText  namapaket, harga, deskripsi;
    ApiInterface mApiInterface;
    Button btUpdate, btBack;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket_edit);
        namapaket = findViewById(R.id.edtNamapaket);
        harga = findViewById(R.id.edtHarga);
        deskripsi = findViewById(R.id.edtDeskripsi);
        Intent mIntent = getIntent();
        ID = mIntent.getStringExtra("Id");
        namapaket.setText(mIntent.getStringExtra("Namapaket"));
        harga.setText(mIntent.getStringExtra("Harga"));
        deskripsi.setText(mIntent.getStringExtra("Deskripsi"));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btUpdate = (Button) findViewById(R.id.btnUpdate);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelPaket> updatePaketCall = mApiInterface.putPaket(
                        ID,
                        namapaket.getText().toString(),
                        harga.getText().toString(),
                        deskripsi.getText().toString());
                updatePaketCall.enqueue(new Callback<PostPutDelPaket>() {
                    @Override
                    public void onResponse(Call<PostPutDelPaket> call, Response<PostPutDelPaket> response) {
                        LaundryFragment.ma.refresh();
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
                ListFragment.ma.refresh();
                finish();
            }
        });
    }
}
