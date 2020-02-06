package com.project.avtomoy;

public class CarWashesClass {
    private String type;
    private String id;
    private GeometryClass geometry;
    private PropertiesClass properties;

    public CarWashesClass(String type, String id, GeometryClass geometry, PropertiesClass properties) {
        this.type = type;
        this.id = id;
        this.geometry = geometry;
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GeometryClass getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryClass geometry) {
        this.geometry = geometry;
    }

    public PropertiesClass getProperties() {
        return properties;
    }

    public void setProperties(PropertiesClass properties) {
        this.properties = properties;
    }
}
