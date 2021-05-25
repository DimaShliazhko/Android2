package com.dshliazhko.android.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UmoriliApi {
// @GET("/api.openweathermap.org/data/2.5/weather?c={city_name}&appid=01f7b7cdf0991c3d27fed9f5167a8ecb")
// Call<List<ModelWeather>> getCurentWeather (@Query("city_name") String resourceName);

    @GET("/data/2.5/find")
    Call<List<ModelWeather>> getCurentWeather(@Query("q") String resourceName, @Query("like") String like, @Query("appid") String api);

 //   @GET("/data/2.5/find?&type=like&APPID=01f7b7cdf0991c3d27fed9f5167a8ecb")
  //  Call<ModelWeather> getMainWeather(@Query("q") String s);

   // http://api.openweathermap.org/data/2.5/forecast?q=Minsk&appid=85e2768b27d91810236dfe385e51dd6b

    @GET("/data/2.5/forecast?&appid=85e2768b27d91810236dfe385e51dd6b")
    Call<ModelWeather> getMainWeather(@Query("q") String s);

}

