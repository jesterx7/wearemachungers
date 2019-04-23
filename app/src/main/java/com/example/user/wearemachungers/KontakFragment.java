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

public class KontakFragment extends Fragment {
    private View view;
    private RecyclerView rvListKontak;
    private ArrayList<Kontak> listKontak;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_kontak, container, false);
        rvListKontak = view.findViewById(R.id.rvListKontak);
        listKontak = new ArrayList<>();

        Kontak kontak1 = new Kontak();
        kontak1.setId("1");
        kontak1.setBidang("Keuangan");
        kontak1.setNama("Bu Lani");
        kontak1.setNomerWA("08978628291");
        kontak1.setLastEdit("21 April 2019");

        Kontak kontak2 = new Kontak();
        kontak2.setId("2");
        kontak2.setBidang("Sekfak");
        kontak2.setNama("Pak Budi");
        kontak2.setNomerWA("08199878282");
        kontak2.setLastEdit("21 April 2019");

        listKontak.add(kontak1);
        listKontak.add(kontak2);

        rvListKontak.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListKontakAdapter listKontakAdapter = new ListKontakAdapter(view.getContext());
        listKontakAdapter.setListKontak(listKontak);
        rvListKontak.setAdapter(listKontakAdapter);

        return view;
    }
}
