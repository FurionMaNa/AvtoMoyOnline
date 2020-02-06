package com.project.avtomoy.ui;

import com.project.avtomoy.AdvertRecordClass;

public class AdvetFinishClass {
    private Boolean error;
    private AdvertRecordClass response;

    public AdvetFinishClass(Boolean error, AdvertRecordClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public AdvertRecordClass getResponse() {
        return response;
    }

    public void setResponse(AdvertRecordClass response) {
        this.response = response;
    }
}
