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

public class ServiceAdapterRecord extends RecyclerView.Adapter<ServicesViewHolderRecord> {
    private ArrayList<ServicesClass> duties;

    public ServiceAdapterRecord(ArrayList<ServicesClass> duties) {
        this.duties = duties;
    }

    @NonNull
    @Override
    public ServicesViewHolderRecord onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_for_duty,parent,false);
        return  new ServicesViewHolderRecord(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesViewHolderRecord holder, int position) {
        holder.bindData(this.duties.get(position));
        Log.i("MyLog", String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return duties.size();
    }

}

