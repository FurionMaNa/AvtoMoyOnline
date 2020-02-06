package com.project.avtomoy.ui.info.fragments.Sales;

public class LoadSalesClass {
    private Boolean error;
    private SalesClass response;

    public LoadSalesClass(Boolean error, SalesClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public SalesClass getResponse() {
        return response;
    }

    public void setResponse(SalesClass response) {
        this.response = response;
    }
}
