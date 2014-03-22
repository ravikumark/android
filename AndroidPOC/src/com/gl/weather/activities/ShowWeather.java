package com.gl.weather.activities;

import com.gl.weather.io.WeatherInfo;
import com.gl.weather.utility.LocationUtils;
import com.gl.weather.utility.WeatherAPI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ShowWeather extends Activity {
	
	View view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_weather);
		
		view = findViewById(android.R.id.content).getRootView();
		new WeatherInfo(view).execute(WeatherAPI.BASE_URI+WeatherAPI.CITY+"?lat=21.145800400000000000&lon=79.088154600000050000");
		Intent i = new Intent(ShowWeather.this, LocationUtils.class);
		startService(i);
		
	}
}
