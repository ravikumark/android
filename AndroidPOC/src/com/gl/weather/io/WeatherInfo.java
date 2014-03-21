package com.gl.weather.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gl.weather.activities.R;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class WeatherInfo extends AsyncTask <String, Void, JSONObject>{

    static InputStream is = null;

    static JSONObject jObj = null;
    static String json = "";

    View view;
    public WeatherInfo(View view) {
        this.view= view;
    }
    
	@Override
	protected JSONObject doInBackground(String... params) {
	
		String url= params[0];
		System.out.println(url);
	    // Making HTTP request
	    try {
	        // defaultHttpClient
	        DefaultHttpClient httpClient = new DefaultHttpClient();
	        HttpGet httpPost = new HttpGet(url);
	
	            HttpResponse getResponse = httpClient.execute(httpPost);
	           final int statusCode = getResponse.getStatusLine().getStatusCode();
	
	           if (statusCode != HttpStatus.SC_OK) { 
	              Log.w(getClass().getSimpleName(), 
	                  "Error " + statusCode + " for URL " + url); 
	              return null;
	           }
	
	           HttpEntity getResponseEntity = getResponse.getEntity();
	           is = getResponseEntity.getContent();            
	
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        Log.d("IO", e.getMessage().toString());
	        e.printStackTrace();
	
	    }
	
	    try {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(
	                is, "iso-8859-1"), 8);
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	        is.close();
	        json = sb.toString();
	    } catch (Exception e) {
	        Log.e("Buffer Error", "Error converting result " + e.toString());
	    }
	
	    // try parse the string to a JSON object
	    try {
	        jObj = new JSONObject(json);
	    } catch (JSONException e) {
	        Log.e("JSON Parser", "Error parsing data " + e.toString());
	    }
	
	    // return JSON String
	    return jObj;
	

	}
	protected void onPostExecute(JSONObject data)
	{   
		TextView  cityNameView = (TextView) view.findViewById(R.id.city_name_textview);
		TextView  temparatureView = (TextView) view.findViewById(R.id.temp_weather_imgview);
		
		JSONArray list = null;
		try {
			list = data.getJSONArray("list");
			
			int length = list.length();
			
			for(int i = 0; i< length; i++){
				if(i == 0){
					JSONObject place = list.getJSONObject(i);
					cityNameView.setText(place.getString("name"));
					temparatureView.setText(place.getJSONObject("main").getString("temp")+ "\u2103");
				}
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}   
}