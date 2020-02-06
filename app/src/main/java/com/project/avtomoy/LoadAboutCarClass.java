package com.project.avtomoy;

public class LoadAboutCarClass {
    private Boolean error;
    private ResponseAboutCarWashClass response;

    public LoadAboutCarClass(Boolean error, ResponseAboutCarWashClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ResponseAboutCarWashClass getResponse() {
        return response;
    }

    public void setResponse(ResponseAboutCarWashClass response) {
        this.response = response;
    }
}
