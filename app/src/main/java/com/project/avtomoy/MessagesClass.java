package com.project.avtomoy;

public class MessagesClass {
    private String car_washes;
    private FeedbackClass feedback;
    private RecordClass record;

    public MessagesClass(String car_washes, FeedbackClass feedback, RecordClass record) {
        this.car_washes = car_washes;
        this.feedback = feedback;
        this.record = record;
    }

    public String getCar_washes() {
        return car_washes;
    }

    public void setCar_washes(String car_washes) {
        this.car_washes = car_washes;
    }

    public FeedbackClass getFeedback() {
        return feedback;
    }

    public void setFeedback(FeedbackClass feedback) {
        this.feedback = feedback;
    }

    public RecordClass getRecord() {
        return record;
    }

    public void setRecord(RecordClass record) {
        this.record = record;
    }
}
