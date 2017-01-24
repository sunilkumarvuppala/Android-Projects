package com.example.spandanaravulapalli.firebasedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    Firebase mRootRef;
    static String emailGot=null;
    User user;

    ArrayList<String> mMessages  =  new ArrayList<>();
    Map<String,Object> map;
    DatabaseReference database;
    private FirebaseAuth mAuth;
    String uname,uemail,upass;
    boolean success = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("iTunes App (SignUp)");

        Log.d("demouser","begining");
       // final User user = new User();
        final EditText name,email,password;
        final Button signup, cancel;
        mAuth = FirebaseAuth.getInstance();


     //   mRootRef =  new Firebase("https://firecastdemo.firebaseio.com/");
        name = (EditText) findViewById(R.id.signup_name);

        email = (EditText) findViewById(R.id.signup_email);

        password = (EditText) findViewById(R.id.signup_password);


        signup= (Button) findViewById(R.id.button_signup);

         database = FirebaseDatabase.getInstance().getReference();

      //  final DatabaseReference myRef = database.getReference("users");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Log.d("demouser","ddbjbdbbdsjbsdbchdsbcdsbsbcshdbcdsbchjdsbcjhb");
                uname=name.getText().toString();
                uemail=email.getText().toString();
                 upass=     password.getText().toString();
             user = new User(uname,upass,uemail);

                signUp();

            }
        });



/*        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user;
                //String value = dataSnapshot.getValue(String.class);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/



        cancel = (Button) findViewById(R.id.button_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    public void signUp(){

        mAuth.createUserWithEmailAndPassword(uemail, upass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("signUp", "createUserWithEmail:onComplete:" + task.isSuccessful());
                    //    Log.d("taskbyme",task.getException()+"  "+task.getResult());
                        success =true;

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "User already exists!.",
                                    Toast.LENGTH_SHORT).show();
                            success =false;
                        }else {
                            database.push().setValue(user);
                            Toast.makeText(SignupActivity.this, "User added successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignupActivity.this,Login.class);
                            //intent.putExtra("EMAIL",uemail);
                            startActivity(intent);
                        }

                        // ...
                    }
                });

    }
}
