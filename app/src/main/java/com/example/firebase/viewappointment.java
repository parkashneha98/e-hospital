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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewappointment extends AppCompatActivity {
    List<AppointListDataModel> appointment_list ;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewappointment);
        appointment_list = new ArrayList<>();

        pd = new ProgressDialog(viewappointment.this);
        pd.setMessage("Loading...");
        pd.show();


        get_data_firebase();

        RecyclerView fruit_recycler = findViewById(R.id.appoint_recyclerlist);

        fruit_recycler.setLayoutManager(new LinearLayoutManager(viewappointment.this , LinearLayoutManager.VERTICAL , false));

       viewappointment.ListAdapter adapter = new ListAdapter();

        fruit_recycler.setAdapter(adapter);


    }




    class ListHolder extends RecyclerView.ViewHolder
    {

        TextView text_docname;

        TextView text_contactapp;
        TextView text_appdate;
        TextView text_apptime;
        TextView text_patname;
        TextView text_fatname;

        public  ListHolder( View itemView ) {
            super(itemView);

            text_docname= itemView.findViewById(R.id.text_docname);

            text_contactapp = itemView.findViewById(R.id.text_contactapp);

            text_appdate = itemView.findViewById(R.id.text_appdate);
            text_apptime = itemView.findViewById(R.id.text_apptime);
            text_patname = itemView.findViewById(R.id.text_patname);
            text_fatname = itemView.findViewById(R.id.text_fatname);
        }


    }

    class ListAdapter extends RecyclerView.Adapter<viewappointment.ListHolder>
    {




        @NonNull
        @Override
        public viewappointment.ListHolder onCreateViewHolder(@NonNull ViewGroup vi, int i) {

            View view = LayoutInflater.from(viewappointment.this).inflate(R.layout.appointment_cell , vi , false);


            viewappointment.ListHolder holder = new ListHolder(view);

            return holder;


        }

        @Override
        public void onBindViewHolder(@NonNull ListHolder listHolder, int i) {

            AppointListDataModel dataModel = appointment_list.get(i);

            listHolder.text_docname.setText( dataModel.doctorname_x );

            listHolder.text_contactapp.setText(dataModel.contactno);

            listHolder.text_appdate.setText(dataModel.appointmentdate);
            listHolder.text_apptime.setText(dataModel.appointmenttime);
            listHolder.text_patname.setText(dataModel.patientname);
            listHolder.text_fatname.setText(dataModel.fathername);



        }

        @Override
        public int getItemCount() {

            return appointment_list.size();
        }
    }

    private void get_data_firebase()
    {

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference("appointments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    AppointListDataModel dataModel1 = snapshot.getValue(AppointListDataModel.class);

                    appointment_list.add(dataModel1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}






