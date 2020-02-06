package com.project.avtomoy.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.avtomoy.R;
import com.project.avtomoy.ServicesClass;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DutyAdapterCardOther extends RecyclerView.Adapter<DutiesViewHolderCardOther> {
    private ArrayList<ServicesClass> duties;
    private ArrayList<Integer> SelectComplex;

    public DutyAdapterCardOther(ArrayList<ServicesClass> duties, ArrayList<Integer> selectComplex) {
        this.duties = duties;
        this.SelectComplex = selectComplex;
    }

    @NonNull
    @Override
    public DutiesViewHolderCardOther onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_card,parent,false);
        return  new DutiesViewHolderCardOther(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DutiesViewHolderCardOther holder, int position) {
        holder.bindData(duties.get(position), SelectComplex);
    }

    @Override
    public int getItemCount() {
        return duties.size();
    }

}

