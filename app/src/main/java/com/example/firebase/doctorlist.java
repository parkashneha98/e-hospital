package com.example.firebase;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firebase.chat.NurseUsers;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class doctorlist extends AppCompatActivity {
    List<DoctorDataModel> doctor_list ;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorlist);
        doctor_list = new ArrayList<>();
        pd = new ProgressDialog(doctorlist.this);
        pd.setMessage("Loading...");
        pd.show();


        get_data_firebase();


        RecyclerView fruit_recycler = findViewById(R.id.doc_recyclerlist);

        fruit_recycler.setLayoutManager(new LinearLayoutManager(doctorlist.this , LinearLayoutManager.VERTICAL , false));

        ListAdapter adapter = new ListAdapter();

        fruit_recycler.setAdapter(adapter);


    }





    class ListHolder extends RecyclerView.ViewHolder
    {

        TextView text_doc;
         TextView text_contact;
        TextView text_disgn;
        TextView text_id;

        public  ListHolder( View itemView ) {
            super(itemView);

            text_doc= itemView.findViewById(R.id.text_doc);
            text_contact= itemView.findViewById(R.id.text_contact);


            text_disgn = itemView.findViewById(R.id.text_disgn);

            text_id = itemView.findViewById(R.id.text_id);
        }


    }

    class ListAdapter extends RecyclerView.Adapter<ListHolder>
    {




        @NonNull
        @Override
        public ListHolder onCreateViewHolder(@NonNull ViewGroup vi, int i) {

            View view = LayoutInflater.from(doctorlist.this).inflate(R.layout.doctor_cell , vi , false);


            ListHolder holder = new ListHolder(view);

            return holder;


        }

        @Override
        public void onBindViewHolder(@NonNull ListHolder listHolder, int i) {

            DoctorDataModel dataModel = doctor_list.get(i);

            listHolder.text_doc.setText( dataModel.name_x );
            listHolder.text_contact.setText(dataModel.contact);

            listHolder.text_disgn.setText(dataModel.disgn);

            listHolder.text_id.setText(dataModel.emailid);



        }

        @Override
        public int getItemCount() {

            return doctor_list.size();
        }
    }


    private void get_data_firebase()
    {

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference("doctor_staff").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    DoctorDataModel dataModel1 = snapshot.getValue(DoctorDataModel.class);

                    doctor_list.add(dataModel1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}




