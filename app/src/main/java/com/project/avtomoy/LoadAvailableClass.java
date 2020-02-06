package com.project.avtomoy;

public class LoadAvailableClass {
    private Boolean error;
    private AvailableDateClass response;

    public LoadAvailableClass(Boolean error, AvailableDateClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public AvailableDateClass getResponse() {
        return response;
    }

    public void setResponse(AvailableDateClass response) {
        this.response = response;
    }
}
