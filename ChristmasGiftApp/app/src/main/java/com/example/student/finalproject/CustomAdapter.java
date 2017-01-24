package com.example.student.finalproject;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by student on 6/28/16.
 */
public class CustomAdapter extends ArrayAdapter {

    ArrayList<Person> senderList;
    Context context;

    public CustomAdapter(Activity context, int resource, ArrayList<Person> senderList) {
        super(context, resource, senderList);
        this.senderList=senderList;
        this.context=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.inflate(R.layout.adapter,null,true);

        TextView textView_person = (TextView) single_row.findViewById(R.id.textViewPerson);
        TextView textView_price = (TextView) single_row.findViewById(R.id.textViewprice123);
        Person person = senderList.get(position);
        Log.d("democheck",person.toString());
        textView_person.setText(person.getName());
        textView_price.setText(person.getPrice()+"");



        return single_row;

    }
}