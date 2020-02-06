package com.project.avtomoy.ui.info.fragments;

import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.project.avtomoy.R;
import com.project.avtomoy.ServicesOrderClass;
import com.project.avtomoy.TimeAndPriceClass;
import com.project.avtomoy.ui.home.ComplexSelect;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DutiesViewHolderCardInfo extends RecyclerView.ViewHolder {

    private TextView
            nameText,
            priceText,
            description,
            time;


    private AlertDialog.Builder ad;
    private LinearLayout CL;
    private ComplexSelect SC;
    public DutiesViewHolderCardInfo(@NonNull final View itemView) {
        super(itemView);
        nameText = itemView.findViewById(R.id.name);
        priceText = itemView.findViewById(R.id.price);
        time = itemView.findViewById(R.id.time);
        description = itemView.findViewById(R.id.description);
        CL=itemView.findViewById(R.id.LLMain);
    }


    public void bindData(final ServicesOrderClass duty){

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
        nameText.setText(duty.getService().getName());
        priceText.setText(duty.getPrice().getPrice().toString()+ "\u20BD");
        description.setText(duty.getService().getDescription());
        time.setText("~"+duty.getPrice().getTime());
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
