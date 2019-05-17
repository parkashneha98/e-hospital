package com.example.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class editnurseprofile extends AppCompatActivity {
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnurseprofile);
        database = FirebaseDatabase.getInstance();
        get_data();

    }
    public void update_profilenurse(View v){
        EditText name_edit = findViewById(R.id.namenur_et);
        EditText email2_edit = findViewById(R.id.emailnur_et);
        EditText areaofinterest_edit = findViewById(R.id.aoinur_et);
        EditText education_edit = findViewById(R.id.educnur_et);
        EditText experience_edit = findViewById(R.id.experiencenur_et);
        EditText speciality_edit = findViewById(R.id.specialnur_et);
        EditText mobileno_edit = findViewById(R.id.mobilenur_et);
        EditText workinghours_edit = findViewById(R.id.hoursnur_et);



        String name = name_edit.getText().toString();
        String email2 = email2_edit.getText().toString();
        String areaofinterest = areaofinterest_edit.getText().toString();
        String education = education_edit.getText().toString();
        String experience = experience_edit.getText().toString();
        String speciality = speciality_edit.getText().toString();
        String mobileno = mobileno_edit.getText().toString();
        String workinghours = workinghours_edit.getText().toString();

        DatabaseReference myRef = database.getReference("nurse");

        String saved_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        DatabaseReference ref2 = myRef.child(saved_email.replace(".",""));


        NurseProfileDataModel dataModel = new NurseProfileDataModel(name, email2, areaofinterest, education,experience,speciality,mobileno,workinghours);


        ref2.setValue(dataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(editnurseprofile.this, "successfuly updated", Toast.LENGTH_SHORT).show();
                }


            }


        });
    }
    private void get_data(){
        DatabaseReference myRef = database.getReference("nurse");

        String saved_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        DatabaseReference ref2 = myRef.child(saved_email.replace(".",""));
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    ProfileDataModel model=dataSnapshot.getValue(ProfileDataModel.class);
                    EditText name_edit = findViewById(R.id.namenur_et);
                    EditText email2_edit = findViewById(R.id.emailnur_et);
                    EditText areaofinterest_edit = findViewById(R.id.aoinur_et);
                    EditText education_edit = findViewById(R.id.educnur_et);
                    EditText experience_edit = findViewById(R.id.experiencenur_et);
                    EditText speciality_edit = findViewById(R.id.specialnur_et);
                    EditText mobileno_edit = findViewById(R.id.mobilenur_et);
                    EditText workinghours_edit = findViewById(R.id.hoursnur_et);
                    name_edit.setText(model.name_x);
                    email2_edit.setText(model.email2);
                    areaofinterest_edit.setText(model.areaofinterest);
                    education_edit.setText(model.education);
                    experience_edit.setText(model.experience);
                    speciality_edit.setText(model.speciality);
                    mobileno_edit.setText(model.mobileno);
                    workinghours_edit.setText(model.workinghours);



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    }



