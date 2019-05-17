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
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class patienthistory extends AppCompatActivity {
    List<ViewPatientHistoryDataModel> viewpatienthistory_list ;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patienthistory);
        viewpatienthistory_list = new ArrayList<>();
        pd = new ProgressDialog(patienthistory.this);
        pd.setMessage("Loading...");
        pd.show();


        get_data_firebase();


        RecyclerView fruit_recycler = findViewById(R.id.viewhistory_recyclerlist);

        fruit_recycler.setLayoutManager(new LinearLayoutManager(patienthistory.this , LinearLayoutManager.VERTICAL , false));

        patienthistory.ListAdapter adapter = new ListAdapter();

        fruit_recycler.setAdapter(adapter);


    }




    class ListHolder extends RecyclerView.ViewHolder
    {

        TextView text_viewpatname;

        TextView text_viewid;
        TextView text_bpup;
        TextView text_bplow;
        TextView text_tem;
        TextView check_morngdos;
        TextView check_noondos;
        TextView check_evedos;
        TextView check_morngmeal;
        TextView check_noonmeal;
        TextView check_evemeal;


        public  ListHolder( View itemView ) {
            super(itemView);

            text_viewpatname= itemView.findViewById(R.id.text_viewpatname);

            text_viewid= itemView.findViewById(R.id.text_viewid);
            text_bpup= itemView.findViewById(R.id.text_bpup);
            text_bplow= itemView.findViewById(R.id.text_bplow);
            text_tem= itemView.findViewById(R.id.text_tem);
            check_morngdos=itemView.findViewById(R.id.check_morngdos);
            check_noondos=itemView.findViewById(R.id.check_noondos);
            check_evedos=itemView.findViewById(R.id.check_evedos);
            check_morngmeal=itemView.findViewById(R.id.check_morngmeal);
            check_noonmeal=itemView.findViewById(R.id.check_noonmeal);
            check_evemeal=itemView.findViewById(R.id.check_evemeal);





        }


    }

    class ListAdapter extends RecyclerView.Adapter<patienthistory.ListHolder>
    {




        @NonNull
        @Override
        public patienthistory.ListHolder onCreateViewHolder(@NonNull ViewGroup vi, int i) {

            View view = LayoutInflater.from(patienthistory.this).inflate(R.layout.history_cell, vi , false);


            patienthistory.ListHolder holder = new ListHolder(view);

            return holder;


        }

        @Override
        public void onBindViewHolder(@NonNull patienthistory.ListHolder listHolder, int i) {

           ViewPatientHistoryDataModel dataModel = viewpatienthistory_list.get(i);

            listHolder.text_viewpatname.setText( dataModel.patiencename_x );

            listHolder.text_viewid.setText(dataModel.emailid);

            listHolder.text_bpup.setText(dataModel.bloodpressureup);

            listHolder.text_bplow.setText(dataModel.bloodpressurelow);

            listHolder.text_tem.setText(dataModel.bodytemperature);

            listHolder.check_morngdos.setText(dataModel.morningdos);
            listHolder.check_noondos.setText(dataModel.afternoondos);
            listHolder.check_evedos.setText(dataModel.eveningdos);
            listHolder.check_morngmeal.setText(dataModel.morningmeal);
            listHolder.check_noonmeal.setText(dataModel.afternoonmeal);
            listHolder.check_evemeal.setText(dataModel.eveningmeal);





        }

        @Override
        public int getItemCount() {

            return viewpatienthistory_list.size();
        }
    }
    private void get_data_firebase()
    {

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference("checklist").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    ViewPatientHistoryDataModel dataModel1 = snapshot.getValue(ViewPatientHistoryDataModel.class);

                    viewpatienthistory_list.add(dataModel1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}




