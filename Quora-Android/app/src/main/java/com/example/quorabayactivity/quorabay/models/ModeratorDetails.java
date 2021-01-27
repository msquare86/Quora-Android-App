package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class ModeratorDetails {

	@SerializedName("ownerId")
	private String ownerId;

	@SerializedName("moderatorId")
	private String moderatorId;

	public String getOwnerId(){
		return ownerId;
	}

	public String getModeratorId(){
		return moderatorId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public void setModeratorId(String moderatorId) {
		this.moderatorId = moderatorId;
	}

	@Override
	public String toString() {
		return "ModeratorDetails{" +
				"ownerId='" + ownerId + '\'' +
				", moderatorId='" + moderatorId + '\'' +
				'}';
	}
}