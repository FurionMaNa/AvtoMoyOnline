package com.project.avtomoy.ui.tools.fragments.Car;

public class Car {
    private String car_number;
    private String car_label;
    private Integer car_region;
    private Integer car_type;
    private Integer id;
    private Integer users_id;
    private Integer is_selected;

    public Car(String car_number, String  car_label, Integer car_region, Integer car_type, Integer id, Integer users_id, Integer is_selected) {
        this.car_number = car_number;
        this.car_label = car_label;
        this.car_region = car_region;
        this.car_type = car_type;
        this.id = id;
        this.users_id = users_id;
        this.is_selected = is_selected;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public String getCar_label() {
        return car_label;
    }

    public void setCar_label(String car_label) {
        this.car_label = car_label;
    }

    public Integer getCar_region() {
        return car_region;
    }

    public void setCar_region(Integer car_region) {
        this.car_region = car_region;
    }

    public Integer getCar_type() {
        return car_type;
    }

    public void setCar_type(Integer car_type) {
        this.car_type = car_type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsers_id() {
        return users_id;
    }

    public void setUsers_id(Integer users_id) {
        this.users_id = users_id;
    }

    public Integer getIs_selected() {
        return is_selected;
    }

    public void setIs_selected(Integer is_selected) {
        this.is_selected = is_selected;
    }
}
