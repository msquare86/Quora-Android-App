package com.example.quorabayactivity.quorabay.dto;

import com.google.gson.annotations.SerializedName;

public class ResponseMessage {

    @SerializedName("message")
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
