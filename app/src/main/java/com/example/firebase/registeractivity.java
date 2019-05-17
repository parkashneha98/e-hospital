package com.example.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class registeractivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);
    }
    public void doctor(View v)
    {
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            Intent i=new Intent(registeractivity.this,doctorhomepage.class);
            startActivity(i);
        }else {
            Intent i = new Intent(registeractivity.this, Doctorloginactivity.class);
            startActivity(i);
            finish();
        }

    }
    public void nurse(View v){
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null) {
            Intent i=new Intent(registeractivity.this,nursehomepage.class);
            startActivity(i);
        }else {
            Intent i = new Intent(registeractivity.this, nurselogin.class);
            startActivity(i);
            finish();
        }
    }
}

