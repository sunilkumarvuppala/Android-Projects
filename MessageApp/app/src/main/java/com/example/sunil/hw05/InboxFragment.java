package com.example.sunil.hw05;



import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class InboxFragment extends Fragment {
DatabaseReference database;
    ArrayList<Sender> senderArrayList=null;
    static CustomAdapter adapter;
    static ListView listView;
    static String requiredKey;
    ArrayList<MessageData> messageData;
    Firebase fire;
    static ArrayList path;
    Activity activity;
    int flag=0;
    static int neededPostion;



    public InboxFragment() {
        // Required empty public constructor
    }
    public InboxFragment(Activity activity) {
        // Required empty public constructor
        this.activity=activity;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        database = FirebaseDatabase.getInstance().getReference();
        senderArrayList=new ArrayList();
        messageData=new ArrayList<>();
        path = new ArrayList();


        database.getRoot().child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot u: dataSnapshot.getChildren()){
                    User user = u.getValue(User.class);
                    Log.d("demoprocess","Inside users");
                    if (user.getUsername().equals(LoginFragment.userNow)){
                      //  Log.d("demoprocess",u.getChildren().toString());
                        requiredKey=u.getRef().getKey().toString();
                        senderArrayList.clear();
                        path.clear();
                        messageData.clear();
                        database.getRoot().child("messages").child(requiredKey).addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot1) {
                             //   Log.d("demoprocess",u.getRef().child("-KLKmP7OaMEyXl7LLUoX").toString());
                                Log.d("demoprocess","Inside message"+requiredKey+" "+dataSnapshot1.getChildrenCount());
                                flag++;
                                for (DataSnapshot sender: dataSnapshot1.getChildren()){
                                    MessageData msg = sender.getValue(MessageData.class);
                                    path.add(sender.getRef());
                                    Sender s = new Sender();
                                    s.setSenderName(msg.getSender());
                                    Log.d("demoprocess","something");
                                    senderArrayList.add(s);
                                    messageData.add(msg);
                                    if(flag==1)
                                    createListView();
                                }
                                Log.d("demoprocess",path.toString());
                                Log.d("demoprocess",senderArrayList.size()+"");

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("demo123",item.getItemId()+","+R.id.email);
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.action_refresh:
                getFragmentManager().beginTransaction().replace(R.id.container,new InboxFragment(activity),"InboxFragment")
                        .addToBackStack(null)
                        .commit();

                break;
            case  R.id.email:
                getFragmentManager().beginTransaction().replace(R.id.container,new ComposeFragment(),"ComposeFragment").addToBackStack(null).commit();
                break;

        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inbox, container, false);
    }
    public void createListView(){
        Log.d("demoprocess",senderArrayList.size()+"");
        if(senderArrayList.size()>0) {
            Log.d("demoprocess","before list view");
            listView = (ListView) getActivity().findViewById(R.id.listView_senderList);
            Log.d("demoprocess","before adapter");
            adapter = new CustomAdapter(getActivity(), R.layout.row_item_layout, senderArrayList);

            Log.d("democheck", senderArrayList.toString());
            listView.setAdapter(adapter);

            adapter.setNotifyOnChange(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MessageData messageDat2 = messageData.get(position);
                   // messageDat2.getMessage();

                    messageDat2.setRead(true);
                    Firebase.setAndroidContext(getActivity());
                    fire=new Firebase(path.get(position).toString());
                    Log.d("demoprocess",path.get(position).toString());
                    neededPostion=position;
                    getFragmentManager().beginTransaction().replace(R.id.container,new ReadFragment(messageDat2),"ReadFragment")
                            .addToBackStack(null).commit();
                    fire.child("read").setValue(true);

                }
            });
        }
    }


}
