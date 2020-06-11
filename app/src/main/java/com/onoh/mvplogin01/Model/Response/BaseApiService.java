package com.onoh.mvplogin01.Model.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseApiService {
    // Fungsi ini untuk memanggil API localhost/api/login
    @FormUrlEncoded
    @POST("login")
    Call<UserResponse> postLogin(@Field("nomor_telepon") String nomor_telepon,
                                 @Field("password") String password);

    @FormUrlEncoded
    @POST("cek_nomor")
    Call<UserResponse> post_check_mumber(@Field("nomor_telepon") String nomor_telepon);

}
