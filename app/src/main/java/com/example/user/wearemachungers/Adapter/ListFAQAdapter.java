package com.example.user.wearemachungers.Adapter;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.wearemachungers.Classes.Berita;
import com.example.user.wearemachungers.Classes.FAQ;
import com.example.user.wearemachungers.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListFAQAdapter extends RecyclerView.Adapter<ListFAQAdapter.FAQViewHolder> {
    private Context context;
    private ArrayList<FAQ> listFAQ;

    public ListFAQAdapter(Context context) {
        this.listFAQ = new ArrayList<>();
        this.context = context;
    }

    public void addAll(ArrayList<FAQ> newFAQ) {
        int initSize = listFAQ.size();
        listFAQ.addAll(newFAQ);
        notifyItemRangeChanged(initSize, newFAQ.size());
    }

    public ArrayList<FAQ> getListFAQ() {
        return listFAQ;
    }

    @Override
    public FAQViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq, parent, false);
        return new FAQViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(final FAQViewHolder holder, int position) {
        holder.tvPertanyaanFAQ.setText(getListFAQ().get(position).getPertanyaan());
        holder.tvJawabanFAQ.setText(getListFAQ().get(position).getJawaban());
        holder.imgIconFAQ.setTag(R.drawable.ic_arrow_drop_down);
        holder.imgIconFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.imgIconFAQ.getTag().equals(R.drawable.ic_arrow_drop_down)) {
                    holder.imgIconFAQ.setImageResource(R.drawable.ic_arrow_drop_up);
                    holder.imgIconFAQ.setTag(R.drawable.ic_arrow_drop_up);
                    holder.tvJawabanFAQ.setVisibility(View.VISIBLE);
                    holder.tvJawabanFAQ.setAlpha(0.0f);
                    holder.tvJawabanFAQ.animate()
                            .translationY(0)
                            .alpha(1.0f)
                            .setListener(null);
                } else {
                    holder.imgIconFAQ.setImageResource(R.drawable.ic_arrow_drop_down);
                    holder.imgIconFAQ.setTag(R.drawable.ic_arrow_drop_down);
                    holder.tvJawabanFAQ.animate()
                            .translationY(-10)
                            .alpha(0.0f)
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animator) {
                                    holder.tvJawabanFAQ.setVisibility(View.GONE);
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
        return getListFAQ().size();
    }

    public class FAQViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgIconFAQ;
        private TextView tvPertanyaanFAQ;
        private TextView tvJawabanFAQ;

        public FAQViewHolder(View itemView) {
            super(itemView);

            imgIconFAQ = itemView.findViewById(R.id.imgIconFAQ);
            tvPertanyaanFAQ = itemView.findViewById(R.id.tvPertanyaanFAQ);
            tvJawabanFAQ = itemView.findViewById(R.id.tvJawabanFAQ);

            //Settings Font for TextView
            Typeface typefacePertanyaanFAQ = Typeface.createFromAsset(context.getAssets(), "fonts/opensans_bold.ttf");
            Typeface typefaceJawabanFAQ = Typeface.createFromAsset(context.getAssets(), "fonts/opensans_regular.ttf");
            tvPertanyaanFAQ.setTypeface(typefacePertanyaanFAQ);
            tvJawabanFAQ.setTypeface(typefaceJawabanFAQ);
        }
    }
}
