package com.example.ad340project;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


public class MovieDetail extends AppCompatActivity {

    TextView textView_desc;
    TextView textView_title;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        textView_desc = (TextView) findViewById(R.id.movieDesc);
        textView_title = (TextView) findViewById(R.id.movieTitle);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            toolbar.setTitle(bundle.getString("mHeading"));
            textView_title.setText(bundle.getString("mTitle"));
            textView_desc.setText(bundle.getString("mDescription"));
        }
    }
}