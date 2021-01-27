package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class UserRegisterResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("jwt")
    private String jwt;

    @SerializedName("profileImage")
    private String profileImage;

    @SerializedName("userId")
    private String userId;

    @SerializedName("username")
    private String username;

    public void setCode(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public void setJwt(String jwt){
        this.jwt = jwt;
    }

    public String getJwt(){
        return jwt;
    }

    public void setProfileImage(String profileImage){
        this.profileImage = profileImage;
    }

    public String getProfileImage(){
        return profileImage;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    @Override
    public String toString(){
        return
                "UserRegisterResponse{" +
                        "code = '" + code + '\'' +
                        ",jwt = '" + jwt + '\'' +
                        ",profileImage = '" + profileImage + '\'' +
                        ",userId = '" + userId + '\'' +
                        ",username = '" + username + '\'' +
                        "}";
    }
}
