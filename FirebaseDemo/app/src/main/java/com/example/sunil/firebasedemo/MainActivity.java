package com.example.spandanaravulapalli.firebasedemo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ListView lv;
    static String name=null;
    /*Button sunny;
    Button foggy;*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Apps Activity");

        tv = (TextView) findViewById(R.id.textView);
        lv = (ListView) findViewById(R.id.listView);
        name= (String) getIntent().getExtras().get("EMAIL");

        new GetAppDataAsyncTask(MainActivity.this).execute("https://itunes.apple.com/us/rss/topgrossingapplications/limit=25/xml");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        //databaseDataManager=new DatabaseDataManager(this);
        switch (item.getItemId()) {
            case R.id.overflow:
                Log.d("demo123", item.getTitle() + "");
                //    value= (ArrayList) databaseDataManager.getAllApps();


                break;
            case R.id.overflow2:
               // Log.d("demo123", item.getTitle() + "");
                new GetAppDataAsyncTask(MainActivity.this).execute("https://itunes.apple.com/us/rss/topgrossingapplications/limit=25/xml");


                break;
        }
        return true;
        //


    }


}
