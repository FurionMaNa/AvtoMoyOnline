package com.project.avtomoy.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.project.avtomoy.R;

import java.util.ArrayList;

public class SelectAdapterServices  extends BaseAdapter {

    private Context context;
    private ArrayList<View> vDuti;
    private ArrayList<Integer> products;

    public SelectAdapterServices(Context context, ArrayList<Integer> products,ArrayList<View> v) {
        this.context = context;
        this.products = products;
        this.vDuti = v;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_select, parent, false);
        TextView tv = view.findViewById(R.id.name);
        Button b=view.findViewById(R.id.removeSelect) ;
        for (int i = 0; i < HomeFragment.ser.size(); i++) {
            if (HomeFragment.ser.get(i).getId() == products.get(position)) {
                tv.setText(HomeFragment.ser.get(i).getName());
                b.setTag(HomeFragment.ser.get(i).getId());
            }
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    WashingRegistration1.onRemoveServices(v,vDuti);
                }catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    WashingRegistration1Other.onRemoveServices(v,vDuti);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}