package com.machungapp.user.wearemachungers.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.machungapp.user.wearemachungers.R;

public class LifeAtContentFragment extends Fragment {
    private View view;
    private DocumentView dvLifeAtContent;
    private TextView tvContentTitle, tvLifeAtLastEdit;
    private String content_id = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.life_at_content, container, false);

        getActivity().setTitle("Life At Ma Chung");

        dvLifeAtContent = view.findViewById(R.id.dvLifeAtContent);
        tvContentTitle = view.findViewById(R.id.tvContentTitle);
        tvLifeAtLastEdit = view.findViewById(R.id.tvLifeAtLastEdit);

        content_id = getArguments().getString("content_id");


        Query query = FirebaseDatabase.getInstance().getReference().child("lifeatmachung").child(content_id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tvContentTitle.setText(dataSnapshot.child("judul").getValue().toString());
                tvLifeAtLastEdit.setText(dataSnapshot.child("last_edit").getValue().toString());
                dvLifeAtContent.setText(Html.fromHtml(dataSnapshot.child("konten").getValue().toString()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}
