package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class UserLevel{

	@SerializedName("level")
	private String level;

	@SerializedName("userName")
	private String userName;

	@SerializedName("userId")
	private String userId;

	public void setLevel(String level){
		this.level = level;
	}

	public String getLevel(){
		return level;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"UserLevel{" + 
			"level = '" + level + '\'' + 
			",userName = '" + userName + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}