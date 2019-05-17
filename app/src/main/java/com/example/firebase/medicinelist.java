 package com.example.firebase;

import android.content.Intent;
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

public class medicinelist extends AppCompatActivity {
    List<MedicineListDataModel> medicine_list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicinelist);
        medicine_list = new ArrayList<>();

         get_data_firebase();


        RecyclerView fruit_recycler = findViewById(R.id.med_recyclerlist);

        fruit_recycler.setLayoutManager(new LinearLayoutManager(medicinelist.this , LinearLayoutManager.VERTICAL , false));

        medicinelist.ListAdapter adapter = new ListAdapter();

        fruit_recycler.setAdapter(adapter);


    }



    class ListHolder extends RecyclerView.ViewHolder
    {

        TextView text_medname;

        TextView text_salt;
        TextView text_medprice;

        public  ListHolder( View itemView ) {
            super(itemView);

            text_medname= itemView.findViewById(R.id.text_medname);

            text_salt= itemView.findViewById(R.id.text_salt);

            text_medprice = itemView.findViewById(R.id.text_medprice);
        }


    }

    class ListAdapter extends RecyclerView.Adapter<medicinelist.ListHolder>
    {




        @NonNull
        @Override
        public medicinelist.ListHolder onCreateViewHolder(@NonNull ViewGroup vi, int i) {

            View view = LayoutInflater.from(medicinelist.this).inflate(R.layout.medicine_cell, vi , false);


          medicinelist.ListHolder holder = new ListHolder(view);

            return holder;


        }

        @Override
        public void onBindViewHolder(@NonNull ListHolder listHolder, int i) {

            MedicineListDataModel dataModel = medicine_list.get(i);

            listHolder.text_medname.setText( dataModel.medicinename_x );

            listHolder.text_salt.setText(dataModel.saltname);

            listHolder.text_medprice.setText(dataModel.price);



        }

        @Override
        public int getItemCount() {

            return medicine_list.size();
        }
    }


    private void get_data_firebase()
    {

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference("medicines").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    MedicineListDataModel dataModel1 = snapshot.getValue(MedicineListDataModel.class);

                    medicine_list.add(dataModel1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}







