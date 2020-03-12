package com.project.avtomoy;

public class SettingsPageClass {
    private Boolean error;
    private ResponseSetting response;

    public SettingsPageClass(Boolean error, ResponseSetting response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ResponseSetting getResponse() {
        return response;
    }

    public void setResponse(ResponseSetting response) {
        this.response = response;
    }
}
