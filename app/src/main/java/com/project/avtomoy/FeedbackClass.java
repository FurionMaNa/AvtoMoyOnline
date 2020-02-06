package com.project.avtomoy;

public class FeedbackClass {
    private Integer users_id;
    private Integer id;
    private Integer records_id;
    private Integer type;
    private Integer status;
    private String car_washes_id;
    private String message;
    private Integer created_at;

    public FeedbackClass(Integer users_id, Integer id, Integer records_id, Integer type, Integer status, String car_washes_id, String message, Integer created_at) {
        this.users_id = users_id;
        this.id = id;
        this.records_id = records_id;
        this.type = type;
        this.status = status;
        this.car_washes_id = car_washes_id;
        this.message = message;
        this.created_at = created_at;
    }

    public Integer getUsers_id() {
        return users_id;
    }

    public void setUsers_id(Integer users_id) {
        this.users_id = users_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecords_id() {
        return records_id;
    }

    public void setRecords_id(Integer records_id) {
        this.records_id = records_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCar_washes_id() {
        return car_washes_id;
    }

    public void setCar_washes_id(String car_washes_id) {
        this.car_washes_id = car_washes_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Integer created_at) {
        this.created_at = created_at;
    }
}
