package com.project.avtomoy.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.avtomoy.R;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.ServicesClass;
import com.project.avtomoy.TimeAndPriceClass;
import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ServicesViewHolderOther extends RecyclerView.ViewHolder {

    private TextView
            nameText,
            priceText;
    ImageView moreText;
    private AlertDialog.Builder ad;
    private CardView CL;
    private ComplexSelect SC;
    public ServicesViewHolderOther(@NonNull final View itemView) {
        super(itemView);
        nameText = itemView.findViewById(R.id.nameText);
        priceText = itemView.findViewById(R.id.priceText);
        moreText=itemView.findViewById(R.id.moreImageView);
        CL=itemView.findViewById(R.id.DutyID);
        CL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WashingRegistration1Other.onSelectServices(v,true);
            }
        });

    }


    public void bindData(final ServicesClass duty, ArrayList<Integer> id){
        nameText.setText(duty.getName());

        Boolean f=true;
        String str_answer;
        TimeAndPriceClass service = null;
        TimeAndPriceClass tp;
        if(duty.getDescription().equals("")){
            moreText.setVisibility(View.INVISIBLE);
            moreText.setVisibility(View.GONE);
        }
        moreText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad = new AlertDialog.Builder(AutoRegActivity.context);
                ad.setTitle(duty.getName());  // заголовок
                ad.setMessage(duty.getDescription()); // сообщение
                ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                    }
                });
                ad.setCancelable(true);
                ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                    }
                });
                ad.show();
            }
        });
        priceText.setText(duty.getPrice().toString());
        SC=new ComplexSelect(duty,"false");
        CL.setTag(SC);

        for (int i = 0; i < id.size(); i++) {
            if (id.get(i) == duty.getId()) {
                CL.setBackgroundResource(R.drawable.duti_drawable_focus);
                SC.setState("true");
                f = false;
            }
        }
        if (f) {
            CL.setBackgroundResource(R.drawable.duti_drawable_unfocus);
        }
    }
    private TimeAndPriceClass deserializeTimeAndPriceResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,TimeAndPriceClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
