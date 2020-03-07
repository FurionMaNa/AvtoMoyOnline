package com.project.avtomoy.ui.info;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.LoadAboutCarClass;
import com.project.avtomoy.R;
import com.project.avtomoy.ThreadRequest;
import com.project.avtomoy.ui.info.fragments.InfoComfortFragment;
import com.project.avtomoy.ui.info.fragments.InfoContactFragment;
import com.project.avtomoy.ui.info.fragments.InfoDutyFragment;
import com.project.avtomoy.ui.info.fragments.InfoSalesFragment;

import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class InfoFragment extends Fragment {

    private String token;

    ProgressDialog pd;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            token= bundle.getString("token", "false");
        }
        String str_answer=null;
        try {
            str_answer = new ThreadRequest().execute("about-car-wash", token, "carWashId=" + AutoRegActivity.carWashId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(str_answer!=null) {
            AutoRegActivity.loadAboutCarClass = deserializeAboutCarResult(str_answer);
        }else{
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
        View view   = inflater.inflate(R.layout.fragment_info, container, false);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        final Bundle args = new Bundle();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.contact_nav_bottom:
                        args.putString("token", token);
                        InfoContactFragment fragmentinfo=new InfoContactFragment();
                        fragmentinfo.setArguments(args);
                        LoadFragment(fragmentinfo);
                        break;
                    case R.id.sales_nav_bottom:
                        args.putString("token", token);
                        InfoSalesFragment fragmentSalesinfo=new InfoSalesFragment();
                        fragmentSalesinfo.setArguments(args);
                        LoadFragment(fragmentSalesinfo);
                        break;
                    case R.id.duty_nav_bottom:
                        args.putString("token", token);
                        InfoDutyFragment fragmentDutyinfo=new InfoDutyFragment();
                        fragmentDutyinfo.setArguments(args);
                        LoadFragment(fragmentDutyinfo);
                        break;
                    case R.id.comfort_nav_bottom:
                        args.putString("token", token);
                        InfoComfortFragment fragmentComfortinfo=new InfoComfortFragment();
                        fragmentComfortinfo.setArguments(args);
                        LoadFragment(fragmentComfortinfo);
                        break;
                }
                return true;
            }
        });
        args.putString("token", token);
        InfoContactFragment fragmentinfo=new InfoContactFragment();
        fragmentinfo.setArguments(args);
        LoadFragment(fragmentinfo);
        return view;
    }
    private void LoadFragment(Fragment fragment){
        getFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment_info, fragment)
                .commit();
    }

    private static LoadAboutCarClass deserializeAboutCarResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoadAboutCarClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}