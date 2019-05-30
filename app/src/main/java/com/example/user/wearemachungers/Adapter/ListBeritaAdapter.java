package com.example.user.wearemachungers.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.wearemachungers.Classes.Berita;
import com.example.user.wearemachungers.R;

import java.util.ArrayList;

public class ListBeritaAdapter extends RecyclerView.Adapter<ListBeritaAdapter.BeritaViewHolder> implements Filterable{
    private Context context;
    private ArrayList<Berita> listBerita;
    private ArrayList<Berita> listBeritaCopy;

    public ListBeritaAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Berita> getListBerita() {
        return listBerita;
    }

    public void setListBerita(ArrayList<Berita> listBerita) {
        this.listBerita = listBerita;
        listBeritaCopy = new ArrayList<>(listBerita);
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

    @Override
    public Filter getFilter() {
        return beritaFilter;
    }

    private Filter beritaFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Berita> filterListBerita = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filterListBerita.addAll(listBeritaCopy);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Berita berita: listBeritaCopy) {
                    if (berita.getJudul().toLowerCase().contains(filterPattern)) {
                        filterListBerita.add(berita);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterListBerita;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listBerita.clear();
            listBerita.addAll((ArrayList)filterResults.values);
            notifyDataSetChanged();
        }
    };

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