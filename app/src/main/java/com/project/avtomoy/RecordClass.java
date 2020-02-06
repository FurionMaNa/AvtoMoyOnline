package com.project.avtomoy;

public class RecordClass {
    private String date;
    private String time_start;
    private String car;

    public RecordClass(String date, String time_start, String car) {
        this.date = date;
        this.time_start = time_start;
        this.car = car;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
