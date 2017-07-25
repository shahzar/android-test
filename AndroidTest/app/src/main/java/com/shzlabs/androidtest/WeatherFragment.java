package com.shzlabs.androidtest;


import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shzlabs.androidtest.Data.Model.WeatherData;
import com.shzlabs.androidtest.Data.WeatherApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {


    private View rootView;
    private static final String TAG = WeatherFragment.class.getSimpleName();
    TextView location;
    TextView temperature;
    TextView weatherDescription;

    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_weather, container, false);

        location = (TextView) rootView.findViewById(R.id.location_name_text_view);
        temperature = (TextView) rootView.findViewById(R.id.temperature_text_view);
        weatherDescription = (TextView) rootView.findViewById(R.id.description_text_view);

        // Init with sample location
        getWeather(35, 139);

        return rootView;
    }

    /**
     * Get Weather via service
     * @param lat Location Latitude
     * @param lon Location Longitude
     */
    private void getWeather(int lat, int lon) {
        WeatherApi.Factory.getInstance().getWeather(lat, lon).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                WeatherData data = response.body();

                // Display data
                location.setText(data.getName());
                Double inCelsius = (data.getMain().getTemp() - 273.15);
                String temp = String.valueOf(inCelsius.intValue()) + "°C";
                temperature.setText(temp);
                weatherDescription.setText(data.getWeather().get(0).getDescription());
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Toast.makeText(getActivity(), "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
