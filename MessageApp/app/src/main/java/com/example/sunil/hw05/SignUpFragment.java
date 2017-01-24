package com.example.sunil.hw05;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {
    Activity activity;
    EditText firstName, lastName, userName, passwordtext, confirmPassword;
    private FirebaseAuth mAuth;
    String stringName, stringPassword, first, last;
    DatabaseReference database;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public SignUpFragment(Activity activity) {
        // Required empty public constructor
        this.activity=activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        database = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        getActivity().findViewById(R.id.buttonSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = (EditText) getActivity().findViewById(R.id.editTextFirstName);
                lastName = (EditText) getActivity().findViewById(R.id.editTextLastName);
                userName = (EditText) getActivity().findViewById(R.id.UserName);
                passwordtext = (EditText) getActivity().findViewById(R.id.editTextPassword);
                confirmPassword = (EditText) getActivity().findViewById(R.id.editTextConfirm);
                stringName = userName.getText().toString();
                stringPassword = passwordtext.getText().toString();
                first = firstName.getText().toString();
                last = lastName.getText().toString();
                Log.d("demo123",stringName+" , "+ stringPassword);
                    signUp();
            }
        });
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }


    public void signUp(){
        Log.d("demo123",stringName+stringPassword);

        mAuth.createUserWithEmailAndPassword(stringName+"@gmail.com", stringPassword)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("signUp", "createUserWithEmail:onComplete:" + task.isSuccessful());
                        Log.d("demo123",task.getResult().toString());

                        if (!task.isSuccessful()) {
                            Toast.makeText(getActivity(), "User already exists!.",
                                    Toast.LENGTH_SHORT).show();

                        }else {
                            User user = new User(first,last,stringName,stringPassword);

                            database.child("users").push().setValue(user);
                            Toast.makeText(getActivity(), "User added successfully!", Toast.LENGTH_SHORT).show();
                            getFragmentManager().beginTransaction().replace(R.id.container,new InboxFragment(),"InboxFragment").commit();


                        }

                    }
                });
    }
}
