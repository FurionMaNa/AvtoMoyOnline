package com.project.avtomoy.ui.info.fragments.Sales;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.avtomoy.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SalesAdapter extends RecyclerView.Adapter<SalesViewHolder> {
    private ArrayList<Sale> sales;

    public SalesAdapter(ArrayList<Sale> sales) {
        this.sales = sales;
    }

    @NonNull
    @Override
    public SalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_for_sales,parent,false);
        return  new SalesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesViewHolder holder, int position) {
        holder.bindData(sales.get(position));
    }

    @Override
    public int getItemCount() {
        return sales.size();
    }
}

