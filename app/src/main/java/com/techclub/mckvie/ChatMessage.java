package com.techclub.mckvie;

import java.util.Date;

public class ChatMessage {
    private String messageText;
    private String messageUser;
    private String messageType;
    private String uid;
    private long messageTime;

    public ChatMessage(String messageText, String messageUser, String messageType, String uid) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.messageType = messageType;
        this.uid = uid;

        messageTime = new Date().getTime();
    }

    public ChatMessage() {
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public String  getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public void setUid(){this.uid = uid;};

    public String getUid() {return uid;};
}