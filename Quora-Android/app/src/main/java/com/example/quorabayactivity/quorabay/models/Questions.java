package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class Questions {

    @SerializedName("questionId")
    private String questionId;

    @SerializedName("userId")
    private String userId;

    @SerializedName("questionText")
    private String questionText;

    @SerializedName("date")
    private String date;

    @SerializedName("category")
    private Category category;

    @SerializedName("corporateId")
    private  String corporateId;

    public Questions() {
    }

    public Questions(String questionId, String userId, String questionText, String date, Category category, String corporateId) {
        this.questionId = questionId;
        this.userId = userId;
        this.questionText = questionText;
        this.date = date;
        this.category = category;
        this.corporateId = corporateId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "questionId='" + questionId + '\'' +
                ", userId='" + userId + '\'' +
                ", questionText='" + questionText + '\'' +
                ", date='" + date + '\'' +
                ", category=" + category +
                ", corporateId='" + corporateId + '\'' +
                '}';
    }
}
