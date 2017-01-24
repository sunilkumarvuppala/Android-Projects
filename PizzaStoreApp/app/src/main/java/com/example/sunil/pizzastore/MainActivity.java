package com.example.sunil.pizzastore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    AlertDialog.Builder builder;
    ImageView imageView_topping,imageView_topping1;
    CharSequence position;

    double price, sharedPrice;
    static boolean delivery;
    final static String PRICE_KEY="price";
    final static String SHAREDPRICE_KEY = "sharedprice";
    final static String VALUES_KEY="toppings";
    final static String TOPPING_NAMES="names";
    final static String DELIVERY_KEY ="Delivery";
    final static String SHARED = "Shared Preferences";
    static LinearLayout layout1,layout2;
    SharedPreferences sharedpreferences;
    final LinearLayout.LayoutParams layoutParams  = new LinearLayout.LayoutParams(150,150);
    public static final String MyPREFERENCES = "MyPrefs" ;
    Button button_checkOut;
    ProgressBar progressBar;
    static int res;
    CheckBox checkBox_delivery,checkBox_load;
    static Map<String, ?> keys;

    final ArrayList<String> array_toppings = new ArrayList<>();
    CharSequence[] addTopping  = {"Bacon", "Cheese", "Garlic", "Green Pepper", "Mushroom", "Olives", "Onions", "Red Pepper"};
    final int[] drawables = {R.drawable.bacon,
            R.drawable.cheese,
            R.drawable.garlic,
            R.drawable.green_pepper,
            R.drawable.mushroom,
            R.drawable.olives,
            R.drawable.onion,
            R.drawable.red_pepper,
            R.drawable.tomato,

                        };
    int[] images;
    ArrayList<String> imag = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relative);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setLogo(R.drawable.app_icon);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
       // actionBar.setHomeAsUpIndicator(R.drawable.app_icon);
       // actionBar.setDisplayHomeAsUpEnabled(true);


        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        sharedpreferences = getSharedPreferences(SHARED, MODE_PRIVATE);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        layout1= (LinearLayout)findViewById(R.id.layout1);
        layout2=(LinearLayout) findViewById(R.id.layout2);
        final LinearLayout.LayoutParams layoutParams  = new LinearLayout.LayoutParams(80, 80);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a Topping");
        final TextView textView = new TextView(this);
        builder.setItems(addTopping, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, final int which) {
                button_checkOut.setEnabled(true);
                position = addTopping[which];
                progressBar.setProgress(progressBar.getProgress()+1);

                //Toast.makeText(MainActivity.this,""+which+ position, Toast.LENGTH_SHORT).show();

                //imageView_topping = new ImageView(MainActivity.this);
              //  imageView_topping.setTag(res);
                //res++;
                imag.add(which+"");

//                Log.d("demoInside",imageView_topping.getTag()+"");
                layout1.removeAllViews();
                layout2.removeAllViews();
                array_toppings.add(position+"");
                layouts();
    //
            }

        });
        final AlertDialog alert = builder.create();

        final Button addTopping_button = (Button) findViewById(R.id.button_addtopping);
        addTopping_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(!(progressBar.getProgress()>=10)) {

                    alert.show();

              //      Toast.makeText(MainActivity.this, ""+progressBar.getProgress()+"", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this, "Maximum toppings added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_checkOut = (Button) findViewById(R.id.button_checkout);
        button_checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if(array_toppings.size() != 0){
                    price=array_toppings.size()*1.5;
                    //Toast.makeText(MainActivity.this, "this is array_toppings", Toast.LENGTH_SHORT).show();

                    if(!checkBox_delivery.isChecked()){
                        delivery=false;
                    }
                    Intent intent = new Intent(MainActivity.this,CheckOut_Activity.class);
                    intent.putExtra(PRICE_KEY,price);
                    intent.putExtra(TOPPING_NAMES,array_toppings);
                    intent.putExtra(DELIVERY_KEY,delivery);
                    //  intent.putExtra(SHARED,editor+"");
                    editor.clear();
                    editor.putString("deliver",delivery+"");
                    Log.d("demokey",delivery+"");
                    for(int i=1; i <= imag.size(); i++){
                        editor.putString(VALUES_KEY+i , imag.get(i-1)+"");
                        Log.d("demokey",VALUES_KEY+i+" "+imag.get(i-1));
                        editor.commit();
                    }

                    startActivity(intent);
                //}

                /*else if(keys.size() != 0){
                    sharedPrice = (keys.size() - 1 ) * 1.5;
                    Toast.makeText(MainActivity.this, "this is shared price", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "key size is: " +keys.size(), Toast.LENGTH_SHORT).show();

                    if(!checkBox_delivery.isChecked()){
                        delivery=false;
                    }
                    Intent intent = new Intent(MainActivity.this,CheckOut_Activity.class);
                    intent.putExtra(SHAREDPRICE_KEY,sharedPrice);
                    intent.putExtra(TOPPING_NAMES,array_toppings);
                    intent.putExtra(DELIVERY_KEY,delivery);
                    //  intent.putExtra(SHARED,editor+"");
                    editor.clear();
                    editor.putString("deliver",delivery+"");
                    Log.d("demokey",delivery+"");
                    for(int i=1; i <= imag.size(); i++){
                        editor.putString(VALUES_KEY+i , imag.get(i-1)+"");
                        Log.d("demokey",VALUES_KEY+i+" "+imag.get(i-1));
                        editor.commit();
                    }

                    startActivity(intent);
                }*/

            }
        });

        checkBox_delivery = (CheckBox) findViewById(R.id.checkBox_Delivery);
        checkBox_load = (CheckBox) findViewById(R.id.checkBox_loadPrevious);


        checkBox_load.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                imag.clear();
                array_toppings.clear();
                if (checkBox_load.isChecked()) {
                    /*Map<String, ?>*/ keys = sharedpreferences.getAll();
                    Log.d("keysize",keys.size()+"");
                    if(keys.size() != 0){

                        for (Map.Entry<String, ?> entry : keys.entrySet()) {
                            Log.d("map values", entry.getKey() + ": " +
                                    entry.getValue().toString());
                            if (!(entry.getValue().toString().equals("[]")))
                                if(!(entry.getKey().equals("deliver")))
                                    imag.add(entry.getValue().toString());
                            if (entry.getValue().equals("true"))
                                checkBox_delivery.setChecked(true);

                        }
                        progressBar.setProgress(progressBar.getProgress()+imag.size());
                        for(int i=0; i<imag.size(); i++){

                            array_toppings.add(""+addTopping[Integer.parseInt(imag.get(i))]);
                        }


                        layouts();

                    }
                    else {
                        Toast.makeText(MainActivity.this, "No previous order found", Toast.LENGTH_SHORT).show();
                        button_checkOut.setEnabled(false);
                    }

                    //  Toast.makeText(MainActivity.this, keys+"", Toast.LENGTH_SHORT).show();
                }
                else {
                    imag.clear();
                    layout1.removeAllViews();
                    layout2.removeAllViews();
                    checkBox_delivery.setChecked(false);
                    button_checkOut.setEnabled(true);
                    progressBar.setProgress(0);
                }
            }
        });
        checkBox_delivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                delivery=true;
            }
        });

    }
    public void onClick_clear(View view) {

        progressBar.setProgress(0);
        layout1.removeAllViews();
        layout2.removeAllViews();
        array_toppings.clear();
        imag.clear();
        res=0;
        checkBox_load.setChecked(false);
        checkBox_delivery.setChecked(false);
    }


    @Override
     public void onClick(View v) {
        v.getTag();
        Log.d("demo",v.getTag()+"");
        array_toppings.remove(v.getTag());
        imag.remove(imag.get(Integer.parseInt(v.getTag()+"")));
        layout1.removeAllViews();
        layout2.removeAllViews();
        array_toppings.remove(Integer.parseInt(v.getTag()+""));
        progressBar.setProgress(progressBar.getProgress()-1);
      // Log.d("demo",v.getTag()+"");
        layouts();
    }


    public void layouts(){
        int count=0;
        for(int i=0; i<imag.size(); i++ ){
            count++;
            imageView_topping = new ImageView(MainActivity.this);
            imageView_topping.setImageResource(drawables[Integer.parseInt(imag.get(i))]);
            imageView_topping.setTag(i);
            imageView_topping.setLayoutParams(layoutParams);
            imageView_topping.setOnClickListener(MainActivity.this);
            if(count<=5){

                layout1.addView(imageView_topping);
            }
            else{
                layout2.addView(imageView_topping);
            }

        }
    }

}
