package com.project.avtomoy;

public class ContactsClass {
    private Integer id;
    private Integer type;
    private String name;
    private String value;

    public ContactsClass(Integer id, Integer type, String name, String value) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.value = value;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
