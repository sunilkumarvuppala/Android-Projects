package com.example.student.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddGifts extends AppCompatActivity {
    GiftAdapter adapter;

    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<Gift> giftArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gifts);

        database = FirebaseDatabase.getInstance();
        reference=database.getReference();

        giftArrayList= new ArrayList<>();
        reference.getRoot().child("Gifts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                giftArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Gift gift = snapshot.getValue(Gift.class);
                    giftArrayList.add(gift);
                }

                 methodList();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }



    public void methodList(){
        Log.d("democheck",giftArrayList.toString());

        ListView listView = (ListView) findViewById(R.id.listViewGifts);
        adapter = new GiftAdapter(AddGifts.this, R.layout.giftadapter,giftArrayList);
        listView.setAdapter(adapter);

        adapter.setNotifyOnChange(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Gift gift=giftArrayList.get(position);
                reference.getRoot().child("giftsperperson").child((String) MainActivity.ref.get(MainActivity.postionReq)).push().setValue(gift);
            }
        });

    }
}
