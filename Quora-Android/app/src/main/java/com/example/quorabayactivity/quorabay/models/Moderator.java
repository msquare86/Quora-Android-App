package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class Moderator{

	@SerializedName("moderatorName")
	private String moderatorName;

	@SerializedName("moderatorId")
	private String moderatorId;

	public void setModeratorName(String moderatorName){
		this.moderatorName = moderatorName;
	}

	public String getModeratorName(){
		return moderatorName;
	}

	public void setModeratorId(String moderatorId){
		this.moderatorId = moderatorId;
	}

	public String getModeratorId(){
		return moderatorId;
	}

	@Override
 	public String toString(){
		return 
			"Moderator{" + 
			"moderatorName = '" + moderatorName + '\'' + 
			",moderatorId = '" + moderatorId + '\'' + 
			"}";
		}
}