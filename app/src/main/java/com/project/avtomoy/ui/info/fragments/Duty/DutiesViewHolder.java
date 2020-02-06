package com.project.avtomoy.ui.info.fragments.Duty;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.R;
import com.project.avtomoy.ServicesOrderClass;
import com.project.avtomoy.TimeAndPriceClass;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DutiesViewHolder extends RecyclerView.ViewHolder {

    private TextView
            nameText,
            priceText;
    private ImageView moreText;

    private AlertDialog.Builder ad;
    public DutiesViewHolder(@NonNull View itemView) {
        super(itemView);
        nameText = itemView.findViewById(R.id.nameText);
        priceText = itemView.findViewById(R.id.priceText);
        moreText=itemView.findViewById(R.id.moreImageView);
    }

    public void bindData(final ServicesOrderClass duty){
        nameText.setText(duty.getService().getName());
        String str_answer;
        TimeAndPriceClass service = null;
        /*try {
            str_answer = new ThreadRequest().execute("get-time-and-price", HomeFragment.token,duty.getId().toString()).get();
            Log.i("MyLog",duty.getId().toString());
            service = deserializeTimeAndPriceResult(str_answer);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        priceText.setText(duty.getPrice().getPrice().toString());
        if(duty.getService().getDescription().equals("")){
            moreText.setVisibility(View.INVISIBLE);
            moreText.setVisibility(View.GONE);
        }
        moreText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad = new AlertDialog.Builder(AutoRegActivity.context);
                ad.setTitle(duty.getService().getName());  // заголовок
                ad.setMessage(duty.getService().getDescription()); // сообщение
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
