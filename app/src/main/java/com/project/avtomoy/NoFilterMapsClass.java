package com.project.avtomoy;

import java.util.ArrayList;

public class NoFilterMapsClass {
    private Boolean error;
    private ArrayList<ResponseNoFilterClass> response;

    public NoFilterMapsClass(Boolean error, ArrayList<ResponseNoFilterClass> response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<ResponseNoFilterClass> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<ResponseNoFilterClass> response) {
        this.response = response;
    }
}
