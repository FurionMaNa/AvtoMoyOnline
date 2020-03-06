package com.project.avtomoy;

public class WorkTimeClass {
    private String Воскресенье;
    private String Понедельник;
    private String Вторник;
    private String Среда;
    private String Четверг;
    private String Пятница;
    private String Суббота;

    public WorkTimeClass(String sunday, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday) {
        Воскресенье = sunday;
        Понедельник = monday;
        Вторник = tuesday;
        Среда = wednesday;
        Четверг = thursday;
        Пятница = friday;
        Суббота = saturday;
    }

    public String getВоскресенье() {
        return Воскресенье;
    }

    public void setВоскресенье(String sunday) {
        Воскресенье = sunday;
    }

    public String getПонедельник() {
        return Понедельник;
    }

    public void setПонедельник(String monday) {
        Понедельник = monday;
    }

    public String getВторник() {
        return Вторник;
    }

    public void setВторник(String tuesday) {
        Вторник = tuesday;
    }

    public String getСреда() {
        return Среда;
    }

    public void setСреда(String wednesday) {
        Среда = wednesday;
    }

    public String getЧетверг() {
        return Четверг;
    }

    public void setЧетверг(String thursday) {
        Четверг = thursday;
    }

    public String getПятница() {
        return Пятница;
    }

    public void setПятница(String friday) {
        Пятница = friday;
    }

    public String getСуббота() {
        return Суббота;
    }

    public void setСуббота(String saturday) {
        Суббота = saturday;
    }
}
