package com.example.test.Model;

import com.google.gson.annotations.SerializedName;

public class HandleError {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @SerializedName("message")
    private String message;
}
