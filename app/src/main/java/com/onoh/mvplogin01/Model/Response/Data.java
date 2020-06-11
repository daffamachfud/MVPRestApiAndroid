package com.onoh.mvplogin01.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("token")
    @Expose
    private Token token;
    @SerializedName("id_otp")
    @Expose
    private int id_otp;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public int getIdOtp() {
        return id_otp;
    }

    public  void setIdOtp(int id_otp) {
        this.id_otp = id_otp;
    }
}
