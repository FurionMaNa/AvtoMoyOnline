package com.project.avtomoy.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.avtomoy.R;
import com.project.avtomoy.ServicesClass;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DutyAdapterRecord extends RecyclerView.Adapter<DutiesViewHolderRecord> {
    private ArrayList<ServicesClass> duties;

    public DutyAdapterRecord(ArrayList<ServicesClass> duties) {
        this.duties = duties;
    }

    @NonNull
    @Override
    public DutiesViewHolderRecord onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_for_duty,parent,false);
        return  new DutiesViewHolderRecord(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DutiesViewHolderRecord holder, int position) {
        holder.bindData(duties.get(position));
    }

    @Override
    public int getItemCount() {
        return duties.size();
    }

}

