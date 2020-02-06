package com.project.avtomoy.ui.info.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.project.avtomoy.PricesClass;
import com.project.avtomoy.R;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.LoadServicesClass;
import com.project.avtomoy.ServicesClass;
import com.project.avtomoy.ServicesOrderClass;
import com.project.avtomoy.ui.info.fragments.Duty.DutyAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InfoDutyFragment extends Fragment {
    ArrayList <ServicesOrderClass> complexes = new ArrayList<>(), duties = new ArrayList<>(), dutiesAdvanced = new ArrayList<>();

    String token;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_duty, container, false);
        ((TextView)view.findViewById(R.id.nameAvtomoy)).setText(AutoRegActivity.loadAboutCarClass.getResponse().getName());
        try {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                token = bundle.getString("token", "false");
            }
            Integer i = 0;
            while (i < AutoRegActivity.loadAboutCarClass.getResponse().getServices().size()) {
                if (AutoRegActivity.loadAboutCarClass.getResponse().getServices().get(i).getService().getIs_agreed() != 1) {
                    ServicesClass s=AutoRegActivity.loadAboutCarClass.getResponse().getServices().get(i).getService();
                    PricesClass p=AutoRegActivity.loadAboutCarClass.getResponse().getServices().get(i).getPrice();
                    ServicesOrderClass so=new ServicesOrderClass(s,p);
                    complexes.add(so);
                }
                i++;
            }
            i = 0;
            while (i < AutoRegActivity.loadAboutCarClass.getResponse().getAdd_services().size()) {
                if (AutoRegActivity.loadAboutCarClass.getResponse().getAdd_services().get(i).getService().getIs_agreed() != 1) {
                    ServicesClass s=AutoRegActivity.loadAboutCarClass.getResponse().getAdd_services().get(i).getService();
                    PricesClass p=AutoRegActivity.loadAboutCarClass.getResponse().getAdd_services().get(i).getPrice();
                    ServicesOrderClass so=new ServicesOrderClass(s,p);
                    duties.add(so);
                }
                i++;
            }
            i = 0;
            while (i < AutoRegActivity.loadAboutCarClass.getResponse().getAdd_services().size()) {
                if (AutoRegActivity.loadAboutCarClass.getResponse().getAdd_services().get(i).getService().getIs_agreed() == 1) {
                    ServicesClass s=AutoRegActivity.loadAboutCarClass.getResponse().getAdd_services().get(i).getService();
                    PricesClass p=AutoRegActivity.loadAboutCarClass.getResponse().getAdd_services().get(i).getPrice();
                    ServicesOrderClass so=new ServicesOrderClass(s,p);
                    dutiesAdvanced.add(so);
                }
                i++;
            }
            i = 0;
            while (i < AutoRegActivity.loadAboutCarClass.getResponse().getServices().size()) {
                if (AutoRegActivity.loadAboutCarClass.getResponse().getServices().get(i).getService().getIs_agreed() == 1) {
                    ServicesClass s=AutoRegActivity.loadAboutCarClass.getResponse().getServices().get(i).getService();
                    PricesClass p=AutoRegActivity.loadAboutCarClass.getResponse().getServices().get(i).getPrice();
                    ServicesOrderClass so=new ServicesOrderClass(s,p);
                    dutiesAdvanced.add(so);
                }
                i++;
            }
            ((TextView)view.findViewById(R.id.textView15)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    LayoutInflater inflater = getLayoutInflater();
                    View convertView = inflater.inflate(R.layout.dialog_recycler_view, null);
                    alertDialog.setView(convertView);
                    RecyclerView list = convertView.findViewById(R.id.list);
                    list.setLayoutManager(new LinearLayoutManager(alertDialog.getContext()));
                    list.setHasFixedSize(true);
                    alertDialog.setTitle("Комплексы");
                    DutyAdapterCardInfo adapter = new DutyAdapterCardInfo(complexes);
                    list.setAdapter(adapter);
                    alertDialog.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alertDialog.show();
                }
            });
            ((TextView)view.findViewById(R.id.textView17)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    LayoutInflater inflater = getLayoutInflater();
                    View convertView = inflater.inflate(R.layout.dialog_recycler_view, null);
                    alertDialog.setView(convertView);
                    RecyclerView list = convertView.findViewById(R.id.list);
                    list.setLayoutManager(new LinearLayoutManager(alertDialog.getContext()));
                    list.setHasFixedSize(true);
                    alertDialog.setTitle("Комплексы");
                    DutyAdapterCardInfo adapter = new DutyAdapterCardInfo(duties);
                    list.setAdapter(adapter);
                    alertDialog.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alertDialog.show();

                }
            });
            ((TextView)view.findViewById(R.id.textView19)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    LayoutInflater inflater = getLayoutInflater();
                    View convertView = inflater.inflate(R.layout.dialog_recycler_view, null);
                    alertDialog.setView(convertView);
                    RecyclerView list = convertView.findViewById(R.id.list);
                    list.setLayoutManager(new LinearLayoutManager(alertDialog.getContext()));
                    list.setHasFixedSize(true);
                    alertDialog.setTitle("Комплексы");
                    DutyAdapterCardInfo adapter = new DutyAdapterCardInfo(dutiesAdvanced);
                    list.setAdapter(adapter);
                    alertDialog.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alertDialog.show();

                }
            });
            RecyclerView complexRV = view.findViewById(R.id.complex_RecyclerView);
            RecyclerView DutyRV = view.findViewById(R.id.duty_RecyclerView);
            RecyclerView DutyAdvancedRV = view.findViewById(R.id.dutyAdvanced_RecyclerView);
            RecyclerView.LayoutManager layoutManager1 =
                    new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            RecyclerView.LayoutManager layoutManager2 =
                    new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            RecyclerView.LayoutManager layoutManager3 =
                    new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

            complexRV.setLayoutManager(layoutManager1);
            DutyRV.setLayoutManager(layoutManager2);
            DutyAdvancedRV.setLayoutManager(layoutManager3);

            DutyAdapter complexesDutyAdapter = new DutyAdapter(complexes);
            DutyAdapter dutiesDutyAdapter = new DutyAdapter(duties);
            DutyAdapter dutiesAdvancedDutyAdapter = new DutyAdapter(dutiesAdvanced);

            complexRV.setAdapter(complexesDutyAdapter);
            DutyRV.setAdapter(dutiesDutyAdapter);
            DutyAdvancedRV.setAdapter(dutiesAdvancedDutyAdapter);
        }catch (Exception e){
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private LoadServicesClass deserializeServiceResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoadServicesClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}