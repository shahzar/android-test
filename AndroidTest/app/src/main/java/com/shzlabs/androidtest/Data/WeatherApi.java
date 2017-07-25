package com.shzlabs.androidtest.Data;

import com.shzlabs.androidtest.Data.Model.WeatherData;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shaz on 25/7/17.
 */

public interface WeatherApi {

    //    http://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b1b15e88fa797225412429c1c50c122a1
    String API_KEY = "73bdfae75f5fb972121c86bbdd7588eb";
    String BASE_URL = "http://samples.openweathermap.org/data/2.5/";

    @GET("weather/?appid=" + API_KEY)
    Call<WeatherData> getWeather(@Query("lat") int lat,
                                 @Query("lon") int lon);

    class Factory {
        private static WeatherApi service;
        public static WeatherApi getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                service = retrofit.create(WeatherApi.class);
                return service;
            }else{
                return service;
            }
        }
    }
}
