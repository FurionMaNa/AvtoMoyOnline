package com.project.avtomoy.ui.home;

import com.project.avtomoy.ServicesClass;

public class ComplexSelect {
    ServicesClass servicesClass;
    String state;

    public ComplexSelect(ServicesClass servicesClass, String state) {
        this.servicesClass = servicesClass;
        this.state = state;
    }

    public ServicesClass getServicesClass() {
        return servicesClass;
    }

    public void setServicesClass(ServicesClass servicesClass) {
        this.servicesClass = servicesClass;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
