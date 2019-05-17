package com.example.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
    TextView email;
    String emailforgot_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        email = (EditText) findViewById(R.id.emailforgot_et);
    }

    public void clicksubmit(View view) {

        emailforgot_et = email.getText().toString();
        FirebaseAuth.getInstance().sendPasswordResetEmail(emailforgot_et)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(forgotpassword.this, "Email Sent!!!Check Email!!", Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            Toast.makeText(forgotpassword.this, "error",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}