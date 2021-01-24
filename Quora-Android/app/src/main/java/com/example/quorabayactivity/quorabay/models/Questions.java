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

    @SerializedName("categoryId")
    private String categoryId;

    @SerializedName("corporateId")
    private  String corporateId;

    public Questions() {
    }

    public Questions(String questionId, String userId, String questionText, String date, String categoryId, String corporateId) {
        this.questionId = questionId;
        this.userId = userId;
        this.questionText = questionText;
        this.date = date;
        this.categoryId = categoryId;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }
}
