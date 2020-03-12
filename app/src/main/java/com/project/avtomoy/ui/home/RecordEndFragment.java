package com.project.avtomoy.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.avtomoy.R;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.DownloadImageTask;
import com.project.avtomoy.LoadCompleteClass;
import com.project.avtomoy.ThreadRequest;
import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class RecordEndFragment  extends Fragment {
    public byte wash=1;
    public String message;
    private EditText mes;
    LoadCompleteClass LRC;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_end, container, false);
        int time_start = AutoRegActivity.GSRL.getResponse().getTime_start();
        String hours_start = String.valueOf(time_start / 60);
        String minutes_start = String.valueOf(time_start % 60);
        if(minutes_start.length()==1){
            minutes_start="0"+minutes_start;
        }
        if(hours_start.length()==1){
            hours_start="0"+hours_start;
        }
        TextView time_date=view.findViewById(R.id.time);
        TextView carnum=view.findViewById(R.id.car);
        carnum.setText(AutoRegActivity.GSRL.getResponse().getCar_number());
        TextView carReg=view.findViewById(R.id.carReg);
        carReg.setText(AutoRegActivity.GSRL.getResponse().getCar_region().toString());
        time_date.setText(AutoRegActivity.GSRL.getResponse().getDate()+", "+hours_start+":"+minutes_start);
        TextView head=view.findViewById(R.id.tv1);
        TextView addres=view.findViewById(R.id.address);
        head.setText(AutoRegActivity.GSRL.getName());
        addres.setText(AutoRegActivity.GSRL.getAddress());
        final Button btnYes=view.findViewById(R.id.yesWash);
        final Button btnNot=view.findViewById(R.id.noWash);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundResource(R.drawable.rounded_button);
                btnNot.setBackgroundResource(R.drawable.rounded_white_button);
                btnYes.setTextColor(Color.WHITE);
                btnNot.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                wash=1;
            }
        });
        btnNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundResource(R.drawable.rounded_button);
                btnYes.setBackgroundResource(R.drawable.rounded_white_button);
                btnNot.setTextColor(Color.WHITE);
                btnYes.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                wash=0;
            }
        });
        mes=view.findViewById(R.id.editTextSendChat);
        ((Button)view.findViewById(R.id.EndRec)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(getActivity());
                dialog2.setMessage("Вы уверены?")
                        .setTitle("Завершение мойки").setPositiveButton("Да",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    message=mes.getText().toString();
                                    if(message==null){
                                        message="";
                                    }
                                    String s = new ThreadRequest().execute("complete-record", AutoRegActivity.token, "recordId=" + AutoRegActivity.GSRL.getResponse().getId().toString()+"&message="+message+"&success="+wash).get();
                                    LRC = deserializeCompleteResult(s);
                                    if(LRC!=null) {
                                        if (!LRC.getError()) {
                                            Toast.makeText(getActivity(), "Удачного пути!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getActivity(), "Ошибка! Данные обновлены!", Toast.LENGTH_SHORT).show();
                                        }
                                        if(LRC.getResponse().getAdvert()!=null) {
                                            if (LRC.getResponse().getAdvert().getPhoto_path() != null) {
                                                ImageView image2 = new ImageView(getActivity());
                                                try {
                                                    new DownloadImageTask(image2).execute(LRC.getResponse().getAdvert().getPhoto_path());
                                                } catch (Exception e) {
                                                    Toast.makeText(getActivity(),"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
                                                }
                                                AlertDialog.Builder dialog3 = new AlertDialog.Builder(getActivity())
                                                        .setView(image2).setNegativeButton("ОК",
                                                                new DialogInterface.OnClickListener() {
                                                                    public void onClick(DialogInterface dialog, int id) {

                                                                        String s;
                                                                        try {
                                                                            s=new ThreadRequest().execute("advert-viewed", AutoRegActivity.token,"advertId="+LRC.getResponse().getAdvert().getId().toString()).get();
                                                                        } catch (ExecutionException e) {
                                                                            e.printStackTrace();
                                                                        } catch (InterruptedException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        Bundle args = new Bundle();
                                                                        HomeFragment homeRecord = new HomeFragment();
                                                                        args.putString("token", AutoRegActivity.token);
                                                                        homeRecord.setArguments(args);
                                                                        LoadFragment(homeRecord);
                                                                        dialog.cancel();
                                                                    }
                                                                });
                                                dialog3.show();
                                            }else{
                                                AlertDialog.Builder dialog3 = new AlertDialog.Builder(getActivity());
                                                dialog3.setMessage(LRC.getResponse().getAdvert().getText())
                                                        .setTitle(LRC.getResponse().getAdvert().getTitle()).setNegativeButton("ОК",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {
                                                                String s;
                                                                try {
                                                                    s=new ThreadRequest().execute("advert-viewed", AutoRegActivity.token,"advertId="+LRC.getResponse().getAdvert().getId().toString()).get();
                                                                } catch (ExecutionException e) {
                                                                    e.printStackTrace();
                                                                } catch (InterruptedException e) {
                                                                    e.printStackTrace();
                                                                }
                                                                Bundle args = new Bundle();
                                                                HomeFragment homeRecord = new HomeFragment();
                                                                args.putString("token", AutoRegActivity.token);
                                                                homeRecord.setArguments(args);
                                                                LoadFragment(homeRecord);
                                                                dialog.cancel();
                                                            }
                                                        });
                                                dialog3.show();
                                            }
                                        }else{
                                            Bundle args = new Bundle();
                                            HomeFragment homeRecord = new HomeFragment();
                                            args.putString("token", AutoRegActivity.token);
                                            homeRecord.setArguments(args);
                                            LoadFragment(homeRecord);
                                            dialog.cancel();
                                        }
                                    }else{
                                        Toast.makeText(getActivity(),"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    Toast.makeText(getActivity(),"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
                                }
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                dialog2.create().show();
            }
        });
        if((AutoRegActivity.GSRL.getAdvert().size()>=3)&&(AutoRegActivity.GSRL.getAdvert().get(2)!=null)) {
            if (AutoRegActivity.GSRL.getAdvert().get(2).getPhoto_path() != null) {
                ImageView image2 = new ImageView(getActivity());
                try {
                    new DownloadImageTask(image2).execute("https://www.avtomoy.online"+AutoRegActivity.GSRL.getAdvert().get(2).getPhoto_path());
                } catch (Exception e) {
                    Toast.makeText(getActivity(),"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity())
                        .setView(image2).setNegativeButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        String s;
                                        try {
                                            s=new ThreadRequest().execute("advert-viewed", AutoRegActivity.token,"advertId="+AutoRegActivity.GSRL.getAdvert().get(2).getId().toString()).get();
                                        } catch (ExecutionException e) {
                                            e.printStackTrace();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        dialog.cancel();
                                    }
                                });
                dialog.show();
            }else{
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setMessage(AutoRegActivity.GSRL.getAdvert().get(2).getText())
                        .setTitle(AutoRegActivity.GSRL.getAdvert().get(2).getTitle()).setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String s;
                                try {
                                    s=new ThreadRequest().execute("advert-viewed", AutoRegActivity.token,"advertId="+AutoRegActivity.GSRL.getAdvert().get(2).getId().toString()).get();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                dialog.cancel();
                            }
                        });
                dialog.show();
            }
        }
        AutoRegActivity.progressBar.setVisibility(View.INVISIBLE);
        return view;
    }

    private void LoadFragment(Fragment fragment){
        getFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit();
    }

    private static LoadCompleteClass deserializeCompleteResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoadCompleteClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
