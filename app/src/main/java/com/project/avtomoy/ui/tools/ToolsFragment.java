package com.project.avtomoy.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.project.avtomoy.R;
import com.project.avtomoy.ui.tools.fragments.ToolCarFragment;
import com.project.avtomoy.ui.tools.fragments.ToolProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
public class ToolsFragment extends Fragment {
    public String token;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tools, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            token= bundle.getString("token", "false");
        }
        Bundle args = new Bundle();
        args.putString("token", token);
        ToolCarFragment fragmentToolsCar=new ToolCarFragment();
        fragmentToolsCar.setArguments(args);
        LoadFragment(fragmentToolsCar);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                assert getFragmentManager() != null;
                Bundle args = new Bundle();
                switch (menuItem.getItemId()){
                    case R.id.cars_nav_bottom:
                        args.putString("token", token);
                        ToolCarFragment fragmentToolsCar=new ToolCarFragment();
                        fragmentToolsCar.setArguments(args);
                        LoadFragment(fragmentToolsCar);
                        break;
                    case R.id.profile_nav_bottom:
                        args.putString("token", token);
                        ToolProfileFragment fragmentToolsPro=new ToolProfileFragment();
                        fragmentToolsPro.setArguments(args);
                        LoadFragment(fragmentToolsPro);
                        break;
                }
                return true;
            }
        });




        return view;
    }

    private void LoadFragment(Fragment fragment){
        getFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment_tools, fragment)
                .commit();
    }
}