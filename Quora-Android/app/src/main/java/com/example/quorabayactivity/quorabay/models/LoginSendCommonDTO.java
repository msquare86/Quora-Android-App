package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class LoginSendCommonDTO {

    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;

    @SerializedName("channelId")
    private int channelId;

    @SerializedName("oauth")
    private int oauth;

    public int getOauth() {
        return oauth;
    }

    public void setOauth(int oauth) {
        this.oauth = oauth;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setChannelId(int channelId){
        this.channelId = channelId;
    }

    public int getChannelId(){
        return channelId;
    }

    @Override
    public String toString() {
        return "LoginSendCommonDTO{" +
                "password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", channelId=" + channelId +
                ", oauth=" + oauth +
                '}';
    }
}
