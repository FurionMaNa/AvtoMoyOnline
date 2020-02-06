package com.project.avtomoy.ui.info.fragments.Sales;

public class Sale {
    private String car_washes_id;
    private String title;
    private String description;
    private String start_date;
    private String end_date;
    private Integer id;

    public Sale(String car_washes_id, String title, String description, String start_date, String end_date, Integer id) {
        this.car_washes_id = car_washes_id;
        this.title = title;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.id = id;
    }

    public String getCar_washes_id() {
        return car_washes_id;
    }

    public void setCar_washes_id(String car_washes_id) {
        this.car_washes_id = car_washes_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
