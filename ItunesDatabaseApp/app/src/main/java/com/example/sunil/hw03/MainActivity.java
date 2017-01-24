package com.example.sunil.hw03;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
DatabaseDataManager databaseDataManager;
   static ArrayList value=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetAppDataAsyncTask(MainActivity.this).execute("https://itunes.apple.com/us/rss/topgrossingapplications/limit=100/xml");

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
databaseDataManager=new DatabaseDataManager(this);
        switch (item.getItemId()) {
            case R.id.overflow:
                Log.d("demo123", item.getTitle() + "");
            //    value= (ArrayList) databaseDataManager.getAllApps();
             Log.d("data",databaseDataManager.getAllApps()+"");

                break;
            case R.id.overflow2:
                Log.d("demo123", item.getTitle() + "");

                break;
        }
        return true;
        //


    }
}
