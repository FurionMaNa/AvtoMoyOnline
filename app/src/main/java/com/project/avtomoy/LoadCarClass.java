package com.project.avtomoy;

public class LoadCarClass {
    Boolean error;
    ResponseCarClass response;

    public LoadCarClass(Boolean error, ResponseCarClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ResponseCarClass getResponse() {
        return response;
    }

    public void setResponse(ResponseCarClass response) {
        this.response = response;
    }
}
