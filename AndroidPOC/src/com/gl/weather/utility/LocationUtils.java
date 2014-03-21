package com.gl.weather.utility;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class LocationUtils extends Service implements LocationListener {
	
	protected LocationManager locationManager;
	protected LocationListener locationListener;
	protected Context context;
	String lat;
	String provider;
	protected double latitude, longitude; 
	protected boolean gps_enabled, network_enabled;
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		latitude = location.getLatitude();
		longitude = location.getLongitude();
		
		Toast.makeText(getApplicationContext(), latitude+" : "+longitude, Toast.LENGTH_LONG).show();
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

		Toast.makeText(getApplicationContext(), latitude+" : "+longitude, Toast.LENGTH_LONG).show();
		
		return super.onStartCommand(intent, flags, startId);
	}
	

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
