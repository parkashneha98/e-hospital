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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addappointment extends AppCompatActivity {
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addappointment);
        database = FirebaseDatabase.getInstance();

    }
    public void add_appointdoc(View view)
    {

        EditText doctorname_edit = findViewById(R.id.doctorname_et);
        EditText contactno_edit = findViewById(R.id.contact_et);
        EditText appointmentdate_edit = findViewById(R.id.date_et);
        EditText appointmenttime_edit = findViewById(R.id.time_et);
        EditText patientname_edit = findViewById(R.id.patient_et);
        EditText fathername_edit = findViewById(R.id.pfather_et);




        String doctorname = doctorname_edit.getText().toString();
        String contactno = contactno_edit.getText().toString();
        String appointmentdate= appointmentdate_edit.getText().toString();
        String appointmenttime= appointmenttime_edit.getText().toString();
        String patientname= patientname_edit.getText().toString();
        String fathername= fathername_edit.getText().toString();

        if (doctorname.equals("")){
            Toast.makeText(addappointment.this,"add doctor name",Toast.LENGTH_SHORT).show();
            return;
        }
        if (contactno.equals("")){
            Toast.makeText(addappointment.this,"add contact no",Toast.LENGTH_SHORT).show();
            return;
        }
        if (appointmentdate.equals("")){
            Toast.makeText(addappointment.this,"add appointment date",Toast.LENGTH_SHORT).show();
            return;
        }
        if (appointmenttime.equals("")){
            Toast.makeText(addappointment.this,"add appointment time",Toast.LENGTH_SHORT).show();
            return;
        }
        if (patientname.equals("")){
            Toast.makeText(addappointment.this,"add patient name",Toast.LENGTH_SHORT).show();
            return;
        }
        if (fathername.equals("")){
            Toast.makeText(addappointment.this,"add father name",Toast.LENGTH_SHORT).show();
            return;
        }





        DatabaseReference myRef = database.getReference("appointments");
        String saved_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        DatabaseReference ref2 = myRef.child(saved_email.replace(".",""));


        AppointDataModel dataModel = new AppointDataModel(doctorname, contactno, appointmentdate,appointmenttime,patientname,fathername);


        ref2.setValue(dataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(addappointment.this, "successfuly registered", Toast.LENGTH_SHORT).show();


                }


            }



        });

    }



}

