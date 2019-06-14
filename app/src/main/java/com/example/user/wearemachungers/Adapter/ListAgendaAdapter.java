package com.example.user.wearemachungers.Adapter;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.wearemachungers.Classes.Agenda;
import com.example.user.wearemachungers.Classes.Newsletter;
import com.example.user.wearemachungers.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListAgendaAdapter extends RecyclerView.Adapter<ListAgendaAdapter.AgendaViewHolder> {
    private Context context;
    private ArrayList<Agenda> listAgenda;

    public ListAgendaAdapter(Context context) {
        this.listAgenda = new ArrayList<>();
        this.context = context;
    }

    public void addAll(ArrayList<Agenda> newAgenda) {
        int initSize = listAgenda.size();
        listAgenda.addAll(newAgenda);
        notifyItemRangeChanged(initSize, newAgenda.size());
    }

    public ArrayList<Agenda> getListAgenda() {
        return listAgenda;
    }

    @Override
    public AgendaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda, parent, false);
        return new AgendaViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(final AgendaViewHolder holder, int position) {
        Glide.with(context)
                .load(getListAgenda().get(position).getImg_url())
                .crossFade()
                .into(holder.imgCoverAgenda);
        holder.tvDetailAgenda.setText(getListAgenda().get(position).getKonten());
        holder.imgDetailAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.llAgendaFirstDisplay.getVisibility() == View.VISIBLE) {
                    holder.imgDetailAgenda.setImageResource(R.drawable.ic_keyboard_arrow_left);
                    holder.llAgendaFirstDisplay.animate()
                            .translationX(-10)
                            .alpha(0.0f)
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animator) {
                                    holder.llAgendaFirstDisplay.setVisibility(View.GONE);
                                    holder.tvDetailAgenda.setVisibility(View.VISIBLE);
                                    holder.tvDetailAgenda.setAlpha(0.0f);
                                    holder.tvDetailAgenda.animate()
                                            .translationX(0)
                                            .alpha(1.0f)
                                            .setListener(null);
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });
                } else {
                    holder.imgDetailAgenda.setImageResource(R.drawable.ic_keyboard_arrow_right);
                    holder.tvDetailAgenda.animate()
                            .translationX(10)
                            .alpha(0.0f)
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animator) {
                                    holder.tvDetailAgenda.setVisibility(View.GONE);
                                    holder.llAgendaFirstDisplay.setVisibility(View.VISIBLE);
                                    holder.llAgendaFirstDisplay.setAlpha(0.0f);
                                    holder.llAgendaFirstDisplay.animate()
                                            .translationX(0)
                                            .alpha(1.0f)
                                            .setListener(null);
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
        holder.tvJudulAgenda.setText(getListAgenda().get(position).getJudul());
        holder.tvTanggalAgenda.setText(getListAgenda().get(position).getTgl_mulai() + " - " + getListAgenda().get(position).getTgl_selesai());
    }

    @Override
    public int getItemCount() {
        return getListAgenda().size();
    }

    public class AgendaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCoverAgenda;
        private ImageView imgDetailAgenda;
        private LinearLayout llAgendaFirstDisplay;
        private TextView tvJudulAgenda;
        private TextView tvTanggalAgenda;
        private TextView tvDetailAgenda;

        public AgendaViewHolder(View itemView) {
            super(itemView);

            imgCoverAgenda = itemView.findViewById(R.id.imgCoverAgenda);
            imgDetailAgenda = itemView.findViewById(R.id.imgDetailAgenda);
            llAgendaFirstDisplay = itemView.findViewById(R.id.llAgendaFirstDisplay);
            tvJudulAgenda = itemView.findViewById(R.id.tvJudulAgenda);
            tvTanggalAgenda = itemView.findViewById(R.id.tvTanggalAgenda);
            tvDetailAgenda = itemView.findViewById(R.id.tvDetailAgenda);

            //Settings Font for TextView
            Typeface typefaceJudulAgenda = Typeface.createFromAsset(context.getAssets(), "fonts/opensans_bold.ttf");
            Typeface typefaceTanggalAgenda = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_condensed_light.ttf");
            tvJudulAgenda.setTypeface(typefaceJudulAgenda);
            tvTanggalAgenda.setTypeface(typefaceTanggalAgenda);
        }
    }
}
