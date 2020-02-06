package com.project.avtomoy.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.avtomoy.R;
import com.project.avtomoy.ServicesClass;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ServiceAdapter extends RecyclerView.Adapter<ServicesViewHolder> {
    private ArrayList<ServicesClass> duties;
    private ArrayList<Integer> SelectServices;

    public ServiceAdapter(ArrayList<ServicesClass> duties, ArrayList<Integer> selectServices) {
        this.duties = duties;
        SelectServices = selectServices;
    }

    @NonNull
    @Override
    public ServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_for_duty,parent,false);
        return  new ServicesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesViewHolder holder, int position) {
        holder.bindData(this.duties.get(position),SelectServices);
        Log.i("MyLog", String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return duties.size();
    }

}

