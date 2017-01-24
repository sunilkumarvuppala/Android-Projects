package com.example.spandanaravulapalli.group12_hw03;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

/**
 * Created by Sunil on 15-06-2016.
 */
public class Getdata extends AsyncTask<String, Void, List<App>> {
    Activity activity;
    int counter, count = 0;

    public Getdata(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected List<App> doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int statusCode  =  connection.getResponseCode();

            if(statusCode == HttpURLConnection.HTTP_OK){
                InputStream in = connection.getInputStream();
                return AppsUtil.AppPullParser.parseApps(in);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(final List<App> apps) {
        super.onPostExecute(apps);
        if (apps != null){
            Log.d("demo",apps.toString());
            final TextView textView_appTitle = (TextView) activity.findViewById(R.id.app_title);
            final TextView textView_devName = (TextView) activity.findViewById(R.id.dev_name);
            final TextView textView_releaseDate = (TextView) activity.findViewById(R.id.release_date);
            final TextView textView_price = (TextView) activity.findViewById(R.id.price);
            final ImageView imageView = (ImageView) activity.findViewById(R.id.imageView);
            ImageView image_prev = (ImageView)activity.findViewById(R.id.id_previous);
            ImageView image_next = (ImageView) activity.findViewById(R.id.id_next);

            textView_appTitle.setText(apps.get(0).getAppTitle());
            textView_devName.setText(apps.get(0).getDevName());
            textView_releaseDate.setText(apps.get(0).getReleaseDate());
            textView_price.setText(apps.get(0).getPrice()+"");
            counter=apps.size();

            //Picasso.with(activity).load(apps.get(0).getLargeImage()).into(imageView);
            Picasso.with(activity)
                    .load(apps.get(0).getLargeImage())
                    .resize(100, 100)
                    .centerCrop()
                    .into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse(apps.get(count).getUrl()));
                    activity.startActivity(viewIntent);
                }
            });

            image_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count++;
                    if(count>=counter){
                        count=0;
                    }
                    textView_appTitle.setText(apps.get(count).getAppTitle());
                    textView_devName.setText(apps.get(count).getDevName());
                    textView_releaseDate.setText(apps.get(count).getReleaseDate());
                    textView_price.setText(apps.get(count).getPrice()+"");
                    Picasso.with(activity)
                            .load(apps.get(count).getLargeImage())
                            .resize(100, 100)
                            .centerCrop()
                            .into(imageView);
                //    Picasso.with(activity).load(apps.get(count).getLargeImage()).into(imageView);

                }
            });

            image_prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count--;
                    if(count<0){
                        count=counter-1;
                    }
                    textView_appTitle.setText(apps.get(count).getAppTitle());
                    textView_devName.setText(apps.get(count).getDevName());
                    textView_releaseDate.setText(apps.get(count).getReleaseDate());
                    textView_price.setText(apps.get(count).getPrice()+"");
                  //  Picasso.with(activity).load(apps.get(count).getLargeImage()).into(imageView);
                    Picasso.with(activity)
                            .load(apps.get(count).getLargeImage())
                            .resize(100, 100)
                            .centerCrop()
                            .into(imageView);
                }
            });

        }
    }
}
