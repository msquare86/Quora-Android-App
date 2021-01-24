package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("areaOfInterests")
	private String areaOfInterests;

	@SerializedName("mobileNumber")
	private String mobileNumber;

	@SerializedName("bio")
	private String bio;

	@SerializedName("dateOfBirth")
	private String dateOfBirth;

	@SerializedName("corporateEntity")
	private String corporateEntity;

	@SerializedName("profileImage")
	private String profileImage;

	@SerializedName("userName")
	private String userName;

	@SerializedName("type")
	private String type;

	@SerializedName("master")
	private String master;

	@SerializedName("password")
	private String password;

	@SerializedName("name")
	private String name;

	@SerializedName("email")
	private String email;

	@SerializedName("channelId")
	private String channelId;

	@SerializedName("notificationToken")
	private String notificationToken;

	public void setAreaOfInterests(String areaOfInterests){
		this.areaOfInterests = areaOfInterests;
	}

	public String getAreaOfInterests(){
		return areaOfInterests;
	}

	public void setMobileNumber(String mobileNumber){
		this.mobileNumber = mobileNumber;
	}

	public String getMobileNumber(){
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

	public void setCorporateEntity(String corporateEntity){
		this.corporateEntity = corporateEntity;
	}

	public String getCorporateEntity(){
		return corporateEntity;
	}

	public void setProfileImage(String profileImage){
		this.profileImage = profileImage;
	}

	public String getProfileImage(){
		return profileImage;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setMaster(String master){
		this.master = master;
	}

	public String getMaster(){
		return master;
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

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setChannelId(String channelId){
		this.channelId = channelId;
	}

	public String getChannelId(){
		return channelId;
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
			"User{" + 
			"areaOfInterests = '" + areaOfInterests + '\'' + 
			",mobileNumber = '" + mobileNumber + '\'' + 
			",bio = '" + bio + '\'' + 
			",dateOfBirth = '" + dateOfBirth + '\'' + 
			",corporateEntity = '" + corporateEntity + '\'' + 
			",profileImage = '" + profileImage + '\'' + 
			",userName = '" + userName + '\'' + 
			",type = '" + type + '\'' + 
			",master = '" + master + '\'' + 
			",password = '" + password + '\'' + 
			",name = '" + name + '\'' + 
			",email = '" + email + '\'' + 
			",channelId = '" + channelId + '\'' + 
			",notificationToken = '" + notificationToken + '\'' + 
			"}";
		}
}