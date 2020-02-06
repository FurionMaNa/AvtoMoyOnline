package com.project.avtomoy;

import java.util.ArrayList;

public class GetSignedRecordLoad {
    private Integer status;
    private Boolean error;
    private GetSignedRecord response;
    private ArrayList<AdvertClass> advert;
    private String address;
    private String name;

    public GetSignedRecordLoad(Integer status, Boolean error, GetSignedRecord response, ArrayList<AdvertClass> advert, String address, String name) {
        this.status = status;
        this.error = error;
        this.response = response;
        this.advert = advert;
        this.address = address;
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public GetSignedRecord getResponse() {
        return response;
    }

    public void setResponse(GetSignedRecord response) {
        this.response = response;
    }

    public ArrayList<AdvertClass> getAdvert() {
        return advert;
    }

    public void setAdvert(ArrayList<AdvertClass> advert) {
        this.advert = advert;
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
}
