package com.example.sunil.midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle(MainActivity.city);
        new GetImage(ResultActivity.this).execute("http://openweathermap.org/img/w/"+weatherObjectMaker.imag);
        TextView temp = (TextView) findViewById(R.id.textView_tempvalue);
        TextView humidity = (TextView) findViewById(R.id.textView_humiValue);
        TextView pressure = (TextView) findViewById(R.id.textView_presvalue);
        TextView weather = (TextView) findViewById(R.id.textView_weathValue);
        WeatherObj weatherObj = (WeatherObj) getIntent().getSerializableExtra("object");
        temp.setText(weatherObj.getTemperature()+"");
        humidity.setText(weatherObj.getHumidity()+"");
        pressure.setText(weatherObj.getPressure()+"");
        weather.setText(weatherObj.getWeather()+"");
        Log.d("demo",weatherObj.getTemperature()+""+MainActivity.Mtemp);
        Log.d("demo",weatherObj.getHumidity()+""+"%");
        Log.d("demo",weatherObj.getPressure()+""+"hPa");
        Log.d("demo",weatherObj.getWeather()+"");
    }
}
