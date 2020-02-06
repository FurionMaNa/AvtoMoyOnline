package com.project.avtomoy;

public class TimeAndPriceClass {
    private Boolean error;
    private ResponseServicesTimePriceClass response;

    public TimeAndPriceClass(Boolean error, ResponseServicesTimePriceClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ResponseServicesTimePriceClass getResponse() {
        return response;
    }

    public void setResponse(ResponseServicesTimePriceClass response) {
        this.response = response;
    }
}
