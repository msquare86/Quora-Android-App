package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class ReactionPK {

    @SerializedName("userId")
    private String userId;

    @SerializedName("answer")
    private Answers answer;

    public ReactionPK(String userId, Answers answer) {
        this.userId = userId;
        this.answer = answer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Answers getAnswer() {
        return answer;
    }

    public void setAnswer(Answers answer) {
        this.answer = answer;
    }
}
