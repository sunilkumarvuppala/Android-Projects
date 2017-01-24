package com.example.sunil.hw03;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    ListView listView;
    CustomAdapter adapter;
    public static final String APP_KEY="appObj";
    ProgressDialog progressDialog;

    public GetAppDataAsyncTask(Activity activity) {
        this.activity = activity;
    }

    //layout items
   // final ImageView star = (ImageView) activity.findViewById(R.id.imageStar);
    /*final ListView listView = (ListView) activity.findViewById(R.id.listView);
    TextView appName = (TextView) activity.findViewById(R.id.id_appName);
    TextView devName = (TextView)activity.findViewById(R.id.id_devName);
    TextView releaseDate = (TextView)activity.findViewById(R.id.id_releaseDate);
    TextView price = (TextView)activity.findViewById(R.id.id_price);
    TextView category = (TextView)activity.findViewById(R.id.id_category);*/

    //get id and image url

    boolean image_type = false;

    DatabaseDataManager databaseDataManager;

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
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(activity, R.style.AppTheme);
        progressDialog.setCancelable(false);
        // progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMax(100);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(final ArrayList<App> apps) {
        super.onPostExecute(apps);
     //   Log.d("data",apps.toString());


        final ArrayList<String> ids = new ArrayList<>();
        final ArrayList<String> appNames = new ArrayList<>();
        final ArrayList<String> devNames = new ArrayList<>();
        final ArrayList<String> releaseDates = new ArrayList<>();
        final ArrayList<String> prices = new ArrayList<>();
        final ArrayList<String> categories = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();
        final ArrayList<String> imgURL  = new ArrayList<>();
        ArrayList value ;

        if (apps != null){
            //Log.d("demo",apps.toString());

            for(App app:apps){
            //    final CustomAdapter customAdapter = new CustomAdapter(activity,app.getSmallImage(),app);
                ids.add(app.getId()); //get id of app
                appNames.add(app.getAppTitle());
                devNames.add(app.getDevName());
                releaseDates.add(app.getReleaseDate());
                prices.add(app.getPrice());
                categories.add(app.getCategory());
                images.add(app.getSmallImage());
                imgURL.add(app.getLargeImage()); //get url of large image
            }
           listView = (ListView) activity.findViewById(R.id.listView);
            adapter = new CustomAdapter(activity,R.layout.row_item_layout,ids,appNames,devNames,releaseDates,prices,categories,images,image_type);
            listView.setAdapter(adapter);

            adapter.setNotifyOnChange(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(activity,PreviewActivity.class);
                    intent.putExtra(APP_KEY,apps.get(position));
                    intent.putExtra("image",image_type);
                    activity.startActivity(intent);
                }
            });
        }
        progressDialog.dismiss();

        //On long tap list view

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("long", "inside long click");
                App app;
                databaseDataManager=new DatabaseDataManager(activity); //main activity context


                if(!image_type){ //if image is grey (not present in db)

                   // star.setImageResource(R.drawable.favoritesyellow);
                    databaseDataManager.saveApp(new App(ids.get(position),appNames.get(position).toString(),devNames.get(position).toString(),
                    releaseDates.get(position).toString(), prices.get(position).toString(),categories.get(position),imgURL.get(position))); //add to database  //add id and img url
                    image_type=true;
                    listView.setAdapter(adapter);
                    adapter.setNotifyOnChange(true);
                    Toast.makeText(activity, "Values inserted into db", Toast.LENGTH_SHORT).show();
                }
                else{ //if present in db
                 //   star.setImageResource(R.drawable.favoritesgrey);

                    databaseDataManager.deleteApp(apps.get(position)); // delete from database
                    listView.setAdapter(adapter);
                    adapter.setNotifyOnChange(true);
                    image_type=false;
                    Toast.makeText(activity, "Values deleted", Toast.LENGTH_SHORT).show();
                }


                return false;
            }
        });
    }

}