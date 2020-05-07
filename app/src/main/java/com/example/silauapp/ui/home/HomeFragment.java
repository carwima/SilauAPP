package com.example.silauapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.silauapp.MainActivity;
import com.example.silauapp.Model.HomeStats;
import com.example.silauapp.Model.transaksi.GetTrans;
import com.example.silauapp.R;
import com.example.silauapp.Rest.ApiClient;
import com.example.silauapp.Rest.ApiInterface;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    TextView descBulanan, Bulanan, descHarian, Harian, descHarian1, Harian1;
    LinearLayout LL3;
    ApiInterface mApiInterface;
    public static HomeFragment ma;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        MainActivity activity = (MainActivity) getActivity();
        final String IDFromActivity = activity.getId();
        String levelfromActivity = activity.getLevel();
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        descBulanan = root.findViewById(R.id.tv_descBulanan);
        Bulanan = root.findViewById(R.id.tv_bulanan);
        descHarian = root.findViewById(R.id.tv_descHarian);
        Harian = root.findViewById(R.id.tv_harian);
        descHarian1 = root.findViewById(R.id.tv_descHarian2);
        Harian1 = root.findViewById(R.id.tv_harian2);
        LL3 = root.findViewById(R.id.linearLayout2);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma = this;
        if(levelfromActivity.equals("pelanggan")){
            LL3.setVisibility(View.GONE);
            descBulanan.setText("Laundry Belum Diambil");
            descHarian.setText("Total Laundry");
            Call<HomeStats> kontakCall = mApiInterface.getBelumdiambil(IDFromActivity);
            kontakCall.enqueue(new Callback<HomeStats>() {
                @Override
                public void onResponse(Call<HomeStats> call, Response<HomeStats>
                        response) {
                    Bulanan.setText(response.body().getHomestats());
                }

                @Override
                public void onFailure(Call<HomeStats> call, Throwable t) {
                    Log.e("ERROR ON Retrofit Get", t.toString());
                }
            });
            kontakCall = mApiInterface.getTotal(IDFromActivity);
            kontakCall.enqueue(new Callback<HomeStats>() {
                @Override
                public void onResponse(Call<HomeStats> call, Response<HomeStats>
                        response) {
                    Harian.setText(response.body().getHomestats());
                }

                @Override
                public void onFailure(Call<HomeStats> call, Throwable t) {
                    Log.e("ERROR ON Retrofit Get", t.toString());
                }
            });
            //fungsi 1;
        }
        else if(levelfromActivity.equals("pekerja")){
            Call<HomeStats> kontakCall = mApiInterface.getPendapatan();
            kontakCall.enqueue(new Callback<HomeStats>() {
                @Override
                public void onResponse(Call<HomeStats> call, Response<HomeStats>
                        response) {
                    Bulanan.setText(response.body().getHomestats());
                }

                @Override
                public void onFailure(Call<HomeStats> call, Throwable t) {
                    Log.e("ERROR ON Retrofit Get", t.toString());
                }
            });
            kontakCall = mApiInterface.getHarian1();
            kontakCall.enqueue(new Callback<HomeStats>() {
                @Override
                public void onResponse(Call<HomeStats> call, Response<HomeStats>
                        response) {
                    Harian.setText(response.body().getHomestats());
                }

                @Override
                public void onFailure(Call<HomeStats> call, Throwable t) {
                    Log.e("ERROR ON Retrofit Get", t.toString());
                }
            });
            kontakCall = mApiInterface.getHarian2();
            kontakCall.enqueue(new Callback<HomeStats>() {
                @Override
                public void onResponse(Call<HomeStats> call, Response<HomeStats>
                        response) {
                    Harian1.setText(response.body().getHomestats());
                }

                @Override
                public void onFailure(Call<HomeStats> call, Throwable t) {
                    Log.e("ERROR ON Retrofit Get", t.toString());
                }
            });
        }
        return root;
    }
}