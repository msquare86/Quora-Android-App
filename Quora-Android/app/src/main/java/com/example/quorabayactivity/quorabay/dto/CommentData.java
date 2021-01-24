package com.example.quorabayactivity.quorabay.dto;

import com.example.quorabayactivity.quorabay.models.Comments;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentData {

    @SerializedName("commentId")
    private String commentId;

    @SerializedName("comments")
    private List<Comments> comments;

    @SerializedName("userId")
    private String userId;

    @SerializedName("answerId")
    private String answerId;

    @SerializedName("commentText")
    private String commentText;

    public String getCommentText() {
        return commentText;
    }
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
    public CommentData() {
    }
    public CommentData(String commentId, List<Comments> comments, String userId, String answerId) {
        this.commentId = commentId;
        this.comments = comments;
        this.userId = userId;
        this.answerId = answerId;
    }
    public String getCommentId() {
        return commentId;
    }
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
    public List<Comments> getComments() {
        return comments;
    }
    public void setComments(List<Comments> comments) {
        this.comments = comments;
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
