package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class Reaction {

    @SerializedName("reactionPK")
    private ReactionPK reactionPK;

    @SerializedName("emoji")
    private Emoji emoji;

    @SerializedName("reactionStatus")
    private  int reactionStatus;

    public Reaction(ReactionPK reactionPK, Emoji emoji, int reactionStatus) {
        this.reactionPK = reactionPK;
        this.emoji = emoji;
        this.reactionStatus = reactionStatus;
    }

    public ReactionPK getReactionPK() {

        return reactionPK;
    }

    public void setReactionPK(ReactionPK reactionPK) {
        this.reactionPK = reactionPK;
    }

    public Emoji getEmoji() {
        return emoji;
    }

    public void setEmoji(Emoji emoji) {
        this.emoji = emoji;
    }

    public int getReactionStatus() {
        return reactionStatus;
    }

    public void setReactionStatus(int reactionStatus) {
        this.reactionStatus = reactionStatus;
    }
}
