package com.example.sunil.hw03;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Sunil on 13-06-2016.
 */
public class GetAppDataAsyncTask extends AsyncTask<String, Void, ArrayList<App>> {
    Activity activity;
    public static final String APP_KEY="appObj";

    public GetAppDataAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<App> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int statusCode = connection.getResponseCode();
            if(statusCode==HttpURLConnection.HTTP_OK){
                Log.d("demo","connected successfully");
                InputStream inputStream = connection.getInputStream();


                return AppUtil.AppPullParser.parseApps(inputStream);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(final ArrayList<App> apps) {
        super.onPostExecute(apps);
        ArrayList<String> appNames = new ArrayList<>();
        ArrayList<String> devNames = new ArrayList<>();
        ArrayList<String> releaseDates = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        ArrayList<String> categories = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();
        if (apps != null){
            //Log.d("demo",apps.toString());
            for(App app:apps){
            //    final CustomAdapter customAdapter = new CustomAdapter(activity,app.getSmallImage(),app);
                appNames.add(app.getAppTitle());
                devNames.add(app.getDevName());
                releaseDates.add(app.getReleaseDate());
                prices.add(app.getPrice());
                categories.add(app.getCategory());
                images.add(app.getSmallImage());
            }
            ListView listView = (ListView) activity.findViewById(R.id.listView);
            final CustomAdapter adapter = new CustomAdapter(activity,R.layout.row_item_layout,appNames,devNames,releaseDates,prices,categories,images);
            listView.setAdapter(adapter);

            adapter.setNotifyOnChange(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(activity,PreviewActivity.class);
                    intent.putExtra(APP_KEY,apps.get(position));
                    activity.startActivity(intent);
                }
            });
        }
    }

}