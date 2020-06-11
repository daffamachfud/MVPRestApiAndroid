package com.onoh.mvplogin01.View;

public interface ILoginView {
    void onLoginResult(String message,int status);

    void onShowLoading();
    void onHideLoading();
}
