package com.example.user.wearemachungers.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.wearemachungers.Classes.Agenda;
import com.example.user.wearemachungers.Adapter.ListAgendaAdapter;
import com.example.user.wearemachungers.R;

import java.util.ArrayList;

public class AgendaFragment extends Fragment {
    private View view;
    private RecyclerView rvListAgenda;
    private ArrayList<Agenda> listAgenda;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_agenda, container, false);
        rvListAgenda = view.findViewById(R.id.rvListAgenda);
        listAgenda = new ArrayList<>();

        Agenda agenda1 = new Agenda();
        agenda1.setCover("http://cdn2.tstatic.net/bali/foto/bank/images/ra-kartini_20180421_110050.jpg");
        agenda1.setId("1");
        agenda1.setJudul("Peringatan Hari Kartini");
        agenda1.setLastEdit("21 April 2019");
        agenda1.setTglMulai("21");
        agenda1.setTglSelesai("21 April 2019");
        agenda1.setDetail("Para mahasiswa diharapkan memakai baju batik");

        Agenda agenda2 = new Agenda();
        agenda2.setCover("https://cosmopolitanfm.com/wp-content/uploads/2016/12/perayaan-unik-hari-raya-natal-di-dunia.jpg");
        agenda2.setId("2");
        agenda2.setJudul("Libur Hari Natal");
        agenda2.setLastEdit("21 April 2019");
        agenda2.setTglMulai("24");
        agenda2.setTglSelesai("25 Desember 2019");
        agenda2.setDetail("Libur pada tanggal merah untuk memperingati hari natal");

        listAgenda.add(agenda1);
        listAgenda.add(agenda2);

        rvListAgenda.addItemDecoration(new DividerItemDecoration(rvListAgenda.getContext(), DividerItemDecoration.VERTICAL));
        rvListAgenda.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListAgendaAdapter listAgendaAdapter = new ListAgendaAdapter(view.getContext());
        listAgendaAdapter.setListAgenda(listAgenda);
        rvListAgenda.setAdapter(listAgendaAdapter);

        return view;
    }
}
