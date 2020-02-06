package com.project.avtomoy;

public class AdvertRecordClass {

    private String title;
    private String site;
    private String text;
    private String phone;
    private String address;

    public AdvertRecordClass(String title, String site, String text, String phone, String address) {
        this.title = title;
        this.site = site;
        this.text = text;
        this.phone = phone;
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
}
