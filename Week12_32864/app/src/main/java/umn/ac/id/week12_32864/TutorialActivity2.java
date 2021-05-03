package umn.ac.id.week12_32864;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class TutorialActivity2 extends AppCompatActivity implements SensorEventListener{
    private TextView tvLight;
    private TextView tvProximity;
    private Sensor sensorLight;
    private Sensor sensorProximity;
    private SensorManager mSensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial2);
        tvLight = findViewById(R.id.lightSensor);
        tvProximity = findViewById(R.id.proximitySensor);
        mSensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        sensorProximity =

                mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorLight =
                mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        float currentValue = event.values[0];
        switch(sensorType){
            case Sensor.TYPE_LIGHT:
                tvLight.setText("Light Sensor : " + String.format("%.2f",currentValue));
                break;
            case Sensor.TYPE_PROXIMITY:
                tvProximity.setText("Proximoty Sensor : " + String.format("%.2f",currentValue));
                break;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (sensorProximity != null) {
            mSensorManager.registerListener(this, sensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (sensorLight != null) {
            mSensorManager.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}