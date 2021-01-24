package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class Emoji {

    @SerializedName("id")
    private int id;

    @SerializedName("emojiText")
    private String emojiText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmojiText() {
        return emojiText;
    }

    public void setEmojiText(String emojiText) {
        this.emojiText = emojiText;
    }

    @Override
    public String toString() {
        return "Emoji{" +
                "id=" + id +
                ", emojiText='" + emojiText + '\'' +
                '}';
    }
}
