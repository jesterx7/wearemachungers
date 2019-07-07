package com.machungapp.user.wearemachungers.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.machungapp.user.wearemachungers.Adapter.ListDepartmentAdapter;
import com.machungapp.user.wearemachungers.Classes.Department;
import com.machungapp.user.wearemachungers.R;

import java.util.ArrayList;

public class FacultyFragment extends Fragment {
    private View view;
    private ImageView imgCoverFaculty;
    private TextView tvFacultyName, tvFacultyContent;
    private RecyclerView rvMajorList;
    private String facultyID = "";
    private ListDepartmentAdapter listDepartmentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.faculty, container, false);

        getActivity().setTitle("Faculty");

        imgCoverFaculty = view.findViewById(R.id.imgCoverFaculty);
        tvFacultyName = view.findViewById(R.id.tvFacultyName);
        tvFacultyContent = view.findViewById(R.id.tvFacultyContent);
        rvMajorList = view.findViewById(R.id.rvMajorList);

        facultyID = getArguments().getString("facultyID");

         listDepartmentAdapter = new ListDepartmentAdapter(view.getContext());

        Query getFaculty = FirebaseDatabase.getInstance().getReference()
                .child("fakultas")
                .child(facultyID);
        getFaculty.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Glide.with(view.getContext())
                        .load(dataSnapshot.child("img_url").getValue().toString())
                        .crossFade()
                        .into(imgCoverFaculty);
                tvFacultyName.setText(dataSnapshot.child("nama").getValue().toString());
                tvFacultyContent.setText(Html.fromHtml(dataSnapshot.child("profil").getValue().toString()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Query query = FirebaseDatabase.getInstance().getReference()
                .child("prodi")
                .orderByChild("last_edit");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Department> newDepartment = new ArrayList<>();
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    if (data.child("fakultas").getValue().toString().equals(facultyID)) {
                        newDepartment.add(data.getValue(Department.class));
                    }
                }
                listDepartmentAdapter.addAll(newDepartment);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        System.out.println("LIST DEPARTMENT " + listDepartmentAdapter.getListDepartment());
        rvMajorList.setLayoutManager(manager);
        rvMajorList.setAdapter(listDepartmentAdapter);


        return view;
    }
}
