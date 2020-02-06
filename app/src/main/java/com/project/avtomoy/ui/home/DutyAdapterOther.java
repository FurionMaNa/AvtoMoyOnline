package com.project.avtomoy.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.avtomoy.R;
import com.project.avtomoy.ServicesClass;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DutyAdapterOther extends RecyclerView.Adapter<DutiesViewHolderOther> {
    private ArrayList<ServicesClass> duties;
    private ArrayList<Integer> SelectComplex;

    public DutyAdapterOther(ArrayList<ServicesClass> duties, ArrayList<Integer> selectComplex) {
        this.duties = duties;
        SelectComplex = selectComplex;
    }

    @NonNull
    @Override
    public DutiesViewHolderOther onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_for_duty,parent,false);
        return  new DutiesViewHolderOther(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DutiesViewHolderOther holder, int position) {
        holder.bindData(duties.get(position),SelectComplex);
    }

    @Override
    public int getItemCount() {
        return duties.size();
    }

}

