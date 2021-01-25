package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class UserDetails {

    @SerializedName("userId")
    private String userId;

    @SerializedName("userName")
    private String userName;

    @SerializedName("ranking")
    private String ranking="Beginner";

    @SerializedName("profileType")
    private int profileType;//0-public, 1- private, 2- corporate

    @SerializedName("imageUrl")
    private String imageUrl;



}
