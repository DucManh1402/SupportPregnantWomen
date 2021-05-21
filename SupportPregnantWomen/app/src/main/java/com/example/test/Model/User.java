package com.example.test.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User {
    @SerializedName("user_id")
    private String user_id;
    private String password;
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("chu_ki_kinh")
    private Integer chu_ki_kinh;
    @SerializedName("thoi_ki_cuoi_cung")
    private Date thoi_ki_cuoi_cung;
    @SerializedName("ngay_lao_dong")
    private Date ngay_lao_dong;
    @SerializedName("ngay_thu_thai")
    private Date ngay_thu_thai;
    @SerializedName("can_nang")
    private String can_nang;
    @SerializedName("chieu_cao")
    private String chieu_cao;
    @SerializedName("thoi_ki_thai_nghen")
    private String thoi_ki_thai_nghen;
    @SerializedName("phone")
    private String phone;
    @SerializedName("address")
    private String address;
    public User(String user_id, String name, String email) {
        this.user_id = user_id;
        this.email = email;

    }

    public User(String user_id, String name, String email, Integer chu_ki_kinh, Date thoi_ki_cuoi_cung, Date ngay_lao_dong, Date ngay_thu_thai, String can_nang, String chieu_cao, String thoi_ki_thai_nghen, String phone, String address) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.chu_ki_kinh = chu_ki_kinh;
        this.thoi_ki_cuoi_cung = thoi_ki_cuoi_cung;
        this.ngay_lao_dong = ngay_lao_dong;
        this.ngay_thu_thai = ngay_thu_thai;
        this.can_nang = can_nang;
        this.chieu_cao = chieu_cao;
        this.thoi_ki_thai_nghen = thoi_ki_thai_nghen;
        this.phone = phone;
        this.address = address;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getChu_ki_kinh() {
        return chu_ki_kinh;
    }

    public void setChu_ki_kinh(Integer chu_ki_kinh) {
        this.chu_ki_kinh = chu_ki_kinh;
    }

    public Date getThoi_ki_cuoi_cung() {
        return thoi_ki_cuoi_cung;
    }

    public void setThoi_ki_cuoi_cung(Date thoi_ki_cuoi_cung) {
        this.thoi_ki_cuoi_cung = thoi_ki_cuoi_cung;
    }

    public Date getNgay_lao_dong() {
        return ngay_lao_dong;
    }

    public void setNgay_lao_dong(Date ngay_lao_dong) {
        this.ngay_lao_dong = ngay_lao_dong;
    }

    public Date getNgay_thu_thai() {
        return ngay_thu_thai;
    }

    public void setNgay_thu_thai(Date ngay_thu_thai) {
        this.ngay_thu_thai = ngay_thu_thai;
    }

    public String getCan_nang() {
        return can_nang;
    }

    public void setCan_nang(String can_nang) {
        this.can_nang = can_nang;
    }

    public String getChieu_cao() {
        return chieu_cao;
    }

    public void setChieu_cao(String chieu_cao) {
        this.chieu_cao = chieu_cao;
    }

    public String getThoi_ki_thai_nghen() {
        return thoi_ki_thai_nghen;
    }

    public void setThoi_ki_thai_nghen(String thoi_ki_thai_nghen) {
        this.thoi_ki_thai_nghen = thoi_ki_thai_nghen;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", hoten='" + name + '\'' +
                ", email='" + email + '\'' +
                +
                        '}';
    }
}
