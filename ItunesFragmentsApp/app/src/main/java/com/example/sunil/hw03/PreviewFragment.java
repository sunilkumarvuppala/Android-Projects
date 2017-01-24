package com.example.sunil.hw03;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class PreviewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public String name,image;
Activity activity;
    public PreviewFragment() {
        // Required empty public constructor
    }   public PreviewFragment(Activity activity, String name, String image) {
        // Required empty public constructor
        this.activity=activity;
        this.name=name;
        this.image=image;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_preview, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_preFrag);
        TextView textView= (TextView) view.findViewById(R.id.id_previewAppName);
        textView.setText(name);
        Picasso.with(activity).load(image).into(imageView);

        return view;
    }






}
