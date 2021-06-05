package com.example.test.Model;

public class Nutrition {

    private String title1;
    private String title2;
    private String description;



    private boolean expanded;

    public Nutrition(String title1, String title2, String description){
        this.title1=title1;
        this.title2=title2;
        this.description=description;
        this.expanded=false;
    }
    public String getTitle1() {
        return title1;
    }

    public String getTitle2() {
        return title2;
    }

    public String getDescription() {
        return description;
    }
    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }


    @Override
    public String toString() {
        return "Nutrition{" +
                "title1='" + title1 + '\'' +
                ", title2='" + title2 + '\'' +
                ", description='" + description + '\'' +
                ", expanded=" + expanded +
                '}';
    }
}
