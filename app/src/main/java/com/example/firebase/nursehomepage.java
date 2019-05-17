package com.example.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.firebase.chat.NurseUsers;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mvc.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;

public class nursehomepage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView profile_image;
    String imageUrl="";
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nursehomepage);
        pd = new ProgressDialog(nursehomepage.this);
        pd.setTitle("Loading");
        pd.setMessage("Please Wait..");

        // width and height will be at least 600px long (optional).
        ImagePicker.setMinQuality(600, 600);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        profile_image = header.findViewById(R.id.imageView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent= new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"hey checkout my application");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
        // TODO do something with the bitmap

        profile_image.setImageBitmap(bitmap);
        uploadFile(bitmap);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nursehomepage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_homepage) {
            Intent i = new Intent(nursehomepage.this, nursehomepage.class);
            startActivity(i);

        } else if (id == R.id.nav_password) {
            Intent i = new Intent(nursehomepage.this, changepassword.class);
            startActivity(i);

        } else if (id == R.id.nav_profile) {
            Intent i = new Intent(nursehomepage.this, editnurseprofile.class);
            startActivity(i);


        } else if (id == R.id.nav_chats) {

            Intent i = new Intent(nursehomepage.this, NurseUsers.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            Intent sendIntent= new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,"hey checkout my application");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);


        } else if (id == R.id.nav_logout) {
            FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
            firebaseAuth.signOut();
            Intent i = new Intent(nursehomepage.this, registeractivity.class);
            finish();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void opencamera2(View v) {
        ImagePicker.pickImage(this, "Select your image:");
    }
    public void add_inpatientrecord(View v){
        Intent i=new Intent(nursehomepage.this,addinpatient.class);
        startActivity(i);
    }

    public void view_medicationnur(View v) {
        Intent i = new Intent(nursehomepage.this, viewmedicinelist.class);
        startActivity(i);
    }

    public void view_stafflist(View v) {
        Intent i = new Intent(nursehomepage.this, viewstaff.class);
        startActivity(i);
    }

    public void add_appointments(View v) {
        Intent i = new Intent(nursehomepage.this, addappointment.class);
        startActivity(i);
    }

    public void open_checklist(View v) {
        Intent i = new Intent(nursehomepage.this, nursechecklist.class);
        startActivity(i);
    }

    public void open_history(View v) {
        Intent i = new Intent(nursehomepage.this, patienthistory.class);
        startActivity(i);


    }
    private void uploadFile(Bitmap bitmap) {

        pd.show();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference mountainImagesRef = storageRef.child("images/" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = mountainImagesRef.putBytes(data);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                if (taskSnapshot.getMetadata() != null) {
                    if (taskSnapshot.getMetadata().getReference() != null) {
                        Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                        result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                pd.hide();

                                imageUrl = uri.toString();
                                //createNewPost(imageUrl);
                            }
                        });
                    }
                }
            }});

    }
}