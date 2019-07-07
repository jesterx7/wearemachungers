package com.machungapp.user.wearemachungers.Fragment;

import android.animation.Animator;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.machungapp.user.wearemachungers.R;

public class InformasiFragment extends Fragment {
    private View view;
    private ImageView imgCoverInformasi,imgExpandFaculty, imgDetailFST, imgDetailFEB, imgDetailFIB;
    private LinearLayout llListFakultas;
    private TextView tvVisiMisi, tvSejarahMachung;
    private int frame;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.informasi, container, false);

        getActivity().setTitle("About Ma Chung");

        imgCoverInformasi = view.findViewById(R.id.imgCoverInformasi);
        imgExpandFaculty = view.findViewById(R.id.imgExpandFaculty);
        imgExpandFaculty.setTag(R.drawable.ic_arrow_drop_down);
        imgDetailFST = view.findViewById(R.id.imgDetailFST);
        imgDetailFEB = view.findViewById(R.id.imgDetailFEB);
        imgDetailFIB = view.findViewById(R.id.imgDetailFIB);
        llListFakultas = view.findViewById(R.id.llListFakultas);
        tvVisiMisi = view.findViewById(R.id.tvVisiMisi);
        tvSejarahMachung = view.findViewById(R.id.tvSejarahMachung);

        String activity = getArguments().getString("frame");
        if (activity.equals("general"))
            frame = R.id.content_frame;
        else
            frame = R.id.content_frame_user;



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

        tvVisiMisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new VisiMisiFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(frame, fragment);
                fragmentTransaction.addToBackStack("visimisi");
                fragmentTransaction.commit();
            }
        });

        tvSejarahMachung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SejarahFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(frame, fragment);
                fragmentTransaction.addToBackStack("sejarah");
                fragmentTransaction.commit();
            }
        });

        imgDetailFST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FacultyFragment();
                Bundle bundle = new Bundle();
                bundle.putString("facultyID", "-LdvbgruOQnhY-yhvA3e");
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(frame, fragment);
                fragmentTransaction.addToBackStack("faculty");
                fragmentTransaction.commit();
            }
        });

        imgDetailFEB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FacultyFragment();
                Bundle bundle = new Bundle();
                bundle.putString("facultyID", "-LeMDT4NatgbVPAvdxRS");
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(frame, fragment);
                fragmentTransaction.addToBackStack("faculty");
                fragmentTransaction.commit();
            }
        });

        imgDetailFIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FacultyFragment();
                Bundle bundle = new Bundle();
                bundle.putString("facultyID", "-LeMDZsrLCDWgZg5eOKO");
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(frame, fragment);
                fragmentTransaction.addToBackStack("faculty");
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
