package com.project.avtomoy.ui.tools.fragments.Car;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.project.avtomoy.R;
import com.project.avtomoy.ui.tools.fragments.ToolCarFragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarViewHolder extends RecyclerView.ViewHolder {
    private TextView
            carNumber,
            regionNumber,
            carType;

    private Button removeCar;
    private int lastSelectedPosition = -1;
    private RecyclerView recyclerView;
    private RadioButton changeTransport;
    public CarViewHolder(@NonNull View itemView) {
        super(itemView);
        carNumber = itemView.findViewById(R.id.carNumber);
        regionNumber = itemView.findViewById(R.id.regionNumber);
        carType = itemView.findViewById(R.id.carType);
        removeCar = itemView.findViewById(R.id.removeCarButton);
        changeTransport=itemView.findViewById(R.id.changeTransport);
        recyclerView=itemView.findViewById(R.id.addedCarView);
        changeTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolCarFragment.onChangeMainCar(v);
            }
        });
    }

    public void bindData(final Car car){
        carNumber.setText(car.getCar_number().toString().toUpperCase());
        regionNumber.setText(car.getCar_region().toString());
        removeCar.setTag(car.getId());
        if((car.getIs_selected()!=null)&&(car.getIs_selected()==1)){
            changeTransport.setChecked(true);
        }
        changeTransport.setTag(car.getId());
        removeCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolCarFragment.onDeleteCar(v);
            }
        });
        switch (car.getCar_type()){
            case 0:
                carType.setText("Легковые");
                break;
            case 1:
                carType.setText("Кроссовер");
                break;
            case 2:
                carType.setText("Внедорожник");
                break;
            case 3:
                carType.setText("Микроавтобус");
                break;
            case 4:
                carType.setText("Другое");
                break;
        }
    }


}
