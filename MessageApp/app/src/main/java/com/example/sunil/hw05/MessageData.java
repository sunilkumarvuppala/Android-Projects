package com.example.sunil.hw05;

/**
 * Created by Sunil on 27-06-2016.
 */
public class MessageData {
    String sender, reciever, message;
    boolean isRead;

    public MessageData(String sender, String reciever, String message, boolean isRead) {
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
        this.isRead = isRead;
    }

    public MessageData() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public String toString() {
        return "MessageData{" +
                "sender='" + sender + '\'' +
                ", reciever='" + reciever + '\'' +
                ", message='" + message + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
