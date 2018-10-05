package com.udacity.gradle.builditbigger.jokesdisplayandroidlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    public static final String JOKE_KEY = "joke_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TextView tv_joke = findViewById(R.id.tv_joke);
            tv_joke.setText(extras.getString(JOKE_KEY));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_jokes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_close) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
