package com.project.avtomoy;

public class ComfortClass {
    private String car_washes_id;
    private Integer id;
    private DataClass data;

    public ComfortClass(String car_washes_id, Integer id, DataClass data) {
        this.car_washes_id = car_washes_id;
        this.id = id;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DataClass getData() {
        return data;
    }

    public void setData(DataClass data) {
        this.data = data;
    }
}
