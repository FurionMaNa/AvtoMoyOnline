package com.project.avtomoy.ui.history;

import java.util.ArrayList;

public class HistoryClass {
    private Integer id;
    private Integer user_id;
    private Integer time_start;
    private Integer time_end;
    private Integer status;
    private Integer car_region;
    private Integer car_type;
    private String comment;
    private String comment_admin;
    private String client_name;
    private String phone;
    private String car_mark;
    private String car_number;
    private String date;
    private String services_ids;
    private String add_services_ids;
    private float price;
    private String car_washes_id;
    private ArrayList<Integer> service_id;
    private ArrayList<Integer> complex_id;

    public HistoryClass(Integer id, Integer user_id, Integer time_start, Integer time_end, Integer status, Integer car_region, Integer car_type, String comment, String comment_admin, String client_name, String phone, String car_mark, String car_number, String date, String services_ids, String add_services_ids, float price, String car_washes_id, ArrayList<Integer> service_id, ArrayList<Integer> complex_id) {
        this.id = id;
        this.user_id = user_id;
        this.time_start = time_start;
        this.time_end = time_end;
        this.status = status;
        this.car_region = car_region;
        this.car_type = car_type;
        this.comment = comment;
        this.comment_admin = comment_admin;
        this.client_name = client_name;
        this.phone = phone;
        this.car_mark = car_mark;
        this.car_number = car_number;
        this.date = date;
        this.services_ids = services_ids;
        this.add_services_ids = add_services_ids;
        this.price = price;
        this.car_washes_id = car_washes_id;
        this.service_id = service_id;
        this.complex_id = complex_id;
    }

    public String getCar_washes_id() {
        return car_washes_id;
    }

    public void setCar_washes_id(String car_washes_id) {
        this.car_washes_id = car_washes_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getTime_start() {
        return time_start;
    }

    public void setTime_start(Integer time_start) {
        this.time_start = time_start;
    }

    public Integer getTime_end() {
        return time_end;
    }

    public void setTime_end(Integer time_end) {
        this.time_end = time_end;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment_admin() {
        return comment_admin;
    }

    public void setComment_admin(String comment_admin) {
        this.comment_admin = comment_admin;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCar_mark() {
        return car_mark;
    }

    public void setCar_mark(String car_mark) {
        this.car_mark = car_mark;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getServices_ids() {
        return services_ids;
    }

    public void setServices_ids(String services_ids) {
        this.services_ids = services_ids;
    }

    public String getAdd_services_ids() {
        return add_services_ids;
    }

    public void setAdd_services_ids(String add_services_ids) {
        this.add_services_ids = add_services_ids;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ArrayList<Integer> getService_id() {
        return service_id;
    }

    public void setService_id(ArrayList<Integer> service_id) {
        this.service_id = service_id;
    }

    public ArrayList<Integer> getComplex_id() {
        return complex_id;
    }

    public void setComplex_id(ArrayList<Integer> complex_id) {
        this.complex_id = complex_id;
    }
}
