package com.example.silauapp.ui.laundry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.silauapp.Adapter.PaketAdapter;
import com.example.silauapp.Model.paketlaundry.GetPaket;
import com.example.silauapp.Model.paketlaundry.Paket;
import com.example.silauapp.PaketTambah;
import com.example.silauapp.PelangganTambah;
import com.example.silauapp.R;
import com.example.silauapp.Rest.ApiClient;
import com.example.silauapp.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaundryFragment extends Fragment {
    Button btIns;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static LaundryFragment ma;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_paket, container, false);

        btIns = root.findViewById(R.id.btIns);
        btIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PaketTambah.class);
                startActivity(intent);
            }
        });
        mRecyclerView = root.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma = this;
        refresh();
        return root;
    }

    public void refresh() {
        Call<GetPaket> paketCall = mApiInterface.getPaket();
        paketCall.enqueue(new Callback<GetPaket>() {
            @Override
            public void onResponse(Call<GetPaket> call, Response<GetPaket>
                    response) {
                List<Paket> PaketList = response.body().getListDataPaket();
                int a = 0;
                if (PaketList == null) a = 0;
                else a = PaketList.size();
                Log.d("Retrofit Get", "Jumlah data Kontak: " + a);
                mAdapter = new PaketAdapter(PaketList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetPaket> call, Throwable t) {
                Log.e("ERROR ON Retrofit Get", t.toString());
            }
        });
    }
}