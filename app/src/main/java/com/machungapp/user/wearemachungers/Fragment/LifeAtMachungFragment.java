package com.machungapp.user.wearemachungers.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.machungapp.user.wearemachungers.R;

public class LifeAtMachungFragment extends Fragment {
    private View view;
    private ImageView imgFirstWeek, imgAccommodation, imgCulinary, imgTransportation;
    private LinearLayout llFirstContent, llSecondContent, llThirdContent, llFourthContent;
    private String frame = "";
    private int frame_id = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.life_at_machung, container, false);

        getActivity().setTitle("Life At Ma Chung");
        frame = getArguments().getString("frame");

        if (frame.equals("general"))
            frame_id = R.id.content_frame;
        else
            frame_id = R.id.content_frame_user;

        imgFirstWeek = view.findViewById(R.id.imgFirstWeek);
        imgAccommodation = view.findViewById(R.id.imgAccommodation);
        imgCulinary = view.findViewById(R.id.imgCulinary);
        imgTransportation = view.findViewById(R.id.imgTransportation);

        llFirstContent = view.findViewById(R.id.llFirstContent);
        llSecondContent = view.findViewById(R.id.llSecondContent);
        llThirdContent = view.findViewById(R.id.llThirdContent);
        llFourthContent = view.findViewById(R.id.llFourthContent);

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

        llFirstContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new LifeAtContentFragment();
                Bundle bundle = new Bundle();
                bundle.putString("content_id", "-Lezs6Qk_UZ0YMZ-anDI");
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(frame_id, fragment);
                fragmentTransaction.addToBackStack("tag");
                fragmentTransaction.commit();
            }
        });

        llSecondContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new LifeAtContentFragment();
                Bundle bundle = new Bundle();
                bundle.putString("content_id", "-Lf--m1lwFqTA160AOb-");
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(frame_id, fragment);
                fragmentTransaction.addToBackStack("tag");
                fragmentTransaction.commit();
            }
        });

        llThirdContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new LifeAtContentFragment();
                Bundle bundle = new Bundle();
                bundle.putString("content_id", "-Lj48T02Tr9PLHxAd42T");
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(frame_id, fragment);
                fragmentTransaction.addToBackStack("tag");
                fragmentTransaction.commit();
            }
        });

        llFourthContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new LifeAtContentFragment();
                Bundle bundle = new Bundle();
                bundle.putString("content_id", "-Lj48tf5Nd4P6uoVm5z8");
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(frame_id, fragment);
                fragmentTransaction.addToBackStack("tag");
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
