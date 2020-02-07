package com.project.avtomoy.ui.info.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.DownloadImageTask;
import com.project.avtomoy.LoadContactsPageClass;
import com.project.avtomoy.R;

import  java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InfoContactFragment extends Fragment {

    private String token;
    private LoadContactsPageClass LCPC;
    Spinner spinner;
    ArrayList<String> day=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            String str_answer = "";
            Bundle bundle = this.getArguments();
            TextView tvName = view.findViewById(R.id.nameAvtomoy);
            TextView tvPhone1 = view.findViewById(R.id.textView10);
            TextView tvPhone2 = view.findViewById(R.id.textView11);
            TextView tvSite = view.findViewById(R.id.textView13);
            TextView tvAddress = view.findViewById(R.id.textView8);
            ImageView photo = view.findViewById(R.id.imageView4);
            ImageView ibWhatsApp = view.findViewById(R.id.imageView8);
            ImageView ibTelegramApp = view.findViewById(R.id.imageView9Telegram);
            ImageView ibViberApp = view.findViewById(R.id.imageView10);
            ImageView ibVKApp = view.findViewById(R.id.imageViewVkLink);
            ImageView ibInstagramApp = view.findViewById(R.id.imageView9);
            ImageView ibFaceBookApp = view.findViewById(R.id.imageViewFacebookLink);

            if (bundle != null) {
                token = bundle.getString("token", "false");
            }
            Uri myUri = Uri.parse(AutoRegActivity.loadAboutCarClass.getResponse().getPhoto());
            photo.setImageURI(myUri);
            String s = AutoRegActivity.loadAboutCarClass.getResponse().getPhoto();
            new DownloadImageTask(photo).execute(s);
            tvName.setText(AutoRegActivity.loadAboutCarClass.getResponse().getContacts().get(0).getValue());
            tvPhone1.setText(AutoRegActivity.loadAboutCarClass.getResponse().getContacts().get(1).getValue());
            tvPhone2.setText(AutoRegActivity.loadAboutCarClass.getResponse().getContacts().get(2).getValue());
            tvSite.setText(AutoRegActivity.loadAboutCarClass.getResponse().getContacts().get(4).getValue());
            tvAddress.setText(AutoRegActivity.loadAboutCarClass.getResponse().getAddress());
            tvSite.setTag(AutoRegActivity.loadAboutCarClass.getResponse().getContacts().get(4).getValue());
            tvSite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(v.getTag().toString()));
                    startActivity(browserIntent);
                }
            });
            ibVKApp.setTag(AutoRegActivity.loadAboutCarClass.getResponse().getContacts().get(5).getValue());
            ibVKApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(v.getTag().toString()));
                    startActivity(browserIntent);
                }
            });
            ibFaceBookApp.setTag(AutoRegActivity.loadAboutCarClass.getResponse().getContacts().get(6).getValue());
            ibFaceBookApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(v.getTag().toString()));
                    startActivity(browserIntent);
                }
            });
            ibInstagramApp.setTag(AutoRegActivity.loadAboutCarClass.getResponse().getContacts().get(7).getValue());
            ibInstagramApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(v.getTag().toString()));
                    startActivity(browserIntent);
                }
            });
            ibTelegramApp.setTag(AutoRegActivity.loadAboutCarClass.getResponse().getContacts().get(8).getValue());
            ibTelegramApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(v.getTag().toString()));
                    startActivity(browserIntent);
                }
            });
            ibWhatsApp.setTag(AutoRegActivity.loadAboutCarClass.getResponse().getContacts().get(9).getValue());
            ibWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(v.getTag().toString()));
                    startActivity(browserIntent);
                }
            });
            ibViberApp.setTag(AutoRegActivity.loadAboutCarClass.getResponse().getContacts().get(10).getValue());
            ibViberApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(v.getTag().toString()));
                    startActivity(browserIntent);
                }
            });
            view.findViewById(R.id.textView9).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+AutoRegActivity.loadAboutCarClass.getResponse().getGeometry().getCoordinates().get(0)+","+AutoRegActivity.loadAboutCarClass.getResponse().getGeometry().getCoordinates().get(1));
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }catch (Exception e){
                        Toast.makeText(getActivity(),"На данном устройстве отключено приложение Google Maps",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });
            spinner=view.findViewById(R.id.timeWork);
            day=new ArrayList<String>();
            day.add(AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getВоскресенье());
            day.add(AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getПонедельник());
            day.add(AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getВторник());
            day.add(AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getСреда());
            day.add(AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getЧетверг());
            day.add(AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getПятница());
            day.add(AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getСуббота());
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,day );
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setSelection(dayOfWeek-1);
            spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    day.clear();
                    day.add("ВС "+AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getВоскресенье());
                    day.add("ПН "+AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getПонедельник());
                    day.add("ВТ "+AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getВторник());
                    day.add("СР "+AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getСреда());
                    day.add("ЧТ "+AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getЧетверг());
                    day.add("ПТ "+AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getПятница());
                    day.add("СБ "+AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getСуббота());
                    adapter.notifyDataSetChanged();
                    return false;
                }
            });
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Calendar c = Calendar.getInstance();
                    c.setTime(new Date());
                    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                    day.clear();
                    day.add(AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getВоскресенье());
                    day.add(AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getПонедельник());
                    day.add(AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getВторник());
                    day.add(AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getСреда());
                    day.add(AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getЧетверг());
                    day.add(AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getПятница());
                    day.add(AutoRegActivity.loadAboutCarClass.getResponse().getWork_time().getСуббота());
                    adapter.notifyDataSetChanged();
                    spinner.setSelection(dayOfWeek-1);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }

            });
            AutoRegActivity.progressBar.setVisibility(View.INVISIBLE);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
    }

    private static LoadContactsPageClass deserializeContactsResult(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString,LoadContactsPageClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}