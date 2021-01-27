package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class DecodedJWTEntity {
    @SerializedName("exp")
    private int exp;

    @SerializedName("iat")
    private int iat;

    @SerializedName("userId")
    private String userId;

    @SerializedName("email")
    private String email;

    @SerializedName("channelId")
    private int channelId;

    @SerializedName("username")
    private String username;

    @SerializedName("profileImage")
    private String profileImage;

    @SerializedName("code")
    private int code;

    @SerializedName("type")
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setExp(int exp){
        this.exp = exp;
    }

    public int getExp(){
        return exp;
    }

    public void setIat(int iat){
        this.iat = iat;
    }

    public int getIat(){
        return iat;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
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

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    @Override
    public String toString() {
        return "DecodedJWTEntity{" +
                "exp=" + exp +
                ", iat=" + iat +
                ", userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", channelId=" + channelId +
                ", username='" + username + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", code=" + code +
                ", type=" + type +
                '}';
    }
}
