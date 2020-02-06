package com.project.avtomoy;

public class LoadGetAvaibleTimes {
    private Boolean error;
    private ResponseTimesClass response;

    public LoadGetAvaibleTimes(Boolean error, ResponseTimesClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ResponseTimesClass getResponse() {
        return response;
    }

    public void setResponse(ResponseTimesClass response) {
        this.response = response;
    }
}
