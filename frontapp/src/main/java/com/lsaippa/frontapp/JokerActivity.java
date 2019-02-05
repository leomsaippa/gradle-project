package com.lsaippa.frontapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class JokerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joker);
        setup();

        if(savedInstanceState == null) {
            setupFragment();
        }
    }

    private void setup() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    private void setupFragment() {

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String joke = extras.getString(Intent.EXTRA_TEXT);
            if(joke != null) {
                JokerFragment jokeActivityFragment = JokerFragment.newInstance(joke);

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, jokeActivityFragment)
                        .commit();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
