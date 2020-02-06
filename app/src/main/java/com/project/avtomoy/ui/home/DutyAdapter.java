package com.project.avtomoy.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.avtomoy.R;
import com.project.avtomoy.ServicesClass;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DutyAdapter extends RecyclerView.Adapter<DutiesViewHolder> {
    private ArrayList<ServicesClass> duties;
    private ArrayList<Integer> SelectComplex;

    public DutyAdapter(ArrayList<ServicesClass> duties, ArrayList<Integer> selectComplex) {
        this.duties = duties;
        this.SelectComplex = selectComplex;
    }

    @NonNull
    @Override
    public DutiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_for_duty,parent,false);
        return  new DutiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DutiesViewHolder holder, int position) {
        holder.bindData(duties.get(position),SelectComplex);
    }

    @Override
    public int getItemCount() {
        return duties.size();
    }

}

