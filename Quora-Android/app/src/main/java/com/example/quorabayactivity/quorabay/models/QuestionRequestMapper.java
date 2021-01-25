package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionRequestMapper {

    @SerializedName("questionRequestList")
    List<QuestionRequest> questionRequestList;

    public QuestionRequestMapper() {
    }

    public QuestionRequestMapper(List<QuestionRequest> questionRequestList) {
        this.questionRequestList = questionRequestList;
    }

    public List<QuestionRequest> getQuestionRequestList() {
        return questionRequestList;
    }

    public void setQuestionRequestList(List<QuestionRequest> questionRequestList) {
        this.questionRequestList = questionRequestList;
    }

    @Override
    public String toString() {
        return "QuestionRequestMapper{" +
                "questionRequestList=" + questionRequestList +
                '}';
    }
}
