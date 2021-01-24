package com.example.quorabayactivity.quorabay.dto;

import com.google.gson.annotations.SerializedName;

public class PostAnswer {

    @SerializedName("userId")
    private String userId;

    @SerializedName("content")
    private String content;

    @SerializedName("questionId")
    private String questionId;

    public PostAnswer(String userId, String content, String questionId) {
        this.userId = userId;
        this.content = content;
        this.questionId = questionId;
    }

    public String getUserId() {

        return userId;
    }

    public PostAnswer() {
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
