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

public class addmedicine extends AppCompatActivity {
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmedicine);
        database = FirebaseDatabase.getInstance();

    }
    public void add_medicine(View view)
    {

        EditText medicinename_edit = findViewById(R.id.medicinename_et);
        EditText saltname_edit = findViewById(R.id.saltname_et);
        EditText price_edit = findViewById(R.id.price_et);




        String medicinename = medicinename_edit.getText().toString();
        String saltname = saltname_edit.getText().toString();
        String price= price_edit.getText().toString();
        if (medicinename.equals("")){
            Toast.makeText(addmedicine.this,"add medicinename",Toast.LENGTH_SHORT).show();
            return;
        }
        if (saltname.equals("")){
            Toast.makeText(addmedicine.this,"add saltname",Toast.LENGTH_SHORT).show();
            return;
        }
        if (price.equals("")){
            Toast.makeText(addmedicine.this,"add price",Toast.LENGTH_SHORT).show();
            return;
        }



        DatabaseReference myRef = database.getReference("medicines");
        String saved_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        DatabaseReference ref2 = myRef.child(saved_email.replace(".",""));


        MedicineDataModel dataModel = new MedicineDataModel(medicinename, saltname, price);


        ref2.setValue(dataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(addmedicine.this, "successfuly registered", Toast.LENGTH_SHORT).show();


                }


            }



        });

    }




}



