package com.example.user.wearemachungers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListKontakAdapter extends RecyclerView.Adapter<ListKontakAdapter.KontakViewHolder> {
    private Context context;
    private ArrayList<Kontak> listKontak;

    public ListKontakAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Kontak> getListKontak() {
        return listKontak;
    }

    public void setListKontak(ArrayList<Kontak> listKontak) {
        this.listKontak = listKontak;
    }

    @Override
    public KontakViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kontak, parent, false);
        return new KontakViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(KontakViewHolder holder, int position) {
        Glide.with(context)
                .load("https://4.bp.blogspot.com/-CuW_nyiedwM/WIqxTKmwHYI/AAAAAAAACUg/zR56L8nXIE4KSoToVNLdLsyoiKQY5rZUQCLcB/s400/VEKTOR%2BICON1.png")
                .crossFade()
                .into(holder.imgWA);
        holder.tvBidang.setText(getListKontak().get(position).getBidang());
        holder.tvNamaKontak.setText(getListKontak().get(position).getNama());
        holder.tvNomerKontak.setText(getListKontak().get(position).getNomerWA());
    }

    @Override
    public int getItemCount() {
        return getListKontak().size();
    }

    public class KontakViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgWA;
        private TextView tvBidang;
        private TextView tvNamaKontak;
        private TextView tvNomerKontak;

        public KontakViewHolder(View itemView) {
            super(itemView);

            imgWA = itemView.findViewById(R.id.imgWA);
            tvBidang = itemView.findViewById(R.id.tvBidang);
            tvNamaKontak = itemView.findViewById(R.id.tvNamaKontak);
            tvNomerKontak = itemView.findViewById(R.id.tvNomerKontak);
        }
    }
}
