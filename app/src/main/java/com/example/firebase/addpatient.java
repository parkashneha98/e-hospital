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

public class addpatient extends AppCompatActivity {
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpatient);
        database = FirebaseDatabase.getInstance();


    }
    public void add_data3(View view)
    {

        EditText name_edit = findViewById(R.id.patientname_et);
        EditText emailpatient_edit = findViewById(R.id.emailpatient_et);
        EditText fathername_edit = findViewById(R.id.father_et);
        EditText mothername_edit = findViewById(R.id.mother_et);
        EditText DOB_edit = findViewById(R.id.dob_et);
        EditText mobileno_edit = findViewById(R.id.mobilepatient_et);
        EditText address_edit = findViewById(R.id.addresspatient_et);
        EditText bloodgroup_edit = findViewById(R.id.bloodgrp_et);
        EditText weight_edit = findViewById(R.id.weight_et);
        EditText height_edit = findViewById(R.id.height_et);
        EditText gender_edit = findViewById(R.id.gender_et);
        EditText complaint_edit = findViewById(R.id.complaint_et);



        String name = name_edit.getText().toString();
        String emailpatient = emailpatient_edit.getText().toString();
        String father = fathername_edit.getText().toString();
        String mother = mothername_edit.getText().toString();
        String DOB = DOB_edit.getText().toString();
        String mobileno = mobileno_edit.getText().toString();
        String address = address_edit.getText().toString();
        String bloodgroup = bloodgroup_edit.getText().toString();
        String weight = weight_edit.getText().toString();
        String height= height_edit.getText().toString();
        String gender = gender_edit.getText().toString();
        String complaint= complaint_edit.getText().toString();
        if (name.equals("")){
            Toast.makeText(addpatient.this,"add name",Toast.LENGTH_SHORT).show();
            return;
        }
        if (emailpatient.equals("")){
            Toast.makeText(addpatient.this,"add emailpatient",Toast.LENGTH_SHORT).show();
            return;
        }
        if (father.equals("")){
            Toast.makeText(addpatient.this,"add father",Toast.LENGTH_SHORT).show();
            return;
        }
        if (mother.equals("")){
            Toast.makeText(addpatient.this,"add mother",Toast.LENGTH_SHORT).show();
            return;
        }
        if (DOB.equals("")){
            Toast.makeText(addpatient.this,"add DOB",Toast.LENGTH_SHORT).show();
            return;
        }
        if (mobileno.equals("")){
            Toast.makeText(addpatient.this,"add mobileno",Toast.LENGTH_SHORT).show();
            return;
        }
        if (address.equals("")){
            Toast.makeText(addpatient.this,"add address",Toast.LENGTH_SHORT).show();
            return;
        }
        if (bloodgroup.equals("")){
            Toast.makeText(addpatient.this,"add bloodgroup",Toast.LENGTH_SHORT).show();
            return;
        }
        if (weight.equals("")){
            Toast.makeText(addpatient.this,"add weight",Toast.LENGTH_SHORT).show();
            return;
        }
        if (height.equals("")){
            Toast.makeText(addpatient.this,"add height",Toast.LENGTH_SHORT).show();
            return;
        }
        if (gender.equals("")){
            Toast.makeText(addpatient.this,"add gender",Toast.LENGTH_SHORT).show();
            return;
        }
        if (complaint.equals("")){
            Toast.makeText(addpatient.this,"add complaint",Toast.LENGTH_SHORT).show();
            return;
        }


        DatabaseReference myRef = database.getReference("patient");
        String saved_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        DatabaseReference ref2 = myRef.child(saved_email.replace(".",""));


        PatientProfileDataModel dataModel = new PatientProfileDataModel(name, emailpatient, father, mother,DOB,mobileno,address,bloodgroup,weight,height,gender,complaint);


        ref2.setValue(dataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(addpatient.this, "successfuly registered", Toast.LENGTH_SHORT).show();


                }


            }



        });

    }



}

