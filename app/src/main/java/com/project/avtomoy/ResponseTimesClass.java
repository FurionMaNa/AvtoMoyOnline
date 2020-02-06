package com.project.avtomoy;

import java.util.ArrayList;

public class ResponseTimesClass {
    ArrayList<String> times;

    public ResponseTimesClass(ArrayList<String> times) {
        this.times = times;
    }

    public ArrayList<String> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<String> times) {
        this.times = times;
    }
}
