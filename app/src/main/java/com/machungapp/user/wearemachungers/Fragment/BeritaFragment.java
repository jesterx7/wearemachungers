package com.machungapp.user.wearemachungers.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.machungapp.user.wearemachungers.Classes.Berita;
import com.machungapp.user.wearemachungers.Adapter.ListBeritaAdapter;
import com.machungapp.user.wearemachungers.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BeritaFragment extends Fragment {
    private View view;
    private RecyclerView rvListBerita;
    private SearchView searchBerita;
    private ListBeritaAdapter listBeritaAdapter;
    private ProgressBar progressBar;

    private final int ITEM_LOAD_COUNT = 5;
    private int total_item = 0;
    private int last_visible_item;
    private boolean isLoading = false, isMaxData = false;
    private String last_node = "", last_key = "", frame = "";
    private int frame_id = 0, menu_id = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_berita, container, false);

        getActivity().setTitle("News");
        frame = getArguments().getString("frame");
        setHasOptionsMenu(true);

        if (frame.equals("general")) {
            frame_id = R.id.content_frame;
            menu_id = R.menu.main;
        } else {
            frame_id = R.id.content_frame_user;
            menu_id = R.menu.user;
        }
        rvListBerita = view.findViewById(R.id.rvListBerita);
        searchBerita = view.findViewById(R.id.searchBerita);
        progressBar = view.findViewById(R.id.progressbarBerita);

        isMaxData = false;
        last_node = "";

        getLastKeyFromFirebase();

        listBeritaAdapter = new ListBeritaAdapter(view.getContext());
        rvListBerita.setAdapter(listBeritaAdapter);

        getBerita();

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        rvListBerita.setLayoutManager(linearLayoutManager);

        rvListBerita.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                total_item = linearLayoutManager.getItemCount();
                last_visible_item = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && total_item <= (last_visible_item + ITEM_LOAD_COUNT)) {
                    getBerita();
                    isLoading = true;
                }
            }
        });


        //Settings for make all of search view clickable
        searchBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchBerita.setIconified(false);
            }
        });

        //Settings search query filter
        searchBerita.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (!TextUtils.isEmpty(last_node))
                    listBeritaAdapter.getFilter().filter(s);
                return false;
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
            Fragment fragment = new BeritaFragment();
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

    private void getBerita() {
        if (!isMaxData) {
            Query query;
            if (TextUtils.isEmpty(last_node)) {
                query = FirebaseDatabase.getInstance().getReference()
                        .child("berita")
                        .orderByChild("last_edit")
                        .limitToFirst(ITEM_LOAD_COUNT);
            }
            else {
                query = FirebaseDatabase.getInstance().getReference()
                        .child("berita")
                        .orderByChild("last_edit")
                        .startAt(last_node)
                        .limitToFirst(ITEM_LOAD_COUNT);
            }
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        ArrayList<Berita> newBerita = new ArrayList();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            newBerita.add(data.getValue(Berita.class));
                        }
                        last_node = newBerita.get(newBerita.size() - 1).getLast_edit();

                        if (!last_node.equals(last_key) && newBerita.size() > 1)
                            newBerita.remove(newBerita.size() - 1);
                        else
                            last_node = "end";

                        listBeritaAdapter.addAll(newBerita);
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
                .child("berita")
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
