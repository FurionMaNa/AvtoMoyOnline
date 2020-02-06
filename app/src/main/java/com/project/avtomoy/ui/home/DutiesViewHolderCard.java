package com.project.avtomoy.ui.home;

import android.app.AlertDialog;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.avtomoy.R;
import com.project.avtomoy.ServicesClass;
import com.project.avtomoy.TimeAndPriceClass;
import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DutiesViewHolderCard extends RecyclerView.ViewHolder {

    private TextView
            nameText,
            priceText,
            description,
            time;


    private AlertDialog.Builder ad;
    private LinearLayout CL;
    private ComplexSelect SC;
    public DutiesViewHolderCard(@NonNull final View itemView) {
        super(itemView);
        nameText = itemView.findViewById(R.id.name);
        priceText = itemView.findViewById(R.id.price);
        time = itemView.findViewById(R.id.time);
        description = itemView.findViewById(R.id.description);
        CL=itemView.findViewById(R.id.LLMain);
    }


    public void bindData(final ServicesClass duty, ArrayList<Integer> id){

        Boolean f=true;
        String str_answer;
        TimeAndPriceClass service = null;
        TimeAndPriceClass tp;
        /*try {
            str_answer = new ThreadRequest().execute("get-time-and-price", HomeFragment.token,duty.getId().toString()).get();
            Log.i("MyLog",duty.getId().toString());
            service = deserializeTimeAndPriceResult(str_answer);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        nameText.setText(duty.getName());
        priceText.setText(duty.getPrice()+ "\u20BD");
        description.setText(duty.getDescription());
        time.setText("~"+duty.getTime());
        SC=new ComplexSelect(duty,"false");
        for (int i=0;i<id.size();i++) {
            if (id.get(i) == duty.getId()) {
                CL.setBackgroundResource(R.drawable.duty_card_drawable_focus);
                nameText.setTextColor(Color.WHITE);
                priceText.setTextColor(Color.WHITE);
                time.setTextColor(Color.WHITE);
                SC.setState("true");
            }
        }
        CL.setTag(SC);
        CL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WashingRegistration1.onSelectComplex(v,false);
            }
        });
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
