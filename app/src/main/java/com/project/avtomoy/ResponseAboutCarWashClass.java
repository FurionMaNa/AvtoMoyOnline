package com.project.avtomoy;

import com.project.avtomoy.ui.info.fragments.Sales.Sale;

import java.util.ArrayList;

public class ResponseAboutCarWashClass {
    private WorkTimeClass work_time;
    private ArrayList<ContactsClass> contacts;
    private String photo;
    private String address;
    private String name;
    private ArrayList<Sale> sales;
    //private ServicesAllClass services;
    private ArrayList<ServicesOrderClass> services;
    private ArrayList<ServicesOrderClass> add_services;
    private ArrayList<ComfortClass> comfort;
    private GeometryClass geometry;

    public ResponseAboutCarWashClass(WorkTimeClass work_time, ArrayList<ContactsClass> contacts, String photo, String address, String name, ArrayList<Sale> sales, ArrayList<ServicesOrderClass> services, ArrayList<ServicesOrderClass> add_services, ArrayList<ComfortClass> comfort, GeometryClass geometry) {
        this.work_time = work_time;
        this.contacts = contacts;
        this.photo = photo;
        this.address = address;
        this.name = name;
        this.sales = sales;
        this.services = services;
        this.add_services = add_services;
        this.comfort = comfort;
        this.geometry = geometry;
    }

    public WorkTimeClass getWork_time() {
        return work_time;
    }

    public void setWork_time(WorkTimeClass work_time) {
        this.work_time = work_time;
    }

    public ArrayList<ContactsClass> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<ContactsClass> contacts) {
        this.contacts = contacts;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sale> sales) {
        this.sales = sales;
    }

    public ArrayList<ServicesOrderClass> getServices() {
        return services;
    }

    public void setServices(ArrayList<ServicesOrderClass> services) {
        this.services = services;
    }

    public ArrayList<ServicesOrderClass> getAdd_services() {
        return add_services;
    }

    public void setAdd_services(ArrayList<ServicesOrderClass> add_services) {
        this.add_services = add_services;
    }

    public ArrayList<ComfortClass> getComfort() {
        return comfort;
    }

    public void setComfort(ArrayList<ComfortClass> comfort) {
        this.comfort = comfort;
    }

    public GeometryClass getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryClass geometry) {
        this.geometry = geometry;
    }
}
