package com.dshliazhko.android.retrofit;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static UmoriliApi umoriliApi;
    private Retrofit retrofit;

    public static UmoriliApi getApi() {
        return umoriliApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")  //базовая часть адресса URL
                .addConverterFactory(GsonConverterFactory.create())  // конвертер необходимый для преобразования GSON в объекты
                .build();
        umoriliApi = retrofit.create(UmoriliApi.class); //  созжание объекта при помощи которого выполняем запросы

    }
}
