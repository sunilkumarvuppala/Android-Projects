package com.example.sunil.hw05;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.Firebase;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadFragment extends Fragment {
    MessageData messageData;
    TextView textView1, textView2;
    Firebase firebase;

    public ReadFragment() {
        // Required empty public constructor
    }
    public ReadFragment(MessageData messageData) {
        // Required empty public constructor
        this.messageData=messageData;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_read, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView1= (TextView) getActivity().findViewById(R.id.textViewSender);
        textView2= (TextView) getActivity().findViewById(R.id.textViewView);

        textView1.setText(messageData.getSender());
        textView2.setText(messageData.getMessage());

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.read, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("demo123",item.getItemId()+","+R.id.discard);
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.discard:
                InboxFragment.path.get(InboxFragment.neededPostion);
                Firebase.setAndroidContext(getActivity());
                firebase = new Firebase((String) InboxFragment.path.get(InboxFragment.neededPostion));

                firebase.removeValue();

                break;

        }
        return true;
    }
}
