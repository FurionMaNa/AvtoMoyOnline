package com.project.avtomoy;

import java.util.ArrayList;

public class LoadResponseClass {
    private Boolean error;
    private String response;
    private ArrayList<AdvertClass> advert;

    public LoadResponseClass(Boolean error, String response, ArrayList<AdvertClass> advert) {
        this.error = error;
        this.response = response;
        this.advert = advert;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ArrayList<AdvertClass> getAdvert() {
        return advert;
    }

    public void setAdvert(ArrayList<AdvertClass> advert) {
        this.advert = advert;
    }
}
