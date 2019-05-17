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

public class viewinpatientlist extends AppCompatActivity {
    List<ViewInpatientDataModel> inpatient_list ;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewinpatientlist);
        inpatient_list = new ArrayList<>();
        pd = new ProgressDialog(viewinpatientlist.this);
        pd.setMessage("Loading...");
        pd.show();


        get_data_firebase();


        RecyclerView fruit_recycler = findViewById(R.id.viewinpatient_recyclerlist);

        fruit_recycler.setLayoutManager(new LinearLayoutManager(viewinpatientlist.this , LinearLayoutManager.VERTICAL , false));

        viewinpatientlist.ListAdapter adapter = new ListAdapter();

        fruit_recycler.setAdapter(adapter);


    }





    class ListHolder extends RecyclerView.ViewHolder
    {

        TextView text_patincename;
        TextView text_emailin;
        TextView text_ward;
        TextView text_disease;
        TextView text_treatment;

        public  ListHolder( View itemView ) {
            super(itemView);

            text_patincename= itemView.findViewById(R.id.text_inpatient);
            text_emailin= itemView.findViewById(R.id.emailin);


            text_ward = itemView.findViewById(R.id.text_wardin);

            text_disease = itemView.findViewById(R.id.text_diseasein);
            text_treatment = itemView.findViewById(R.id.text_treatmentin);
        }


    }

    class ListAdapter extends RecyclerView.Adapter<viewinpatientlist.ListHolder>
    {




        @NonNull
        @Override
        public viewinpatientlist.ListHolder onCreateViewHolder(@NonNull ViewGroup vi, int i) {

            View view = LayoutInflater.from(viewinpatientlist.this).inflate(R.layout.inpatient_cell, vi , false);


            viewinpatientlist.ListHolder holder = new ListHolder(view);

            return holder;


        }

        @Override
        public void onBindViewHolder(@NonNull viewinpatientlist.ListHolder listHolder, int i) {

            ViewInpatientDataModel dataModel = inpatient_list.get(i);

            listHolder.text_patincename.setText( dataModel.patincename);
            listHolder.text_emailin.setText(dataModel.emailid);

            listHolder.text_ward.setText(dataModel.ward);

            listHolder.text_disease.setText(dataModel.disease);
            listHolder.text_treatment.setText(dataModel.treatment);



        }

        @Override
        public int getItemCount() {

            return inpatient_list.size();
        }
    }


    private void get_data_firebase()
    {

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference("inpatient details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ViewInpatientDataModel dataModel1 = snapshot.getValue(ViewInpatientDataModel.class);

                    inpatient_list.add(dataModel1);
                }
                pd.hide();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}



