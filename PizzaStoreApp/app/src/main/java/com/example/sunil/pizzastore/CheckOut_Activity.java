package com.example.sunil.pizzastore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CheckOut_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<String> names;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setLogo(R.drawable.app_icon);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
       // actionBar.set
        if (getIntent().getExtras() != null) {


            TextView tv = (TextView) findViewById(R.id.id_base);
            tv.setText("Base Price:              "+"6.5" + "$");

         //   toppingPrice = toppingPrice + 6.5;
            double toppingPrice = getIntent().getExtras().getDouble(MainActivity.PRICE_KEY);
            double sharedPrice = getIntent().getExtras().getDouble(MainActivity.SHAREDPRICE_KEY);
            TextView tv2 = (TextView) findViewById(R.id.id_Toppingtotal);

            //if(toppingPrice != 0){
                tv2.setText("Toppings:                   "+String.valueOf(toppingPrice + "$"));
            //}
            //else if(sharedPrice != 0){
                //tv2.setText("Toppings:                   "+String.valueOf(sharedPrice + "$"));
            //}

            boolean deliveryPrice = getIntent().getExtras().getBoolean(MainActivity.DELIVERY_KEY);

            TextView tv5 = (TextView)findViewById(R.id.textView_delivery);
            String toppings="";
            names = getIntent().getExtras().getStringArrayList(MainActivity.TOPPING_NAMES);
            TextView tv3 = (TextView) findViewById(R.id.id_toppingNames);
            if(names.size()!=0){
            toppings = names.get(0);
            for(String s:names){
                if(s== names.get(0)){

                }else
                toppings=toppings+","+s;
            }}else
            toppings="No toppings selected";

            tv3.setText(String.valueOf(toppings));
            TextView tv4 = (TextView)findViewById(R.id.textView_totalValue);
            if(deliveryPrice){
                toppingPrice = toppingPrice + 6.5 + 2;
                tv5.setText("Delivery Charge:        $2");
                tv4.setText(String.valueOf("TOTAL                   "+toppingPrice + "$"));
            }

            else{
                toppingPrice = toppingPrice + 6.5;
                tv5.setText("Delivery Charge:        $0");
                tv4.setText(String.valueOf("TOTAL                   "+toppingPrice + "$"));
            }




        }
        Button finish = (Button) findViewById(R.id.button_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckOut_Activity.this, MainActivity.class);
                MainActivity.res=0;
        //        MainActivity.tableLayout.removeAllViews();

                startActivity(intent);
            }
        });

    }
}
