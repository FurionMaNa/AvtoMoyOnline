package com.project.avtomoy.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.avtomoy.R;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.GetSignedRecordLoad;
import com.project.avtomoy.LoadHistoryClass;
import com.project.avtomoy.ServicesClass;
import com.project.avtomoy.ThreadRequest;
import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    public static String token;

    public static ArrayList<ServicesClass> com=new ArrayList<ServicesClass>();
    public static ArrayList<ServicesClass> ser=new ArrayList<ServicesClass>();
    public static String dd,m,y;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        LoadHistoryClass history=null;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            token= bundle.getString("token", "false");
        }
        String str_answer = null;
        try {
            str_answer = new ThreadRequest().execute("get-signed-record", token).get();
            AutoRegActivity.GSRL = deserializeGetSignedRecordResult(str_answer);
            if((AutoRegActivity.GSRL==null)&&(str_answer==null)){
                Toast.makeText(getActivity(),"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
            }else{
                if((AutoRegActivity.GSRL==null)&&(str_answer!=null)){
                    if(AutoRegActivity.myWash) {
                        Bundle args = new Bundle();
                        args.putString("token", token);
                        WashingRegistration1 fragmentWashing = new WashingRegistration1();
                        fragmentWashing.setArguments(args);
                        LoadFragment(fragmentWashing);
                    }else{
                        Bundle args = new Bundle();
                        args.putString("token", token);
                        WashingRegistration1Other fragmentWashing = new WashingRegistration1Other();
                        fragmentWashing.setArguments(args);
                        LoadFragment(fragmentWashing);
                    }
                }else{
                    Bundle args = new Bundle();
                    switch (AutoRegActivity.GSRL.getStatus()){
                        case 0:
                        case 1:
                            RecordFragment fragmentRecord = new RecordFragment();
                            args.putString("token", token);
                            fragmentRecord.setArguments(args);
                            LoadFragment(fragmentRecord);
                            break;
                        case 2:
                            RecordEndFragment fragmentRecordEnd = new RecordEndFragment();
                            args.putString("token", token);
                            fragmentRecordEnd.setArguments(args);
                            LoadFragment(fragmentRecordEnd);
                            break;
                    }
                }
            }
        } catch (Exception e){
            if((AutoRegActivity.GSRL==null)&&(str_answer==null)){
                Toast.makeText(getActivity(),"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
            }
        }
        return view;
    }

    private void LoadFragment(Fragment fragment){
        getFragmentManager().beginTransaction()
                .add(R.id.nav_host_fragment, fragment,"fragmentWash1")
                .show(fragment)
                .addToBackStack(null)
                .commit();
    }

    private static GetSignedRecordLoad deserializeGetSignedRecordResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,GetSignedRecordLoad.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}