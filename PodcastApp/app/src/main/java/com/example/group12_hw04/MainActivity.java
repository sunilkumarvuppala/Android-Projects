package com.example.spandanaravulapalli.group12_hw04;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    public static final String APP_KEY="appObj";
    public static final String MEDIA_KEY="mediaObj";
    public static final String DURATION_KEY="durationObj";
    public static final String DESCRIPTION_KEY="descObj";
    public static final String PUBLICATIONDATE_KEY="pubObj";
    public static boolean change=false;
    public static String image=null;
    //media player
  //  static MediaPlayer mp = new MediaPlayer();
    public static ProgressBar pb;
    public static ImageView imageView;

    //recycler view
    static RecyclerView recyclerView;
    static MyAdapter mAdapter;
    ArrayList<App> apps =  new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView= (ImageView) findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.ic_action_pause);

        pb = new ProgressBar(MainActivity.this,null,android.R.attr.progressBarStyleHorizontal);
        pb.setMax(100);

        pb = (ProgressBar) findViewById(R.id.progressBar2);
        pb.setVisibility(ProgressBar.GONE);
        imageView.setVisibility(ImageView.GONE);


        //recycler view
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        Log.d("recycler", recyclerView +"");
        mAdapter = new MyAdapter(this,apps,change);
        recyclerView.setHasFixedSize(false);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            Log.d("layout", mLayoutManager+"");
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ted);
       // actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);



        new GetAppDataAsyncTask(MainActivity.this).execute("http://www.npr.org/rss/podcast.php?id=510298");
/*
        final ActionBar ab = getSupportActionBar();*/
       // Log.d("imagebyapp",image);
/*        Picasso.with(this)
                .load(image)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        ab.setIcon(d);
                        ab.setDisplayShowHomeEnabled(true);
                        ab.setDisplayHomeAsUpEnabled(true);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });*/


/*
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Movie movie = movieList.get(position);
                Toast.makeText(getApplicationContext(), "Recycler is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),PreviewActivity.class);
                Log.d("intent" ,GetAppDataAsyncTask.apps.get(position).getTitle());
                intent.putExtra(APP_KEY,GetAppDataAsyncTask.apps.get(position).getImgURL());
                intent.putExtra(MEDIA_KEY,GetAppDataAsyncTask.apps.get(position).getMp3url());
                intent.putExtra(DURATION_KEY,GetAppDataAsyncTask.apps.get(position).getDuration());

                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/

        //media player
        /*try {
            mp.setDataSource("http://play.podtrac.com/npr-510298/npr.mc.tritondigital.com/NPR_510298/media/anon.npr-mp3/npr/ted/2016/06/20160616_ted_trust.mp3?orgId=1&d=3214&p=510298&story=481872111&t=podcast&e=481872111&ft=pod&f=510298");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {

            case R.id.action_refresh:
                MainActivity.imageView.setVisibility(ImageView.GONE);
                MainActivity.pb.setVisibility(ProgressBar.GONE);
                MyAdapter.mp.stop();
                pb.setProgress(0);
                change=!change;
                    Log.d("sunil","refresh checked"+change);
                if(!change){
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                    Log.d("layout", mLayoutManager+"");
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);



                }else{
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,2);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);


                }

                break;
        }
        return true;

    }

    /*public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private MainActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

*//*            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }*//*
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
*/
    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }

        return false;
    }


}
