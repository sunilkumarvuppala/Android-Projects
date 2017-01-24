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

public class Login extends AppCompatActivity {

    DatabaseReference database;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String emailText,passwordText;
    boolean status=false;
    static String emailGot=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("iTunes App (Login)");


        //Firebase mRootRef;
        ArrayList<String> mMessages  =  new ArrayList<>();

       // mRootRef =  new Firebase("https://firecastdemo.firebaseio.com/");
        final EditText email = (EditText) findViewById(R.id.login_email);
        final EditText password = (EditText) findViewById(R.id.login_password);

        final Button login = (Button) findViewById(R.id.button_login);
        Button create = (Button) findViewById(R.id.button_signup);
        Firebase loginRef = new Firebase("https://firecastdemo.firebaseio.com/users");

        database = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailText = email.getText().toString();
                passwordText = password.getText().toString();

            signIn();

            }
        });

/*
        loginRef.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if(authData != null){
                    //user is logged in
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login.this, "User already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/



    }

    public void signIn(){

        mAuth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Login", "signInWithEmail:onComplete:" + task.isSuccessful());
                        status=true;

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("Login", "signInWithEmail", task.getException());
                            status=false;
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {

                                    Intent intent = new Intent(Login.this, MainActivity.class);
                            intent.putExtra("EMAIL", emailText);
                            startActivity(intent);


                        }

                        // ...
                    }
                });

    }

}
