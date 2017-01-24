package com.example.sunil.hw05;


import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ComposeFragment extends Fragment {
    AlertDialog.Builder alertDialog;
    DatabaseReference database;
    ArrayList<String> userList;
    ArrayList refList;
    Button button_send;
    int position;
    EditText editText;
    String sender, receiver, message;
    boolean isread;



    public ComposeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compose, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.ic_action_person);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("demo123","sunil is checking");
                database = FirebaseDatabase.getInstance().getReference();

                database.getRoot().child("users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                       // dataSnapshot.getChildren();
                        userList= new ArrayList();
                        refList= new ArrayList();
                        userList.add("Users");
                       // Log.d("demo123",dataSnapshot.getValue()+"");
                        for (DataSnapshot u: dataSnapshot.getChildren()) {
                            Log.d("demo12345", String.valueOf(u.getRef().getKey()));
                            refList.add(u.getRef().getKey());
                            User user = u.getValue(User.class);

                            userList.add(user.getUsername());


                        }
                        Log.d("demo",userList.toString());
                        final Spinner s = (Spinner) getActivity().findViewById(R.id.spinner);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_list_item_1, userList);
                        s.setAdapter(adapter);

                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                                Toast.makeText(getActivity(), "Please select a value", Toast.LENGTH_LONG).show();

                            }
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                                if (pos>0) {
                                   Log.d("demo123", database.child(userList.get(pos)).getRef().toString());
                                    Toast.makeText(getActivity(), refList.get(pos-1)+"", Toast.LENGTH_SHORT).show();
                                    position=pos-1;
                                }
                            }
                        });

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        button_send= (Button) getActivity().findViewById(R.id.button_send);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = (EditText) getActivity().findViewById(R.id.editText_message);
                String value=editText.getText().toString();
                sender=LoginFragment.userNow;
                receiver=userList.get(position+1);
                message=editText.getText().toString();


                MessageData messageData= new MessageData(sender,receiver,message,isread);


                database.child("messages").child(refList.get(position).toString()).push().setValue(messageData);
            }
        });
    }

}
