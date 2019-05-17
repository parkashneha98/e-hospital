package com.example.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.firebase.chat.doctorchat;
import com.example.firebase.chat.userdocdetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userdoc extends AppCompatActivity {
    ListView usersList1;
    TextView noUsersText1;
    ArrayList<String> al = new ArrayList<>();
    int totalUsers = 0;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdoc);

        usersList1 = (ListView)findViewById(R.id.usersList1);
        noUsersText1 = (TextView)findViewById(R.id.noUsersText1);

        pd = new ProgressDialog(userdoc.this);
        pd.setMessage("Loading...");
        pd.show();

        FirebaseAuth auth = FirebaseAuth.getInstance();

        userdocdetails.username1 = auth.getCurrentUser().getEmail().replace(".","");


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference("nurse").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                doOnSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        usersList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userdocdetails.chatWith1 = al.get(position);
                startActivity(new Intent(userdoc.this, doctorchat.class));
            }
        });
    }

    public void doOnSuccess(DataSnapshot snapshot){

        for(DataSnapshot snapshot1 :  snapshot.getChildren())
        {
            al.add(snapshot1.getKey());

            totalUsers++;
        }



        if(totalUsers < 1){
            noUsersText1.setVisibility(View.VISIBLE);
            usersList1.setVisibility(View.GONE);
        }
        else{
            noUsersText1.setVisibility(View.GONE);
            usersList1.setVisibility(View.VISIBLE);
            usersList1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al));
        }

        pd.dismiss();
    }


}


