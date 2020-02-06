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

public class SelectAdapter  extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> products;
    private ArrayList<View> vDuti;

    public SelectAdapter(Context context, ArrayList<Integer> products,ArrayList<View> v) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_select,parent,false);
        TextView tv=view.findViewById(R.id.name);
        Button b=view.findViewById(R.id.removeSelect) ;
        for(int i=0; i<HomeFragment.com.size();i++){
            if(HomeFragment.com.get(i).getId()==products.get(position)){
                tv.setText(HomeFragment.com.get(i).getName());
                b.setTag(HomeFragment.com.get(i).getId());
            }
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    WashingRegistration1.onRemove(v,vDuti);
                }catch (Exception e){
                    e.printStackTrace();
                }
                try {
                   WashingRegistration1Other.onRemove(v,vDuti);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}
