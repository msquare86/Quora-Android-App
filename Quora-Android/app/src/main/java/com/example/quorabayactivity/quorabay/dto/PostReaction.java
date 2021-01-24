package com.example.quorabayactivity.quorabay.dto;

import com.google.gson.annotations.SerializedName;

public class PostReaction {

    @SerializedName("reactionStatus")
    private int reactionStatus;

    @SerializedName("userId")
    private String userId;

    @SerializedName("answerId")
    private String answerId;

    public int getReactionStatus() {
        return reactionStatus;
    }

    public void setReactionStatus(int reactionStatus) {
        this.reactionStatus = reactionStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PostReaction() {
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public PostReaction(int reactionStatus, String userId, String answerId) {
        this.reactionStatus = reactionStatus;
        this.userId = userId;
        this.answerId = answerId;
    }
}
