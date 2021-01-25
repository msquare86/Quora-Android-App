package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class CorporateDeatails {

	@SerializedName("corporateName")
	private String corporateName;

	@SerializedName("corporateId")
	private String corporateId;

	public void setCorporateName(String corporateName){
		this.corporateName = corporateName;
	}

	public String getCorporateName(){
		return corporateName;
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
			"CorporateName{" + 
			"corporateName = '" + corporateName + '\'' + 
			",corporateId = '" + corporateId + '\'' + 
			"}";
		}
}