package com.example.quorabayactivity.quorabay.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ReactionAnalytics {

    @SerializedName("postId")
    private String postId;

    @SerializedName("channelId")
    private int channelId=1;

    @SerializedName("userId")
    private String userId;

    @SerializedName("categoryName")
    private String categoryName;

    @SerializedName("date")
    private Date date;

    @SerializedName("action")
    private String action;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
