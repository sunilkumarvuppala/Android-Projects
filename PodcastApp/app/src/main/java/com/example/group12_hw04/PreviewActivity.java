package com.example.spandanaravulapalli.group12_hw04;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PreviewActivity extends AppCompatActivity {

    MediaPlayer mp = new MediaPlayer();
    ProgressBar pb;
    ImageButton ib;
    TextView desc,durauion,pubdate;
    static boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        //App app = (App) getIntent().getSerializableExtra(MainActivity.APP_KEY);
        String imgurl = getIntent().getExtras().getString(MainActivity.APP_KEY);
        final String mp3url = getIntent().getExtras().getString(MainActivity.MEDIA_KEY);
        final String desc = getIntent().getExtras().getString(MainActivity.DESCRIPTION_KEY);
         String pubdate = getIntent().getExtras().getString(MainActivity.PUBLICATIONDATE_KEY);
        final int duration = Integer.parseInt(getIntent().getExtras().getString(MainActivity.DURATION_KEY));

        pubdate= pubdate.substring(4,pubdate.length());

        String strDate=pubdate;
        Date varDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        try {
            varDate=dateFormat.parse(strDate);
            dateFormat=new SimpleDateFormat("dd/MM/yyyy");
            Log.d("Date123",dateFormat.format(varDate));
            TextView date = (TextView)findViewById(R.id.id_pubdate);
            date.setText(dateFormat.format(varDate));
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ted);
        // actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("Play!");

        ImageView imageView = (ImageView) findViewById(R.id.imageView_Preview);
        ib = (ImageButton) findViewById(R.id.imageButton);
        Picasso.with(PreviewActivity.this).load(imgurl).into(imageView);
        ib.setImageResource(R.drawable.ic_action_play);

        TextView description = (TextView)findViewById(R.id.id_desc);
        description.setText(desc);



        TextView dur = (TextView) findViewById(R.id.id_duration);
        dur.setText(String.valueOf(duration));
try{
    mp.reset();
    mp.setDataSource(mp3url);
    mp.prepare();
   // mp.start();
    pb = (ProgressBar) findViewById(R.id.progressBar);

    pb.setVisibility(ProgressBar.VISIBLE);
    pb.setProgress(0);
}catch (IOException e) {
    e.printStackTrace();
}
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=!flag;
                if(flag){

              /*      mp.reset();
                    mp.setDataSource(mp3url);
                    mp.prepare();*/
                    ib.setImageResource(R.drawable.ic_action_pause);
                    mp.start();
                    Log.d("previewurl", mp3url);

                    //pb.setIndeterminate(false);
                    //Log.d("durationbysunil", duration + "");
                    pb.setMax(duration * 1000);
                    final long dur = duration;
                    new CountDownTimer(duration * 1000, duration) {

                        @Override
                        public void onTick(long millisUntilFinished) {

                            //Log.d("durationby",pb.getProgress()+"");
                            if (mp.isPlaying()) {
                                pb.setProgress(pb.getProgress() + duration);
                                //Log.d("durationby", pb.getProgress() + "");
                            }
                        }

                        @Override
                        public void onFinish() {
                            mp.stop();
                            return;

                        }

                    }.start();
                    //pb.setProgress(pb.getProgress()+1);
                    //pb.setProgress(duration);


                    Log.d("durationPreview", duration + "");


            }else{
                    ib.setImageResource(R.drawable.ic_action_play);
                    mp.pause();
                }

            }
        });




    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();// Don't forget this line
        mp.stop(); // Or whatever the function is to pause it
    }


}
