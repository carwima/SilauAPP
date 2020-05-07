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

import java.util.List;

public class TransAdapter extends RecyclerView.Adapter<TransAdapter.MyViewHolder>{
    List<Trans> mTransList;
    String Level,User;

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
        holder.mTextViewId.setText("Id = " + mTransList.get(position).getId());
        holder.mTextViewNama.setText("Id Pelanggan = " + mTransList.get(position).getIdPelanggan());
        holder.mTextViewTanggal.setText("Tanggal Masuk = " + mTransList.get(position).getTglMasuk());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailTransaksi.class);
                mIntent.putExtra("Id", mTransList.get(position).getId());
                mIntent.putExtra("IdPelanggan", mTransList.get(position).getIdPelanggan());
                mIntent.putExtra("Tanggal", mTransList.get(position).getTglMasuk());
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
