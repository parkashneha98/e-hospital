package com.example.firebase;

import android.app.ProgressDialog;
import android.content.Context;
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

public class nursesignup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nursesignup);
        mAuth = FirebaseAuth.getInstance();
    }
    public void nurse_signup(View v){

        EditText email3_et = findViewById(R.id.email3_et);
        EditText password3_et = findViewById(R.id.password3_et);

        pd = new ProgressDialog(nursesignup.this);
        pd.setMessage("Loading...");
        pd.show();



        String email = email3_et.getText().toString();
        String password = password3_et.getText().toString();



        OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Boolean bb =   task.isSuccessful();

                if( bb )
                {

                    Toast.makeText(nursesignup.this , "successfuly registered" , Toast.LENGTH_SHORT).show();

                    Intent i= new Intent(nursesignup.this, nurseinfo.class) ;

                    startActivity(i);

                }
                else {

                    Toast.makeText(nursesignup.this , "error try again" , Toast.LENGTH_SHORT).show();

                }

            }
        };


        mAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(nursesignup.this , listener);




    }





}




