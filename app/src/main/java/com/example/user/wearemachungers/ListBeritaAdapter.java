package com.example.user.wearemachungers;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListBeritaAdapter extends RecyclerView.Adapter<ListBeritaAdapter.BeritaViewHolder> {
    private Context context;
    private ArrayList<Berita> listBerita;

    public ListBeritaAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Berita> getListBerita() {
        return listBerita;
    }

    public void setListBerita(ArrayList<Berita> listBerita) {
        this.listBerita = listBerita;
    }

    @Override
    public BeritaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berita, parent, false);
        return new BeritaViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(BeritaViewHolder holder, int position) {
        Glide.with(context)
                .load(getListBerita().get(position).getGambar())
                .crossFade()
                .into(holder.imgCoverBerita);
        holder.tvJudulBerita.setText(getListBerita().get(position).getJudul());
        holder.tvTglBerita.setText(getListBerita().get(position).getLast_edit());
    }

    @Override
    public int getItemCount() {
        return getListBerita().size();
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgCoverBerita;
        private TextView tvJudulBerita;
        private TextView tvTglBerita;
        private Button btnDetail;
        public BeritaViewHolder(View itemView) {
            super(itemView);

            imgCoverBerita = itemView.findViewById(R.id.imgCoverBerita);
            tvJudulBerita = itemView.findViewById(R.id.tvJudulBerita);
            tvTglBerita = itemView.findViewById(R.id.tvTanggalBerita);
            btnDetail = itemView.findViewById(R.id.btnDetail);
        }
    }
}
