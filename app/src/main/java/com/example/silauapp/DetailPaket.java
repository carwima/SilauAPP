package com.example.silauapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailPaket extends AppCompatActivity {
    TextView tvid, tvnamapaket, tvharga, tvdeskripsi;
    Button btBack, btnUpdate;
    public static DetailPaket mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_paket);
        tvid =  findViewById(R.id.tv_id);
        tvnamapaket = findViewById(R.id.tv_namapaket);
        tvharga = findViewById(R.id.tv_harga);
        tvdeskripsi = findViewById(R.id.tv_deskripsi);
        Intent mIntent = getIntent();
        tvid.setText(mIntent.getStringExtra("Id"));
        tvnamapaket.setText(mIntent.getStringExtra("Namapaket"));
        tvharga.setText(mIntent.getStringExtra("Harga"));
        tvdeskripsi.setText(mIntent.getStringExtra("Deskripsi"));

        btnUpdate =  findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailPaket.this, PaketEdit.class);
                i.putExtra("Id", tvid.getText().toString());
                i.putExtra("Namapaket", tvnamapaket.getText().toString());
                i.putExtra("Harga", tvharga.getText().toString());
                i.putExtra("Deskripsi", tvdeskripsi.getText().toString());
                startActivity(i);
            }
        });
        btBack =  findViewById(R.id.btBackGo);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
