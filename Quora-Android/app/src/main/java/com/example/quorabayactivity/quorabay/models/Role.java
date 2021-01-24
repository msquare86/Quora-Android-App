package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class Role{

	@SerializedName("roleId")
	private String roleId;

	@SerializedName("roleName")
	private String roleName;

	public void setRoleId(String roleId){
		this.roleId = roleId;
	}

	public String getRoleId(){
		return roleId;
	}

	public void setRoleName(String roleName){
		this.roleName = roleName;
	}

	public String getRoleName(){
		return roleName;
	}

	@Override
 	public String toString(){
		return 
			"Role{" + 
			"roleId = '" + roleId + '\'' + 
			",roleName = '" + roleName + '\'' + 
			"}";
		}
}