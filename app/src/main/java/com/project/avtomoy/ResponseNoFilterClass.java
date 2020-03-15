package com.project.avtomoy;

public class ResponseNoFilterClass {
    private String id;
    private String address;
    private String name;
    private GeometryClass geometry;

    public ResponseNoFilterClass(String id, String address, String name, GeometryClass geometry) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.geometry = geometry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public GeometryClass getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryClass geometry) {
        this.geometry = geometry;
    }
}
