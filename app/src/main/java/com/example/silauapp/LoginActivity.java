package com.example.silauapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.silauapp.Model.Pelanggan.Pelanggan;
import com.example.silauapp.Rest.ApiClient;
import com.example.silauapp.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etUname, etPasswd;
    Button btnLogin, btnRegister;
    ProgressDialog loading;

    ApiInterface mApiService;
    ApiInterface mApiInterface;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        etUname = findViewById(R.id.etLoginPasswd);
        etPasswd = findViewById(R.id.etLoginUname);
        btnLogin =findViewById(R.id.btnLogin);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, PelangganTambah.class);
                startActivity(i);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Pelanggan> postLogin = mApiInterface.getLogin(etUname.getText().toString(), etPasswd.getText().toString());
                postLogin.enqueue(new Callback<Pelanggan>() {
                    @Override
                    public void onResponse(Call<Pelanggan> call, Response<Pelanggan> response) {
                        Pelanggan KontakList = response.body();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra("sampleObject", KontakList);
                        i.putExtra("status", "pelanggan");
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "Welcome "+KontakList.getNama(),Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Pelanggan> call, Throwable t) {
                            Call<Pelanggan> postLogin = mApiInterface.getAdmin(etUname.getText().toString(), etPasswd.getText().toString());
                            postLogin.enqueue(new Callback<Pelanggan>() {
                                @Override
                                public void onResponse(Call<Pelanggan> call, Response<Pelanggan> response) {
                                    Pelanggan KontakList = response.body();
                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                    i.putExtra("sampleObject", KontakList);
                                    i.putExtra("status", "pekerja");
                                    startActivity(i);
                                    Toast.makeText(getApplicationContext(), "Welcome "+KontakList.getNama(),Toast.LENGTH_SHORT).show();
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<Pelanggan> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                                }
                        });
                    }
                });
            }
        });

    }

}
