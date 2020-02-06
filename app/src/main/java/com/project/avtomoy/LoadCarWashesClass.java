package com.project.avtomoy;

public class LoadCarWashesClass {
    private Boolean error;
    private ResponseCarWashesFilterClass response;

    public LoadCarWashesClass(Boolean error, ResponseCarWashesFilterClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ResponseCarWashesFilterClass getResponse() {
        return response;
    }

    public void setResponse(ResponseCarWashesFilterClass response) {
        this.response = response;
    }
}
