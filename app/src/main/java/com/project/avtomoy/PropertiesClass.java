package com.project.avtomoy;

public class PropertiesClass {
    private String balloonContentHeader;
    private String balloonContentBody;

    public PropertiesClass(String balloonContentHeader, String balloonContentBody) {
        this.balloonContentHeader = balloonContentHeader;
        this.balloonContentBody = balloonContentBody;
    }

    public String getBalloonContentHeader() {
        return balloonContentHeader;
    }

    public void setBalloonContentHeader(String balloonContentHeader) {
        this.balloonContentHeader = balloonContentHeader;
    }

    public String getBalloonContentBody() {
        return balloonContentBody;
    }

    public void setBalloonContentBody(String balloonContentBody) {
        this.balloonContentBody = balloonContentBody;
    }
}
