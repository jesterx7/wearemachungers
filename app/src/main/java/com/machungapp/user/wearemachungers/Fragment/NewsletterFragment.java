package com.machungapp.user.wearemachungers.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.machungapp.user.wearemachungers.Adapter.ListNewsletterAdapter;
import com.machungapp.user.wearemachungers.Classes.Newsletter;
import com.machungapp.user.wearemachungers.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewsletterFragment extends Fragment {
    private View view;
    private RecyclerView rvListNewsletter;
    private ListNewsletterAdapter listNewsletterAdapter;
    private ProgressBar progressBar;

    private final int ITEM_LOAD_COUNT = 5;
    private int total_item = 0;
    private int last_visible_item;
    private boolean isLoading = false, isMaxData = false;
    private String last_node = "", last_key = "", frame = "";
    private int frame_id = 0, menu_id = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_newsletter, container, false);

        getActivity().setTitle("Newsletter");
        frame = getArguments().getString("frame");
        setHasOptionsMenu(true);

        if (frame.equals("general")) {
            frame_id = R.id.content_frame;
            menu_id = R.menu.main;
        } else {
            frame_id = R.id.content_frame_user;
            menu_id = R.menu.user;
        }

        rvListNewsletter = view.findViewById(R.id.rvListNewsletter);
        progressBar = view.findViewById(R.id.progressbarNewsletter);

        isMaxData = false;
        last_node = "";

        getLastKeyFromFirebase();

        listNewsletterAdapter = new ListNewsletterAdapter(view.getContext());
        rvListNewsletter.setAdapter(listNewsletterAdapter);

        getNewsletter();

        rvListNewsletter.addItemDecoration(new DividerItemDecoration(rvListNewsletter.getContext(), DividerItemDecoration.VERTICAL));
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        rvListNewsletter.setLayoutManager(linearLayoutManager);

        rvListNewsletter.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                total_item = linearLayoutManager.getItemCount();
                last_visible_item = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && total_item <= (last_visible_item + ITEM_LOAD_COUNT)) {
                    getNewsletter();
                    isLoading = true;
                }
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(menu_id, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh || id == R.id.action_refresh_user) {
            Fragment fragment = new NewsletterFragment();
            Bundle bundle = new Bundle();
            bundle.putString("frame", frame);
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(frame_id, fragment);
            fragmentTransaction.addToBackStack("berita");
            fragmentTransaction.commit();
        }
        return true;
    }

    private void getNewsletter() {
        if (!isMaxData) {
            Query query;
            if (TextUtils.isEmpty(last_node)) {
                query = FirebaseDatabase.getInstance().getReference()
                        .child("newsletter")
                        .orderByChild("last_edit")
                        .limitToFirst(ITEM_LOAD_COUNT);
            }
            else {
                query = FirebaseDatabase.getInstance().getReference()
                        .child("newsletter")
                        .orderByChild("last_edit")
                        .startAt(last_node)
                        .limitToFirst(ITEM_LOAD_COUNT);
            }
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        ArrayList<Newsletter> newNewsletter = new ArrayList();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            newNewsletter.add(data.getValue(Newsletter.class));
                        }
                        last_node = newNewsletter.get(newNewsletter.size() - 1).getLast_edit();

                        if (!last_node.equals(last_key) && newNewsletter.size() > 1)
                            newNewsletter.remove(newNewsletter.size() - 1);
                        else
                            last_node = "end";

                        listNewsletterAdapter.addAll(newNewsletter);
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
                .child("newsletter")
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
