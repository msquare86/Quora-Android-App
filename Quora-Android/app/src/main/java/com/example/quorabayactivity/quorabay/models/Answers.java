package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class Answers {

    @SerializedName("answerId")
    private String answerId;

    @SerializedName("questionId")
    private String questionId;

    @SerializedName("userId")
    private String userId;

    @SerializedName("answerText")
    private String answerText;

    @SerializedName("isAccepted")
    private boolean isAccepted;

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
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

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    @Override
    public String toString() {
        return "Answers{" +
                "answerId='" + answerId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", userId='" + userId + '\'' +
                ", answerText='" + answerText + '\'' +
                ", isAccepted=" + isAccepted +
                '}';
    }
}
