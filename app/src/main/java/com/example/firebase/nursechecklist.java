package com.example.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class nursechecklist extends AppCompatActivity {
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nursechecklist);
        database = FirebaseDatabase.getInstance();


    }
    public void save_checklist(View v)
    {
        EditText patientname_edit = findViewById(R.id.pname_et);
        EditText emailid_edit = findViewById(R.id.check_id_et);
        EditText bloodpressureup_edit = findViewById(R.id.notify_up);
        EditText bloodpressurelow_edit = findViewById(R.id.notify_low);
        EditText bodytemperature_edit = findViewById(R.id.note_tem);
        CheckBox morningdos_edit = findViewById(R.id.morn_dos);
        CheckBox afternoondos_edit = findViewById(R.id.noon_dos);
        CheckBox eveningdos_edit = findViewById(R.id.eve_dos);
        CheckBox morningmeal_edit = findViewById(R.id.morn_meal);
        CheckBox afternoonmeal_edit = findViewById(R.id.noon_meal);
        CheckBox eveningmeal_edit = findViewById(R.id.eve_meal);





        String patientname = patientname_edit.getText().toString();
        String emailid = emailid_edit.getText().toString();
        String bloodpressureup= bloodpressureup_edit.getText().toString();
        String bloodpressurelow= bloodpressurelow_edit.getText().toString();
        String bodytemperature= bodytemperature_edit.getText().toString();
        String morningdos;
        if ( morningdos_edit.isChecked()){
            morningdos="morningdos";
        }
        else{
            morningdos="no dose";
        }

        String afternoondos;
        if (afternoondos_edit.isChecked()){
             afternoondos="afternoondos";

        }
        else{
            afternoondos= "no dose";

        }

        String eveningdos;
        if (eveningdos_edit.isChecked()){
            eveningdos="eveningdos";

        }
        else{
            eveningdos= "no dose";

        }

        String morningmeal;
        if (morningmeal_edit.isChecked()){
            morningmeal="morningmeal";

        }
        else{
            morningmeal= "no dose";

        }

        String afternoonmeal;
        if (afternoonmeal_edit.isChecked()){
            afternoonmeal="afternoonmeal";

        }
        else{
            afternoonmeal= "no dose";

        }

        String eveningmeal;
        if (eveningmeal_edit.isChecked()){
            eveningmeal="eveningmeal";

        }
        else{
            eveningmeal= "no dose";

        }




        if (patientname.equals("")){
            Toast.makeText(nursechecklist.this,"add patience name",Toast.LENGTH_SHORT).show();
            return;
        }
        if (emailid.equals("")){
            Toast.makeText(nursechecklist.this,"add email id",Toast.LENGTH_SHORT).show();
            return;
        }
        if (bloodpressureup.equals("")){
            Toast.makeText(nursechecklist.this,"add blood pressure up",Toast.LENGTH_SHORT).show();
            return;
        }
        if (bloodpressurelow.equals("")){
            Toast.makeText(nursechecklist.this,"add blood pressure low",Toast.LENGTH_SHORT).show();
            return;
        }
        if (bodytemperature.equals("")){
            Toast.makeText(nursechecklist.this,"add body temperature",Toast.LENGTH_SHORT).show();
            return;
        }







        DatabaseReference myRef = database.getReference("checklist");
        String saved_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        DatabaseReference ref2 = myRef.child(saved_email.replace(".",""));


        ChecklistDataModel checklistDataModel = new ChecklistDataModel(patientname, emailid, bloodpressureup,bloodpressurelow,bodytemperature,morningdos,afternoondos,eveningdos,morningmeal,afternoonmeal,eveningmeal);


        ref2.setValue(checklistDataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(nursechecklist.this, "successfuly registered", Toast.LENGTH_SHORT).show();


                }


            }



        });

    }



}












