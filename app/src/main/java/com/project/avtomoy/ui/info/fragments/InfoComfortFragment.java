package com.project.avtomoy.ui.info.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.LoadComfortClass;
import com.project.avtomoy.R;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

public class InfoComfortFragment extends Fragment {

    private String token;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            token= bundle.getString("token", "false");
        }
        View view=inflater.inflate(R.layout.fragment_info_comfort, container, false);
        try {
            ((TextView)view.findViewById(R.id.nameAvtomoy)).setText(AutoRegActivity.loadAboutCarClass.getResponse().getName());
            String str_answer;
            LoadComfortClass LCC;
            TableLayout tb = view.findViewById(R.id.TableComfort);
            for (int i = 0; i < AutoRegActivity.loadAboutCarClass.getResponse().getComfort().get(0).getData().getComfort().size(); i += 2) {
                TableRow tr = new TableRow(getActivity());
                tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                tr.setWeightSum(2);
                LinearLayout ll1 = new LinearLayout(getActivity());
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(55, 55);
                LinearLayout.LayoutParams layoutParamst=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParamst.setMargins(0,25,0,25);
                layoutParams.setMargins(25,30,0,30);
                LinearLayout ll2 = new LinearLayout(getActivity());
                ImageView iv1=new ImageView(getActivity());
                iv1.setLayoutParams(new ViewGroup.LayoutParams(55,55));
                iv1.setLayoutParams(layoutParams);
                iv1.setColorFilter(Color.argb(255, 255, 102, 52));
                TextView tv1 = new TextView(getActivity());
                tv1.setTextColor(Color.parseColor("#000000"));
                tv1.setLayoutParams(layoutParamst);
                tv1.setTypeface(ResourcesCompat.getFont(getActivity(),R.font.roboto));
                tv1.setTextSize(16);
                tv1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ImageView iv2=new ImageView(getActivity());
                LinearLayout.LayoutParams layoutParams2=new LinearLayout.LayoutParams(55, 55);
                layoutParams2.setMargins(60,30,0,30);
                iv2.setColorFilter(Color.argb(255, 255, 102, 52));
                iv2.setLayoutParams(new ViewGroup.LayoutParams(55,55));
                iv2.setLayoutParams(layoutParams2);
                TextView tv2 = new TextView(getActivity());
                tv2.setTextColor(Color.parseColor("#000000"));
                tv2.setLayoutParams(layoutParamst);
                tv2.setTypeface(ResourcesCompat.getFont(getActivity(),R.font.roboto));
                tv2.setTextSize(16);
                tv2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                switch (AutoRegActivity.loadAboutCarClass.getResponse().getComfort().get(0).getData().getComfort().get(i)) {
                    case "0":  iv1.setImageResource(R.drawable.wifi);            tv1.setText("   Wi-Fi");break;
                    case "1":  iv1.setImageResource(R.drawable.glass_of_water);  tv1.setText("   Кулер");break;
                    case "2":  iv1.setImageResource(R.drawable.shopping_bag);    tv1.setText("   Магазин");break;
                    case "3":  iv1.setImageResource(R.drawable.couch);           tv1.setText("   Зона отдыха");break;
                    case "4":  iv1.setImageResource(R.drawable.monitor);         tv1.setText("   Телевизор");break;
                    case "5":  iv1.setImageResource(R.drawable.security_camera); tv1.setText("   Видеонаблюдение");break;
                    case "6":  iv1.setImageResource(R.drawable.air_conditioner); tv1.setText("   Кондиционер");break;
                    case "7":  iv1.setImageResource(R.drawable.spoon);           tv1.setText("   Кафе");break;
                    case "8":  iv1.setImageResource(R.drawable.toilet);          tv1.setText("   Туалет");break;
                    case "9":  iv1.setImageResource(R.drawable.tea_cup);         tv1.setText("   Кофе-аппарат");break;
                    case "10": iv1.setImageResource(R.drawable.payment_method);  tv1.setText("   Банкомат");break;
                    case "11": iv1.setImageResource(R.drawable.washing_hands);   tv1.setText("   Умывальник");break;
                }

                if (i + 1 != AutoRegActivity.loadAboutCarClass.getResponse().getComfort().get(0).getData().getComfort().size()) {
                    switch (AutoRegActivity.loadAboutCarClass.getResponse().getComfort().get(0).getData().getComfort().get(i + 1)) {
                        case "0":  iv2.setImageResource(R.drawable.wifi);            tv2.setText("   Wi-Fi");break;
                        case "1":  iv2.setImageResource(R.drawable.glass_of_water);  tv2.setText("   Кулер");break;
                        case "2":  iv2.setImageResource(R.drawable.shopping_bag);    tv2.setText("   Магазин");break;
                        case "3":  iv2.setImageResource(R.drawable.couch);           tv2.setText("   Зона отдыха");break;
                        case "4":  iv2.setImageResource(R.drawable.monitor);         tv2.setText("   Телевизор");break;
                        case "5":  iv2.setImageResource(R.drawable.security_camera); tv2.setText("   Видеонаблюдение");break;
                        case "6":  iv2.setImageResource(R.drawable.air_conditioner); tv2.setText("   Кондиционер");break;
                        case "7":  iv2.setImageResource(R.drawable.spoon);           tv2.setText("   Кафе");break;
                        case "8":  iv2.setImageResource(R.drawable.toilet);          tv2.setText("   Туалет");break;
                        case "9":  iv2.setImageResource(R.drawable.tea_cup);         tv2.setText("   Кофе-аппарат");break;
                        case "10": iv2.setImageResource(R.drawable.payment_method);  tv2.setText("   Банкомат");break;
                        case "11": iv2.setImageResource(R.drawable.washing_hands);   tv2.setText("   Умывальник");break;
                    }
                }
                ll1.addView(iv1);
                ll1.addView(tv1);
                ll2.addView(iv2);
                ll2.addView(tv2);
                tr.addView(ll1);
                tr.addView(ll2);
                tb.addView(tr);
            }
            LinearLayout ll = view.findViewById(R.id.PriceVar);
            ll.setOrientation(LinearLayout.VERTICAL);
            if (1 <= AutoRegActivity.loadAboutCarClass.getResponse().getComfort().get(0).getData().getPayment().size()) {
                TextView tv1 = new TextView(getActivity());
                tv1.setTextColor(Color.parseColor("#000000"));
                tv1.setTypeface(ResourcesCompat.getFont(getActivity(),R.font.roboto));
                LinearLayout.LayoutParams layoutParamst=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParamst.setMargins(45,25,0,25);
                tv1.setLayoutParams(layoutParamst);
                tv1.setTextSize(16);
                tv1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                switch (AutoRegActivity.loadAboutCarClass.getResponse().getComfort().get(0).getData().getComfort().get(0)) {
                    case "0":
                        tv1.setText("Банковской картой");
                        break;case "1": tv1.setText("Онлайн");
                        break;case "2": tv1.setText("Безналичнй расчёт");
                        break;
                }
                ll.addView(tv1);
            }
            if (2 <= AutoRegActivity.loadAboutCarClass.getResponse().getComfort().get(0).getData().getPayment().size()) {
                TextView tv2 = new TextView(getActivity());
                tv2.setTextColor(Color.parseColor("#000000"));
                tv2.setTypeface(ResourcesCompat.getFont(getActivity(),R.font.roboto));
                LinearLayout.LayoutParams layoutParamst=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParamst.setMargins(45,25,0,25);
                tv2.setLayoutParams(layoutParamst);
                tv2.setTextSize(16);
                tv2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                switch (AutoRegActivity.loadAboutCarClass.getResponse().getComfort().get(0).getData().getComfort().get(1)) {
                    case "0":
                        tv2.setText("Банковской картой");
                        break;case "1": tv2.setText("Онлайн");
                        break;case "2": tv2.setText("Безналичнй расчёт");
                        break;
                }
                ll.addView(tv2);
            }
            if (3 <= AutoRegActivity.loadAboutCarClass.getResponse().getComfort().get(0).getData().getPayment().size()) {
                TextView tv3 = new TextView(getActivity());
                tv3.setTextColor(Color.parseColor("#000000"));
                tv3.setTypeface(ResourcesCompat.getFont(getActivity(),R.font.roboto));
                LinearLayout.LayoutParams layoutParamst=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParamst.setMargins(45,25,0,25);
                tv3.setLayoutParams(layoutParamst);
                tv3.setTextSize(16);
                tv3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                switch (AutoRegActivity.loadAboutCarClass.getResponse().getComfort().get(0).getData().getComfort().get(2)) {
                    case "0":
                        tv3.setText("Банковской картой");
                        break;case "1": tv3.setText("Онлайн");
                        break;case "2": tv3.setText("Безналичнй расчёт");
                        break;
                }
                ll.addView(tv3);
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private static LoadComfortClass deserializeComfortResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoadComfortClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}