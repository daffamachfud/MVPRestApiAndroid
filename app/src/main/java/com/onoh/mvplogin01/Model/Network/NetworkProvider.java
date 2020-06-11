package com.onoh.mvplogin01.Model.Network;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkProvider {

    public static Retrofit providesHttpAdapter(){
        return new Retrofit.Builder()
                .client(providesHttpClient())
                .baseUrl("http://dekaewallet.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient providesHttpClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(providesHttpLoggingInterceptor())
                .build();
    }

    private static Interceptor providesHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

}
