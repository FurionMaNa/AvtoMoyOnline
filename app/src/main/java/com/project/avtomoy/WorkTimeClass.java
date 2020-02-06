package com.project.avtomoy;

public class WorkTimeClass {
    private String Воскресенье;
    private String Понедельник;
    private String Вторник;
    private String Среда;
    private String Четверг;
    private String Пятница;
    private String Суббота;

    public WorkTimeClass(String воскресенье, String понедельник, String вторник, String среда, String четверг, String пятница, String суббота) {
        Воскресенье = воскресенье;
        Понедельник = понедельник;
        Вторник = вторник;
        Среда = среда;
        Четверг = четверг;
        Пятница = пятница;
        Суббота = суббота;
    }

    public String getВоскресенье() {
        return Воскресенье;
    }

    public void setВоскресенье(String воскресенье) {
        Воскресенье = воскресенье;
    }

    public String getПонедельник() {
        return Понедельник;
    }

    public void setПонедельник(String понедельник) {
        Понедельник = понедельник;
    }

    public String getВторник() {
        return Вторник;
    }

    public void setВторник(String вторник) {
        Вторник = вторник;
    }

    public String getСреда() {
        return Среда;
    }

    public void setСреда(String среда) {
        Среда = среда;
    }

    public String getЧетверг() {
        return Четверг;
    }

    public void setЧетверг(String четверг) {
        Четверг = четверг;
    }

    public String getПятница() {
        return Пятница;
    }

    public void setПятница(String пятница) {
        Пятница = пятница;
    }

    public String getСуббота() {
        return Суббота;
    }

    public void setСуббота(String суббота) {
        Суббота = суббота;
    }
}
