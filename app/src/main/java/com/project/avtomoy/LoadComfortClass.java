package com.project.avtomoy;

public class LoadComfortClass {
    private Boolean error;
    private ResponseComfortClass response;

    public LoadComfortClass(Boolean error, ResponseComfortClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ResponseComfortClass getResponse() {
        return response;
    }

    public void setResponse(ResponseComfortClass response) {
        this.response = response;
    }
}
