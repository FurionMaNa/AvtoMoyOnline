package com.project.avtomoy.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.project.avtomoy.R;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.LoadCarWashesClass;
import com.project.avtomoy.PeriodsClass;
import com.project.avtomoy.ThreadRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private String token;
    LinearLayout LLSetting;
    GoogleMap map;
    SupportMapFragment mapFragment;
    private PeriodsClass periods;
    private ArrayList<String> per;
    private ArrayList<String>start;
    private Integer intervalFilter=0;
    private Integer durationFilter=0;
    private static LoadCarWashesClass LCWC;
    ArrayList<Float> duration=new ArrayList<Float>();
    ArrayList<String> durationStr=new ArrayList<String>();
    private String other;

    private String date;
    private Integer durations;
    private Integer minute;
    private String minuteStr;
    private String price;
    private AlertDialog.Builder ad;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_maps, container, false);
        duration.add((float) 30);
        duration.add((float) 1);
        duration.add((float) 1.5);
        duration.add((float) 2);
        duration.add((float) 2.5);
        duration.add((float) 3);
        duration.add((float) 3.5);
        duration.add((float) 4);
        duration.add((float) 4.5);
        duration.add((float) 5);
        durationStr.add("30 минут");
        durationStr.add("1 час");
        durationStr.add("1.5 часа");
        durationStr.add("2 часа");
        durationStr.add("2.5 часа");
        durationStr.add("3 часа");
        durationStr.add("3.5 часа");
        durationStr.add("4 часа");
        durationStr.add("4.5 часа");
        durationStr.add("5 часов");
        try {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                token = bundle.getString("token", "false");
                other = bundle.getString("Other", "");
                date = bundle.getString("date", "false");
                durations = bundle.getInt("duration", -1);
                minute = bundle.getInt("minute", -1);
                minuteStr = bundle.getString("minuteStr", "false");
                price = bundle.getString("price", "false");
            }
            LLSetting = view.findViewById(R.id.LLSetting);
            view.findViewById(R.id.filter).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (LLSetting.getVisibility() == View.VISIBLE) {
                        v.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255, 102, 52)));
                        LLSetting.setVisibility(View.INVISIBLE);
                    } else {
                        v.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(143, 143, 143)));
                        LLSetting.setVisibility(View.VISIBLE);
                    }
                }
            });
            final SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);
            String str_answer;
            try {
                str_answer = new ThreadRequest().execute("get-select-map-periods", token, "date=" + date + "&carWashId=" + AutoRegActivity.carWashId).get();
                periods = deserializePeriodsResult(str_answer);
                start = periods.getResponse().getDates();
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, start);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner spinner2 = (Spinner) view.findViewById(R.id.IntervalStart);
                spinner2.setAdapter(adapter2);
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        intervalFilter = position;
                        String str;
                        try {
                            str = new ThreadRequest().execute("get-car-washes-by-filter", token, "approximateTime=" + duration.get(durationFilter) + "&timeInterval=" + start.get(position)).get();
                            LCWC = deserializeFilterResult(str);
                            Log.i("MyLog", "get-car-washes-by-filter");
                            mapFragment.getMapAsync(MapsFragment.this);
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, durationStr);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner spinner = (Spinner) view.findViewById(R.id.TimeRecord);
            spinner.setAdapter(adapter1);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    durationFilter = position;
                    String str;
                    try {
                        str = new ThreadRequest().execute("get-car-washes-by-filter", token, "approximateTime=" + duration.get(position) + "&timeInterval=" + start.get(intervalFilter)).get();
                        LCWC = deserializeFilterResult(str);
                        Log.i("MyLog", "get-car-washes-by-filter");
                        mapFragment.getMapAsync(MapsFragment.this);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            view.findViewById(R.id.mapBack).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (other.equals("true")) {
                        WashingRegistration2Other washFragment = new WashingRegistration2Other();
                        Bundle args = new Bundle();
                        args.putString("token", token);
                        args.putString("date", HomeFragment.dd + "." + HomeFragment.m + "." + HomeFragment.y);
                        args.putInt("duration", durations);
                        args.putInt("minute", minute);
                        args.putString("minuteStr", minuteStr);
                        args.putString("price", price);
                        washFragment.setArguments(args);
                        LoadFragment(washFragment,1);
                    } else {
                        if (other.equals("trueT")) {
                            WashingRegistration1Other washFragment = new WashingRegistration1Other();
                            Bundle args = new Bundle();
                            args.putString("token", token);
                            washFragment.setArguments(args);
                            LoadFragment(washFragment,2);
                        } else {
                            if (other.equals("trueE")) {
                                WashingRegistration2Other washFragment = new WashingRegistration2Other();
                                Bundle args = new Bundle();
                                args.putString("token", token);
                                args.putString("date", HomeFragment.dd + "." + HomeFragment.m + "." + HomeFragment.y);
                                args.putInt("duration", durations);
                                args.putInt("minute", minute);
                                args.putString("minuteStr", minuteStr);
                                args.putString("price", price);
                                washFragment.setArguments(args);
                                LoadFragment(washFragment,1);
                            } else {
                                if (other.equals("trueK")) {
                                    WashingRegistration1 washFragment = new WashingRegistration1();
                                    Bundle args = new Bundle();
                                    args.putString("token", token);
                                    args.putString("date", HomeFragment.dd + "." + HomeFragment.m + "." + HomeFragment.y);
                                    args.putInt("duration", durations);
                                    args.putInt("minute", minute);
                                    args.putString("minuteStr", minuteStr);
                                    args.putString("price", price);
                                    washFragment.setArguments(args);
                                    LoadFragment(washFragment,2);
                                } else {
                                    WashingRegistration2 washFragment = new WashingRegistration2();
                                    Bundle args = new Bundle();
                                    args.putString("token", token);
                                    args.putString("date", HomeFragment.dd + "." + HomeFragment.m + "." + HomeFragment.y);
                                    args.putInt("duration", durations);
                                    args.putInt("minute", minute);
                                    args.putString("minuteStr", minuteStr);
                                    args.putString("price", price);
                                    washFragment.setArguments(args);
                                    LoadFragment(washFragment,1);
                                }
                            }
                        }
                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
        if(LCWC!=null) {
            for(int i=0;i<LCWC.getResponse().getCarWashes().size();i++) {
                map.addMarker(new MarkerOptions().position(new LatLng(LCWC.getResponse().getCarWashes().get(i).getGeometry().getCoordinates().get(0), LCWC.getResponse().getCarWashes().get(i).getGeometry().getCoordinates().get(1))).title(LCWC.getResponse().getCarWashes().get(i).getProperties().getBalloonContentHeader())).setTag(i);
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(LCWC.getResponse().getCarWashes().get(i).getGeometry().getCoordinates().get(0), LCWC.getResponse().getCarWashes().get(i).getGeometry().getCoordinates().get(1)))
                        .zoom(12)
                        .build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                map.animateCamera(cameraUpdate);
                final int finalI = i;
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(final Marker marker) {

                        ad = new AlertDialog.Builder(getContext());
                        ad.setTitle(LCWC.getResponse().getCarWashes().get(Integer.parseInt(marker.getTag().toString())).getProperties().getBalloonContentHeader());  // заголовок
                        ad.setMessage(LCWC.getResponse().getCarWashes().get(Integer.parseInt(marker.getTag().toString())).getProperties().getBalloonContentBody()); // сообщение
                        ad.setPositiveButton("Выбрать", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg1) {
                                AutoRegActivity.carWashId=LCWC.getResponse().getCarWashes().get(Integer.parseInt(marker.getTag().toString())).getId();
                                AutoRegActivity.myWash=false;
                                WashingRegistration1Other fragmentWashingReg;
                                fragmentWashingReg=new WashingRegistration1Other();
                                Bundle args = new Bundle();
                                args.putString("token", token);
                                fragmentWashingReg.setArguments(args);
                                LoadFragment(fragmentWashingReg,0);
                            }
                        });
                        ad.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg1) {
                                ad=null;
                            }
                        });
                        ad.setCancelable(true);
                        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            public void onCancel(DialogInterface dialog) {
                            }
                        });
                        ad.show();
                        return false;
                    }
                });

            }
        }
    }

    private void LoadFragment(Fragment fragment,int page){
        switch (page){
            case 0:getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment,"fragmentWash1").addToBackStack(null).commit();break;
            case 1:getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentByTag("FragmentMaps")).show(getFragmentManager().findFragmentByTag("FragmentWash2")).commit();break;
            case 2:getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentByTag("FragmentWash2")).show(getFragmentManager().findFragmentByTag("fragmentWash1")).commit();break;
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

    private static LoadCarWashesClass deserializeFilterResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoadCarWashesClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
