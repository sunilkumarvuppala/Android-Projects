package com.example.spandanaravulapalli.threaddemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] arraySpinner;
    boolean up,low,num,spl;
    int length;
    String password;
    ProgressDialog progressDialog;
    TextView textView;
    Handler handler;
    String KEY;

    Util_New util = new Util_New();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new Async().execute();

        this.arraySpinner = new String[] {
                "Select Password Length","8 - 12", "13 - 17", "18 - 22"
        };

        //Spinner
        final Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(MainActivity.this, "Please select a value", Toast.LENGTH_LONG).show();

            }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 1) {
                    length = 0;
                }else if(pos == 2){
                    length = 1;
                }
                else if(pos ==  3){
                    length = 2;
                }
            }
        });


        //Checkboxes

        final CheckBox number, lower, upper, special;
        number = (CheckBox)findViewById(R.id.id_number);
        lower = (CheckBox)findViewById(R.id.id_lower);
        upper = (CheckBox)findViewById(R.id.id_upper);
        special = (CheckBox)findViewById(R.id.id_special);

        //number checkbox
        number.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(number.isChecked()){
                    System.out.println("Checked");
                    num = true;
                }else{
                    System.out.println("Un-Checked");
                    num = false;
                }
            }
        });

        //lower checkbox
        lower.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(lower.isChecked()){
                    System.out.println("Checked");
                    low = true;
                }else{
                    System.out.println("Un-Checked");
                    low  = false;
                }
            }
        });

        //uppercase checkbox

        upper.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(upper.isChecked()){
                    System.out.println("Checked");
                    up=true;
                }else{
                    System.out.println("Un-Checked");
                    up = false;
                }
            }
        });

        //special character checkbox
        special.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(special.isChecked()){
                    System.out.println("Checked");
                    spl =  true;
                }else{
                    System.out.println("Un-Checked");
                    spl = false;
                }
            }
        });

        //thread buttons

        final Button button_async = (Button) findViewById(R.id.button_async);

        button_async.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s.getSelectedItem().toString().equals("Select Password Length")){
                    Toast.makeText(MainActivity.this, "Please Select Password Length!", Toast.LENGTH_SHORT).show();
                }
                else if((num == false) && (up == false) && (low == false) && (spl == false)){

                    Toast.makeText(MainActivity.this, "Please select any of the checkboxes", Toast.LENGTH_LONG).show();

                }
                else {

                    new Async().execute();
                }
            }
        });
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Generating passwords...");
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        final Button button_thread = (Button) findViewById(R.id.button_thread);

        button_thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s.getSelectedItem().toString().equals("Select Password Length")){
                    Toast.makeText(MainActivity.this, "Please select Length of password!", Toast.LENGTH_SHORT).show();
                }
                else if(num == false && up == false && low == false && spl == false ){
                    Toast.makeText(MainActivity.this, "Please check atleast one checkbox!", Toast.LENGTH_SHORT).show();
                }else {
                    Thread thread = new Thread(new innerThread());
                    thread.start();
                    progressDialog.show();
                }
            }
        });

       // progressDialog.show();
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.getData().containsKey(KEY)){

                    String text = msg.getData().getString(KEY);
                    textView = (TextView) findViewById(R.id.pass_display);
                    textView.setText(text);
                    progressDialog.dismiss();
                }
                return true;
            }
        });

        //password result
        textView  = (TextView)findViewById(R.id.pass_display);
        //textView.setText(password);


    }
    class innerThread implements Runnable{

        @Override
        public void run() {

            Message msg = new Message();
            Bundle bundle = new Bundle();
            String password =  Util_New.getPassword(length,num,up,low,spl);

            bundle.putString(KEY, password);
            msg.setData(bundle);
            handler.sendMessage(msg);
            //  textView.setText(password);



        }
    }

    //Async Task
    class Async extends AsyncTask<Integer, Void, String>{

        @Override
        protected String doInBackground(Integer... params) {

                password = Util_New.getPassword(length, num, up, low, spl);

                return password;

        }

        protected  void onPostExecute(String result){
            super.onPostExecute(result);
            progressDialog.dismiss();
            textView.setText(result);


        }

        protected  void onPreExecute(){

            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMax(200);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Generating passwords...");
            progressDialog.show();

        }


    }

}
