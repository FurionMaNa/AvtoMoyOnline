package com.project.avtomoy.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.project.avtomoy.R;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.ServicesClass;
import com.project.avtomoy.TimeAndPriceClass;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DutiesViewHolderRecord extends RecyclerView.ViewHolder {

    private TextView
            nameText,
            priceText;
    ImageView moreText;
    private AlertDialog.Builder ad;
    private CardView CL;
    private ComplexSelect SC;
    public DutiesViewHolderRecord(@NonNull final View itemView) {
        super(itemView);
        nameText = itemView.findViewById(R.id.nameText);
        priceText = itemView.findViewById(R.id.priceText);
        moreText=itemView.findViewById(R.id.moreImageView);
        CL=itemView.findViewById(R.id.DutyID);
        CL.setBackgroundResource(R.drawable.duti_drawable_focus);
    }


    public void bindData(final ServicesClass duty){

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
        }
        nameText.setText(duty.getName());
        priceText.setText(service.getResponse().getPrice().getServices().getPrice().toString());
        SC=new ComplexSelect(duty,"false");
        CL.setTag(SC);*/
        nameText.setText(duty.getName());
        priceText.setText("10");
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
