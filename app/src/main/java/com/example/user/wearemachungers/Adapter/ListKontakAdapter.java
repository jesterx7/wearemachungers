package com.example.user.wearemachungers.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.wearemachungers.Classes.Kontak;
import com.example.user.wearemachungers.R;

import java.net.URLEncoder;
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
    public void onBindViewHolder(final KontakViewHolder holder, final int position) {
        Glide.with(context)
                .load("https://4.bp.blogspot.com/-CuW_nyiedwM/WIqxTKmwHYI/AAAAAAAACUg/zR56L8nXIE4KSoToVNLdLsyoiKQY5rZUQCLcB/s400/VEKTOR%2BICON1.png")
                .crossFade()
                .into(holder.imgWA);
        holder.imgWA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager pm= context.getPackageManager();
                try {

                    PackageManager packageManager = context.getPackageManager();
                    Intent i = new Intent(Intent.ACTION_VIEW);

                    String url = "https://api.whatsapp.com/send?phone="+ listKontak.get(position).getNomerWA() +"&text=" + URLEncoder.encode("Hello World", "UTF-8");
                    i.setPackage("com.whatsapp");
                    i.setData(Uri.parse(url));
                    if (i.resolveActivity(packageManager) != null) {
                        context.startActivity(i);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(context, "WhatsApp Not Installed", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

            //Settings Font for TextView
            Typeface typefaceBidang = Typeface.createFromAsset(context.getAssets(), "fonts/opensans_bold.ttf");
            Typeface typefaceNamaKontak = Typeface.createFromAsset(context.getAssets(), "fonts/opensans_semibold.ttf");
            tvBidang.setTypeface(typefaceBidang);
            tvNamaKontak.setTypeface(typefaceNamaKontak);
        }
    }
}
