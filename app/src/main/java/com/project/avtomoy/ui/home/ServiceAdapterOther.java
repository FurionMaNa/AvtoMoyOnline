package com.project.avtomoy.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.avtomoy.R;
import com.project.avtomoy.ServicesClass;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ServiceAdapterOther extends RecyclerView.Adapter<ServicesViewHolderOther> {
    private ArrayList<ServicesClass> duties;
    private ArrayList<Integer> SelectServices;

    public ServiceAdapterOther(ArrayList<ServicesClass> duties, ArrayList<Integer> selectServices) {
        this.duties = duties;
        SelectServices = selectServices;
    }

    @NonNull
    @Override
    public ServicesViewHolderOther onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_for_duty,parent,false);
        return  new ServicesViewHolderOther(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesViewHolderOther holder, int position) {
        holder.bindData(duties.get(position),SelectServices);
    }

    @Override
    public int getItemCount() {
        return duties.size();
    }

}

