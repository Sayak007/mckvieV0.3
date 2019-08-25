package com.techclub.mckvie;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class AdminMarksActivity extends AppCompatActivity {

    private String dept,sem,year,ct;
    public static final int PICK_FILE_REQUEST=2;
    private static final int READ_REQUEST_CODE = 42;
    private Uri fileUri;
    public String filePath;
    EditText paper;
    private String roll,marks;
    DatabaseReference mDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmarks);

        Spinner spinner2 = findViewById(R.id.spinner2);
        Spinner spinner3 = findViewById(R.id.spinner3);
        Spinner spinner4 = findViewById(R.id.spinner6);
        Spinner spinner5 = findViewById(R.id.spinner7);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Marks/");

        //Button submitButton = findViewById(R.id.button2);

        paper = findViewById(R.id.paper);

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

        /*submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (paper.getText().toString().isEmpty()) {
                    paper.setError("Enter a Valid Paper Code");
                    paper.requestFocus();
                    return;
                }



                /*if(!rn1.getText().toString().isEmpty() && !m1.getText().toString().isEmpty()) {
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
        });*/
    }

    public void chooseFile(View view)
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    public void uploadFile(View view)
    {
        if (paper.getText().toString().isEmpty()) {
            paper.setError("Enter a Valid Paper Code");
            paper.requestFocus();
            return;
        }

        try {
            File file = new File(filePath);
            FileInputStream myInput = new FileInputStream(file);

            // Create a POI File System object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            // We now need something to iterate through the cells.
            Iterator<Row> rowIterator = mySheet.rowIterator();
            //Iterator<Keyboard.Row> rowIter = mySheet.rowIterator();
            int rowno =0;
            String temp;
            while (rowIterator.hasNext()) {
                Log.d("row", " row no "+ rowno );
                HSSFRow myRow = (HSSFRow) rowIterator.next();
                if(rowno > 4) {
                    Iterator<Cell> cellIter = myRow.cellIterator();
                    int colno =0;
                    while (cellIter.hasNext()) {
                        HSSFCell myCell = (HSSFCell) cellIter.next();
                        if (colno==0){
                            temp = myCell.toString();
                            roll = temp.substring(0,temp.indexOf("."));
                        }else if (colno==1) {
                            temp = myCell.toString();
                            marks = temp.substring(0,temp.indexOf("."));
                        }
                        colno++;

                    }
                    mDatabase.child(dept+year+sem+ct+roll+"/"+paper.getText().toString()).setValue(marks);
                    Log.d("row", dept+year+sem+ct+roll+"/"+paper.getText().toString());
                }
                rowno++;
            }
        } catch (Exception e) {
            Log.e("Error", "error "+ e.toString());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            if (data != null) {
                fileUri = data.getData();
                String Path = fileUri.getPath();
                filePath = Path.substring(Path.indexOf("/storage"));
                Toast.makeText(this, filePath , Toast.LENGTH_LONG).show();

                //filePath.substring(filePath.indexOf("/storage"));
            }
        }
    }
}
