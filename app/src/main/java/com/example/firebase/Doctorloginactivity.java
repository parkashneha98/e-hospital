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

public class Doctorloginactivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        mAuth = FirebaseAuth.getInstance();
    }






    public void opensignup(View v){
        Intent i= new Intent(Doctorloginactivity.this, DoctorSignup.class) ;
        startActivity(i);
        finish();
        }


        public void opendoctorlogin(View v){
            {
                EditText email1_et = findViewById(R.id.email_et);
            EditText password1_et = findViewById(R.id.password1_et);

                pd = new ProgressDialog(Doctorloginactivity.this);
                pd.setMessage("Loading...");
                pd.show();




                String email = email1_et.getText().toString();
            String password = password1_et.getText().toString();



            OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    Boolean bb =   task.isSuccessful();

                    if( bb )
                    {
                        Toast.makeText(Doctorloginactivity.this , "successfuly registered" , Toast.LENGTH_SHORT).show();
                        Intent i= new Intent(Doctorloginactivity.this,doctorhomepage.class);
                        startActivity(i);
                    }
                    else {

                        Toast.makeText(Doctorloginactivity.this , "error try again" , Toast.LENGTH_SHORT).show();

                    }

                }
            };


            mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(Doctorloginactivity.this , listener);




        }

        }
        public void openforgetdoc(View v){
        Intent i = new Intent(Doctorloginactivity.this,forgotpassword.class);
        startActivity(i);

        }
    }

