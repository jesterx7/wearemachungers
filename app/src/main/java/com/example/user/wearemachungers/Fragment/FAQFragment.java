package com.example.user.wearemachungers.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.wearemachungers.Classes.Berita;
import com.example.user.wearemachungers.Classes.FAQ;
import com.example.user.wearemachungers.Adapter.ListFAQAdapter;
import com.example.user.wearemachungers.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FAQFragment extends Fragment {
    private View view;
    private RecyclerView rvListFAQ;
    private ListFAQAdapter listFAQAdapter;
    private ProgressBar progressBar;

    private final int ITEM_LOAD_COUNT = 8;
    private int total_item = 0;
    private int last_visible_item;
    private boolean isLoading = false, isMaxData = false;
    private String last_node = "", last_key = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_faq, container, false);
        rvListFAQ = view.findViewById(R.id.rvListFAQ);
        progressBar = view.findViewById(R.id.progressbarFAQ);

        isMaxData = false;
        last_node = "";

        getLastKeyFromFirebase();

        listFAQAdapter = new ListFAQAdapter(view.getContext());
        rvListFAQ.setAdapter(listFAQAdapter);

        getFAQ();

        rvListFAQ.addItemDecoration(new DividerItemDecoration(rvListFAQ.getContext(), DividerItemDecoration.VERTICAL));
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        rvListFAQ.setLayoutManager(linearLayoutManager);


        rvListFAQ.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                total_item = linearLayoutManager.getItemCount();
                last_visible_item = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && total_item <= (last_visible_item + ITEM_LOAD_COUNT)) {
                    getFAQ();
                    isLoading = true;
                }
            }
        });

        return view;
    }

    private void getFAQ() {
        if (!isMaxData) {
            Query query;
            if (TextUtils.isEmpty(last_node)) {
                query = FirebaseDatabase.getInstance().getReference()
                        .child("faq")
                        .orderByChild("last_edit")
                        .limitToFirst(ITEM_LOAD_COUNT);
            }
            else {
                query = FirebaseDatabase.getInstance().getReference()
                        .child("faq")
                        .orderByChild("last_edit")
                        .startAt(last_node)
                        .limitToFirst(ITEM_LOAD_COUNT);
            }
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        ArrayList<FAQ> newFAQ = new ArrayList();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            newFAQ.add(data.getValue(FAQ.class));
                        }
                        last_node = newFAQ.get(newFAQ.size() - 1).getLast_edit();

                        if (!last_node.equals(last_key) && newFAQ.size() > 1)
                            newFAQ.remove(newFAQ.size() - 1);
                        else
                            last_node = "end";

                        listFAQAdapter.addAll(newFAQ);
                        isLoading = false;
                        progressBar.setVisibility(View.GONE);
                    }
                    else {
                        isLoading = false;
                        isMaxData = true;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    isLoading = false;
                }
            });
        }
    }

    private void getLastKeyFromFirebase() {
        final Query query = FirebaseDatabase.getInstance().getReference()
                .child("faq")
                .orderByChild("last_edit")
                .limitToLast(1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren())
                    last_key = data.child("last_edit").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(view.getContext(), "Cannot get last key..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
