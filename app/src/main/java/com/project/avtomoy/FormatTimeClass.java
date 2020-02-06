package com.project.avtomoy;

public class FormatTimeClass {
    private Integer approximateMinutes;
    private String approximateTime;

    public FormatTimeClass(Integer approximateMinutes, String approximateTime) {
        this.approximateMinutes = approximateMinutes;
        this.approximateTime = approximateTime;
    }

    public Integer getApproximateMinutes() {
        return approximateMinutes;
    }

    public void setApproximateMinutes(Integer approximateMinutes) {
        this.approximateMinutes = approximateMinutes;
    }

    public String getApproximateTime() {
        return approximateTime;
    }

    public void setApproximateTime(String approximateTime) {
        this.approximateTime = approximateTime;
    }
}
