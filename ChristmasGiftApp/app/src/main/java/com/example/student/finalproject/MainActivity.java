package com.example.student.finalproject;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {
ListView listView ;
    CustomAdapter adapter;

    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<Person> personsList;
    static ArrayList ref;
    static int postionReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Christmas Gift");

        database = FirebaseDatabase.getInstance();
        reference=database.getReference();
        personsList= new ArrayList<>();
        ref =  new ArrayList();
        reference.getRoot().child("Persons").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /* MessageData msg = sender.getValue(MessageData.class);
                                    path.add(sender.getRef());*/
                personsList.clear();
                Log.d("democheck", dataSnapshot.getChildrenCount() + "");
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ref.add(snapshot.getKey());
                    Person person = snapshot.getValue(Person.class);
                    personsList.add(person);
                    Log.d("democheck", person.toString());

                }
                Log.d("democheck123",ref.toString());
                methodList();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    /*    listView = (ListView) findViewById(R.id.listView_list);
        Log.d("democheck","before list");
        Log.d("democheck", personsList.toString());
        adapter = new CustomAdapter(MainActivity.this, R.layout.adapter, personsList);
        Log.d("democheck","after list");
       Log.d("democheck", personsList.toString());
        listView.setAdapter(adapter);

        adapter.setNotifyOnChange(true);*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.new_person:
                Intent intent = new Intent(MainActivity.this,AddPerson.class);
                startActivity(intent);
                break;

        }
        return true;
    }
    public void methodList(){
        listView = (ListView) findViewById(R.id.listView_list);
        Log.d("democheck","before list");
        Log.d("democheck", personsList.toString());
        adapter = new CustomAdapter(MainActivity.this, R.layout.adapter, personsList);
        Log.d("democheck","after list");
        Log.d("democheck", personsList.toString());
        listView.setAdapter(adapter);

        adapter.setNotifyOnChange(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,GiftList.class);
                postionReq=position;
                startActivity(intent);
            }
        });
    }
}
