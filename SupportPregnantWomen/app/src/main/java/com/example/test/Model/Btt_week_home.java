package com.example.test.Model;



public class Btt_week_home {
    String No_week_home;
    int image_week_home;

    public Btt_week_home(String no_week_home) {
        No_week_home = no_week_home;
    }

    public String getNo_week_home() {
        return No_week_home;
    }

    public void setNo_week_home(String no_week_home) {
        No_week_home = no_week_home;
    }

    public int getImage_week_home() {
        return image_week_home;
    }

    public void setImage_week_home(int image_week_home) {
        this.image_week_home = image_week_home;
    }

    public Btt_week_home(String no_week_home, int image_week_home) {
        No_week_home = no_week_home;
        this.image_week_home = image_week_home;
    }
}
