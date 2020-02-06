package com.project.avtomoy;

public class ServicesOrderClass {
    private ServicesClass service;
    private PricesClass price;

    public ServicesOrderClass(ServicesClass service, PricesClass price) {
        this.service = service;
        this.price = price;
    }

    public ServicesClass getService() {
        return service;
    }

    public void setService(ServicesClass service) {
        this.service = service;
    }

    public PricesClass getPrice() {
        return price;
    }

    public void setPrice(PricesClass price) {
        this.price = price;
    }
}
