package com.example.silauapp.Adapter;


import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.silauapp.EditActivity;
import com.example.silauapp.Model.Pelanggan;
import com.example.silauapp.R;

import java.util.List;

public class PelangganAdapter extends RecyclerView.Adapter<PelangganAdapter.MyViewHolder> {
    List<Pelanggan> mKontakList;

    public PelangganAdapter(List <Pelanggan> KontakList) {
        mKontakList = KontakList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pelanggan, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        holder.mTextViewId.setText("Id = " + mKontakList.get(position).getId());
        holder.mTextViewNama.setText("Nama = " + mKontakList.get(position).getNama());
        holder.mTextViewNomor.setText("Nomor = " + mKontakList.get(position).getNomor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditActivity.class);
                mIntent.putExtra("Id", mKontakList.get(position).getId());
                mIntent.putExtra("Nama", mKontakList.get(position).getNama());
                mIntent.putExtra("Nomor", mKontakList.get(position).getNomor());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount () {
        return mKontakList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewNama, mTextViewNomor;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId = (TextView) itemView.findViewById(R.id.tvId);
            mTextViewNama = (TextView) itemView.findViewById(R.id.tvNama);
            mTextViewNomor = (TextView) itemView.findViewById(R.id.tvNomor);
        }
    }
}
