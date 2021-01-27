package com.example.quorabayactivity.quorabay.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseMessage implements Serializable {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
