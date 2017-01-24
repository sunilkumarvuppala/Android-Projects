package com.example.student.finalproject;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPerson extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    EditText editText_person, editText_budAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);




       database = FirebaseDatabase.getInstance();
        reference=database.getReference();
        editText_person= (EditText) findViewById(R.id.editText_personname);
        editText_budAmount= (EditText) findViewById(R.id.editText_budget);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.add_person:
                Person person = new Person();
                person.setName(editText_person.getText().toString());
                person.setPrice(Integer.parseInt(editText_budAmount.getText().toString()));
                reference.child("Persons").push().setValue(person);

                break;

        }
        return true;
    }
}
