package com.example.email.Dto;

public class EmailToAllDTO {
    private String subject;
    private String text;


    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
