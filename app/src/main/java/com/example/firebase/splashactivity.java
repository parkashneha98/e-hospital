package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class splashactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);


        Handler handler = new Handler();

        Runnable r = new Runnable() {

            public void run() {
                Intent i = new Intent(splashactivity.this, registeractivity.class);
                startActivity(i);
                finish();

                // code you want to run after some time
            }

        };
        handler.postDelayed(r, 2000); // here run method will run after 2 secs ( 2000 msec)

    }

}