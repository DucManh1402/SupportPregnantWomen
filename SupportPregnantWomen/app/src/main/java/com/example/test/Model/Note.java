package com.example.test.Model;

import android.media.Image;

import com.google.gson.annotations.SerializedName;

public class Note {
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("diary_id")
    private String diary_id;
    @SerializedName("content")
    private String content;
    @SerializedName("date_diary")
    private String date_diary;
    @SerializedName("image_diary")
    private Image image_diary;

    public Note(String user_id, String diary_id, String content, String date_diary, Image image_diary) {
        this.user_id = user_id;
        this.diary_id = diary_id;
        this.content = content;
        this.date_diary = date_diary;
        this.image_diary = image_diary;
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

    public Image getImage_diary() {
        return image_diary;
    }

    public void setImage_diary(Image image_diary) {
        this.image_diary = image_diary;
    }
}
