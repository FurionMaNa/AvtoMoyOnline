package com.project.avtomoy;

public class LoadChatClass {
    private Boolean error;
    private ResponseChatClass response;

    public LoadChatClass(Boolean error, ResponseChatClass response) {
        this.error = error;
        this.response = response;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ResponseChatClass getResponse() {
        return response;
    }

    public void setResponse(ResponseChatClass response) {
        this.response = response;
    }
}
