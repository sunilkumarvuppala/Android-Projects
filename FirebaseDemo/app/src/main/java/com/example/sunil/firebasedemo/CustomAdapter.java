package com.example.spandanaravulapalli.firebasedemo;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by spandanaravulapalli on 6/14/16.
 */
public class CustomAdapter extends ArrayAdapter {

    ArrayList<String> appNames,devNames,releaseDates,prices,categories,images,imgURL,ids;
    Context context;
    boolean imageType;

    public CustomAdapter(Activity context, int resource, ArrayList<String> ids, ArrayList<String> appNames, ArrayList<String> devNames, ArrayList<String> releaseDates, ArrayList<String> prices, ArrayList<String> categories, ArrayList<String> images, boolean image_type) {
        super(context, resource, appNames);
        this.appNames=appNames;
        this.devNames=devNames;
        this.releaseDates=releaseDates;
        this.prices=prices;
        this.categories=categories;
        this.images=images;
        this.context=context;
        this.imageType=image_type;
        this.ids=ids;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.inflate(R.layout.row_item_layout,null,true);

        TextView textView_appName = (TextView) single_row.findViewById(R.id.id_appName);
        TextView textView_devName = (TextView) single_row.findViewById(R.id.id_devName);
        TextView textView_releaseDate = (TextView) single_row.findViewById(R.id.id_releaseDate);
        TextView textView_price = (TextView) single_row.findViewById(R.id.id_price);
        TextView textView_category = (TextView) single_row.findViewById(R.id.id_category);
        ImageView imageView= (ImageView) single_row.findViewById(R.id.imageView);
        ImageView imageStar = (ImageView) single_row.findViewById(R.id.imageStar);

        textView_appName.setText(appNames.get(position));
        textView_devName.setText(devNames.get(position));
        textView_releaseDate.setText(releaseDates.get(position));
        textView_price.setText(prices.get(position));
        textView_category.setText(categories.get(position));
        imageStar.setImageResource(R.drawable.favoritesgrey);
        Log.d("image","reacgedddddd");
        Picasso.with(context).load(images.get(position)).into(imageView);
      //  new GetImage((Activity) context).execute(images.get(position));

        /*if(databaseDataManager.getApp(ids.get(position)) != null){
            imageStar.setImageResource(R.drawable.favoritesyellow);
        }else{
            imageStar.setImageResource(R.drawable.favoritesgrey);
        }*/

        return single_row;
    }
}
