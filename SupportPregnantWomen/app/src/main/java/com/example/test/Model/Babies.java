package com.example.test.Model;

import com.google.gson.annotations.SerializedName;

public class Babies {

    @SerializedName("baby_id")
    private String baby_id;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("name")
    private String name;
    @SerializedName("gender")
    private String gender;
    @SerializedName("tinh_trang_suc_khoe")
    private String tinh_trang_suc_khoe;

    public String getTinh_trang_suc_khoe() {
        return tinh_trang_suc_khoe;
    }

    public void setTinh_trang_suc_khoe(String tinh_trang_suc_khoe) {
        this.tinh_trang_suc_khoe = tinh_trang_suc_khoe;
    }

    public Babies(String user_id, String name, String tinh_trang_suc_khoe,String gender) {
        this.user_id = user_id;
        this.name = name;
        this.tinh_trang_suc_khoe = tinh_trang_suc_khoe;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Babies{" +
                "user_id='" + user_id + '\'' +
                ", tinh_trang_suc_khoe='" + tinh_trang_suc_khoe + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                +
                        '}';
    }
}
