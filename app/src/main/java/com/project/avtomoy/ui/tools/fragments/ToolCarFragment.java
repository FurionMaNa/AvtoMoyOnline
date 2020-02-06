package com.project.avtomoy.ui.tools.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.avtomoy.AutoRegActivity;
import com.project.avtomoy.LoadCarClass;
import com.project.avtomoy.R;
import com.project.avtomoy.RegistryCar;
import com.project.avtomoy.ThreadRequest;
import com.project.avtomoy.ui.tools.fragments.Car.Car;
import com.project.avtomoy.ui.tools.fragments.Car.CarAdapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ToolCarFragment extends Fragment {

    static ArrayList<Car> carsList = new ArrayList<>();
    static RecyclerView recyclerView;
    public static String token;
    static CarAdapter carAdapter = null;
    static RecyclerView.LayoutManager layoutManager;
    public static Button addCarButton;


    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        final View view = inflater.inflate(R.layout.fragment_tools_car, container, false);
        if (bundle != null) {
            token= bundle.getString("token", "false");
        }
        try {
            LoadCar();
            addCarButton = view.findViewById(R.id.addCarButton);
            if (carsList.size() == 3) {
                addCarButton.setVisibility(View.GONE);
                //addCarButton.setEnabled(false);
                //addCarButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(143, 143, 143)));
            }
            recyclerView = view.findViewById(R.id.addedCarView);
            layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            carAdapter = new CarAdapter(carsList);
            recyclerView.setAdapter(carAdapter);
            view.findViewById(R.id.addCarButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RegistryCar registryCar = new RegistryCar(carsList, recyclerView);
                    registryCar.show(getFragmentManager(), "SetCar");
                }
            });
        }catch (Exception e){
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
        AutoRegActivity.progressBar.setVisibility(View.INVISIBLE);
        return view;
    }

    public static void LoadCar(){
        try {
            LoadCarClass carsLoad = null;
            String str_answer;
            try {
                str_answer = new ThreadRequest().execute("my-cars-page", token).get();
                carsLoad = deserializeCarResult(str_answer);

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            carsList = carsLoad.getResponse().getCars();
        }catch (Exception e){
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
    }

    public static void onDeleteCar(View v) {
        try {
            String str_answer;
            try {
                str_answer = new ThreadRequest().execute("remove-transport", token, "car_id=" + v.getTag().toString()).get();
                LoadCar();
                recyclerView.setLayoutManager(layoutManager);
                carAdapter = new CarAdapter(carsList);
                recyclerView.setAdapter(carAdapter);
                if (carsList.size() != 3) {
                    addCarButton.setVisibility(View.VISIBLE);
                    //addCarButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255, 102, 52)));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
    }

    public static void onAddTransport() {
        LoadCar();
        recyclerView.setLayoutManager(layoutManager);
        carAdapter = new CarAdapter(carsList);
        recyclerView.setAdapter(carAdapter);
    }

    public static void onChangeMainCar(View v) {
        try {
            String str_answer;
            try {
                str_answer = new ThreadRequest().execute("change-transport", token, "transportId=" + v.getTag().toString()).get();
                LoadCar();
                recyclerView.setLayoutManager(layoutManager);
                carAdapter = new CarAdapter(carsList);
                recyclerView.setAdapter(carAdapter);
                if (carsList.size() != 3) {
                    addCarButton.setVisibility(View.VISIBLE);
                    //addCarButton.setEnabled(true);
                    //addCarButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255, 102, 52)));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            Toast.makeText(AutoRegActivity.context,"Проверьте подключение к интернету!!!",Toast.LENGTH_SHORT).show();
        }
    }

    private static LoadCarClass deserializeCarResult(String JSonString) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(JSonString, LoadCarClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}