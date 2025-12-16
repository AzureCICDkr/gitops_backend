package com.example.demo.dto;

import java.util.Objects;

public class ChatMessage {


    private String name;
    private String sessionId;
    private String message;
    private String timeStamp;
    private String messageId;


    public ChatMessage(){}
    public ChatMessage(String name, String message){
        this.name = name;
        this.message = message;
    }


    @Override
    public String toString() {
        return "ChatMessage{" +
                "name='" + name + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", message='" + message + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", messageId='" + messageId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return Objects.equals(name, that.name) && Objects.equals(sessionId, that.sessionId) && Objects.equals(message, that.message) && Objects.equals(timeStamp, that.timeStamp) && Objects.equals(messageId, that.messageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sessionId, message, timeStamp, messageId);
    }

    public void setMessage(String message){this.message = message;}
    public String getMessage(){return this.message;}
    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}
    public void setTimestamp(String timeStamp){this.timeStamp = timeStamp;}
    public String getTimeStamp(){return this.timeStamp;}
    public void setMessageId(String messageId){this.messageId = messageId;}
    public String getMessageId(){return this.messageId;}
    public void setSessionId(String sessionId){this.sessionId = sessionId;};
    public String getSessionId(){return this.sessionId;}


    
}
