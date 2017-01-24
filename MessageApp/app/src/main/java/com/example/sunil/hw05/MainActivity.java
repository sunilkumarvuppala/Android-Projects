package com.example.sunil.hw05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().add(R.id.container,new LoginFragment(MainActivity.this),"Login").commit();
    }
    @Override
    public void onBackPressed() {

        if(getFragmentManager().getBackStackEntryCount()>0) {
            try {
                if (getFragmentManager().findFragmentByTag("ReadFragment").isVisible()) {
                    getFragmentManager().popBackStack();
                    //getFragmentManager().beginTransaction().replace(R.id.container,new AppFragment(),"AppFragment").addToBackStack(null).commit();
                    getFragmentManager().beginTransaction().replace(R.id.container,new InboxFragment(),"InboxFragment")
                            .addToBackStack(null)
                            .commit();



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


    /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }*/
}
