package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class UserProfileData {
    @SerializedName("userId")
    private String userId;

    @SerializedName("followerId")
    private String followerId;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("userName")
    private String userName;

    public UserProfileData() {
    }

    public UserProfileData(String userId, String followerId, String imageUrl, String userName) {
        this.userId = userId;
        this.followerId = followerId;
        this.imageUrl = imageUrl;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
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
        return "UserProfileData{" +
                "userId='" + userId + '\'' +
                ", followerId='" + followerId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
