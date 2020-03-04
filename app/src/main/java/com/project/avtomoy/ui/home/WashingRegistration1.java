package com.project.avtomoy.ui.home;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.LoadAvailableClass;
import com.project.avtomoy.LoadCarClass;
import com.project.avtomoy.LoadServicesClass;
import com.project.avtomoy.PeriodsClass;
import com.project.avtomoy.R;
import com.project.avtomoy.ServicesClass;
import com.project.avtomoy.ThreadRequest;
import com.project.avtomoy.TimeAndPriceClass;
import com.project.avtomoy.ui.AdvetFinishClass;
import com.project.avtomoy.ui.tools.ToolsFragment;
import com.project.avtomoy.ui.tools.fragments.Car.Car;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WashingRegistration1 extends Fragment {

    private DatePicker mDatePicker;
    private Calendar dateAndTime= Calendar.getInstance();
    private TextView currentDateTime;
    private static String token;
    private RecyclerView addedComplexView;
    private RecyclerView addedServiceView;
    public static String final_price="";
    public static String final_time="";
    public static TextView timetv;
    public static TextView pricetv;
    public ConstraintLayout fragment;
    private String load;
    ProgressDialog pd;
    static GridView GLSelect;
    static GridView GLSelectServices;
    static LinearLayout LLSelectComplex;
    static LinearLayout LLSelectServices;
    public static TimeAndPriceClass service_time_price = null;
    public static WashingRegistration2 fragmentWashingReg;
    public static ArrayList<View> viewArrayComplex=new ArrayList<View>();
    public static ArrayList<View> viewArrayServices=new ArrayList<View>();
    public static DutyAdapter hisAdapter;
    public static ServiceAdapter hisAdapter2;
    public static AdvetFinishClass advetFinishClass;
    static ArrayList<Car> carsList = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_washing_registration, container, false);

        GLSelect = view.findViewById(R.id.SelectComplexGV);
        GLSelectServices = view.findViewById(R.id.SelectServicesGV);
        LLSelectComplex=view.findViewById(R.id.SelectComplex);
        LLSelectServices=view.findViewById(R.id.SelectServices);
        GLSelect.setNumColumns(3);
        GLSelect.setColumnWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        GLSelectServices.setNumColumns(3);
        GLSelectServices.setColumnWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        try {
            Spanned textSpan;
            LoadServicesClass service;
            final_time = "";
            final_price = "";
            AutoRegActivity.SelectServices = new ArrayList<>();
            AutoRegActivity.SelectComplex = new ArrayList<>();
            HomeFragment.com = new ArrayList<ServicesClass>();
            HomeFragment.ser = new ArrayList<ServicesClass>();
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                token = bundle.getString("token", "false");
            }

            AutoRegActivity.SelectServices = new ArrayList<Integer>();
            AutoRegActivity.SelectComplex = new ArrayList<Integer>();
            pricetv = view.findViewById(R.id.PriceTV);
            timetv = view.findViewById(R.id.TimeTV);
            fragment = view.findViewById(R.id.fragment_washing1);
            currentDateTime = view.findViewById(R.id.Data);
            String htmlTaggedString = "<u>" + HomeFragment.dd + "." + HomeFragment.m + "." + HomeFragment.y + "</u>";
            TextView DataPicker = view.findViewById(R.id.DataPick);
            HomeFragment.y = String.valueOf(dateAndTime.get(Calendar.YEAR));
            HomeFragment.m = String.valueOf(dateAndTime.get(Calendar.MONTH) + 1);
            HomeFragment.dd = String.valueOf(dateAndTime.get(Calendar.DAY_OF_MONTH));
            if (HomeFragment.dd.length() == 1) {
                HomeFragment.dd = "0" + HomeFragment.dd;
            }
            if (HomeFragment.m.length() == 1) {
                HomeFragment.m = "0" + HomeFragment.m;
            }
            htmlTaggedString = "<u>" + HomeFragment.dd + "." + HomeFragment.m + "." + HomeFragment.y + "</u>";
            textSpan = Html.fromHtml(htmlTaggedString);
            currentDateTime.setText(textSpan);
            pricetv.setText(" " + final_price.toString() + "\u20BD");
            timetv.setText(" ~ " + final_time.toString() + " минут");

            LoadCar();
            if(carsList.size()!=0) {
                String str_answer;
                try {
                    str_answer = new ThreadRequest().execute("get-services2", token, AutoRegActivity.carWashId).get();
                    service = deserializeServiceResult(str_answer);
                    Integer i = 0;
                    while (i < service.getResponse().getServices().size()) {
                        if (service.getResponse().getServices().get(i).getIs_agreed() != 1) {
                            HomeFragment.com.add(service.getResponse().getServices().get(i));
                        }
                        i++;
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    str_answer = new ThreadRequest().execute("get-add-services2", token, AutoRegActivity.carWashId).get();
                    service = deserializeServiceResult(str_answer);
                    Integer i = 0;
                    while (i < service.getResponse().getServices().size()) {
                        if (service.getResponse().getServices().get(i).getIs_agreed() != 1) {
                            HomeFragment.ser.add(service.getResponse().getServices().get(i));
                        }
                        i++;
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < HomeFragment.com.size(); i++) {
                    str_answer = new ThreadRequest().execute("get-time-and-price", HomeFragment.token, HomeFragment.com.get(i).getId().toString()).get();
                    TimeAndPriceClass service2 = deserializeTimeAndPriceResult(str_answer);
                    HomeFragment.com.get(i).setPrice(service2.getResponse().getPrice().getServices().getPrice().toString());
                    HomeFragment.com.get(i).setTime(service2.getResponse().getFormatTime().getApproximateTime());
                }
                for (int i = 0; i < HomeFragment.ser.size(); i++) {
                    str_answer = new ThreadRequest().execute("get-time-and-price", HomeFragment.token, HomeFragment.ser.get(i).getId().toString()).get();
                    TimeAndPriceClass service2 = deserializeTimeAndPriceResult(str_answer);
                    HomeFragment.ser.get(i).setPrice(service2.getResponse().getPrice().getServices().getPrice().toString());
                    HomeFragment.ser.get(i).setTime(service2.getResponse().getFormatTime().getApproximateTime());
                }

            }else{

                Bundle args = new Bundle();
                args.putString("token", token);
                args.putString("nocar", "true");
                ToolsFragment fragmentTools=new ToolsFragment();
                fragmentTools.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragmentTools).commit();

                Toast.makeText(getActivity(),"У вас нет машин! Зарегистрируйте хотя бы одну!",Toast.LENGTH_SHORT).show();
            }
            addedComplexView = view.findViewById(R.id.ComplexAdded);
            RecyclerView.LayoutManager layoutManager =
                    new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            addedComplexView.setLayoutManager(layoutManager);
            hisAdapter = new DutyAdapter(HomeFragment.com, AutoRegActivity.SelectComplex);
            addedComplexView.setAdapter(hisAdapter);
            addedServiceView = view.findViewById(R.id.ServiceAdded);
            RecyclerView.LayoutManager layoutManager2 =
                    new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            addedServiceView.setLayoutManager(layoutManager2);
            hisAdapter2 = new ServiceAdapter(HomeFragment.ser, AutoRegActivity.SelectServices);
            addedServiceView.setAdapter(hisAdapter2);
            view.findViewById(R.id.OtherAvtoMoyTV).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MapsFragment mapsFragment = new MapsFragment();
                    Bundle args = new Bundle();
                    args.putString("token", token);
                    args.putString("Other", "trueK");
                    mapsFragment.setArguments(args);
                    LoadFragment(mapsFragment);
                }
            });
            fragmentWashingReg = new WashingRegistration2();
            ((TextView) view.findViewById(R.id.AllComplex)).setOnClickListener(new View.OnClickListener() {
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
                    DutyAdapterCard adapter = new DutyAdapterCard(HomeFragment.com, AutoRegActivity.SelectComplex);
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
            ((TextView) view.findViewById(R.id.AllService)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(getActivity());
                    LayoutInflater inflater = getLayoutInflater();
                    View convertView = inflater.inflate(R.layout.dialog_recycler_view, null);
                    alertDialog2.setView(convertView);
                    RecyclerView list = convertView.findViewById(R.id.list);
                    list.setLayoutManager(new LinearLayoutManager(alertDialog2.getContext()));
                    list.setHasFixedSize(true);
                    alertDialog2.setTitle("Услуги");
                    ServiceAdapterCard adapter2 = new ServiceAdapterCard(HomeFragment.ser, AutoRegActivity.SelectServices);
                    list.setAdapter(adapter2);
                    alertDialog2.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alertDialog2.show();
                }
            });
            view.findViewById(R.id.nextButtonMain).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str_answer;
                    PeriodsClass periods = null;
                    try {
                        str_answer = new ThreadRequest().execute("get-select-map-periods", token, "date=" + HomeFragment.dd + "." + HomeFragment.m + "." + HomeFragment.y + "&carWashId=" + AutoRegActivity.carWashId).get();
                        periods = deserializePeriodsResult(str_answer);
                        if (periods == null) {
                            Toast.makeText(getActivity(), "Выбери другую дату или другую автомойку", Toast.LENGTH_SHORT).show();
                        } else {
                            if ((AutoRegActivity.SelectServices.size() != 0) || (AutoRegActivity.SelectComplex.size() != 0)) {
                                fragment.setVisibility(View.INVISIBLE);
                                Bundle args = new Bundle();
                                args.putString("token", token);
                                args.putString("date", HomeFragment.dd + "." + HomeFragment.m + "." + HomeFragment.y);
                                args.putInt("duration", service_time_price.getResponse().getFormatTime().getApproximateMinutes());
                                args.putInt("minute", service_time_price.getResponse().getFormatTime().getApproximateMinutes());
                                args.putString("minuteStr", service_time_price.getResponse().getFormatTime().getApproximateTime());
                                args.putString("price", service_time_price.getResponse().getPrice().getServices().getPrice().toString());
                                fragmentWashingReg.setArguments(args);
                                LoadFragment(fragmentWashingReg);
                            } else {
                                Toast.makeText(getActivity(), "Вы должны выбрать хотя бы один комплекс или услугу", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            htmlTaggedString = "<u>" + HomeFragment.dd + "." + HomeFragment.m + "." + HomeFragment.y + "</u>";
            textSpan = Html.fromHtml(htmlTaggedString);
            currentDateTime.setText(textSpan);
            currentDateTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getActivity()), d, dateAndTime.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH), dateAndTime.get(Calendar.DAY_OF_MONTH));
                    dateAndTime = Calendar.getInstance();
                    HomeFragment.y = String.valueOf(dateAndTime.get(Calendar.YEAR));
                    HomeFragment.m = String.valueOf(dateAndTime.get(Calendar.MONTH) + 1);
                    HomeFragment.dd = String.valueOf(dateAndTime.get(Calendar.DAY_OF_MONTH));
                    if (HomeFragment.dd.length() == 1) {
                        HomeFragment.dd = "0" + HomeFragment.dd;
                    }
                    if (HomeFragment.m.length() == 1) {
                        HomeFragment.m = "0" + HomeFragment.m;
                    }
                    String str_answer;
                    LoadAvailableClass LADC = null;
                    try {
                        str_answer = new ThreadRequest().execute("get-available-dates", token, AutoRegActivity.carWashId).get();
                        LADC = deserializeAvailableResult(str_answer);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (LADC != null) {
                        Date dateResult;
                        Calendar instance = Calendar.getInstance();
                        instance.setTime(Date.valueOf(HomeFragment.y + "-" + HomeFragment.m + "-" + HomeFragment.dd));
                        switch (LADC.getResponse().getDates()) {
                            case "0":
                                instance.add(Calendar.DAY_OF_MONTH, 7);
                                break;case "1": instance.add(Calendar.DAY_OF_MONTH, 20);
                                break;case "2": instance.add(Calendar.DAY_OF_MONTH, 31);
                                break;
                        }
                        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String day = formatter.format(instance.getTime());
                        dateResult = Date.valueOf(day);
                        datePickerDialog.getDatePicker().setMaxDate(dateResult.getTime());
                        datePickerDialog.getDatePicker().setMinDate(Date.valueOf(HomeFragment.y + "-" + HomeFragment.m + "-" + HomeFragment.dd).getTime());
                    }
                    datePickerDialog.show();
                }
            });
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String str_answer;
                    try {
                        str_answer = new ThreadRequest().execute("get-mailing", token).get();
                        advetFinishClass=deserializeAdvetrResult(str_answer);
                        if((advetFinishClass!=null)&&(advetFinishClass.getResponse()!=null)) {
                            DialogAdvert dialog = new DialogAdvert(advetFinishClass);
                            dialog.show(getFragmentManager(), "SetCar");
                        }
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e){
            Toast.makeText(getActivity(),"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
        AutoRegActivity.progressBar.setVisibility(View.INVISIBLE);
        return view;
    }

    private static LoadCarClass deserializeCarResult(String JSonString) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(JSonString, LoadCarClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void LoadCar(){
        try {
            LoadCarClass carsLoad = null;
            String str_answer;
            try {
                str_answer = new ThreadRequest().execute("my-cars-page", token).get();
                carsLoad = deserializeCarResult(str_answer);

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            carsList = carsLoad.getResponse().getCars();
        }catch (Exception e){
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
    }


    private static AdvetFinishClass deserializeAdvetrResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,AdvetFinishClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static PeriodsClass deserializePeriodsResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,PeriodsClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void LoadFragment(Fragment fragment){
        getFragmentManager().beginTransaction()
                .add(R.id.nav_host_fragment, fragment,"FragmentWash2")
                .show(fragment)
                .hide(getFragmentManager().findFragmentByTag("fragmentWash1"))
                .addToBackStack(null)
                .commit();
    }

    private void setInitialDateTime() {
        if(HomeFragment.dd.length()==1){HomeFragment.dd="0"+HomeFragment.dd;}
        if(HomeFragment.m.length()==1){HomeFragment.m="0"+HomeFragment.m;}
        String htmlTaggedString  = "<u>"+HomeFragment.dd+"."+HomeFragment.m+"."+HomeFragment.y+"</u>";
        Spanned textSpan  =  android.text.Html.fromHtml(htmlTaggedString);
        currentDateTime.setText(textSpan);
    }

    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            HomeFragment.dd=String.valueOf(dayOfMonth);
            HomeFragment.y=String.valueOf(year);
            HomeFragment.m=String.valueOf(monthOfYear+1);
            setInitialDateTime();
        }
    };

    private LoadServicesClass deserializeServiceResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoadServicesClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private LoadAvailableClass deserializeAvailableResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoadAvailableClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void onSelectComplex(View v, Boolean f){
        try {
            final_price = "";
            ComplexSelect SC = (ComplexSelect) v.getTag();
            if (SC.getState().equals("true")) {
                for (int i = 0; i < AutoRegActivity.SelectComplex.size(); i++) {
                    if (AutoRegActivity.SelectComplex.get(i) == SC.getServicesClass().getId()) {
                        AutoRegActivity.SelectComplex.remove(i);
                        viewArrayComplex.remove(i);
                        break;
                    }
                }
                if(f) {
                    v.setBackgroundResource(R.drawable.duti_drawable_unfocus);
                }else{
                    v.setBackgroundResource(R.drawable.duty_card_drawable_unfocus);
                    ((TextView)v.findViewById(R.id.name)).setTextColor(ContextCompat.getColor(AutoRegActivity.context, R.color.colorPrimary));
                    ((TextView)v.findViewById(R.id.price)).setTextColor(ContextCompat.getColor(AutoRegActivity.context, R.color.colorPrimary));
                    ((TextView)v.findViewById(R.id.time)).setTextColor(ContextCompat.getColor(AutoRegActivity.context, R.color.colorPrimary));
                    hisAdapter.notifyDataSetChanged();
                }
                SC.setState("false");
            } else {
                AutoRegActivity.SelectComplex.add(SC.getServicesClass().getId());
                if(f) {
                    v.setBackgroundResource(R.drawable.duti_drawable_focus);
                }else{
                    v.setBackgroundResource(R.drawable.duty_card_drawable_focus);
                    ((TextView)v.findViewById(R.id.name)).setTextColor(Color.WHITE);
                    ((TextView)v.findViewById(R.id.price)).setTextColor(Color.WHITE);
                    ((TextView)v.findViewById(R.id.time)).setTextColor(Color.WHITE);
                    hisAdapter.notifyDataSetChanged();
                }
                viewArrayComplex.add(v);
                SC.setState("true");
            }
            for (int i = 0; i < AutoRegActivity.SelectComplex.size(); i++) {
                if (i == AutoRegActivity.SelectComplex.size() - 1) {
                    final_price += AutoRegActivity.SelectComplex.get(i).toString();
                } else {
                    final_price += AutoRegActivity.SelectComplex.get(i).toString() + ",";
                }
            }
            if (AutoRegActivity.SelectServices.size() != 0) {
                final_price += ",";
                for (int i = 0; i < AutoRegActivity.SelectServices.size(); i++) {
                    if (i == AutoRegActivity.SelectServices.size() - 1) {
                        final_price += AutoRegActivity.SelectServices.get(i).toString();
                    } else {
                        final_price += AutoRegActivity.SelectServices.get(i).toString() + ",";
                    }
                }
            }
            String str_answer;
            TimeAndPriceClass tp;
            service_time_price = null;
            if (!final_price.equals("")) {
                try {
                    str_answer = new ThreadRequest().execute("get-time-and-price", HomeFragment.token, final_price).get();
                    service_time_price = deserializeTimeAndPriceResult(str_answer);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!final_price.equals("")) {
                pricetv.setText(" " + service_time_price.getResponse().getPrice().getServices().getPrice().toString() + "\u20BD");
                timetv.setText(" ~ " + service_time_price.getResponse().getFormatTime().getApproximateTime());
            } else {
                pricetv.setText(" " + "\u20BD");
                timetv.setText(" ~ минут");
            }
            v.setTag(SC);
            if(AutoRegActivity.SelectComplex.size()>0){
                LLSelectComplex.setVisibility(View.VISIBLE);
            }else{
                LLSelectComplex.setVisibility(View.GONE);
            }
            GLSelect.setAdapter(new SelectAdapter(AutoRegActivity.context, AutoRegActivity.SelectComplex,viewArrayComplex));
        } catch (Exception e){
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
    }

    public static void onSelectServices(View v, Boolean f){
        try {
            final_price = "";
            ComplexSelect SC = (ComplexSelect) v.getTag();
            if (SC.getState().equals("true")) {
                for (int i = 0; i < AutoRegActivity.SelectServices.size(); i++) {
                    if (AutoRegActivity.SelectServices.get(i) == SC.getServicesClass().getId()) {
                        AutoRegActivity.SelectServices.remove(i);
                        viewArrayServices.remove(i);
                        break;
                    }
                }
                if(f) {
                    v.setBackgroundResource(R.drawable.duti_drawable_unfocus);
                }else{
                    v.setBackgroundResource(R.drawable.duty_card_drawable_unfocus);
                    ((TextView)v.findViewById(R.id.name)).setTextColor(ContextCompat.getColor(AutoRegActivity.context, R.color.colorPrimary));
                    ((TextView)v.findViewById(R.id.price)).setTextColor(ContextCompat.getColor(AutoRegActivity.context, R.color.colorPrimary));
                    ((TextView)v.findViewById(R.id.time)).setTextColor(ContextCompat.getColor(AutoRegActivity.context, R.color.colorPrimary));
                    hisAdapter2.notifyDataSetChanged();
                }
                SC.setState("false");
            } else {
                AutoRegActivity.SelectServices.add(SC.getServicesClass().getId());
                if(f) {
                    v.setBackgroundResource(R.drawable.duti_drawable_focus);
                }else{
                    v.setBackgroundResource(R.drawable.duty_card_drawable_focus);
                    ((TextView)v.findViewById(R.id.name)).setTextColor(Color.WHITE);
                    ((TextView)v.findViewById(R.id.price)).setTextColor(Color.WHITE);
                    ((TextView)v.findViewById(R.id.time)).setTextColor(Color.WHITE);
                    hisAdapter2.notifyDataSetChanged();
                }
                viewArrayServices.add(v);
                SC.setState("true");
            }

            for (int i = 0; i < AutoRegActivity.SelectComplex.size(); i++) {
                if (i == AutoRegActivity.SelectComplex.size() - 1) {
                    final_price += AutoRegActivity.SelectComplex.get(i).toString();
                } else {
                    final_price += AutoRegActivity.SelectComplex.get(i).toString() + ",";
                }
            }
            if (AutoRegActivity.SelectServices.size() != 0) {
                final_price += ",";
                for (int i = 0; i < AutoRegActivity.SelectServices.size(); i++) {
                    if (i == AutoRegActivity.SelectServices.size() - 1) {
                        final_price += AutoRegActivity.SelectServices.get(i).toString();
                    } else {
                        final_price += AutoRegActivity.SelectServices.get(i).toString() + ",";
                    }
                }
            }
            String str_answer;
            service_time_price = null;
            TimeAndPriceClass tp;
            if (!final_price.equals("")) {
                try {
                    str_answer = new ThreadRequest().execute("get-time-and-price", HomeFragment.token, final_price).get();
                    service_time_price = deserializeTimeAndPriceResult(str_answer);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!final_price.equals("")) {
                pricetv.setText(" " + service_time_price.getResponse().getPrice().getServices().getPrice().toString() + "\u20BD");
                timetv.setText(" ~ " + service_time_price.getResponse().getFormatTime().getApproximateTime());
            } else {
                pricetv.setText(" " + "\u20BD");
                timetv.setText(" ~ минут");
            }
            v.setTag(SC);
            if(AutoRegActivity.SelectServices.size()>0){
                LLSelectServices.setVisibility(View.VISIBLE);
            }else{
                LLSelectServices.setVisibility(View.GONE);
            }
            GLSelectServices.setAdapter(new SelectAdapterServices(AutoRegActivity.context, AutoRegActivity.SelectServices,viewArrayServices));
        }catch (Exception e){
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
    }

    public static void onRemove(View v,ArrayList<View> vDuty) {
        for (int i = 0; i < AutoRegActivity.SelectComplex.size(); i++) {
            if (AutoRegActivity.SelectComplex.get(i) == v.getTag()) {
                vDuty.get(i).setBackgroundResource(R.drawable.duti_drawable_unfocus);
                AutoRegActivity.SelectComplex.remove(i);
                ComplexSelect SC = (ComplexSelect) vDuty.get(i).getTag();
                SC.setState("false");
                vDuty.get(i).setTag(SC);
                vDuty.remove(i);
                hisAdapter.notifyDataSetChanged();

                break;
            }
        }
        if(AutoRegActivity.SelectComplex.size()>0){
            LLSelectComplex.setVisibility(View.VISIBLE);
        }else{
            LLSelectComplex.setVisibility(View.GONE);
        }
        final_price="";
        for (int i = 0; i < AutoRegActivity.SelectComplex.size(); i++) {
            if (i == AutoRegActivity.SelectComplex.size() - 1) {
                final_price += AutoRegActivity.SelectComplex.get(i).toString();
            } else {
                final_price += AutoRegActivity.SelectComplex.get(i).toString() + ",";
            }
        }
        if (AutoRegActivity.SelectServices.size() != 0) {
            final_price += ",";
            for (int i = 0; i < AutoRegActivity.SelectServices.size(); i++) {
                if (i == AutoRegActivity.SelectServices.size() - 1) {
                    final_price += AutoRegActivity.SelectServices.get(i).toString();
                } else {
                    final_price += AutoRegActivity.SelectServices.get(i).toString() + ",";
                }
            }
        }
        String str_answer;
        TimeAndPriceClass tp;
        service_time_price = null;
        if (!final_price.equals("")) {
            try {
                str_answer = new ThreadRequest().execute("get-time-and-price", HomeFragment.token, final_price).get();
                service_time_price = deserializeTimeAndPriceResult(str_answer);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!final_price.equals("")) {
            pricetv.setText(" " + service_time_price.getResponse().getPrice().getServices().getPrice().toString() + "\u20BD");
            timetv.setText(" ~ " + service_time_price.getResponse().getFormatTime().getApproximateTime());
        } else {
            pricetv.setText(" " + "\u20BD");
            timetv.setText(" ~ минут");
        }
        GLSelect.setAdapter(new SelectAdapter(AutoRegActivity.context, AutoRegActivity.SelectComplex,vDuty));
    }

    public static void onRemoveServices(View v,ArrayList<View> vDuty) {
        for (int i = 0; i < AutoRegActivity.SelectServices.size(); i++) {
            if (AutoRegActivity.SelectServices.get(i) == v.getTag()) {
                vDuty.get(i).setBackgroundResource(R.drawable.duti_drawable_unfocus);
                AutoRegActivity.SelectServices.remove(i);
                ComplexSelect SC = (ComplexSelect) vDuty.get(i).getTag();
                SC.setState("false");
                vDuty.get(i).setTag(SC);
                vDuty.remove(i);
                hisAdapter2.notifyDataSetChanged();
                break;
            }
        }
        if(AutoRegActivity.SelectServices.size()>0){
            LLSelectServices.setVisibility(View.VISIBLE);
        }else{
            LLSelectServices.setVisibility(View.GONE);
        }
        final_price="";
        for (int i = 0; i < AutoRegActivity.SelectComplex.size(); i++) {
            if (i == AutoRegActivity.SelectComplex.size() - 1) {
                final_price += AutoRegActivity.SelectComplex.get(i).toString();
            } else {
                final_price += AutoRegActivity.SelectComplex.get(i).toString() + ",";
            }
        }
        if (AutoRegActivity.SelectServices.size() != 0) {
            final_price += ",";
            for (int i = 0; i < AutoRegActivity.SelectServices.size(); i++) {
                if (i == AutoRegActivity.SelectServices.size() - 1) {
                    final_price += AutoRegActivity.SelectServices.get(i).toString();
                } else {
                    final_price += AutoRegActivity.SelectServices.get(i).toString() + ",";
                }
            }
        }
        String str_answer;
        TimeAndPriceClass tp;
        service_time_price = null;
        if (!final_price.equals("")) {
            try {
                str_answer = new ThreadRequest().execute("get-time-and-price", HomeFragment.token, final_price).get();
                service_time_price = deserializeTimeAndPriceResult(str_answer);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!final_price.equals("")) {
            pricetv.setText(" " + service_time_price.getResponse().getPrice().getServices().getPrice().toString() + "\u20BD");
            timetv.setText(" ~ " + service_time_price.getResponse().getFormatTime().getApproximateTime());
        } else {
            pricetv.setText(" " + "\u20BD");
            timetv.setText(" ~ минут");
        }
        GLSelectServices.setAdapter(new SelectAdapterServices(AutoRegActivity.context, AutoRegActivity.SelectServices,vDuty));
    }

    private static TimeAndPriceClass deserializeTimeAndPriceResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,TimeAndPriceClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}