package com.project.avtomoy.ui.history;

import com.project.avtomoy.ServicesClass;

import java.util.ArrayList;

public class ArrayServicesClass {
    ArrayList<ServicesClass> services=new ArrayList<ServicesClass>();

    public ArrayServicesClass(ArrayList<ServicesClass> services) {
        this.services = services;
    }

    public ArrayList<ServicesClass> getServices() {
        return services;
    }

    public void setServices(ArrayList<ServicesClass> services) {
        this.services = services;
    }
}
