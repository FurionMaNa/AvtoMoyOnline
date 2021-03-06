package com.project.avtomoy.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.avtomoy.R;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.DownloadImageTask;
import com.project.avtomoy.LoadContactsPageClass;
import com.project.avtomoy.LoadResponseClass;
import com.project.avtomoy.ServicesClass;
import com.project.avtomoy.ThreadRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecordFragment extends Fragment {
    private RecyclerView addedComplexView;
    private RecyclerView addedServiceView;
    private ArrayList<ServicesClass> com=new ArrayList<ServicesClass>();
    private ArrayList<ServicesClass> ser=new ArrayList<ServicesClass>();
    private LoadContactsPageClass LCPC;
    LoadResponseClass LRC;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_record, container, false);

        View v =getActivity().getCurrentFocus();
        if(v!=null) {
            v.clearFocus();
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        int time_start = AutoRegActivity.GSRL.getResponse().getTime_start();
        String hours_start = String.valueOf(time_start / 60);
        String minutes_start = String.valueOf(time_start % 60);
        if(minutes_start.length()==1){
            minutes_start="0"+minutes_start;
        }
        if(hours_start.length()==1){
            hours_start="0"+hours_start;
        }
        Integer longWash=Math.abs(AutoRegActivity.GSRL.getResponse().getTime_start()-AutoRegActivity.GSRL.getResponse().getTime_end());
        ((TextView)view.findViewById(R.id.PriceTV)).setText(AutoRegActivity.GSRL.getResponse().getPrice().toString()+" Р");
        ((TextView)view.findViewById(R.id.TimeTV)).setText("~"+longWash.toString()+" минут");
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
        com = AutoRegActivity.GSRL.getResponse().getServices_ids();
        ser = AutoRegActivity.GSRL.getResponse().getAdd_services_ids();
        addedComplexView = view.findViewById(R.id.ComplexAdded);
        if((com!=null)&&(com.size()!=0)) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            addedComplexView.setLayoutManager(layoutManager);
            final DutyAdapterRecord hisAdapter = new DutyAdapterRecord(com);
            addedComplexView.setAdapter(hisAdapter);
        }else{
            addedComplexView.setVisibility(View.GONE);
        }
        addedServiceView = view.findViewById(R.id.ServiceAdded);
        if((ser!=null)&&(ser.size()!=0)) {
            RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            addedServiceView.setLayoutManager(layoutManager2);
            final ServiceAdapterRecord hisAdapter2 = new ServiceAdapterRecord(ser);
            addedServiceView.setAdapter(hisAdapter2);
        }else{
            addedServiceView.setVisibility(View.GONE);
        }
        if(AutoRegActivity.GSRL.getAdvert().size()>=AutoRegActivity.GSRL.getStatus()+1) {
            if (AutoRegActivity.GSRL.getAdvert().get(AutoRegActivity.GSRL.getStatus()) != null) {
                if (AutoRegActivity.GSRL.getAdvert().get(AutoRegActivity.GSRL.getStatus()).getPhoto_path() != null) {
                    ImageView image = new ImageView(getActivity());
                    try {
                        new DownloadImageTask(image).execute(AutoRegActivity.GSRL.getAdvert().get(AutoRegActivity.GSRL.getStatus()).getPhoto_path());
                    } catch (Exception e) {

                    }
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity())
                            .setView(image).setNegativeButton("ОК",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            String s;
                                            try {
                                                s = new ThreadRequest().execute("advert-viewed", AutoRegActivity.token, "advertId=" + AutoRegActivity.GSRL.getAdvert().get(AutoRegActivity.GSRL.getStatus()).getId().toString()).get();
                                            } catch (ExecutionException e) {
                                                e.printStackTrace();
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            dialog.cancel();
                                        }
                                    });
                    dialog.show();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setMessage(AutoRegActivity.GSRL.getAdvert().get(AutoRegActivity.GSRL.getStatus()).getText())
                            .setTitle(AutoRegActivity.GSRL.getAdvert().get(AutoRegActivity.GSRL.getStatus()).getTitle()).setNegativeButton("ОК",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    String s;
                                    try {
                                        s = new ThreadRequest().execute("advert-viewed", AutoRegActivity.token, "advertId=" + AutoRegActivity.GSRL.getAdvert().get(AutoRegActivity.GSRL.getStatus()).getId().toString()).get();
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
        }
        Button btn=view.findViewById(R.id.nextButtonMain);
        if(AutoRegActivity.GSRL.getStatus()==1){
            TextView tv=view.findViewById(R.id.Status);
            tv.setText("Выполняется");
            btn.setText("Завершить");
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AutoRegActivity.GSRL.getStatus()==1) {
                    AlertDialog.Builder dialog2 = new AlertDialog.Builder(getActivity());
                    dialog2.setMessage("Вы уверены?")
                            .setTitle("Завершение мойки").setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Bundle args = new Bundle();
                            RecordEndFragment endRecord = new RecordEndFragment();
                            args.putString("token", AutoRegActivity.token);
                            endRecord.setArguments(args);
                            LoadFragment(endRecord);
                        }
                    }).setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).create().show();
                }else{
                    AlertDialog.Builder dialog2 = new AlertDialog.Builder(getActivity());
                    dialog2.setMessage("Вы уверены?")
                            .setTitle("Отмена мойки").setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            try {
                                String s = new ThreadRequest().execute("cancel-record", AutoRegActivity.token, "recordId=" + AutoRegActivity.GSRL.getResponse().getId().toString()).get();
                                LRC = deserializeResponseResult(s);
                                if (LRC != null) {
                                    if (!LRC.getError()) {
                                        Toast.makeText(getActivity(), "Запись отменена!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getActivity(), "Ошибка! Данные обновлены!", Toast.LENGTH_SHORT).show();
                                    }

                                    if (AutoRegActivity.GSRL.getAdvert().size() != 0) {
                                        if (LRC.getAdvert().get(0) != null) {
                                            if (LRC.getAdvert().get(0).getPhoto_path() != null) {
                                                ImageView image = new ImageView(getActivity());
                                                try {
                                                    new DownloadImageTask(image).execute(LRC.getAdvert().get(0).getPhoto_path());
                                                } catch (Exception e) {

                                                }
                                                AlertDialog.Builder dialog3 = new AlertDialog.Builder(getActivity())
                                                        .setView(image).setNegativeButton("ОК",
                                                                new DialogInterface.OnClickListener() {
                                                                    public void onClick(DialogInterface dialog, int id) {
                                                                        String s;
                                                                        try {
                                                                            s = new ThreadRequest().execute("advert-viewed", AutoRegActivity.token, "advertId=" + LRC.getAdvert().get(0).getId().toString()).get();
                                                                            s += "";
                                                                        } catch (ExecutionException e) {
                                                                            e.printStackTrace();
                                                                        } catch (InterruptedException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        dialog.cancel();
                                                                        Bundle args = new Bundle();
                                                                        HomeFragment homeRecord = new HomeFragment();
                                                                        args.putString("token", AutoRegActivity.token);
                                                                        homeRecord.setArguments(args);
                                                                        LoadFragment(homeRecord);
                                                                    }
                                                                });
                                                dialog3.show();
                                            } else {
                                                AlertDialog.Builder dialog3 = new AlertDialog.Builder(getActivity());
                                                dialog3.setMessage(LRC.getAdvert().get(0).getText())
                                                        .setTitle(LRC.getAdvert().get(0).getTitle()).setNegativeButton("ОК",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {
                                                                try {
                                                                    String s = new ThreadRequest().execute("advert-viewed", AutoRegActivity.token, "advertId=" + LRC.getAdvert().get(0).getId().toString()).get();
                                                                } catch (ExecutionException e) {
                                                                    e.printStackTrace();
                                                                } catch (InterruptedException e) {
                                                                    e.printStackTrace();
                                                                }
                                                                dialog.cancel();
                                                                Bundle args = new Bundle();
                                                                HomeFragment homeRecord = new HomeFragment();
                                                                args.putString("token", AutoRegActivity.token);
                                                                homeRecord.setArguments(args);
                                                                LoadFragment(homeRecord);
                                                            }
                                                        });
                                                dialog3.show();
                                            }
                                        } else {
                                            Bundle args = new Bundle();
                                            HomeFragment homeRecord = new HomeFragment();
                                            args.putString("token", AutoRegActivity.token);
                                            homeRecord.setArguments(args);
                                            LoadFragment(homeRecord);
                                        }
                                    } else {
                                        Bundle args = new Bundle();
                                        HomeFragment homeRecord = new HomeFragment();
                                        args.putString("token", AutoRegActivity.token);
                                        homeRecord.setArguments(args);
                                        LoadFragment(homeRecord);
                                    }
                                }
                            } catch (Exception e) {
                                Toast.makeText(getActivity(), "Проверьте подключение к интернету!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).create().show();
                }
            }
        });
        AutoRegActivity.progressBar.setVisibility(View.INVISIBLE);
        return view;
    }

    private void LoadFragment(Fragment fragment){
        getFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit();
    }

    private static LoadResponseClass deserializeResponseResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoadResponseClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static LoadContactsPageClass deserializeContactsResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoadContactsPageClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
