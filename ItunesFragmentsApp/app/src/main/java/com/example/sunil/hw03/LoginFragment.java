package com.example.sunil.hw03;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    Activity activity;

    public LoginFragment() {
        // Required empty public constructor
    }
    public LoginFragment(Activity activity) {
        // Required empty public constructor
        this.activity=activity;

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
        getActivity().findViewById(R.id.id_button_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            EditText user = (EditText) getActivity().findViewById(R.id.id_username);
            EditText password = (EditText) getActivity().findViewById(R.id.id_password);
                if(user.getText().toString().equals("admin") && password.getText().toString().equals("test123")){
                    new GetAppDataAsyncTask(activity).execute("http://itunes.apple.com/us/rss/topgrossingapplications/limit=25/xml");
                    getFragmentManager().beginTransaction().replace(R.id.container,new AppFragment(),"AppFragment")
                            .addToBackStack(null)
                            .commit();
                }else {
                    Toast.makeText(activity, "Please enter valid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
