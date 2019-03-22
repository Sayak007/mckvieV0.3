package com.techclub.mckvie;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MarksActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView marksList;
    private Spinner spinner1,spinner2,spinner3;
    private EditText editTextRoll;
    private Button submitButton;
    private String course,dept,sem;

    private ArrayList<String> mMarks = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);

        spinner1 = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        editTextRoll = findViewById(R.id.editTextroll);

        marksList = findViewById(R.id.listView_marks);

        submitButton = findViewById(R.id.button2);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mMarks);
        marksList.setAdapter(arrayAdapter);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<>(MarksActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Courses));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter1);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(MarksActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Department));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdapter2);

        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<>(MarksActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Sem));
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(myAdapter3);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    dept = "cse/";
                } else if (i == 1) {
                    dept = "me/";
                } else if (i == 2) {
                    dept = "it/";
                } else if (i == 3) {
                    dept = "ece/";
                } else if (i == 4) {
                    dept = "ee/";
                } else if (i == 5) {
                    dept = "aue/";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    course = "btech/";
                } else if (i == 1) {
                    course = "mtech/";
                } else if (i == 2) {
                    course = "mca/";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    sem = "1sem/";
                } else if (i == 1) {
                    sem = "2sem/";
                } else if (i == 2) {
                    sem = "3sem/";
                } else if (i == 3) {
                    sem = "4sem/";
                } else if (i == 4) {
                    sem = "5sem/";
                } else if (i == 5) {
                    sem = "6sem/";
                } else if (i == 6) {
                    sem = "7sem/";
                } else if (i == 7) {
                    sem = "8sem/";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayAdapter.clear();
                arrayAdapter.notifyDataSetChanged();

                mDatabase = FirebaseDatabase.getInstance().getReference().child("Marks/"+ course + dept + sem + editTextRoll.getText().toString());

                mDatabase.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        String value = dataSnapshot.getValue().toString();
                        String name = dataSnapshot.getKey();
                        mMarks.add(name+":      "+value);
                        arrayAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        Toast.makeText(MarksActivity.this, "Item not Found", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }

}



