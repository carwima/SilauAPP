package com.example.silauapp.ui.transaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.silauapp.Adapter.TransAdapter;
import com.example.silauapp.MainActivity;
import com.example.silauapp.Model.transaksi.GetTrans;
import com.example.silauapp.Model.transaksi.Trans;
import com.example.silauapp.R;
import com.example.silauapp.Rest.ApiClient;
import com.example.silauapp.Rest.ApiInterface;
import com.example.silauapp.TransaksiTambah;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.Date;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionFragment extends Fragment {

    Button btIns;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView tvDay;
    public static TransactionFragment ma;
    private String namahari;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        MainActivity activity = (MainActivity) getActivity();
        final String IDFromActivity = activity.getId();
        String levelfromActivity = activity.getLevel();
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_transaction, container, false);

        btIns = root.findViewById(R.id.btInsPaket);
        if(levelfromActivity.equals("pelanggan")){
            btIns.setVisibility(View.GONE);
        }
        btIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TransaksiTambah.class);
                intent.putExtra("id_admin", IDFromActivity);
                startActivity(intent);
            }
        });
        mRecyclerView = root.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma = this;
        refresh(levelfromActivity, IDFromActivity);
        return root;
    }

    public void refresh(final String levelfromActivity, final String IDFromActivity) {
        Call<GetTrans> kontakCall = mApiInterface.getTrans();
        if(levelfromActivity.equals("pelanggan")){
            kontakCall = mApiInterface.getTransID(IDFromActivity);
        }
        kontakCall.enqueue(new Callback<GetTrans>() {
            @Override
            public void onResponse(Call<GetTrans> call, Response<GetTrans>
                    response) {
                List<Trans> KontakList = response.body().getListDataTrans();
                int a = 0;
                if (KontakList == null) a = 0;
                else a = KontakList.size();
                Log.d("Retrofit Get", "Jumlah data Kontak: " + a);
                    mAdapter = new TransAdapter(KontakList, levelfromActivity, IDFromActivity);
                    mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetTrans> call, Throwable t) {
                Log.e("ERROR ON Retrofit Get", t.toString());
            }
        });
    }
}