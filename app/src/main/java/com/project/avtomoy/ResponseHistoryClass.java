package com.project.avtomoy;

import java.util.ArrayList;

public class ResponseHistoryClass {
    private ArrayList<HistoryAllClass> history;
    private Integer totalPages;

    public ResponseHistoryClass(ArrayList<HistoryAllClass> history, Integer totalPages) {
        this.history = history;
        this.totalPages = totalPages;
    }

    public ArrayList<HistoryAllClass> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<HistoryAllClass> history) {
        this.history = history;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
