package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class QuestionRequest {

    @SerializedName("requestId")
    private String requestId;

    @SerializedName("categoryId")
    private String categoryId;

    @SerializedName("questionText")
    private String questionText;

    @SerializedName("corporateId")
    private String corporateId;

    @SerializedName("userId")
    private String userId;

    @SerializedName("status")
    private boolean status;

    public QuestionRequest() {
    }

    public QuestionRequest(String requestId, String categoryId, String questionText, String corporateId, String userId, boolean status) {
        this.requestId = requestId;
        this.categoryId = categoryId;
        this.questionText = questionText;
        this.corporateId = corporateId;
        this.userId = userId;
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "QuestionRequest{" +
                "requestId='" + requestId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", questionText='" + questionText + '\'' +
                ", corporateId='" + corporateId + '\'' +
                ", userId='" + userId + '\'' +
                ", status=" + status +
                '}';
    }
}
