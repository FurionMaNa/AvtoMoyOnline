package com.project.avtomoy.ui.info.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.avtomoy.R;
import com.project.avtomoy.ServicesOrderClass;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DutyAdapterCardInfo extends RecyclerView.Adapter<DutiesViewHolderCardInfo> {
    private ArrayList<ServicesOrderClass> duties;

    public DutyAdapterCardInfo(ArrayList<ServicesOrderClass> duties) {
        this.duties = duties;
    }

    @NonNull
    @Override
    public DutiesViewHolderCardInfo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_card,parent,false);
        return  new DutiesViewHolderCardInfo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DutiesViewHolderCardInfo holder, int position) {
        holder.bindData(duties.get(position));
    }

    @Override
    public int getItemCount() {
        return duties.size();
    }

}

