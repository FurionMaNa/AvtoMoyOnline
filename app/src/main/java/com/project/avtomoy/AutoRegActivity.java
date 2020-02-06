package com.project.avtomoy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.project.avtomoy.ui.chat.ChatFragment;
import com.project.avtomoy.ui.history.HistoryFragment;
import com.project.avtomoy.ui.home.HomeFragment;
import com.project.avtomoy.ui.info.InfoFragment;
import com.project.avtomoy.ui.tools.ToolsFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class AutoRegActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    public static String token;
    public SharedPreferences myPrefereces;
    public static ArrayList<Integer> SelectComplex=new ArrayList<>();
    public static ArrayList<Integer> SelectServices=new ArrayList<>();
    public static String carWashId="";
    public static Boolean myWash=true;
    public static String myCarWash;
    public static String login;
    public static NavigationView navigationView;
    public static Context context;
    public static ProgressBar progressBar;
    public static GetSignedRecordLoad GSRL = null;
    public static LoadAboutCarClass loadAboutCarClass;
    public static Integer page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        super.onCreate(savedInstanceState);
        context=AutoRegActivity.this;
        setContentView(R.layout.activity_auto_reg);
        Intent intents=this.getIntent();
        token= (String)intents.getSerializableExtra("token");
        carWashId= (String)intents.getSerializableExtra("id");
        myCarWash= (String)intents.getSerializableExtra("id");
        myWash=true;
        progressBar=findViewById(R.id.LoadProgressBar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.whiteFragment2,
                R.id.nav_home, R.id.nav_info, R.id.nav_history,
                R.id.nav_chat, R.id.nav_tools,R.id.nav_exit)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        View header = navigationView.getHeaderView(0);
        LinearLayout linearLayout = header.findViewById(R.id.head_Container);
        final Button btn = linearLayout.findViewById(R.id.hidenavbar_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        ColorStateList csl = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked}
                },
                new int[]{
                        Color.WHITE,
                        getResources().getColor(R.color.colorAccent)
                });

        navigationView.setItemTextColor(csl);
        navigationView.setItemIconTintList(csl);

        onNavigationItemSelected(navigationView.getMenu().getItem(1));
        navigationView.getMenu().getItem(1).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        Bundle args = new Bundle();
        switch (page){
            case 0:
                args.putString("token", token);
                HomeFragment fragmentHome=new HomeFragment();
                fragmentHome.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,  fragmentHome).commit();
                break;
            case 1:

                args.putString("token", token);
                InfoFragment fragmentinfo = new InfoFragment();
                fragmentinfo.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragmentinfo).commit();
                break;
            case 2:
                args.putString("token", token);
                HistoryFragment fragmentHistory=new HistoryFragment();
                fragmentHistory.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragmentHistory).commit();
                break;
            case 3:
                args.putString("token", token);
                ChatFragment fragmentChat=new ChatFragment();
                fragmentChat.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragmentChat).commit();
                break;
            case 4:
                args.putString("token", token);
                ToolsFragment fragmentTools=new ToolsFragment();
                fragmentTools.setArguments(args);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, fragmentTools).commit();
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        AutoRegActivity.progressBar.setVisibility(View.VISIBLE);
        ((DrawerLayout)findViewById(R.id.drawer_layout)).closeDrawers();
        Toolbar toolbar = findViewById(R.id.toolbar);
        Bundle args = new Bundle();
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                args.putString("token", token);
                HomeFragment fragmentHome=new HomeFragment();
                fragmentHome.setArguments(args);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,  fragmentHome).addToBackStack(null).commit();
                /*ChatFragment fragmentChats=new ChatFragment();
                fragmentChats.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragmentChats).commit();*/
                page=0;
                break;
            case R.id.nav_info:
                //String str_answer=null;
                //try {
                //    str_answer = new ThreadRequest().execute("about-car-wash", token, "carWashId=" + AutoRegActivity.carWashId).get();
                //} catch (ExecutionException e) {
                //    e.printStackTrace();
                //} catch (InterruptedException e) {
                //    e.printStackTrace();
                //}
                //if(str_answer!=null) {
                //    loadAboutCarClass = deserializeAboutCarResult(str_answer);
                //}else{
                //    Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
                //}
                args.putString("token", token);
                InfoFragment fragmentinfo = new InfoFragment();
                fragmentinfo.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragmentinfo).commit();
                page=1;
                break;
            case R.id.nav_history:
                args.putString("token", token);
                HistoryFragment fragmentHistory=new HistoryFragment();
                fragmentHistory.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragmentHistory).commit();
                page=2;

                break;
            case R.id.nav_chat:
                args.putString("token", token);
                ChatFragment fragmentChat=new ChatFragment();
                fragmentChat.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragmentChat).commit();
                page=3;
                break;
            case R.id.nav_tools:
                args.putString("token", token);
                ToolsFragment fragmentTools=new ToolsFragment();
                fragmentTools.setArguments(args);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, fragmentTools).commit();
                page=4;
                break;
            case R.id.nav_exit:
                Intent intent = new Intent(AutoRegActivity.this,MainActivity.class);
                intent.putExtra("Param","param");
                startActivity(intent);
                this.finish();
                //this.finishAffinity();
                break;
        }
        toolbar.setTitle("");
        menuItem.setChecked(true);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    private TimeAndPriceClass deserializeTimeAndPriceResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,TimeAndPriceClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.i("MyLog","Error");
            return null;
        }
    }
}
