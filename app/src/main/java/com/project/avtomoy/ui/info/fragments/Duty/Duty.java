package com.project.avtomoy.ui.info.fragments.Duty;

public class Duty {
    protected String nameText, priceText,moreText;

    public Duty(String nameText, String priceText){
        this.nameText = nameText;
        this.priceText = priceText;
    }

    public Duty(String nameText, String priceText,String moreText){
        this.nameText = nameText;
        this.priceText = priceText;
        this.moreText = moreText;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public String getPriceText() {
        return priceText;
    }

    public void setPriceText(String priceText) {
        this.priceText = priceText;
    }

    public String getMoreText() {
        return moreText;
    }

    public void setMoreText(String moreText) {
        this.moreText = moreText;
    }
}
