package com.project.avtomoy;

public class ServicesClass {
    private String car_washes_id;
    private String name;
    private String description;
    private Integer is_addition;
    private Integer is_agreed;
    private Integer price_from;
    private Integer update_at;
    private Integer created_at;
    private String price;
    private String time;
    private Integer id;

    public ServicesClass(String car_washes_id, String name, String description, Integer is_addition, Integer is_agreed, Integer price_from, Integer update_at, Integer created_at, String price, String time, Integer id) {
        this.car_washes_id = car_washes_id;
        this.name = name;
        this.description = description;
        this.is_addition = is_addition;
        this.is_agreed = is_agreed;
        this.price_from = price_from;
        this.update_at = update_at;
        this.created_at = created_at;
        this.price = price;
        this.time = time;
        this.id = id;
    }

    public String getCar_washes_id() {
        return car_washes_id;
    }

    public void setCar_washes_id(String car_washes_id) {
        this.car_washes_id = car_washes_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIs_addition() {
        return is_addition;
    }

    public void setIs_addition(Integer is_addition) {
        this.is_addition = is_addition;
    }

    public Integer getIs_agreed() {
        return is_agreed;
    }

    public void setIs_agreed(Integer is_agreed) {
        this.is_agreed = is_agreed;
    }

    public Integer getPrice_from() {
        return price_from;
    }

    public void setPrice_from(Integer price_from) {
        this.price_from = price_from;
    }

    public Integer getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Integer update_at) {
        this.update_at = update_at;
    }

    public Integer getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Integer created_at) {
        this.created_at = created_at;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
