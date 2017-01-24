package com.example.sunil.hw03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        App app = (App) getIntent().getSerializableExtra(GetAppDataAsyncTask.APP_KEY);

        TextView textView = (TextView) findViewById(R.id.textView_APP);
        textView.setText(app.getAppTitle());
        ImageView imageView = (ImageView) findViewById(R.id.imageView_Preview);
        Picasso.with(PreviewActivity.this).load(app.getLargeImage()).into(imageView);
    }
}
