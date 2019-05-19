package com.example.ad340project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    public void movieList(View view) {
        Intent strIntent = new Intent(this, MovieList.class);
        startActivity(strIntent);
    }

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


}
