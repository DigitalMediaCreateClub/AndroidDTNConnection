package com.example.procon2;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class SensorActivity extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mTemperature ;
    
    private static int temp;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mTemperature  = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mTemperature , SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


	@Override
	public void onSensorChanged(SensorEvent event) {
		 if (event.sensor.getType() != Sensor.TYPE_AMBIENT_TEMPERATURE) 
			 return;
		 else
			 Log.d("Temp",Integer.toString((int)mTemperature.getPower()));
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	
	public static int getTemp(){
		return temp;
	}



}
