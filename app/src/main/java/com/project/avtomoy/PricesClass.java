package com.project.avtomoy;

public class PricesClass {
    private Integer id;
    private Integer services_id;
    private Integer transport;
    private Integer price;
    private Integer time;

    public PricesClass(Integer id, Integer services_id, Integer transport, Integer price, Integer time) {
        this.id = id;
        this.services_id = services_id;
        this.transport = transport;
        this.price = price;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServices_id() {
        return services_id;
    }

    public void setServices_id(Integer services_id) {
        this.services_id = services_id;
    }

    public Integer getTransport() {
        return transport;
    }

    public void setTransport(Integer transport) {
        this.transport = transport;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
