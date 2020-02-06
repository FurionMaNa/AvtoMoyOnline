package com.project.avtomoy;

public class ResponseClass {
    private String token;
    private CarWashesClass carWashId;

    public ResponseClass(String token, CarWashesClass carWashId) {
        this.token = token;
        this.carWashId = carWashId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public CarWashesClass getCarWashId() {
        return carWashId;
    }

    public void setCarWashId(CarWashesClass carWashId) {
        this.carWashId = carWashId;
    }
}
