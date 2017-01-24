package com.example.sunil.hw05;

/**
 * Created by Sunil on 28-06-2016.
 */


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    ArrayList<Sender> senderList;
    Context context;

    public CustomAdapter(Activity context, int resource, ArrayList<Sender> senderList) {
        super(context, resource, senderList);
        this.senderList=senderList;
        this.context=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.inflate(R.layout.row_item_layout,null,true);

        TextView textView_senderName = (TextView) single_row.findViewById(R.id.textView_senderName);
        Log.d("democustom","sunil was here");
        Sender sender = senderList.get(position);
        Log.d("democustom",sender.getSenderName());
        textView_senderName.setText(sender.getSenderName());


        //  new GetImage((Activity) context).execute(images.get(position));

        return single_row;
    }
}
