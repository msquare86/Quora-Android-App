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

    @SerializedName("date")
    private String date;

    public Comments(String commentId, String answerId, String userId, String commentText, String date) {
        this.commentId = commentId;
        this.answerId = answerId;
        this.userId = userId;
        this.commentText = commentText;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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
                ", date='" + date + '\'' +
                '}';
    }
}
