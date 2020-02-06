package com.project.avtomoy;

public class AdvertClass {
    private String ml_admin_id;
    private String title;
    private String text;
    private String true_end_date;
    private String photo_path;
    private String start_date;
    private String end_date;
    private String phone;
    private String address;
    private String site;
    private Integer id;
    private Integer type;

    public AdvertClass(String ml_admin_id, String title, String text, String true_end_date, String photo_path, String start_date, String end_date, String phone, String address, String site, Integer id, Integer type) {
        this.ml_admin_id = ml_admin_id;
        this.title = title;
        this.text = text;
        this.true_end_date = true_end_date;
        this.photo_path = photo_path;
        this.start_date = start_date;
        this.end_date = end_date;
        this.phone = phone;
        this.address = address;
        this.site = site;
        this.id = id;
        this.type = type;
    }

    public String getMl_admin_id() {
        return ml_admin_id;
    }

    public void setMl_admin_id(String ml_admin_id) {
        this.ml_admin_id = ml_admin_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTrue_end_date() {
        return true_end_date;
    }

    public void setTrue_end_date(String true_end_date) {
        this.true_end_date = true_end_date;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
