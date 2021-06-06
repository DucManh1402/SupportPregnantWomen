package com.example.test.Model;

import android.media.Image;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Diary {
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("diary_id")
    private String diary_id;
    @SerializedName("title")
    private String title;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("content")
    private String content;
    @SerializedName("date_diary")
    private String date_diary;

    public Diary(String user_id, String diary_id, String title, String subtitle, String content, String date_diary) {
        this.user_id = user_id;
        this.diary_id = diary_id;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.date_diary = date_diary;
    }

    public Diary() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDiary_id() {
        return diary_id;
    }

    public void setDiary_id(String diary_id) {
        this.diary_id = diary_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate_diary() {
        return date_diary;
    }

    public void setDate_diary(String date_diary) {
        this.date_diary = date_diary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Diary(String user_id, String diary_id, String title, String subtitle, String content, String date_diary, Image image_diary, List<Diary> diaries) {
        this.user_id = user_id;
        this.diary_id = diary_id;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.date_diary = date_diary;
    }
}