package com.example.brebner.sensorapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;
    private Sensor pressure;
    private Sensor gyro;
    private Sensor magno;
    private Sensor light;
    private Sensor temperature;
    private Sensor humidity;
    private TextView xAccelTextView, yAccelTextView, zAccelTextView;
    private TextView xGyroTextView, yGyroTextView, zGyroTextView;
    private TextView xMagnoTextView, yMagnoTextView, zMagnoTextView;
    private TextView lightTextView, pressureTextView, temperatureTextView, humidityTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // accelerometer
        xAccelTextView = (TextView) findViewById(R.id.textViewXvalue);
        yAccelTextView = (TextView) findViewById(R.id.textViewYvalue);
        zAccelTextView = (TextView) findViewById(R.id.textViewZvalue);
        // Gyroscope
        xGyroTextView = (TextView) findViewById(R.id.textViewXGyroValue);
        yGyroTextView = (TextView) findViewById(R.id.textViewYGyroValue);
        zGyroTextView = (TextView) findViewById(R.id.textViewZGyroValue);
        // Magnetometer
        xMagnoTextView = (TextView) findViewById(R.id.textViewXMagnoValue);
        yMagnoTextView = (TextView) findViewById(R.id.textViewYMagnoValue);
        zMagnoTextView = (TextView) findViewById(R.id.textViewZMagnoValue);
        // other single-value items
        lightTextView = (TextView) findViewById(R.id.textViewLightValue);
        pressureTextView = (TextView) findViewById(R.id.textViewPressureValue);
        temperatureTextView = (TextView) findViewById(R.id.textViewTemperatureValue);
        humidityTextView = (TextView) findViewById(R.id.textViewHumidityValue);
        // done
        Log.d(TAG, "onCreate: Initializing sensor services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // check for sensor support
        // accelerometer
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered accelerometer listener");
        }
        else {
            xAccelTextView.setText(R.string.sensor_not_supported);
            yAccelTextView.setText(R.string.sensor_not_supported);
            zAccelTextView.setText(R.string.sensor_not_supported);
        }
        // gyroscope
        gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyro != null) {
            sensorManager.registerListener(MainActivity.this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered gyro listener");
        }
        else {
            xGyroTextView.setText(R.string.sensor_not_supported);
            yGyroTextView.setText(R.string.sensor_not_supported);
            zGyroTextView.setText(R.string.sensor_not_supported);
        }
        // magnetometer
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magnetometer != null) {
            sensorManager.registerListener(MainActivity.this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered magnetometer listener");
        }
        else {
            xMagnoTextView.setText(R.string.sensor_not_supported);
            yMagnoTextView.setText(R.string.sensor_not_supported);
            zMagnoTextView.setText(R.string.sensor_not_supported);
        }
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (light != null) {
            sensorManager.registerListener(MainActivity.this, light, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered light listener");
        }
        else {
            lightTextView.setText(R.string.sensor_not_supported);
        }
        pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (pressure != null) {
            sensorManager.registerListener(MainActivity.this, pressure, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered pressure listener");
        }
        else {
            pressureTextView.setText(R.string.sensor_not_supported);
        }
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (temperature != null) {
            sensorManager.registerListener(MainActivity.this, temperature, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered temperature listener");
        }
        else {
            temperatureTextView.setText(R.string.sensor_not_supported);
        }
        humidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if (humidity != null) {
            sensorManager.registerListener(MainActivity.this, humidity, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered humidity listener");
        }
        else {
            humidityTextView.setText(R.string.sensor_not_supported);
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        switch (sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                xAccelTextView.setText(String.format(Locale.ENGLISH,"X: %+3.2f", event.values[0]));
                yAccelTextView.setText(String.format(Locale.ENGLISH,"Y: %+3.2f", event.values[1]));
                zAccelTextView.setText(String.format(Locale.ENGLISH,"Z: %+3.2f", event.values[2]));
                break;
            case Sensor.TYPE_GYROSCOPE:
                xGyroTextView.setText(String.format(Locale.ENGLISH,"X: %+3.2f", event.values[0]));
                yGyroTextView.setText(String.format(Locale.ENGLISH,"Y: %+3.2f", event.values[1]));
                zGyroTextView.setText(String.format(Locale.ENGLISH,"Z: %+3.2f", event.values[2]));
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                xMagnoTextView.setText(String.format(Locale.ENGLISH,"X: %+3.2f", event.values[0]));
                yMagnoTextView.setText(String.format(Locale.ENGLISH,"Y: %+3.2f", event.values[1]));
                zMagnoTextView.setText(String.format(Locale.ENGLISH,"Z: %+3.2f", event.values[2]));
                break;
            case Sensor.TYPE_LIGHT:
                lightTextView.setText(String.format(Locale.ENGLISH,"%+3.2f", event.values[0]));
                break;
            case Sensor.TYPE_PRESSURE:
                lightTextView.setText(String.format(Locale.ENGLISH,"%+3.2f", event.values[0]));
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                lightTextView.setText(String.format(Locale.ENGLISH,"%+3.2f", event.values[0]));
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                lightTextView.setText(String.format(Locale.ENGLISH,"%+3.2f", event.values[0]));
                break;
            default:
                Log.e(TAG, "Unknown sensor event!");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged - new accuracy is " + accuracy);
    }
}

