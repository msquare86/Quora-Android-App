package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class Ranking {

    @SerializedName("userId")
    private String userId;

    @SerializedName("points")
    private int points;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "userId='" + userId + '\'' +
                ", points=" + points +
                '}';
    }
}
