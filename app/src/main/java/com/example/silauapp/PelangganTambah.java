package com.example.silauapp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.silauapp.Model.Pelanggan.PostPutDelPelanggan;
import com.example.silauapp.Rest.ApiClient;
import com.example.silauapp.Rest.ApiInterface;

public class PelangganTambah extends AppCompatActivity {
    EditText edtNama, edtNomor, edtUname,edtPasswd;
    Button btInsert, btBack;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pelanggantambah);
        edtNama = findViewById(R.id.edtNama);
        edtNomor = findViewById(R.id.edtNomor);
        edtUname = findViewById(R.id.edtUname);
        edtPasswd = findViewById(R.id.edtPasswd);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btInsert =findViewById(R.id.btInserting);
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelPelanggan> postKontakCall = mApiInterface.postKontak(edtNama.getText().toString(), edtNomor.getText().toString(),
                                                                                    edtUname.getText().toString(), edtPasswd.getText().toString());
                postKontakCall.enqueue(new Callback<PostPutDelPelanggan>() {
                    @Override
                    public void onResponse(Call<PostPutDelPelanggan> call, Response<PostPutDelPelanggan> response) {
                        ListPelanggan.ma.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDelPelanggan> call, Throwable t) {
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
