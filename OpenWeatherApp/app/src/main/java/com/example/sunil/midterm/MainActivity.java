package com.example.sunil.midterm;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
   static String city,unit;
    ToggleButton toggleButton;
   static EditText editText;
    TextView textView;
    static String Mtemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("MAD Weather!");
        unit = "metric";

        editText = (EditText)findViewById(R.id.editText_location);
        textView = (TextView) findViewById(R.id.textViewmetricSystem);
       // textView.setText("Use metric system");
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!(toggleButton.isChecked())) {
                    unit = "imperial";
                    Mtemp="Fahrenheit";
                }
                else if (toggleButton.isChecked()){
                    unit ="metric";
                    Mtemp="Celsius";
                }
            }
        });

        Button button = (Button) findViewById(R.id.button_checkWeather);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city=editText.getText().toString();
                Log.d("demo",city);
                Log.d("demo",unit);
                new GetData(MainActivity.this).execute("http://api.openweathermap.org/data/2.5/forecast/city?q="+city+"&units="+unit+"&APPID=3b2f72ae551e6b34baf0b4de5f31f78f");

            }
        });

    }
    public void onClick_tb(View view){
        if (!(toggleButton.isChecked())) {
            unit = "imperial";
        }
        else if (toggleButton.isChecked()){
            unit ="metric";
        }
    }
}
