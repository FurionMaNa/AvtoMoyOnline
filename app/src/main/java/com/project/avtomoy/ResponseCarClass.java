package com.project.avtomoy;

import com.project.avtomoy.ui.tools.fragments.Car.Car;

import java.util.ArrayList;

public class ResponseCarClass {
    ArrayList<Car> cars = new ArrayList<>();

    public ResponseCarClass(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }
}
