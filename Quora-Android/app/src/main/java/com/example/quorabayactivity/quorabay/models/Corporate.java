package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class Corporate{

	@SerializedName("role")
	private String role;

	@SerializedName("userId")
	private String userId;

	@SerializedName("corporateId")
	private String corporateId;

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setCorporateId(String corporateId){
		this.corporateId = corporateId;
	}

	public String getCorporateId(){
		return corporateId;
	}

	@Override
 	public String toString(){
		return 
			"Corporate{" + 
			"role = '" + role + '\'' + 
			",userId = '" + userId + '\'' + 
			",corporateId = '" + corporateId + '\'' + 
			"}";
		}
}