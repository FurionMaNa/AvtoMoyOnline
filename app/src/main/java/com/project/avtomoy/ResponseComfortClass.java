package com.project.avtomoy;

import java.util.ArrayList;

public class ResponseComfortClass {
    ArrayList<ComfortClass> comfort;

    public ResponseComfortClass(ArrayList<ComfortClass> comfort) {
        this.comfort = comfort;
    }

    public ArrayList<ComfortClass> getComfort() {
        return comfort;
    }

    public void setComfort(ArrayList<ComfortClass> comfort) {
        this.comfort = comfort;
    }
}
