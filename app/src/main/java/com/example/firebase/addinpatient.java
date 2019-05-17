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

public class addinpatient extends AppCompatActivity {
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinpatient);
        database = FirebaseDatabase.getInstance();
    }
    public void add_inpatient(View v){
        EditText patientname_edit = findViewById(R.id.inpatientname_et);
        EditText emailid_edit = findViewById(R.id.emailin_et);
        EditText ward_edit = findViewById(R.id.ward_et);
        EditText disease_edit = findViewById(R.id.disease_et);
        EditText treatment_edit = findViewById(R.id.treatment_et);





        String patientname = patientname_edit.getText().toString();
        String emailid = emailid_edit.getText().toString();
        String ward= ward_edit.getText().toString();
        String disease= disease_edit.getText().toString();
        String treatment= disease_edit.getText().toString();


        if (patientname.equals("")){
            Toast.makeText(addinpatient.this,"add patient name",Toast.LENGTH_SHORT).show();
            return;
        }
        if (emailid.equals("")){
            Toast.makeText(addinpatient.this,"add email id",Toast.LENGTH_SHORT).show();
            return;
        }
        if (ward.equals("")){
            Toast.makeText(addinpatient.this,"add ward ",Toast.LENGTH_SHORT).show();
            return;
        }
        if (disease.equals("")){
            Toast.makeText(addinpatient.this,"add disease  ",Toast.LENGTH_SHORT).show();
            return;
        }
        if (treatment.equals("")){
            Toast.makeText(addinpatient.this,"add treatment  ",Toast.LENGTH_SHORT).show();
            return;
        }




        DatabaseReference myRef = database.getReference("inpatient details");
        String saved_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        DatabaseReference ref2 = myRef.child(saved_email.replace(".",""));


        AddInpatientDataModel dataModel = new AddInpatientDataModel(patientname, emailid, ward,disease,treatment);


        ref2.setValue(dataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(addinpatient.this, "successfuly registered", Toast.LENGTH_SHORT).show();


                }


            }



        });

    }
    public void view_record(View v){
        Intent i=new Intent(addinpatient.this,viewinpatientlist.class);
        startActivity(i);

        }

    }












