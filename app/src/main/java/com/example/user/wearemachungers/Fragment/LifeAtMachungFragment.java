package com.example.user.wearemachungers.Fragment;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.user.wearemachungers.R;

public class LifeAtMachungFragment extends Fragment {
    private View view;
    private ImageView imgFirstWeek, imgAccommodation, imgCulinary, imgTransportation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.life_at_machung, container, false);

        imgFirstWeek = view.findViewById(R.id.imgFirstWeek);
        imgAccommodation = view.findViewById(R.id.imgAccommodation);
        imgCulinary = view.findViewById(R.id.imgCulinary);
        imgTransportation = view.findViewById(R.id.imgTransportation);

        Glide.with(view.getContext())
                .load("http://3.bp.blogspot.com/-jSjPf6t0YNQ/ViW6cXAm0TI/AAAAAAAAAJE/AMe39fqNa_E/s1600/rnd2.jpg")
                .crossFade()
                .into(imgFirstWeek);

        Glide.with(view.getContext())
                .load("http://1.bp.blogspot.com/-XEHovvRKO78/VhMvXGJ0yoI/AAAAAAAAADY/NZzhzbuhsj4/s1600/FB_IMG_1441268692253.jpg")
                .crossFade()
                .into(imgAccommodation);

        Glide.with(view.getContext())
                .load("https://foodfornet.com/wp-content/uploads/Nasi-Goreng-Final-1.jpg")
                .crossFade()
                .into(imgCulinary);

        Glide.with(view.getContext())
                .load("http://3.bp.blogspot.com/-SuQfn3Rbdxg/ViW6LfmGleI/AAAAAAAAAI4/6IsN-Tt89Z0/s1600/kantor1-th.jpg")
                .crossFade()
                .into(imgTransportation);

        return view;
    }
}
