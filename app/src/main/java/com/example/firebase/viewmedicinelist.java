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

public class viewmedicinelist extends AppCompatActivity {
    List<ViewMedListDataModel> viewmedicine_list ;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmedicinelist);
        viewmedicine_list = new ArrayList<>();

        pd = new ProgressDialog(viewmedicinelist.this);
        pd.setMessage("Loading...");
        pd.show();


        get_data_firebase();


        RecyclerView fruit_recycler = findViewById(R.id.viewmed_recyclerlist);

        fruit_recycler.setLayoutManager(new LinearLayoutManager(viewmedicinelist.this , LinearLayoutManager.VERTICAL , false));

        viewmedicinelist.ListAdapter adapter = new ListAdapter();

        fruit_recycler.setAdapter(adapter);


    }





    class ListHolder extends RecyclerView.ViewHolder
    {

        TextView text_viewmedname;

        TextView text_viewsalt;
        TextView text_viewmedprice;

        public  ListHolder( View itemView ) {
            super(itemView);

            text_viewmedname= itemView.findViewById(R.id.text_viewmedname);

            text_viewsalt= itemView.findViewById(R.id.text_viewsalt);

            text_viewmedprice = itemView.findViewById(R.id.text_viewmedprice);
        }



    }

    class ListAdapter extends RecyclerView.Adapter<viewmedicinelist.ListHolder>
    {




        @NonNull
        @Override
        public viewmedicinelist.ListHolder onCreateViewHolder(@NonNull ViewGroup vi, int i) {

            View view = LayoutInflater.from(viewmedicinelist.this).inflate(R.layout.viewmed_cell, vi , false);


            viewmedicinelist.ListHolder holder = new ListHolder(view);

            return holder;


        }

        @Override
        public void onBindViewHolder(@NonNull viewmedicinelist.ListHolder listHolder, int i) {

            ViewMedListDataModel dataModel = viewmedicine_list.get(i);

            listHolder.text_viewmedname.setText( dataModel.medicinename_x );

            listHolder.text_viewsalt.setText(dataModel.saltname);

            listHolder.text_viewmedprice.setText(dataModel.price);



        }



        @Override
        public int getItemCount() {

            return viewmedicine_list.size();
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
                    ViewMedListDataModel dataModel1 = snapshot.getValue(ViewMedListDataModel.class);

                    viewmedicine_list.add(dataModel1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}


