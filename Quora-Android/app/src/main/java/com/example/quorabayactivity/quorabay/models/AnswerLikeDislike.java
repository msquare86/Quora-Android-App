package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class AnswerLikeDislike {

    @SerializedName("userId")
    private String userId;

    @SerializedName("answerId")
    private String answerId;

    @SerializedName("reactionStatus")
    private boolean reactionStatus; // like - 1 , dislike - -1 , no - 0

    @SerializedName("emojiId")
    private int emojiId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public boolean isReactionStatus() {
        return reactionStatus;
    }

    public void setReactionStatus(boolean reactionStatus) {
        this.reactionStatus = reactionStatus;
    }

    public int getEmojiId() {
        return emojiId;
    }

    public void setEmojiId(int emojiId) {
        this.emojiId = emojiId;
    }

    @Override
    public String toString() {
        return "AnswerLikeDislike{" +
                "userId='" + userId + '\'' +
                ", answerId='" + answerId + '\'' +
                ", reactionStatus=" + reactionStatus +
                ", emojiId=" + emojiId +
                '}';
    }
}
