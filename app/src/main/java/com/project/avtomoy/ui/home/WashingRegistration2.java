package com.project.avtomoy.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.LoadGetAvaibleTimes;
import com.project.avtomoy.PeriodsClass;
import com.project.avtomoy.R;
import com.project.avtomoy.ThreadRequest;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class WashingRegistration2 extends Fragment {

    private String token;
    private String date;
    private Integer duration;
    private Integer minute;
    private String minuteStr;
    private String price;
    private PeriodsClass periods;
    private ArrayList<String>per;
    private ArrayList<String>start;
    private ArrayList<String> avaible;
    private Integer startInterval;
    private Integer timeRecord;
    private Spinner spinner;
    private EditText comment;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        View view = inflater.inflate(R.layout.fragment_washing_registration_part2, container, false);
        try {
            if (bundle != null) {
                token = bundle.getString("token", "false");
                date = bundle.getString("date", "false");
                duration = bundle.getInt("duration", -1);
                minute = bundle.getInt("minute", -1);
                minuteStr = bundle.getString("minuteStr", "false");
                price = bundle.getString("price", "false");
                ((TextView) view.findViewById(R.id.TimeTVFin)).setText("~" + minuteStr);
                ((TextView) view.findViewById(R.id.PriceTVFin)).setText(" " + price + "\u20BD");
            }
            final String str_answer;
            try {
                periods = null;
                str_answer = new ThreadRequest().execute("get-select-map-periods", token, "date=" + date + "&carWashId=" + AutoRegActivity.carWashId).get();
                periods = deserializePeriodsResult(str_answer);
                start = periods.getResponse().getDates();
                spinner = (Spinner) view.findViewById(R.id.TimeRecord);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, start);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner spinner2 = (Spinner) view.findViewById(R.id.IntervalStart);
                spinner2.setAdapter(adapter2);
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            startInterval = position;
                            LoadGetAvaibleTimes pr;
                            String str_answer = "";
                            if (position == start.size() - 1) {
                                str_answer = new ThreadRequest().execute("get-avaible-times", token, "date=" + date + "&duration=" + duration + "&timeInterval=" + start.get(position) + "&nextInterval=" + start.get(0)).get();
                            } else {
                                str_answer = new ThreadRequest().execute("get-avaible-times", token, "date=" + date + "&duration=" + duration + "&timeInterval=" + start.get(position) + "&nextInterval=" + start.get(position + 1)).get();
                            }
                            pr = deserializeAvaibleResult(str_answer);
                            avaible = pr.getResponse().getTimes();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, avaible);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adapter);
                            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    timeRecord = position;
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


            view.findViewById(R.id.OtherAvtoMoyTV).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MapsFragment mapsFragment = new MapsFragment();
                    Bundle args = new Bundle();
                    args.putString("token", token);
                    args.putString("Other", "false");
                    args.putString("date", HomeFragment.dd + "." + HomeFragment.m + "." + HomeFragment.y);
                    args.putInt("duration", duration);
                    args.putInt("minute", minute);
                    args.putString("minuteStr", minuteStr);
                    args.putString("price", price);
                    mapsFragment.setArguments(args);
                    LoadFragment(mapsFragment);
                }
            });
            view.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WashingRegistration1 fragmentWashing = new WashingRegistration1();
                    Bundle args = new Bundle();
                    args.putString("token", token);
                    args.putString("Load", "false");
                    fragmentWashing.setArguments(args);
                    BackFragment(fragmentWashing);
                }
            });
            comment = view.findViewById(R.id.editTextComment);
            view.findViewById(R.id.recordButtonMain).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String strRequest = "";
                    for (int i = 0; i < AutoRegActivity.SelectComplex.size(); i++) {
                        strRequest += "services[" + i + "]=" + AutoRegActivity.SelectComplex.get(i) + "&";
                    }
                    for (int i = 0; i < AutoRegActivity.SelectServices.size(); i++) {
                        strRequest += "additionServices[" + i + "]=" + AutoRegActivity.SelectServices.get(i) + "&";
                    }
                    strRequest += "date=" + date + "&";
                    strRequest += "comment=" + comment.getText().toString() + "&";
                    strRequest += "price=" + price + "&";
                    String st = start.get(startInterval);
                    Integer stC = Integer.parseInt(String.valueOf(st.charAt(0)) + String.valueOf(st.charAt(1)));
                    Integer stM = Integer.parseInt(String.valueOf(st.charAt(3)) + String.valueOf(st.charAt(4)));
                    Integer etC = Integer.parseInt(String.valueOf(st.charAt(8)) + String.valueOf(st.charAt(9)));
                    Integer etM = Integer.parseInt(String.valueOf(st.charAt(11)) + String.valueOf(st.charAt(12)));
                    strRequest += "timeInterval=" + (stC * 60 + stM) + "," + (etC * 60 + etM) + "&";
                    st = avaible.get(timeRecord);
                    stC = Integer.parseInt(String.valueOf(st.charAt(0)) + String.valueOf(st.charAt(1)));
                    stM = Integer.parseInt(String.valueOf(st.charAt(3)) + String.valueOf(st.charAt(4)));
                    etC = Integer.parseInt(String.valueOf(st.charAt(8)) + String.valueOf(st.charAt(9)));
                    etM = Integer.parseInt(String.valueOf(st.charAt(11)) + String.valueOf(st.charAt(12)));
                    strRequest += "time=" + (stC * 60 + stM) + "," + (etC * 60 + etM);
                    String str_answer;
                    Log.i("MyLog", strRequest);
                    try {
                        str_answer = new ThreadRequest().execute("signup-car-wash", token, strRequest).get();

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Bundle args = new Bundle();
                    HomeFragment homeFragment = new HomeFragment();
                    args.putString("token", token);
                    homeFragment.setArguments(args);
                    LoadFragment(homeFragment);
                }
            });
        }catch (Exception e){
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void LoadFragment(Fragment fragment){
        getFragmentManager().beginTransaction()
                //.replace(R.id.nav_host_fragment,fragment)
                //.addToBackStack(null)
                //.commit();
                .add(R.id.nav_host_fragment, fragment,"FragmentMaps")
                .show(fragment)
                .hide(getFragmentManager().findFragmentByTag("FragmentWash2"))
                .addToBackStack(null)
                .commit();
    }

    private void BackFragment(Fragment fragment){
        getFragmentManager().beginTransaction()
                .remove(getFragmentManager().findFragmentByTag("FragmentWash2"))
                .show(getFragmentManager().findFragmentByTag("fragmentWash1"))
                .commit();
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

    private static LoadGetAvaibleTimes deserializeAvaibleResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoadGetAvaibleTimes.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
