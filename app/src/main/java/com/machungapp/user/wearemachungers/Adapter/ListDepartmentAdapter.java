package com.machungapp.user.wearemachungers.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.machungapp.user.wearemachungers.Classes.Department;
import com.machungapp.user.wearemachungers.R;

import java.util.ArrayList;

public class ListDepartmentAdapter extends RecyclerView.Adapter<ListDepartmentAdapter.DepartmentViewHolder> {
    private Context context;
    private ArrayList<Department> listDepartment;

    public ListDepartmentAdapter(Context context) {
        this.listDepartment = new ArrayList<>();
        this.context = context;
    }

    public ArrayList<Department> getListDepartment() {
        return listDepartment;
    }

    public void addAll(ArrayList<Department> newBerita) {
        int initSize = listDepartment.size();
        listDepartment.addAll(newBerita);
        notifyItemRangeChanged(initSize, newBerita.size());
    }

    @NonNull
    @Override
    public ListDepartmentAdapter.DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_department, viewGroup, false);
        return new DepartmentViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ListDepartmentAdapter.DepartmentViewHolder departmentViewHolder, int i) {
        departmentViewHolder.tvDepartment.setText(listDepartment.get(i).getNama());
    }

    @Override
    public int getItemCount() {
        return listDepartment.size();
    }

    public class DepartmentViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDepartment;

        public DepartmentViewHolder(View itemView) {
            super(itemView);

            tvDepartment = itemView.findViewById(R.id.tvDepartment);
        }
    }
}
