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
import android.view.MenuInflater;


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

    public class Listener implements android.view.View.OnClickListener {

        CharSequence c;

        public Listener(CharSequence c) {
            this.c = c;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), c, Toast.LENGTH_SHORT).show();
        }
    }

    private Toolbar mTopToolbar;

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu, menu);
//        return true;
//
//    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                Intent intent = new Intent(MainActivity.this, aboutPage.class);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_favorite:
//                Intent intent = new Intent(MainActivity.this, aboutPage.class);
//                startActivity(intent);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//
//        }
//    }
}

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_favorite) {
//            Intent strIntent = new Intent(this, aboutPage.class);
//            startActivity(strIntent);
////            Toast.makeText(MainActivity.this, "About", Toast.LENGTH_LONG).show();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//
//    }}


//        switch(item.getItemId()) {
//            case R.id.action_favorite:
//                Intent intent = new Intent(this, aboutPage.class);
//                startActivity(intent);
//                return true;
//
//        }} }
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()) {
//            case R.id.action_favorite:
//                Intent intent = new Intent(MainActivity.this, aboutPage.class);
//                startActivity(intent);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//
//        }
//    }



