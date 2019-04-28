package com.example.ad340project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button button2 = findViewById(R.id.toastTwo);
        button2.setOnClickListener(new Listener("Bye"));

        Button button3 = findViewById(R.id.toastThree);
        button3.setOnClickListener(new Listener("Wow"));

        Button button4 = findViewById(R.id.toastFour);
        button4.setOnClickListener(new Listener("Cool"));
    }

    public void foodList(View view) {
        Intent strIntent = new Intent(this, MovieList.class);
        startActivity(strIntent);
    }

    public class Listener implements  android.view.View.OnClickListener {

        CharSequence c;

        public Listener(CharSequence c) {
            this.c = c;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), c,Toast.LENGTH_SHORT).show();
        }
    }
}
