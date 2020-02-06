package com.project.avtomoy.ui.history;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.FeedbackAnswerClass;
import com.project.avtomoy.FeedbackClass;
import com.project.avtomoy.HistoryAllClass;
import com.project.avtomoy.LoadHistoryClass;
import com.project.avtomoy.R;
import com.project.avtomoy.ServicesClass;
import com.project.avtomoy.ThreadRequest;
import com.project.avtomoy.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryFragment extends Fragment {
    public static String token;
    static ArrayList<HistoryAllClass>his;
    static RecyclerView addedHistoryView;
    static HistoryParamClass HPC=new HistoryParamClass(new ArrayList<HistoryClass>(),new ArrayList<FeedbackClass>(),new ArrayList<FeedbackAnswerClass>(),null,null);
    public static Context context;
    static ArrayList<HistoryClass> HC=new ArrayList<HistoryClass>();
    static ArrayList<FeedbackClass> FC=new ArrayList<FeedbackClass>();
    static ArrayList<FeedbackAnswerClass> FCA=new ArrayList<FeedbackAnswerClass>();
    static ArrayList<ArrayServicesClass> SC=new ArrayList<ArrayServicesClass>() ;
    static ArrayList<ArrayServicesClass> ASC=new ArrayList<ArrayServicesClass>() ;
    ArrayList<ServicesClass> SC1;
    ArrayList<ServicesClass> ASC1;
    public static RecyclerView.LayoutManager layoutManager;
    public static Integer count=0;
    public static Boolean load=false;
    public static LoadHistoryClass history = null;
    public static int counts=0;
    @RequiresApi(api = Build.VERSION_CODES.M)

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        count=0;
        counts=0;
        try {
            context = this.getContext();
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                token = bundle.getString("token", "false");
            }
            String str_answer;
            try {
                str_answer = new ThreadRequest().execute("history-page", token,"offset="+count.toString()+"&limit=30").get();
                history = deserializeHistoryResult(str_answer);
                his = history.getResponse().getHistory();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
            addedHistoryView = view.findViewById(R.id.addedHistoryView);
            layoutManager= new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            addedHistoryView.setLayoutManager(layoutManager);
            final HistoryAdapter hisAdapter = new HistoryAdapter(his, token);
            addedHistoryView.setAdapter(hisAdapter);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
        addedHistoryView.setOnScrollListener(scrollListener);
        AutoRegActivity.progressBar.setVisibility(View.INVISIBLE);
        return view;
    }

    public static void onScrollEnd(){
        Thread thread = new Thread(null, HistoryFragment.doBackgroundThreadProcessing,
                "Background");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Runnable doBackgroundThreadProcessing = new Runnable() {
        public void run() {
            backgroundThreadProcessing();
        }
    };

    private static void backgroundThreadProcessing() {
        String str_answer;
        count+=30;
        if(his.size()!=history.getResponse().getTotalPages()) {
            try {
                str_answer = new ThreadRequest().execute("history-page", token, "offset=" + count.toString() + "&limit=30").get();
                history = deserializeHistoryResult(str_answer);
                counts = his.size();
                his.addAll(history.getResponse().getHistory());
                if (his.size() != 0) {
                    load = true;
                    addedHistoryView.getAdapter().notifyDataSetChanged();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Log.i("My",String.valueOf(his.size()));
            Log.i("My",String.valueOf(his.size()==history.getResponse().getTotalPages()));
        }
    }

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (load) {
                addedHistoryView.getAdapter().notifyDataSetChanged();
                load = false;
            }
        }
    };

    private static LoadHistoryClass deserializeHistoryResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoadHistoryClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /*RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();//смотрим сколько элементов на экране
            int totalItemCount = layoutManager.getItemCount();//сколько всего элементов
            int firstVisibleItems = layoutManager.;//какая позиция первого элемента

        }
    };*/

    public static void ReturnRecord(View view){
        AutoRegActivity.navigationView.getMenu().getItem(1).setChecked(true);
        Bundle args = new Bundle();
        args.putString("token",token);
        HomeFragment fragmentHome= new HomeFragment();
        AutoRegActivity.carWashId=view.getTag().toString();
        fragmentHome.setArguments(args);
        ((AutoRegActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, fragmentHome)
                .commit();
    }


}