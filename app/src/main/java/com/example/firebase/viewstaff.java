package com.example.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class viewstaff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstaff);
    }
    public void doctor1(View v){
        Intent i = new Intent(viewstaff.this,doctorlist.class);
        startActivity(i);

    }
    public void nurse1(View v){
        Intent i = new Intent(viewstaff.this,nurselist.class);
        startActivity(i);

    }
}
