package com.example.quorabayactivity.quorabay.dto;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.Date;

public class DataanalyticsPost {

    @SerializedName("postId")
    private String postId;

    @SerializedName("timeStamp")
    private Date timeStamp;

    @SerializedName("type")
    private String type = "text";

    @SerializedName("channelId")
    private int channelId = 1;

    @SerializedName("categoryName")
    private String categoryName;

    @SerializedName("action")
    private String action;

    public DataanalyticsPost(String postId, Date timeStamp, String type, int channelId, String categoryName, String action) {
        this.postId = postId;
        this.timeStamp = timeStamp;
        this.type = type;
        this.channelId = channelId;
        this.categoryName = categoryName;
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public DataanalyticsPost() {
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "DataanalyticsPost{" +
                "postId='" + postId + '\'' +
                ", timeStamp=" + timeStamp +
                ", type='" + type + '\'' +
                ", channelId=" + channelId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
