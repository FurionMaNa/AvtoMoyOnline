package com.project.avtomoy;

public class LoadCompleteClass {
    private Boolean error;
    private ResponseCompleteClass response;

    public LoadCompleteClass(Boolean error, ResponseCompleteClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ResponseCompleteClass getResponse() {
        return response;
    }

    public void setResponse(ResponseCompleteClass response) {
        this.response = response;
    }
}
