package com.example.feedbacker.Feedbacker;

public class Feedback {
    private String text;
    private String name;

    public Feedback (String name, String text) {
        this.name = name;
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName() + '\n' + getText();
    }
}

