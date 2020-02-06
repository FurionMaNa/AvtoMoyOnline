package com.project.avtomoy;

public class TimeClass {

    private ServicesTimeClass services;
    private ServicesTimeClass additionServices;

    public TimeClass(ServicesTimeClass services, ServicesTimeClass additionServices) {
        this.services = services;
        this.additionServices = additionServices;
    }

    public ServicesTimeClass getServices() {
        return services;
    }

    public void setServices(ServicesTimeClass services) {
        this.services = services;
    }

    public ServicesTimeClass getAdditionServices() {
        return additionServices;
    }

    public void setAdditionServices(ServicesTimeClass additionServices) {
        this.additionServices = additionServices;
    }
}
