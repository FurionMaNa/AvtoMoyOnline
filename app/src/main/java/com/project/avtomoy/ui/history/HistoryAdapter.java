package com.project.avtomoy.ui.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.avtomoy.HistoryAllClass;
import com.project.avtomoy.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {
    private ArrayList<HistoryAllClass> history;
    private String token;

    public HistoryAdapter(ArrayList<HistoryAllClass> his,String tok) {
        this.history = his;
        this.token=tok;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_history,parent,false);
        return  new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.bindData(history.get(position));
        if(position==history.size()-15){
            HistoryFragment.onScrollEnd();
        }
    }

    @Override
    public int getItemCount() {
        return history.size();
    }
}
