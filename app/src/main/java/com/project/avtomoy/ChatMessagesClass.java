package com.project.avtomoy;

public class ChatMessagesClass {
    private MessagesClass messages;

    public ChatMessagesClass(MessagesClass messages) {
        this.messages = messages;
    }

    public MessagesClass getMessages() {
        return messages;
    }

    public void setMessages(MessagesClass messages) {
        this.messages = messages;
    }
}
