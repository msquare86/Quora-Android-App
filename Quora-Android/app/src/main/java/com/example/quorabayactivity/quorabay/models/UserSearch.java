package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class UserSearch {

    @SerializedName("userId")
    private String userId;

    public UserSearch(String userId, String profileImage, String userName) {
        this.userId = userId;
        this.profileImage = profileImage;
        this.userName = userName;
    }

    @SerializedName("profileImage")
    private String profileImage;

    @SerializedName("userName")
    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
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
                ", profileImage='" + profileImage + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
