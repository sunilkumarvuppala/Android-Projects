package com.example.sunil.midterm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sunil on 09-06-2016.
 */
public class weatherObjectMaker {
    static String imag;
    static public class weatherJSONParser{
        static WeatherObj parseWeather (String string) throws JSONException {
            WeatherObj weatherObj = new WeatherObj();
            JSONObject root = new JSONObject(string);
         //   JSONObject city = root.getJSONObject("city");
            JSONArray list = root.getJSONArray("list");
            JSONObject objInsideList = list.getJSONObject(0);
            JSONArray forWeather = objInsideList.getJSONArray("weather");
            JSONObject main = objInsideList.getJSONObject("main");
            JSONObject desp = forWeather.getJSONObject(0);
             imag = desp.getString("icon");
            weatherObj.setWeather(desp.getString("description"));
            weatherObj.setTemperature(main.getDouble("temp"));
            weatherObj.setPressure(main.getDouble("pressure"));
            weatherObj.setHumidity(main.getDouble("humidity"));


            return weatherObj;
        }

    }
}
