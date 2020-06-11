package com.onoh.mvplogin01.Model.Login;

import android.text.TextUtils;

public class Login implements ILogin {

    private String nomorTelepon, pin;

    public Login(String nomorTelepon, String pin){
        this.nomorTelepon = nomorTelepon;
        this.pin = pin;
    }


    @Override
    public String getNomorTelepon() {
        return nomorTelepon;
    }

    @Override
    public String getPin() {
        return pin;
    }

    @Override
    public boolean isValid() {
        //1.Cek apakah nomor telepon terisi
        //2.Cek apakah pin terisi
        //3.Cek apakah nomor telepon terdaftar
        return !TextUtils.isEmpty(getPin());
    }



}
