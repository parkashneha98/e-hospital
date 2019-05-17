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

public class adddoctorcolleague extends AppCompatActivity {
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddoctorcolleague);
        database = FirebaseDatabase.getInstance();
    }
    public void add_doccll(View v)
    { EditText doctorname_edit = findViewById(R.id.doctorcoll_et);
        EditText contactno_edit = findViewById(R.id.contact_et);
        EditText designation_edit = findViewById(R.id.designcoll_et);
        EditText emailid_edit = findViewById(R.id.emailcoll_et);





        String doctorname = doctorname_edit.getText().toString();
        String contactno = contactno_edit.getText().toString();
        String designation= designation_edit.getText().toString();
        String emailid= emailid_edit.getText().toString();


        if (doctorname.equals("")){
            Toast.makeText(adddoctorcolleague.this,"add doctor name",Toast.LENGTH_SHORT).show();
            return;
        }
        if (contactno.equals("")){
            Toast.makeText(adddoctorcolleague.this,"add contact no",Toast.LENGTH_SHORT).show();
            return;
        }
        if (designation.equals("")){
            Toast.makeText(adddoctorcolleague.this,"add designation ",Toast.LENGTH_SHORT).show();
            return;
        }
        if (emailid.equals("")){
            Toast.makeText(adddoctorcolleague.this,"add email id ",Toast.LENGTH_SHORT).show();
            return;
        }




        DatabaseReference myRef = database.getReference("doctor_staff");
        String saved_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        DatabaseReference ref2 = myRef.child(saved_email.replace(".",""));


        AddDocColleagueDataModel dataModel = new AddDocColleagueDataModel(doctorname, contactno, designation,emailid);


        ref2.setValue(dataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(adddoctorcolleague.this, "successfuly registered", Toast.LENGTH_SHORT).show();


                }


            }



        });

    }



}





