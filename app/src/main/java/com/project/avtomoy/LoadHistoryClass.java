package com.project.avtomoy;

public class LoadHistoryClass {
    private Boolean error;
    private ResponseHistoryClass response;

    public LoadHistoryClass(Boolean error, ResponseHistoryClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ResponseHistoryClass getResponse() {
        return response;
    }

    public void setResponse(ResponseHistoryClass response) {
        this.response = response;
    }
}
