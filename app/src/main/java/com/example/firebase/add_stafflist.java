package com.example.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class add_stafflist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stafflist);
    }
    public void adddoctor(View v){
        Intent i=new Intent(add_stafflist.this,adddoctorcolleague.class);
        startActivity(i);

    }
    public void addnurse(View v){
        Intent i=new Intent(add_stafflist.this,addnursecolleague.class);
        startActivity(i);

    }
}
