package com.example.student.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GiftList extends AppCompatActivity {
    GiftAdapter adapter;
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<Gift> giftArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_list);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        giftArrayList = new ArrayList<>();
        reference.getRoot().child("giftsperperson").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                giftArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getKey().equals(MainActivity.ref.get(MainActivity.postionReq))) {
                        reference.getRoot().child("giftsperperson").child((String) MainActivity.ref.get(MainActivity.postionReq)).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot1) {
                                for (DataSnapshot data : dataSnapshot1.getChildren()) {
                                    Gift gift = data.getValue(Gift.class);
                                    giftArrayList.add(gift);
                                }
                                methodInside();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

public void methodInside() {

    ListView listView = (ListView) findViewById(R.id.listViewGiftList);
    adapter = new GiftAdapter(GiftList.this, R.layout.giftadapter, giftArrayList);
    listView.setAdapter(adapter);

    adapter.setNotifyOnChange(true);

}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_gift_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.add_gift:
                Intent intent = new Intent(GiftList.this,AddGifts.class);
                startActivity(intent);


                break;

        }
        return true;
    }
}
