package com.example.quorabayactivity.quorabay.models;

import com.google.gson.annotations.SerializedName;

public class CorporateId {

    @SerializedName("corporateId")
    private String corporateId;

    @SerializedName("userId")
    private String userId;

    public CorporateId() {
    }

    public CorporateId(String corporateId, String userId) {
        this.corporateId = corporateId;
        this.userId = userId;
    }

    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CorporateId{" +
                "corporateId='" + corporateId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
