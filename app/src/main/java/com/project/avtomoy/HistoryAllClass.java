package com.project.avtomoy;

import com.project.avtomoy.ui.history.HistoryClass;

import java.util.ArrayList;

public class HistoryAllClass {
    private String carWashName;
    private HistoryClass record;
    private FeedbackClass feedback;
    private FeedbackClass feedbackAnswer;
    private ArrayList<ServicesClass> services;
    private ArrayList<ServicesClass> additionServices;
    private String feedback_datetime;
    private String feedbackAnswer_datetime;

    public HistoryAllClass(String carWashName, HistoryClass record, FeedbackClass feedback, FeedbackClass feedbackAnswer, ArrayList<ServicesClass> services, ArrayList<ServicesClass> additionServices, String feedback_datetime, String feedbackAnswer_datetime) {
        this.carWashName = carWashName;
        this.record = record;
        this.feedback = feedback;
        this.feedbackAnswer = feedbackAnswer;
        this.services = services;
        this.additionServices = additionServices;
        this.feedback_datetime = feedback_datetime;
        this.feedbackAnswer_datetime = feedbackAnswer_datetime;
    }

    public String getCarWashName() {
        return carWashName;
    }

    public void setCarWashName(String carWashName) {
        this.carWashName = carWashName;
    }

    public HistoryClass getRecord() {
        return record;
    }

    public void setRecord(HistoryClass record) {
        this.record = record;
    }

    public FeedbackClass getFeedback() {
        return feedback;
    }

    public void setFeedback(FeedbackClass feedback) {
        this.feedback = feedback;
    }

    public FeedbackClass getFeedbackAnswer() {
        return feedbackAnswer;
    }

    public void setFeedbackAnswer(FeedbackClass feedbackAnswer) {
        this.feedbackAnswer = feedbackAnswer;
    }

    public ArrayList<ServicesClass> getServices() {
        return services;
    }

    public void setServices(ArrayList<ServicesClass> services) {
        this.services = services;
    }

    public ArrayList<ServicesClass> getAdditionServices() {
        return additionServices;
    }

    public void setAdditionServices(ArrayList<ServicesClass> additionServices) {
        this.additionServices = additionServices;
    }

    public String getFeedback_datetime() {
        return feedback_datetime;
    }

    public void setFeedback_datetime(String feedback_datetime) {
        this.feedback_datetime = feedback_datetime;
    }

    public String getFeedbackAnswer_datetime() {
        return feedbackAnswer_datetime;
    }

    public void setFeedbackAnswer_datetime(String feedbackAnswer_datetime) {
        this.feedbackAnswer_datetime = feedbackAnswer_datetime;
    }
}
