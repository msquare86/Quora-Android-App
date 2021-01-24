package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class Comments {

    @SerializedName("commentId")
    private String commentId;

    @SerializedName("answerId")
    private String answerId;

    @SerializedName("userId")
    private String userId;

    @SerializedName("commentText")
    private String commentText;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "commentId='" + commentId + '\'' +
                ", answerId='" + answerId + '\'' +
                ", userId='" + userId + '\'' +
                ", commentText='" + commentText + '\'' +
                '}';
    }
}
