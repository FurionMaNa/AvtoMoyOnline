package com.project.avtomoy.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.LoadChatClass;
import com.project.avtomoy.R;
import com.project.avtomoy.ThreadRequest;

import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChatFragment extends Fragment {

    private String token;
    private LoadChatClass LCC;
    private RecyclerView addedChatView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chat, container, false);
        try {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                token = bundle.getString("token", "false");
            }
            String str_answer;
            try {
                str_answer = new ThreadRequest().execute("chat-inbox-page", token).get();
                LCC = deserializeChatResult(str_answer);

                addedChatView = view.findViewById(R.id.addedChatView);
                RecyclerView.LayoutManager layoutManager =
                        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                addedChatView.setLayoutManager(layoutManager);
                final ChatAdapter hisAdapter = new ChatAdapter(LCC.getResponse().getChatMessages());
                addedChatView.setAdapter(hisAdapter);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
        AutoRegActivity.progressBar.setVisibility(View.INVISIBLE);
        return view;
    }

    private static LoadChatClass deserializeChatResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoadChatClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}