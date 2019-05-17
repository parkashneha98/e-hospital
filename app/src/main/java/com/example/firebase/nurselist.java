package com.example.firebase;

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

public class nurselist extends AppCompatActivity {
    List<NurseDataModel> nurse_list ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurselist);
        nurse_list = new ArrayList<>();

        get_data_firebase();


        RecyclerView fruit_recycler = findViewById(R.id.nurse_recyclerlist);

        fruit_recycler.setLayoutManager(new LinearLayoutManager(nurselist.this , LinearLayoutManager.VERTICAL , false));

        nurselist.ListAdapter adapter = new ListAdapter();

        fruit_recycler.setAdapter(adapter);


    }




    private class ListHolder extends RecyclerView.ViewHolder
    {

        TextView text_nur;
        TextView text_contactnur;

        TextView text_nursedesign;
        TextView text_nurseid;

        public  ListHolder( View itemView ) {
            super(itemView);

            text_nur= itemView.findViewById(R.id.text_nur);
            text_contactnur= itemView.findViewById(R.id.text_contactnur);

            text_nursedesign = itemView.findViewById(R.id.text_nursedesign);

            text_nurseid = itemView.findViewById(R.id.text_nurseid);
        }


    }

    private class ListAdapter extends RecyclerView.Adapter<ListHolder>
    {




        @NonNull
        @Override
        public nurselist.ListHolder onCreateViewHolder(@NonNull ViewGroup vi, int i) {

            View view = LayoutInflater.from(nurselist.this).inflate(R.layout.nurse_cell , vi , false);


           nurselist.ListHolder holder = new ListHolder(view);

            return holder;


        }

        @Override
        public void onBindViewHolder(@NonNull nurselist.ListHolder listHolder, int i) {

           NurseDataModel dataModel = nurse_list.get(i);

            listHolder.text_nur.setText( dataModel.name_x );
            listHolder.text_contactnur.setText( dataModel.contact );

            listHolder.text_nursedesign.setText(dataModel.disgn);

            listHolder.text_nurseid.setText(dataModel.emailid);



        }

        @Override
        public int getItemCount() {

            return nurse_list.size();
        }
    }
    private void get_data_firebase()
    {

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference("nurse_staff").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    NurseDataModel dataModel1 = snapshot.getValue(NurseDataModel.class);

                    nurse_list.add(dataModel1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}






