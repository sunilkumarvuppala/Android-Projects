package com.example.sunil.hw03;

/**
 * Created by spandanaravulapalli on 6/14/16.
 */
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sunil on 6/7/16.
 */
public class GetImage extends AsyncTask<String, Void, Bitmap>{
    Activity mActivity;

    public GetImage(Activity mActivity) {
        this.mActivity = mActivity;
    }

    protected Bitmap doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            Log.d("test", "test");
            Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            return  bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        ImageView imagaView = (ImageView) mActivity.findViewById(R.id.imageView);

        if(bitmap != null){
            Log.d("test","insidrcdfcgfv");
            imagaView.setImageBitmap(bitmap);
        }
    }


}