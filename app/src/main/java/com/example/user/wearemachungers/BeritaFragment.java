package com.example.user.wearemachungers;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class BeritaFragment extends Fragment {
    private View view;
    private RecyclerView rvListBerita;
    private ArrayList<Berita> listBerita;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_berita, container, false);
        rvListBerita = view.findViewById(R.id.rvListBerita);
        listBerita = new ArrayList<>();

        Berita berita1 = new Berita();
        berita1.setId("1");
        berita1.setGambar("https://i2.wp.com/harga.web.id/wp-content/uploads/Universitas-Ma-Chung-Malang.jpg");
        berita1.setJudul("Sejarah Universitas Ma Chung");
        berita1.setDetail("Penjelasan tentang Universitas Ma Chung");
        berita1.setLast_edit("14 April 2019");

        Berita berita2 = new Berita();
        berita2.setId("2");
        berita2.setGambar("http://3.bp.blogspot.com/-7QvTzUmHNuY/ViUWXTGHxYI/AAAAAAAAAGE/pau4GFBipsY/s1600/IMG_20151019_144055.jpg");
        berita2.setJudul("Student Center Telah Dibuka");
        berita2.setDetail("Dibukanya student center pada hari kerja kecuali weekend");
        berita2.setLast_edit("15 April 2019");

        listBerita.add(berita1);
        listBerita.add(berita2);

        rvListBerita.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListBeritaAdapter listBeritaAdapter = new ListBeritaAdapter(view.getContext());
        listBeritaAdapter.setListBerita(listBerita);
        rvListBerita.setAdapter(listBeritaAdapter);
        return view;
    }
}
