package com.gl.weather.activities;

import com.gl.weather.io.WeatherInfo;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

public class ShowWeather extends Activity {
	
	private TextView weatherView;
	View view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_weather);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		view = findViewById(android.R.id.content).getRootView();
		new WeatherInfo(view).execute("http://api.openweathermap.org/data/2.1/find/city?lat=55&lon=37&cnt=10");

		
	}
}
