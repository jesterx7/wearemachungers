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

import com.example.user.wearemachungers.Classes.FAQ;
import com.example.user.wearemachungers.Adapter.ListFAQAdapter;
import com.example.user.wearemachungers.R;

import java.util.ArrayList;

public class FAQFragment extends Fragment {
    private View view;
    private RecyclerView rvListFAQ;
    private ArrayList<FAQ> listFAQ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_faq, container, false);
        rvListFAQ = view.findViewById(R.id.rvListFAQ);
        listFAQ = new ArrayList<>();

        FAQ faq1 = new FAQ();
        faq1.setId("1");
        faq1.setPertanyaanFAQ("Bagaimana cara mendapat beasiswa?");
        faq1.setJawabanFAQ("Mendapatkan IPK > 3.0");
        faq1.setLastEdit("21 April 2019");

        FAQ faq2 = new FAQ();
        faq2.setId("2");
        faq2.setPertanyaanFAQ("Apa saja UKM di Ma Chung?");
        faq2.setJawabanFAQ("Chinese Corner, Dance, Wushu, dll");
        faq2.setLastEdit("21 April 2019");

        listFAQ.add(faq1);
        listFAQ.add(faq2);

        rvListFAQ.addItemDecoration(new DividerItemDecoration(rvListFAQ.getContext(), DividerItemDecoration.VERTICAL));
        rvListFAQ.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListFAQAdapter listFAQAdapter = new ListFAQAdapter(view.getContext());
        listFAQAdapter.setListFAQ(listFAQ);
        rvListFAQ.setAdapter(listFAQAdapter);

        return view;
    }
}
