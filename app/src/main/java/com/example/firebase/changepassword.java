package com.example.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class changepassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
    }

    FirebaseUser user;
    String TAG;


    public void reset_password( View view) {
        EditText current_password=findViewById(R.id.current_et);
         EditText new_password=findViewById(R.id.newpassword_et);
       final EditText confirm_password=findViewById(R.id.confirm_et);



        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = (AuthCredential) EmailAuthProvider
                .getCredential( email , current_password.getText().toString());
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(confirm_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(changepassword.this, "UPDATED SUCESSFULLY",Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(changepassword.this,Doctorloginactivity.class);
                                        startActivity(i);
                                    } else {
                                        Toast.makeText(changepassword.this, "ERROR",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Log.d(TAG, "Error auth failed");
                        }
                    }
                });
    }
}
