package com.example.user.wearemachungers.Fragment;

import android.animation.Animator;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;
import com.example.user.wearemachungers.R;

public class InformasiFragment extends Fragment {
    private View view;
    private ImageView imgCoverInformasi,imgExpandFaculty, imgDetailFST, imgDetailFEB, imgDetailFIB;
    private LinearLayout llListFakultas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.informasi, container, false);
        imgCoverInformasi = view.findViewById(R.id.imgCoverInformasi);
        imgExpandFaculty = view.findViewById(R.id.imgExpandFaculty);
        imgExpandFaculty.setTag(R.drawable.ic_arrow_drop_down);
        imgDetailFST = view.findViewById(R.id.imgDetailFST);
        imgDetailFEB = view.findViewById(R.id.imgDetailFEB);
        imgDetailFIB = view.findViewById(R.id.imgDetailFIB);
        llListFakultas = view.findViewById(R.id.llListFakultas);

        imgExpandFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imgExpandFaculty.getTag().equals(R.drawable.ic_arrow_drop_down)) {
                    llListFakultas.setVisibility(View.VISIBLE);
                    llListFakultas.setAlpha(0.0f);
                    llListFakultas.animate()
                            .translationY(10)
                            .alpha(1.0f)
                            .setListener(null);
                    imgExpandFaculty.setImageResource(R.drawable.ic_arrow_drop_up);
                    imgExpandFaculty.setTag(R.drawable.ic_arrow_drop_up);
                } else {
                    llListFakultas.animate()
                            .translationY(-10)
                            .alpha(0.0f)
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animator) {
                                    llListFakultas.setVisibility(View.GONE);
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });
                    imgExpandFaculty.setImageResource(R.drawable.ic_arrow_drop_down);
                    imgExpandFaculty.setTag(R.drawable.ic_arrow_drop_down);
                }
            }
        });

        Glide.with(view.getContext())
                .load("https://hoteldekatkampus.com/wp-content/uploads/2014/10/universitas-ma-chung.jpg")
                .crossFade()
                .into(imgCoverInformasi);

        return view;
    }
}
