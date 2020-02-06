package com.project.avtomoy.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.avtomoy.R;
import com.project.avtomoy.ChatMessagesClass;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {
    private ArrayList<ChatMessagesClass> duties;

    public ChatAdapter(ArrayList<ChatMessagesClass> duties) {
        this.duties = duties;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_chat,parent,false);
        return  new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.bindData(duties.get(position));
    }

    @Override
    public int getItemCount() {
        return duties.size();
    }

}

