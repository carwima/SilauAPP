package com.example.silauapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.silauapp.DetailPaket;
import com.example.silauapp.Model.paketlaundry.Paket;
import com.example.silauapp.R;

import java.util.List;

public class PaketAdapter extends RecyclerView.Adapter<PaketAdapter.MyViewHolder> {
    List<Paket> mPaketList;

    public PaketAdapter(List<Paket> PaketList) {
        mPaketList = PaketList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.paket_list, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextViewId.setText("Id = " + mPaketList.get(position).getId());
        holder.mTextViewNamapaket.setText("Namapaket = " + mPaketList.get(position).getNamapaket());
        holder.mTextViewHarga.setText("Harga = " + mPaketList.get(position).getHarga());
        holder.mTextViewDeskripsi.setText("Deskripsi = " + mPaketList.get(position).getDeskripsi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailPaket.class);
                mIntent.putExtra("Id", mPaketList.get(position).getId());
                mIntent.putExtra("Namapaket", mPaketList.get(position).getNamapaket());
                mIntent.putExtra("Harga", mPaketList.get(position).getHarga());
                mIntent.putExtra("Deskripsi", mPaketList.get(position).getDeskripsi());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mPaketList != null)
            return mPaketList.size();
        else
            return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewNamapaket, mTextViewHarga, mTextViewDeskripsi;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId = (TextView) itemView.findViewById(R.id.tvId);
            mTextViewNamapaket = (TextView) itemView.findViewById(R.id.tvNamaPaket);
            mTextViewHarga = (TextView) itemView.findViewById(R.id.tvHarga);
            mTextViewDeskripsi = (TextView) itemView.findViewById(R.id.tvDskripsi);
        }
    }
}
