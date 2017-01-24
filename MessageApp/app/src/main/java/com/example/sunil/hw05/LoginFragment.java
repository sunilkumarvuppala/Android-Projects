package com.example.sunil.hw05;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    Activity activity;
    DatabaseReference database;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String name,password;
    Button signning;
    static String userNow;


    public LoginFragment() {
        // Required empty public constructor
    }
    public LoginFragment(Activity activity) {
        this.activity=activity;
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        signning = (Button) getActivity().findViewById(R.id.buttonLogIn);
        signning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userName = (EditText) getActivity().findViewById(R.id.TextUserName);
                EditText passwordText = (EditText) getActivity().findViewById(R.id.TextPassword);

                name = userName.getText().toString();
                password=passwordText.getText().toString();
                signIn();
            }
        });

        getActivity().findViewById(R.id.buttonNewUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.container,new SignUpFragment(),"SignUpFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
    public void signIn(){

        mAuth.signInWithEmailAndPassword(name+"@gmail.com", password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Login", "signInWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w("Login", "signInWithEmail", task.getException());
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            userNow = name;

                            getFragmentManager().beginTransaction().replace(R.id.container,new InboxFragment(activity),"InboxFragment")
                                    .addToBackStack(null)
                            .commit();
                               }

                        // ...
                    }
                });

    }
}
