package com.project.avtomoy;

import com.project.avtomoy.ui.PriceClass;

public class ResponseServicesTimePriceClass {
    private TimeClass time;
    private PriceClass price;
    private FormatTimeClass formatTime;

    public ResponseServicesTimePriceClass(TimeClass time, PriceClass price, FormatTimeClass formatTime) {
        this.time = time;
        this.price = price;
        this.formatTime = formatTime;
    }

    public TimeClass getTime() {
        return time;
    }

    public void setTime(TimeClass time) {
        this.time = time;
    }

    public PriceClass getPrice() {
        return price;
    }

    public void setPrice(PriceClass price) {
        this.price = price;
    }

    public FormatTimeClass getFormatTime() {
        return formatTime;
    }

    public void setFormatTime(FormatTimeClass formatTime) {
        this.formatTime = formatTime;
    }
}
