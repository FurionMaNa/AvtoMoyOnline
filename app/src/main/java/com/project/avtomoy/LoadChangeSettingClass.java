package com.project.avtomoy;

public class LoadChangeSettingClass {
    private Boolean error;
    private String response;

    public LoadChangeSettingClass(Boolean error, String response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
