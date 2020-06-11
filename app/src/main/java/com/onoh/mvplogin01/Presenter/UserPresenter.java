package com.onoh.mvplogin01.Presenter;

import android.content.Intent;
import android.widget.Toast;

import com.onoh.mvplogin01.Model.Login.Login;
import com.onoh.mvplogin01.Model.Login.LoginNumber;
import com.onoh.mvplogin01.Model.Network.NetworkProvider;
import com.onoh.mvplogin01.Model.Response.BaseApiService;
import com.onoh.mvplogin01.Model.Response.UserResponse;
import com.onoh.mvplogin01.Model.Utils.SharedPrefManager;
import com.onoh.mvplogin01.View.Activity.MainActivity;
import com.onoh.mvplogin01.View.Activity.PinActivity;
import com.onoh.mvplogin01.View.ILoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter implements IUserPresenter {

    private ILoginView loginView;

    public UserPresenter(ILoginView loginView){
        this.loginView = loginView;
    }




    @Override
    public void onLogin(String nomorTelepon, String pin) {
        Login login = new Login(nomorTelepon, pin);
        boolean isLoginSuccess = login.isValid();

        if(isLoginSuccess){
            NetworkProvider.providesHttpAdapter().create(BaseApiService.class).postLogin(nomorTelepon,pin)
                    .enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            if(response.isSuccessful()){
                                loginView.onHideLoading();
                                try{
                                    assert response.body() != null;
                                    if (response.body().getStatus() == 201 && !(response.body().getData()==null)){
                                        loginView.onLoginResult("Login Success",201);
                                    }else{
                                        loginView.onHideLoading();
                                        loginView.onLoginResult("PIN Anda Salah",401);
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                            }else{
                                loginView.onHideLoading();
                                loginView.onLoginResult("Gagal Koneksi",500);
                            }
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            loginView.onLoginResult(t.toString(),500);
                        }
                    });
        }else{
            loginView.onLoginResult("PIN Tidak boleh kosong",404);
        }
    }

    @Override
    public void onNumberLogin(String nomorTelepon) {
        loginView.onShowLoading();
        LoginNumber number = new LoginNumber(nomorTelepon);
        boolean isNumbervalid = number.isNumberValid();

        if(isNumbervalid){
            NetworkProvider.providesHttpAdapter().create(BaseApiService.class).post_check_mumber(nomorTelepon)
                    .enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            if(response.isSuccessful()){
                                loginView.onHideLoading();
                                try{
                                    assert response.body() != null;
                                    if (response.body().getStatus() == 201 ){
                                        loginView.onLoginResult("Nomor Terdaftar",201);
                                    }else{
                                        loginView.onHideLoading();
                                        loginView.onLoginResult("Nomor Tidak terdaftar",401);
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                            }else{
                                loginView.onHideLoading();
                                loginView.onLoginResult("Gagal Koneksi",500);
                            }
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            loginView.onLoginResult(t.toString(),500);
                        }
                    });
        }else{
            loginView.onLoginResult("Nomor Telepon tidak boleh kosong",404);
            loginView.onHideLoading();
        }
    }
}
