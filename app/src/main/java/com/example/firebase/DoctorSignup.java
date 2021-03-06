package com.example.firebase;

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

public class DoctorSignup extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_signup);
        mAuth = FirebaseAuth.getInstance();
    }


    public void opensignup(View v)
    {


        EditText email2_et = findViewById(R.id.email2_et);
        EditText password2_et = findViewById(R.id.password2_et);




        String email = email2_et.getText().toString();
        String password = password2_et.getText().toString();



        OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Boolean bb =   task.isSuccessful();

                if( bb )
                {

                    Toast.makeText(DoctorSignup.this , "successfuly registered" , Toast.LENGTH_SHORT).show();

                    Intent i= new Intent(DoctorSignup.this, SaveDoctorInfo.class) ;

                    startActivity(i);

                }
                else {

                    Toast.makeText(DoctorSignup.this , "error try again" , Toast.LENGTH_SHORT).show();

                }

            }
        };


        mAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(DoctorSignup.this , listener);




    }





}
