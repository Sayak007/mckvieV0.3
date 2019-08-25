package com.techclub.mckvie;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.inputmethodservice.Keyboard;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class admin_app extends AppCompatActivity {

    public static final int PICKFILE_RESULT_CODE = 1;
    public static final int PICK_FILE_REQUEST=2;

    private Uri fileUri;
    public String filePath;
    object object1;
    String msg1 = "10000";
    int c = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_app);


        Button Insert = findViewById(R.id.insert1);
        Button Insertmarks = findViewById(R.id.insert2);

        Query ref2 = FirebaseDatabase.getInstance().getReference().child("Notices/all").orderByKey().limitToFirst(1);
        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    msg1 = child.getKey();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        object1 = new object();

        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(admin_app.this);
                builder.setMessage("Are you sure to upload?");
                builder.setCancelable(true);
                builder.setPositiveButton("Recheck", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                EditText Title = findViewById(R.id.title1);


                if (Title.getText().toString().isEmpty()) {
                    Title.setError(getString(R.string.title_error));
                    Title.requestFocus();
                    return;
                }

                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (Integer.parseInt(msg1) != 0)
                            c = Integer.parseInt(msg1);
                        c = c - 1;
                        FirebaseDatabase.getInstance().getReference("Notices/all").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                getvalues();
                                FirebaseDatabase.getInstance().getReference("Notices/all").child(Integer.toString(c)).setValue(object1);
                                Toast.makeText(admin_app.this, "Notice Inserted", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        });



        Insertmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(admin_app.this, AdminMarksActivity.class);
                startActivity(intent);

            }
        });
    }

    private void getvalues(){

        EditText Desc = findViewById(R.id.desc1);
        EditText Image = findViewById(R.id.image1);
        EditText Url = findViewById(R.id.url1);
        EditText Title = findViewById(R.id.title1);

        if(Desc.getText().toString().isEmpty()) {
            object1.setDesc("none");
        }
        else {
            object1.setDesc(Desc.getText().toString().trim());
        }
        if(Title.getText().toString().isEmpty()) {
            object1.setTitle("none");
        }
        else {
            object1.setTitle(Title.getText().toString().trim());
        }
        if(Image.getText().toString().isEmpty()) {
            object1.setImage("none");
        }
        else {
            object1.setImage(Image.getText().toString().trim());
        }
        if(Url.getText().toString().isEmpty()) {
            object1.setUrl("none");
        }
        else {
            object1.setUrl(Url.getText().toString().trim());
        }

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c);

        object1.setTime(formattedDate);
    }

    @Override
    public void onStop() {
        super.onStop();
        FirebaseDatabase.getInstance().goOffline();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}