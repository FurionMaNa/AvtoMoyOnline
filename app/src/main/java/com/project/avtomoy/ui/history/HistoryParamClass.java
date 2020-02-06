package com.project.avtomoy.ui.history;

import com.project.avtomoy.FeedbackAnswerClass;
import com.project.avtomoy.FeedbackClass;

import java.util.ArrayList;

public class HistoryParamClass {

    private ArrayList<HistoryClass> record;
    private ArrayList<FeedbackClass> feedback;
    private ArrayList<FeedbackAnswerClass> feedbackAnswer;
    private ArrayList<ArrayServicesClass> services;
    private ArrayList<ArrayServicesClass> additionServices;

    public HistoryParamClass(ArrayList<HistoryClass> record, ArrayList<FeedbackClass> feedback, ArrayList<FeedbackAnswerClass> feedbackAnswer, ArrayList<ArrayServicesClass> services, ArrayList<ArrayServicesClass> additionServices) {
        this.record = record;
        this.feedback = feedback;
        this.feedbackAnswer = feedbackAnswer;
        this.services = services;
        this.additionServices = additionServices;
    }

    public ArrayList<HistoryClass> getRecord() {
        return record;
    }

    public void setRecord(ArrayList<HistoryClass> record) {
        this.record = record;
    }

    public ArrayList<FeedbackClass> getFeedback() {
        return feedback;
    }

    public void setFeedback(ArrayList<FeedbackClass> feedback) {
        this.feedback = feedback;
    }

    public ArrayList<FeedbackAnswerClass> getFeedbackAnswer() {
        return feedbackAnswer;
    }

    public void setFeedbackAnswer(ArrayList<FeedbackAnswerClass> feedbackAnswer) {
        this.feedbackAnswer = feedbackAnswer;
    }

    public ArrayList<ArrayServicesClass> getServices() {
        return services;
    }

    public void setServices(ArrayList<ArrayServicesClass> services) {
        this.services = services;
    }

    public ArrayList<ArrayServicesClass> getAdditionServices() {
        return additionServices;
    }

    public void setAdditionServices(ArrayList<ArrayServicesClass> additionServices) {
        this.additionServices = additionServices;
    }
}
