package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class UserDetails {

    @SerializedName("userId")
    private String userId;

    @SerializedName("userName")
    private String userName;

    @SerializedName("ranking")
    private String ranking="Beginner";

    @SerializedName("profileType")
    private int profileType;//0-public, 1- private, 2- corporate

    @SerializedName("imageUrl")
    private String imageUrl;

    public UserDetails() {
    }

    public UserDetails(String userId, String userName, String ranking, int profileType, String imageUrl) {
        this.userId = userId;
        this.userName = userName;
        this.ranking = ranking;
        this.profileType = profileType;
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public int getProfileType() {
        return profileType;
    }

    public void setProfileType(int profileType) {
        this.profileType = profileType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", ranking='" + ranking + '\'' +
                ", profileType=" + profileType +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
