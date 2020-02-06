package com.project.avtomoy;

import java.util.ArrayList;

public class ServicesAllClass {
    private ArrayList<ServicesOrderClass> services;
    private ArrayList<ServicesOrderClass> add_services;

    public ServicesAllClass(ArrayList<ServicesOrderClass> services, ArrayList<ServicesOrderClass> add_services) {
        this.services = services;
        this.add_services = add_services;
    }

    public ArrayList<ServicesOrderClass> getServices() {
        return services;
    }

    public void setServices(ArrayList<ServicesOrderClass> services) {
        this.services = services;
    }

    public ArrayList<ServicesOrderClass> getAdd_services() {
        return add_services;
    }

    public void setAdd_services(ArrayList<ServicesOrderClass> add_services) {
        this.add_services = add_services;
    }
}
