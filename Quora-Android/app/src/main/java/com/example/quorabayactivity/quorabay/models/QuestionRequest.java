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
