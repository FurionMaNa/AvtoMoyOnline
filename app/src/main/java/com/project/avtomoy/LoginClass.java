package com.project.avtomoy;

public class LoginClass {
    Boolean error;
    ResponseClass response;

    public LoginClass(Boolean error, ResponseClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ResponseClass getResponse() {
        return response;
    }

    public void setResponse(ResponseClass response) {
        this.response = response;
    }
}
