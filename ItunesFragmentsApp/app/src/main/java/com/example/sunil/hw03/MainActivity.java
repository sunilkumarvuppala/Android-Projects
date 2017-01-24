package com.example.sunil.hw03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getFragmentManager().beginTransaction().add(R.id.container, new LoginFragment(MainActivity.this), "login").commit();

        //  new GetAppDataAsyncTask(MainActivity.this).execute("http://itunes.apple.com/us/rss/topgrossingapplications/limit=25/xml");

    }

    /*@Override
    public void onBackPressed() {

        if(getFragmentManager().getBackStackEntryCount() > 0){

        Log.d("back", "inside on back pressed");
            getFragmentManager().popBackStack();
        }
        else{
            super.onBackPressed();
        }

    }*/

    @Override
    public void onBackPressed() {

        if(getFragmentManager().getBackStackEntryCount()>0) {
            try {
                if (getFragmentManager().findFragmentByTag("preview").isVisible()) {
                    getFragmentManager().popBackStack();
                    //getFragmentManager().beginTransaction().replace(R.id.container,new AppFragment(),"AppFragment").addToBackStack(null).commit();

                    new GetAppDataAsyncTask(this).execute("http://itunes.apple.com/us/rss/topgrossingapplications/limit=25/xml");


                } else
                    getFragmentManager().popBackStack();
            }catch (NullPointerException e){
                getFragmentManager().popBackStack();
            }
        }
        else {
            super.onBackPressed();
        }
    }
}
