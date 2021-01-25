package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class FollowRequest {

    @SerializedName("id")
    private String id;

    @SerializedName("userId")
    private String userId;

    @SerializedName("followerId")
    private String followerId;

    public FollowRequest() {
    }

    public FollowRequest(String id, String userId, String followerId) {
        this.id = id;
        this.userId = userId;
        this.followerId = followerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "FollowRequest{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", followerId='" + followerId + '\'' +
                '}';
    }
}
