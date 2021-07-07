package com.cindodcindy.vieroshoesadmin.view.model;

import java.util.HashMap;
import java.util.Map;

public class Message {

    public  String text;

    public Message() {

    }

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map toMap() {

        HashMap result = new HashMap<>();
        result.put("text", this.text);

        return result;
    }
}
