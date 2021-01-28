package com.example.quorabayactivity.quorabay.dto;

import com.example.quorabayactivity.quorabay.models.Answers;
import com.google.gson.annotations.SerializedName;

public class AnswerDto {

    @SerializedName("answers")
    private Answers answers;

    @SerializedName("likeCount")
    private int likeCount;

    @SerializedName("dilikeCount")
    private int dilikeCount;

    @SerializedName("userName")
    private String userName;

    public AnswerDto() {
    }

    public AnswerDto(Answers answers, int likeCount, int dilikeCount, String userName) {
        this.answers = answers;
        this.likeCount = likeCount;
        this.dilikeCount = dilikeCount;
        this.userName = userName;
    }

    public Answers getAnswers() {
        return answers;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDilikeCount() {
        return dilikeCount;
    }

    public void setDilikeCount(int dilikeCount) {
        this.dilikeCount = dilikeCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "AnswerDto{" +
                "answers=" + answers +
                ", likeCount=" + likeCount +
                ", dilikeCount=" + dilikeCount +
                ", userName='" + userName + '\'' +
                '}';
    }
}
