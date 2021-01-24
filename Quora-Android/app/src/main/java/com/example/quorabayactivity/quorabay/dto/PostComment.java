package com.example.quorabayactivity.quorabay.dto;

import com.google.gson.annotations.SerializedName;

public class PostComment {

    @SerializedName("commentText")
    private String commentText;

    @SerializedName("parentId")
    private String parentId;

    @SerializedName("userId")
    private String userId;

    @SerializedName("answerId")
    private String answerId;

    public PostComment(String commentText, String parentId, String userId, String answerId) {
        this.commentText = commentText;
        this.parentId = parentId;
        this.userId = userId;
        this.answerId = answerId;
    }

    public PostComment() {
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }
}
