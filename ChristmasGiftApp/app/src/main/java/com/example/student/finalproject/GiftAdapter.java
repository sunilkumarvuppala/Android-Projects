package com.example.student.finalproject;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by student on 6/28/16.
 */
public class GiftAdapter extends ArrayAdapter {
    ArrayList<Gift> senderList;
    Context context;

    public GiftAdapter(Activity context, int resource, ArrayList<Gift> senderList) {
        super(context, resource, senderList);
        this.senderList=senderList;
        this.context=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.inflate(R.layout.giftadapter,null,true);

  TextView textViewGiftName = (TextView) single_row.findViewById(R.id.textViewGiftName);
        TextView textViewPrice = (TextView) single_row.findViewById(R.id.textViewGiftPrice);

        ImageView imageView = (ImageView) single_row.findViewById(R.id.imageViewGift);

        Gift gift= senderList.get(position);
        textViewGiftName.setText(gift.getGift());
        textViewPrice.setText(gift.getPrice()+"");

        Picasso.with(context).load(gift.getImageUrl()).into(imageView);


        return single_row;

    }
}