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
import android.widget.LinearLayout;

import com.example.user.wearemachungers.Adapter.ListNewsletterAdapter;
import com.example.user.wearemachungers.Classes.Newsletter;
import com.example.user.wearemachungers.R;

import java.util.ArrayList;

public class NewsletterFragment extends Fragment {
    private View view;
    private RecyclerView rvListNewsletter;
    private ArrayList<Newsletter> listNewsletter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_newsletter, container, false);
        rvListNewsletter = view.findViewById(R.id.rvListNewsletter);
        listNewsletter = new ArrayList<>();

        Newsletter newsletter1 = new Newsletter();
        newsletter1.setId("1");
        newsletter1.setEmail("marcell@gmail.com");
        newsletter1.setNamaPDF("Kalender Akademik");
        newsletter1.setUrl("https://journal.unnes.ac.id/nju/index.php/kemas/article/download/3068/3085");
        newsletter1.setLastEdit("21 April 2019");

        Newsletter newsletter2 = new Newsletter();
        newsletter2.setId("1");
        newsletter2.setEmail("ekky@gmail.com");
        newsletter2.setNamaPDF("Jadwal Kuliah IF");
        newsletter2.setUrl("http://journal.uin-alauddin.ac.id/index.php/kesehatan/article/download/5029/5171");
        newsletter2.setLastEdit("21 April 2019");

        listNewsletter.add(newsletter1);
        listNewsletter.add(newsletter2);

        rvListNewsletter.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        rvListNewsletter.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListNewsletterAdapter listNewsletterAdapter = new ListNewsletterAdapter(view.getContext());
        listNewsletterAdapter.setListNewsletter(listNewsletter);
        rvListNewsletter.setAdapter(listNewsletterAdapter);

        return view;
    }
}
