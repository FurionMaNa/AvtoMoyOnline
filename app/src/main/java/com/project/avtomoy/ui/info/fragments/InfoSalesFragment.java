package com.project.avtomoy.ui.info.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.project.avtomoy.R;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.ui.info.fragments.Sales.LoadIsFreeClass;
import com.project.avtomoy.ui.info.fragments.Sales.LoadSalesClass;
import com.project.avtomoy.ui.info.fragments.Sales.Sale;
import com.project.avtomoy.ui.info.fragments.Sales.SalesAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InfoSalesFragment extends Fragment {

    ArrayList<Sale> salesList = new ArrayList<>();
    private String token;

    public View onCreateView( LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            token= bundle.getString("token", "false");
        }
        View view =inflater.inflate(R.layout.fragment_info_sales, container, false);
        ((TextView)view.findViewById(R.id.nameAvtomoy)).setText(AutoRegActivity.loadAboutCarClass.getResponse().getName());
        try {
            /*String str_answer;
            try {
                str_answer = new ThreadRequest().execute("carwash-sales-page", token, "carWashId=" + AutoRegActivity.carWashId).get();
                LoadSalesClass LSC = deserializeSaleResult(str_answer);
                salesList = LSC.getResponse().getSales();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                str_answer = new ThreadRequest().execute("is-free", token).get();
                LoadIsFreeClass LIFC = deserializeIsFreeResult(str_answer);
                ((TextView) view.findViewById(R.id.textView3)).setText("У вас бесплатных обслуживаний: " + LIFC.getResponse().getFree().toString() + ". До бесплатного обслуживания осталось: " + LIFC.getResponse().getLeft().toString());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            RecyclerView recyclerView = view.findViewById(R.id.salesView);
            if(AutoRegActivity.loadAboutCarClass.getResponse().getSales().size()==0){
                recyclerView.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);
            SalesAdapter salesAdapter = new SalesAdapter(AutoRegActivity.loadAboutCarClass.getResponse().getSales());
            recyclerView.setAdapter(salesAdapter);
        }catch (Exception e){
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private static LoadSalesClass deserializeSaleResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoadSalesClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static LoadIsFreeClass deserializeIsFreeResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoadIsFreeClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}