package com.project.avtomoy.ui.info.fragments.Sales;

public class LoadIsFreeClass {
    private Boolean error;
    private IsFreeClass response;

    public LoadIsFreeClass(Boolean error, IsFreeClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public IsFreeClass getResponse() {
        return response;
    }

    public void setResponse(IsFreeClass response) {
        this.response = response;
    }
}
