package com.example.quorabayactivity.quorabay.dto;

import com.google.gson.annotations.SerializedName;

public class ErrorMessage {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorMessage(String message) {

        this.message = message;
    }
}
