package com.project.avtomoy.ui;

import com.project.avtomoy.ServicesPriceClass;

public class PriceClass {

    private ServicesPriceClass services;
    private ServicesPriceClass additionServices;

    public PriceClass(ServicesPriceClass services, ServicesPriceClass additionServices) {
        this.services = services;
        this.additionServices = additionServices;
    }

    public ServicesPriceClass getServices() {
        return services;
    }

    public void setServices(ServicesPriceClass services) {
        this.services = services;
    }

    public ServicesPriceClass getAdditionServices() {
        return additionServices;
    }

    public void setAdditionServices(ServicesPriceClass additionServices) {
        this.additionServices = additionServices;
    }
}
