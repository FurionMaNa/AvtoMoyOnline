package com.project.avtomoy;

import java.util.ArrayList;

public class GetSignedRecord {

    private Integer id;
    private Integer users_id;
    private Integer time_start;
    private Integer time_end;
    private Integer price;
    private Integer status;
    private Integer boxes_id;
    private Integer car_type;
    private Integer car_region;
    private String car_washes_id;
    private String date;
    private ArrayList<ServicesClass> services_ids;
    private ArrayList<ServicesClass> add_services_ids;
    private String comment;
    private String car_mark;
    private String car_number;
    private String phone;
    private String client_name;

    public GetSignedRecord(Integer id, Integer users_id, Integer time_start, Integer time_end, Integer price, Integer status, Integer boxes_id, Integer car_type, Integer car_region, String car_washes_id, String date, ArrayList<ServicesClass> services_ids, ArrayList<ServicesClass> add_services_ids, String comment, String car_mark, String car_number, String phone, String client_name) {
        this.id = id;
        this.users_id = users_id;
        this.time_start = time_start;
        this.time_end = time_end;
        this.price = price;
        this.status = status;
        this.boxes_id = boxes_id;
        this.car_type = car_type;
        this.car_region = car_region;
        this.car_washes_id = car_washes_id;
        this.date = date;
        this.services_ids = services_ids;
        this.add_services_ids = add_services_ids;
        this.comment = comment;
        this.car_mark = car_mark;
        this.car_number = car_number;
        this.phone = phone;
        this.client_name = client_name;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBoxes_id() {
        return boxes_id;
    }

    public void setBoxes_id(Integer boxes_id) {
        this.boxes_id = boxes_id;
    }

    public Integer getCar_type() {
        return car_type;
    }

    public void setCar_type(Integer car_type) {
        this.car_type = car_type;
    }

    public Integer getCar_region() {
        return car_region;
    }

    public void setCar_region(Integer car_region) {
        this.car_region = car_region;
    }

    public String getCar_washes_id() {
        return car_washes_id;
    }

    public void setCar_washes_id(String car_washes_id) {
        this.car_washes_id = car_washes_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<ServicesClass> getServices_ids() {
        return services_ids;
    }

    public void setServices_ids(ArrayList<ServicesClass> services_ids) {
        this.services_ids = services_ids;
    }

    public ArrayList<ServicesClass> getAdd_services_ids() {
        return add_services_ids;
    }

    public void setAdd_services_ids(ArrayList<ServicesClass> add_services_ids) {
        this.add_services_ids = add_services_ids;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }
}
