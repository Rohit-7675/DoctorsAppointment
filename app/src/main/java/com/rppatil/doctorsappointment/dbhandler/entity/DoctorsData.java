package com.rppatil.doctorsappointment.dbhandler.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class DoctorsData implements Serializable {
    @PrimaryKey
    private int userid;
    @ColumnInfo
    private String email;
    @ColumnInfo
    private String fname;
    @ColumnInfo
    private String doctor_name;
    @ColumnInfo
    private String lname;
    @ColumnInfo
    private String profile_image;
    @ColumnInfo
    private String doctor_street;
    @ColumnInfo
    private String doctor_city;
    @ColumnInfo
    private String doctor_state;
    @ColumnInfo
    private String doctor_zip;
    @ColumnInfo
    private String doctor_speciality;
    @ColumnInfo
    private String doctor_online_status;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getDoctor_street() {
        return doctor_street;
    }

    public void setDoctor_street(String doctor_street) {
        this.doctor_street = doctor_street;
    }

    public String getDoctor_city() {
        return doctor_city;
    }

    public void setDoctor_city(String doctor_city) {
        this.doctor_city = doctor_city;
    }

    public String getDoctor_state() {
        return doctor_state;
    }

    public void setDoctor_state(String doctor_state) {
        this.doctor_state = doctor_state;
    }

    public String getDoctor_zip() {
        return doctor_zip;
    }

    public void setDoctor_zip(String doctor_zip) {
        this.doctor_zip = doctor_zip;
    }

    public String getDoctor_speciality() {
        return doctor_speciality;
    }

    public void setDoctor_speciality(String doctor_speciality) {
        this.doctor_speciality = doctor_speciality;
    }

    public String getDoctor_online_status() {
        return doctor_online_status;
    }

    public void setDoctor_online_status(String doctor_online_status) {
        this.doctor_online_status = doctor_online_status;
    }

}
