package com.project.avtomoy;

public class LoadServicesClass {
    private Boolean error;
    private ResponseServiceClass response;

    public LoadServicesClass(Boolean error, ResponseServiceClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ResponseServiceClass getResponse() {
        return response;
    }

    public void setResponse(ResponseServiceClass response) {
        this.response = response;
    }
}
