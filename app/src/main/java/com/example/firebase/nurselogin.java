package com.example.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class nurselogin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurselogin);
        mAuth = FirebaseAuth.getInstance();
    }
    public void openlogin(View v){

        EditText email_et = findViewById(R.id.email_et);
        EditText password_et = findViewById(R.id.password_et);

        pd = new ProgressDialog(nurselogin.this);
        pd.setMessage("Loading...");
        pd.show();




        String email = email_et.getText().toString();
        String password = password_et.getText().toString();


        OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Boolean bb = task.isSuccessful();

                if (bb) {
                    Toast.makeText(nurselogin.this, "successfuly registered", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(nurselogin.this,nursehomepage.class);
                    startActivity(i);

                } else {

                    Toast.makeText(nurselogin.this, "error try again", Toast.LENGTH_SHORT).show();

                }

            }
        };


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(nurselogin.this, listener);



    }
    public void opensignup2(View v) {
        Intent i= new Intent(nurselogin.this,nursesignup.class);
        startActivity(i);



}

    public void openforget(View v)
    {
        Intent o=new Intent(nurselogin.this,forgotpassword.class);
        startActivity(o);

    }



}


