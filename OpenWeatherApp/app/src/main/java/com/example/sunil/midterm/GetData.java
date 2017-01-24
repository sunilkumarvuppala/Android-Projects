package com.example.sunil.midterm;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Sunil on 09-06-2016.
 */
public class GetData extends AsyncTask<String, Void, WeatherObj>  {
    ProgressDialog progressDialog;
    Activity activity;

    public GetData(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected WeatherObj doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String lines = reader.readLine();
                while (lines != null) {
                    stringBuilder.append(lines);
                    lines = reader.readLine();
                }
                return weatherObjectMaker.weatherJSONParser.parseWeather(stringBuilder + "");

            }else{
                Toast.makeText(activity, "Please enter valid input", Toast.LENGTH_SHORT).show();
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog= new ProgressDialog(activity);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading Please wait");
        progressDialog.show();

    }

    @Override
    protected void onPostExecute(WeatherObj weatherObj) {
        super.onPostExecute(weatherObj);

        if (weatherObj != null){

            Intent intent = new Intent(activity, ResultActivity.class);
            intent.putExtra("object", weatherObj);
            activity.startActivity(intent);
        }
        progressDialog.dismiss();
    }
}