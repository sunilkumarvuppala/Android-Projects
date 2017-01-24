package com.example.spandanaravulapalli.firebasedemo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PreviewActivity extends AppCompatActivity {
    boolean imageType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Preview Activity");

        App app = (App) getIntent().getSerializableExtra(GetAppDataAsyncTask.APP_KEY);

        TextView textView = (TextView) findViewById(R.id.textView_APP);
        textView.setText(app.getAppTitle());
        ImageView imageView = (ImageView) findViewById(R.id.imageView_Preview);
        Picasso.with(PreviewActivity.this).load(app.getLargeImage()).into(imageView);
        ImageView imageView1 = (ImageView) findViewById(R.id.id_previewStar);
         imageType = getIntent().getExtras().getBoolean("image");

        if(imageType){
            imageView1.setImageResource(R.drawable.favoritesyellow);
        }else {
            imageView1.setImageResource(R.drawable.favoritesgrey);
        }
    }
}
