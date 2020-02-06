package com.project.avtomoy;

import java.util.ArrayList;

public class ResponseServiceClass {
    private ArrayList<ServicesClass> services;

    public ResponseServiceClass(ArrayList<ServicesClass> services) {
        this.services = services;
    }

    public ArrayList<ServicesClass> getServices() {
        return services;
    }

    public void setServices(ArrayList<ServicesClass> services) {
        this.services = services;
    }
}
