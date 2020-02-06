package com.project.avtomoy;

public class PeriodsClass {
    Boolean error;
    DatesClass response;

    public PeriodsClass(Boolean error, DatesClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public DatesClass getResponse() {
        return response;
    }

    public void setResponse(DatesClass response) {
        this.response = response;
    }
}
