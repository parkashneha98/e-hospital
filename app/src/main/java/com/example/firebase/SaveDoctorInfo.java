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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SaveDoctorInfo extends AppCompatActivity {
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);
        database = FirebaseDatabase.getInstance();
    }

    public void add_data(View view)
    {
        Intent i = new Intent(SaveDoctorInfo.this,doctorhomepage.class);
        startActivity(i);

        EditText name_edit = findViewById(R.id.name_et);
        EditText email2_edit = findViewById(R.id.email2_et);
        EditText areaofinterest_edit = findViewById(R.id.aoi_et);
        EditText education_edit = findViewById(R.id.educ_et);
        EditText experience_edit = findViewById(R.id.experience_et);
        EditText speciality_edit = findViewById(R.id.special_et);
        EditText mobileno_edit = findViewById(R.id.mobile_et);
        EditText workinghours_edit = findViewById(R.id.hours_et);



        String name = name_edit.getText().toString();
        String email2 = email2_edit.getText().toString();
        String areaofinterest = areaofinterest_edit.getText().toString();
        String education = education_edit.getText().toString();
        String experience = experience_edit.getText().toString();
        String speciality = speciality_edit.getText().toString();
        String mobileno = mobileno_edit.getText().toString();
        String workinghours = workinghours_edit.getText().toString();

        DatabaseReference myRef = database.getReference("doctor");

        String saved_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        DatabaseReference ref2 = myRef.child(saved_email.replace(".",""));


        ProfileDataModel dataModel = new ProfileDataModel(name, email2, areaofinterest, education,experience,speciality,mobileno,workinghours);


        ref2.setValue(dataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SaveDoctorInfo.this, "successfuly registered", Toast.LENGTH_SHORT).show();
                }


            }


        });
    }   }