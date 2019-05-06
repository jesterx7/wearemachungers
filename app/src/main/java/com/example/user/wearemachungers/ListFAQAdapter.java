package com.example.user.wearemachungers;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListFAQAdapter extends RecyclerView.Adapter<ListFAQAdapter.FAQViewHolder> {
    private Context context;
    private ArrayList<FAQ> listFAQ;

    public ListFAQAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<FAQ> getListFAQ() {
        return listFAQ;
    }

    public void setListFAQ(ArrayList<FAQ> listFAQ) {
        this.listFAQ = listFAQ;
    }

    @Override
    public FAQViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq, parent, false);
        return new FAQViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(final FAQViewHolder holder, int position) {
        holder.tvPertanyaanFAQ.setText(getListFAQ().get(position).getPertanyaanFAQ());
        holder.tvJawabanFAQ.setText(getListFAQ().get(position).getJawabanFAQ());
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
        }
    }
}
