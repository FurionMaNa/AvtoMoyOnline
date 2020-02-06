package com.project.avtomoy;

import java.util.ArrayList;

public class ResponseCarWashesFilterClass {
    private ArrayList<CarWashesClass> carWashes;

    public ResponseCarWashesFilterClass(ArrayList<CarWashesClass> carWashes) {
        this.carWashes = carWashes;
    }

    public ArrayList<CarWashesClass> getCarWashes() {
        return carWashes;
    }

    public void setCarWashes(ArrayList<CarWashesClass> carWashes) {
        this.carWashes = carWashes;
    }
}
