package com.example.spandanaravulapalli.group12_inclass05;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CharSequence position;
    ProgressDialog progressDialog;
    AlertDialog.Builder builder;
    String keyword=null;
    //ProgressBar progressBar;
    final CharSequence[] Keyword  = {"UNCC", "Android", "Winter", "Aurora", "Wonders"};
    final CharSequence[] Keyword_url  = {"uncc", "android", "winter", "aurora", "wonders"};
    ImageView prev;
    ImageView next;
    int counter,count=0;

    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, isConnected()+"", Toast.LENGTH_SHORT).show();
        prev = (ImageView) findViewById(R.id.id_previous);
        next = (ImageView) findViewById(R.id.id_next);
        prev.setEnabled(false);
        next.setEnabled(false);

        final EditText editText = (EditText) findViewById(R.id.id_search);
        final Button button_go = (Button) findViewById(R.id.button_go);


        builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a Keyword");


        builder.setItems(Keyword, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                position = Keyword[which];
                editText.setText(position);
                keyword = (String) position;
                prev.setEnabled(true);
                next.setEnabled(true);
              //  Log.d("demo", "before task");

                new GetDataTask().execute("http://dev.theappsdr.com/apis/summer_2016/inclass5/index.php?keyword="+Keyword_url[which]);
Log.d("demo", "after task");
               // new GetImage(MainActivity.this).execute(arrayList.get(0));
            }
        });


        final AlertDialog alert = builder.create();

        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert.show();

            }
        });

    }

    private boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }

        return false;
    }

    private class GetDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader =  null;
            //      Log.d("demo", "first line do in background");
            try {
        //        Log.d("demo", "before url");
                URL url = new URL(params[0]);
         //       Log.d("demo",params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
            //    Log.d("demo","before bug=ffer");
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line="";
            ///    Log.d("demo", "url connected");
                while ((line=reader.readLine())!=null){
                    stringBuilder.append(line);
            //        Log.d("demo", "not null inside do background");
                }
                reader.close();
            //    Log.d("demo", "url connected!!!");
                return stringBuilder+"";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("demo", "onpost execuite");
            if (s != null) {
               // Log.d("demo", s);
                String[] list;
                list=s.split(";");
                for(String string: list){
                    if (!(string==list[0])){
                        arrayList.add(string);

                    }

                }
                new GetImage(MainActivity.this).execute(arrayList.get(0));
                Log.d("image",arrayList.get(0));
                counter = arrayList.size();

                prev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        count--;
                        if(count<0){
                            count=counter-1;
                        }
                        Log.d("image",arrayList.get(count));
                        progressDialog = new ProgressDialog(MainActivity.this, R.style.AppTheme);
                        progressDialog.setCancelable(false);
                       // progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialog.setMax(100);
                        progressDialog.show();

                        new GetImage(MainActivity.this).execute(arrayList.get(count));
                        progressDialog.dismiss();
                    }
                });
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        count++;
                        if(count>=counter){
                            count=0;
                        }
                        progressDialog = new ProgressDialog(MainActivity.this, R.style.AppTheme);
                        progressDialog.setCancelable(false);
                      //  progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                        progressDialog.setMax(100);
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialog.show();

                        new GetImage(MainActivity.this).execute(arrayList.get(count));
                        progressDialog.dismiss();
                    }

                });





            }
            else {
                Toast.makeText(MainActivity.this, "empty noticed", Toast.LENGTH_SHORT).show();
            }
        }
    }
    static public void display(Bitmap bitmap){


    }
}

