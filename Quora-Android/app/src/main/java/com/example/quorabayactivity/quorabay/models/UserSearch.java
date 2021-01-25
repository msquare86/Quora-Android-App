package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class UserSearch {

    @SerializedName("userId")
    private String userId;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("userName")
    private String userName;

    public UserSearch() {
    }

    public UserSearch(String userId, String imageUrl, String userName) {
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserSearch{" +
                "userId='" + userId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
