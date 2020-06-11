package com.onoh.mvplogin01.Model.Login;

import android.text.TextUtils;

public class LoginNumber implements ILoginNumber {
    String nomorTeleponLogin;

    public LoginNumber(String nomorTeleponLogin){
        this.nomorTeleponLogin=nomorTeleponLogin;
    }


    @Override
    public String getNomorTeleponLogin() {
        return nomorTeleponLogin;
    }

    @Override
    public boolean isNumberValid() {
        return !TextUtils.isEmpty(getNomorTeleponLogin());
    }


}
