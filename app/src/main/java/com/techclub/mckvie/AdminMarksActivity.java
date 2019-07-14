package com.techclub.mckvie;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminMarksActivity extends AppCompatActivity {

    private String dept,sem,year,ct;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmarks);

        Spinner spinner2 = findViewById(R.id.spinner2);
        Spinner spinner3 = findViewById(R.id.spinner3);
        Spinner spinner4 = findViewById(R.id.spinner6);
        Spinner spinner5 = findViewById(R.id.spinner7);

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Marks/");

        Button submitButton = findViewById(R.id.button2);

        final EditText rn1 = findViewById(R.id.rn1);
        final EditText rn2 = findViewById(R.id.rn2);
        final EditText rn3 = findViewById(R.id.rn3);
        final EditText rn4 = findViewById(R.id.rn4);
        final EditText rn5 = findViewById(R.id.rn5);
        final EditText rn6 = findViewById(R.id.rn6);
        final EditText rn7 = findViewById(R.id.rn7);
        final EditText rn8 = findViewById(R.id.rn8);
        final EditText rn9 = findViewById(R.id.rn9);
        final EditText rn10 = findViewById(R.id.rn10);
        final EditText rn11 = findViewById(R.id.rn11);

        final EditText m1 = findViewById(R.id.marks1);
        final EditText m2 = findViewById(R.id.marks2);
        final EditText m3 = findViewById(R.id.marks3);
        final EditText m4 = findViewById(R.id.marks4);
        final EditText m5 = findViewById(R.id.marks5);
        final EditText m6 = findViewById(R.id.marks6);
        final EditText m7 = findViewById(R.id.marks7);
        final EditText m8 = findViewById(R.id.marks8);
        final EditText m9 = findViewById(R.id.marks9);
        final EditText m10 = findViewById(R.id.marks10);
        final EditText m11 = findViewById(R.id.marks11);

        final EditText paper = findViewById(R.id.paper);

        ArrayAdapter<String> myAdapter5 = new ArrayAdapter<>(AdminMarksActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ct));
        myAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(myAdapter5);

        ArrayAdapter<String> myAdapter4 = new ArrayAdapter<>(AdminMarksActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.year));
        myAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(myAdapter4);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(AdminMarksActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Department));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdapter2);

        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<>(AdminMarksActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Sem));
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(myAdapter3);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    dept = "CSE/";
                } else if (i == 1) {
                    dept = "ME/";
                } else if (i == 2) {
                    dept = "IT/";
                } else if (i == 3) {
                    dept = "ECE/";
                } else if (i == 4) {
                    dept = "EE/";
                } else if (i == 5) {
                    dept = "AUE/";
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

        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    ct = "ct1/";
                } else if (i == 1) {
                    ct = "ct2/";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    year = "2017/";
                } else if (i == 1) {
                    year = "2018/";
                } else if (i == 2) {
                    year = "2019/";
                } else if (i == 3) {
                    year = "2020/";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (paper.getText().toString().isEmpty()) {
                    paper.setError("Enter a Valid Paper Code");
                    paper.requestFocus();
                    return;
                }


                if(!rn1.getText().toString().isEmpty() && !m1.getText().toString().isEmpty()) {
                    mDatabase.child(dept+year+sem+ct+rn1.getText().toString()+"/"+paper.getText().toString()).setValue(m1.getText().toString());
                }
                if(!rn2.getText().toString().isEmpty() && !m2.getText().toString().isEmpty()) {
                    mDatabase.child(dept+year+sem+ct+rn2.getText().toString()+"/"+paper.getText().toString()).setValue(m2.getText().toString());
                }
                if(!rn3.getText().toString().isEmpty() && !m3.getText().toString().isEmpty()) {
                    mDatabase.child(dept+year+sem+ct+rn3.getText().toString()+"/"+paper.getText().toString()).setValue(m3.getText().toString());
                }
                if(!rn4.getText().toString().isEmpty() && !m4.getText().toString().isEmpty()) {
                    mDatabase.child(dept+year+sem+ct+rn4.getText().toString()+"/"+paper.getText().toString()).setValue(m4.getText().toString());
                }
                if(!rn5.getText().toString().isEmpty() && !m5.getText().toString().isEmpty()) {
                    mDatabase.child(dept+year+sem+ct+rn5.getText().toString()+"/"+paper.getText().toString()).setValue(m5.getText().toString());
                }
                if(!rn6.getText().toString().isEmpty() && !m6.getText().toString().isEmpty()) {
                    mDatabase.child(dept+year+sem+ct+rn6.getText().toString()+"/"+paper.getText().toString()).setValue(m6.getText().toString());
                }
                if(!rn7.getText().toString().isEmpty() && !m7.getText().toString().isEmpty()) {
                    mDatabase.child(dept+year+sem+ct+rn7.getText().toString()+"/"+paper.getText().toString()).setValue(m7.getText().toString());
                }
                if(!rn8.getText().toString().isEmpty() && !m8.getText().toString().isEmpty()) {
                    mDatabase.child(dept+year+sem+ct+rn8.getText().toString()+"/"+paper.getText().toString()).setValue(m8.getText().toString());
                }
                if(!rn9.getText().toString().isEmpty() && !m9.getText().toString().isEmpty()) {
                    mDatabase.child(dept+year+sem+ct+rn9.getText().toString()+"/"+paper.getText().toString()).setValue(m9.getText().toString());
                }
                if(!rn10.getText().toString().isEmpty() && !m10.getText().toString().isEmpty()) {
                    mDatabase.child(dept+year+sem+ct+rn10.getText().toString()+"/"+paper.getText().toString()).setValue(m10.getText().toString());
                }
                if(!rn11.getText().toString().isEmpty() && !m11.getText().toString().isEmpty()) {
                    mDatabase.child(dept+year+sem+ct+rn11.getText().toString()+"/"+paper.getText().toString()).setValue(m11.getText().toString());
                }
            }
        });
    }
}
