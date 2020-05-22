package com.example.silauapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.silauapp.DetailTransaksi;
import com.example.silauapp.Model.transaksi.Trans;
import com.example.silauapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

public class TransAdapter extends RecyclerView.Adapter<TransAdapter.MyViewHolder>{
    List<Trans> mTransList;
    String Level,User;
    String namahari;

    public TransAdapter(List <Trans> KontakList, String level, String user) {
        mTransList = KontakList;
        Level=level;
        User=user;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_trans, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        String s = mTransList.get(position).getTglMasuk().substring(0, 10);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.ENGLISH);
        Date myDate = null;
        try {
            Calendar c = Calendar.getInstance();
            myDate = sdf.parse(s);
            c.setTime(myDate);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek==1){
                namahari="Minggu, ";
            }
            else if(dayOfWeek==2){
                namahari="Senin, ";
            }
            else if(dayOfWeek==3){
                namahari="Selasa, ";
            }
            else if(dayOfWeek==4){
                namahari="Rabu, ";
            }
            else if(dayOfWeek==5){
                namahari="Kamis, ";
            }
            else if(dayOfWeek==6){
                namahari="Jum'at, ";
            }
            else if(dayOfWeek==7){
                namahari="Sabtu, ";
            }
            myDate = sdf.parse(s);
            sdf.applyPattern("dd-MM-yyyy");
            namahari = namahari+sdf.format(myDate);
            holder.mTextViewTanggal.setText("Tanggal Masuk = " + namahari);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.mTextViewId.setText("Id = " + mTransList.get(position).getId());
        holder.mTextViewNama.setText("Id Pelanggan = " + mTransList.get(position).getIdPelanggan());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailTransaksi.class);
                mIntent.putExtra("Id", mTransList.get(position).getId());
                mIntent.putExtra("IdPelanggan", mTransList.get(position).getIdPelanggan());
                mIntent.putExtra("Tanggal", namahari);
                mIntent.putExtra("IdPaket", mTransList.get(position).getIdPaket());
                mIntent.putExtra("Harga", mTransList.get(position).getBerat());
                mIntent.putExtra("Status", mTransList.get(position).getStatusBayar());
                mIntent.putExtra("Level", Level);
                mIntent.putExtra("User", User);
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount () {
        if(mTransList != null)
            return mTransList.size();
        else
            return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewNama, mTextViewTanggal;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId = (TextView) itemView.findViewById(R.id.tvId);
            mTextViewNama = (TextView) itemView.findViewById(R.id.tvTglmsk);
            mTextViewTanggal = (TextView) itemView.findViewById(R.id.tvNama);
        }
    }
}
