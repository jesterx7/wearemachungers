package com.machungapp.user.wearemachungers.Adapter;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.machungapp.user.wearemachungers.Classes.Berita;
import com.machungapp.user.wearemachungers.R;

import java.util.ArrayList;

public class ListBeritaAdapter extends RecyclerView.Adapter<ListBeritaAdapter.BeritaViewHolder> implements Filterable{
    private Context context;
    private ArrayList<Berita> listBerita;
    private ArrayList<Berita> listBeritaCopy;

    public void addAll(ArrayList<Berita> newBerita) {
        int initSize = listBerita.size();
        listBerita.addAll(newBerita);
        listBeritaCopy = new ArrayList<>(listBerita);
        notifyItemRangeChanged(initSize, newBerita.size());
    }

    public ListBeritaAdapter(Context context) {
        this.listBerita = new ArrayList<>();
        this.context = context;
    }

    public ArrayList<Berita> getListBerita() {
        return listBerita;
    }

    public String getLastItemLastEdit() {
        return listBerita.get(listBerita.size() - 1).getLast_edit();
    }

    public void removeLastItem() {
        listBerita.remove(listBerita.size() - 1);
    }


    @Override
    public BeritaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berita, parent, false);
        return new BeritaViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(final BeritaViewHolder holder, int position) {
        Glide.with(context)
                .load(getListBerita().get(position).getImg_url())
                .crossFade()
                .into(holder.imgCoverBerita);
        holder.tvJudulBerita.setText(getListBerita().get(position).getJudul());
        holder.tvTglBerita.setText(getListBerita().get(position).getLast_edit());
        holder.tvKontenBerita.setText(getListBerita().get(position).getKonten());
        holder.btnkonten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.tvKontenBerita.getVisibility() == View.GONE) {
                    holder.tvKontenBerita.setVisibility(View.VISIBLE);
                    holder.tvKontenBerita.setAlpha(0.0f);
                    holder.tvKontenBerita.animate()
                            .translationY(0)
                            .alpha(1.0f)
                            .setListener(null);
                } else {
                    holder.tvKontenBerita.animate()
                            .translationY(-10)
                            .alpha(0.0f)
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animator) {
                                    holder.tvKontenBerita.setVisibility(View.GONE);
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });
                }
            }
        });
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
        private LinearLayout llShortDisplay;
        private LinearLayout llExpandDisplay;
        private TextView tvJudulBerita;
        private TextView tvKontenBerita;
        private TextView tvTglBerita;
        private Button btnkonten;
        public BeritaViewHolder(View itemView) {
            super(itemView);

            imgCoverBerita = itemView.findViewById(R.id.imgCoverBerita);
            llShortDisplay = itemView.findViewById(R.id.llShortDisplay);
            llExpandDisplay = itemView.findViewById(R.id.llExpandDisplay);
            tvJudulBerita = itemView.findViewById(R.id.tvJudulBerita);
            tvKontenBerita = itemView.findViewById(R.id.tvKontenBerita);
            tvTglBerita = itemView.findViewById(R.id.tvTanggalBerita);
            btnkonten = itemView.findViewById(R.id.btnKonten);

            //Settings Font for TextView
            Typeface typefaceJudul = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_condensed_bold.ttf");
            tvJudulBerita.setTypeface(typefaceJudul);


            //Settings Opacity For Date Berita Created
            tvTglBerita.setTextColor(Color.argb(50, 0, 0, 0));
        }
    }
}
