package com.project.avtomoy;

import java.util.ArrayList;

public class ResponseChatClass {
    private ArrayList<ChatMessagesClass> chatMessages;

    public ResponseChatClass(ArrayList<ChatMessagesClass> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public ArrayList<ChatMessagesClass> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(ArrayList<ChatMessagesClass> chatMessages) {
        this.chatMessages = chatMessages;
    }
}
