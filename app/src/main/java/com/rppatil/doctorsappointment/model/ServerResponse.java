package com.rppatil.doctorsappointment.model;

import com.google.gson.annotations.SerializedName;
import com.rppatil.doctorsappointment.dbhandler.entity.DoctorsData;

import java.util.List;

public class ServerResponse {
    @SerializedName("status")
    public String status;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    private List<DoctorsData> mResult;

    public List<DoctorsData> getResult() {
        return mResult;
    }

    public void setResult(List<DoctorsData> result) {
        mResult = result;
    }
}
