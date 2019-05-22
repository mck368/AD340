package com.example.ad340project;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class MovieDetail extends AppCompatActivity {

    TextView textView_desc;
    TextView textView_title;
    TextView textView_year;
    TextView textView_dir;
    ImageView img_movie;
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
        textView_year = (TextView) findViewById(R.id.movieYear);
        textView_dir = (TextView) findViewById(R.id.movieDir);
        img_movie = (ImageView) findViewById(R.id.img_movie);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            toolbar.setTitle(bundle.getString("mHeading"));
            textView_title.setText(bundle.getString("mTitle"));
            textView_desc.setText(bundle.getString("mDescription"));
            textView_year.setText(bundle.getString("mYear"));
            textView_dir.setText(bundle.getString("mDir"));
            Picasso p = Picasso.get();
            p.setIndicatorsEnabled(true);
            p.setLoggingEnabled(true);
            p.load(bundle.getString("mImg")).into(img_movie);
        }
    }
}