package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class UserRegisterEntity {
    @SerializedName("areaOfInterests")
    private String areaOfInterests;

    @SerializedName("mobileNumber")
    private long mobileNumber;

    @SerializedName("bio")
    private String bio;

    @SerializedName("dateOfBirth")
    private String dateOfBirth;

    @SerializedName("profileImage")
    private String profileImage;

    @SerializedName("type")
    private int type;

    @SerializedName("userId")
    private String userId;

    @SerializedName("master")
    private boolean master;

    @SerializedName("timeStamp")
    private String timeStamp;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;

    @SerializedName("channelId")
    private int channelId;

    @SerializedName("email")
    private String email;

    @SerializedName("username")
    private String username;

    @SerializedName("notificationToken")
    private String notificationToken;

    public void setAreaOfInterests(String areaOfInterests){
        this.areaOfInterests = areaOfInterests;
    }

    public String getAreaOfInterests(){
        return areaOfInterests;
    }

    public void setMobileNumber(long mobileNumber){
        this.mobileNumber = mobileNumber;
    }

    public long getMobileNumber(){
        return mobileNumber;
    }

    public void setBio(String bio){
        this.bio = bio;
    }

    public String getBio(){
        return bio;
    }

    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth(){
        return dateOfBirth;
    }

    public void setProfileImage(String profileImage){
        this.profileImage = profileImage;
    }

    public String getProfileImage(){
        return profileImage;
    }

    public void setType(int type){
        this.type = type;
    }

    public int getType(){
        return type;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }

    public void setMaster(boolean master){
        this.master = master;
    }

    public boolean isMaster(){
        return master;
    }

    public void setTimeStamp(String timeStamp){
        this.timeStamp = timeStamp;
    }

    public String getTimeStamp(){
        return timeStamp;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setChannelId(int channelId){
        this.channelId = channelId;
    }

    public int getChannelId(){
        return channelId;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setNotificationToken(String notificationToken){
        this.notificationToken = notificationToken;
    }

    public String getNotificationToken(){
        return notificationToken;
    }

    @Override
    public String toString(){
        return
                "UserRegisterEntity{" +
                        "areaOfInterests = '" + areaOfInterests + '\'' +
                        ",mobileNumber = '" + mobileNumber + '\'' +
                        ",bio = '" + bio + '\'' +
                        ",dateOfBirth = '" + dateOfBirth + '\'' +
                        ",profileImage = '" + profileImage + '\'' +
                        ",type = '" + type + '\'' +
                        ",userId = '" + userId + '\'' +
                        ",master = '" + master + '\'' +
                        ",timeStamp = '" + timeStamp + '\'' +
                        ",password = '" + password + '\'' +
                        ",name = '" + name + '\'' +
                        ",channelId = '" + channelId + '\'' +
                        ",email = '" + email + '\'' +
                        ",username = '" + username + '\'' +
                        ",notificationToken = '" + notificationToken + '\'' +
                        "}";
    }
}
