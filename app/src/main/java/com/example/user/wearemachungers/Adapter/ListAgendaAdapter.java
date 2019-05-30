package com.example.user.wearemachungers.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.wearemachungers.Classes.Agenda;
import com.example.user.wearemachungers.R;

import java.util.ArrayList;

public class ListAgendaAdapter extends RecyclerView.Adapter<ListAgendaAdapter.AgendaViewHolder> {
    private Context context;
    private ArrayList<Agenda> listAgenda;

    public ListAgendaAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Agenda> getListAgenda() {
        return listAgenda;
    }

    public void setListAgenda(ArrayList<Agenda> listAgenda) {
        this.listAgenda = listAgenda;
    }

    @Override
    public AgendaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda, parent, false);
        return new AgendaViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(AgendaViewHolder holder, int position) {
        Glide.with(context)
                .load(getListAgenda().get(position).getCover())
                .crossFade()
                .into(holder.imgCoverAgenda);
        holder.tvJudulAgenda.setText(getListAgenda().get(position).getJudul());
        holder.tvTanggalAgenda.setText(getListAgenda().get(position).getTglMulai() + " - " + getListAgenda().get(position).getTglSelesai());
    }

    @Override
    public int getItemCount() {
        return getListAgenda().size();
    }

    public class AgendaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCoverAgenda;
        private TextView tvJudulAgenda;
        private TextView tvTanggalAgenda;

        public AgendaViewHolder(View itemView) {
            super(itemView);

            imgCoverAgenda = itemView.findViewById(R.id.imgCoverAgenda);
            tvJudulAgenda = itemView.findViewById(R.id.tvJudulAgenda);
            tvTanggalAgenda = itemView.findViewById(R.id.tvTanggalAgenda);
        }
    }
}
