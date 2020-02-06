package com.project.avtomoy;

public class LoadContactsPageClass {
    private Boolean error;
    private ResponseContactClass response;

    public LoadContactsPageClass(Boolean error, ResponseContactClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ResponseContactClass getResponse() {
        return response;
    }

    public void setResponse(ResponseContactClass response) {
        this.response = response;
    }
}
