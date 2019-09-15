package com.techclub.mckvie;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;

import static com.techclub.mckvie.admin_app.PICK_FILE_REQUEST;


public class Testing extends AppCompatActivity {

    private Uri fileUri;
    public String filePath;
    private String roll,marks;
    private String dept,sem,year,ct;
    TextView filename;
    ConstraintLayout con1;
    Button Cancel;
    EditText paper;
    private String str[][]=new String[100][100];
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing);

        Spinner spinner2 = findViewById(R.id.spinner2);
        Spinner spinner3 = findViewById(R.id.spinner3);
        Spinner spinner4 = findViewById(R.id.spinner6);
        Spinner spinner5 = findViewById(R.id.spinner7);

        final Button chooseFile = (Button) findViewById(R.id.button4);
        con1 = (ConstraintLayout)findViewById(R.id.rl1);
        Button submitButton = findViewById(R.id.button2);
        filename=(TextView)findViewById(R.id.filename);
        paper = findViewById(R.id.paper);
        Cancel =(Button)findViewById(R.id.canccel);

        ArrayAdapter<String> myAdapter5 = new ArrayAdapter<>(Testing.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ct));
        myAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(myAdapter5);

        ArrayAdapter<String> myAdapter4 = new ArrayAdapter<>(Testing.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.year));
        myAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(myAdapter4);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(Testing.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Department));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdapter2);

        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<>(Testing.this,
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

        chooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paper.getText().toString().isEmpty()) {
                    paper.setError("Enter a Valid Paper Code");
                    paper.requestFocus();
                    return;
                }
                else {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(Intent.createChooser(intent, "Select Excel"), PICK_FILE_REQUEST);
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile(filePath);
                filename.setText("");
                con1.setVisibility(View.GONE);
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filename.setText("");
                con1.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FILE_REQUEST && resultCode == Activity.RESULT_OK) {

            if (data != null) {
                fileUri = data.getData();
                filePath= fileUri.getPath();
                //filePath = Path.substring(Path.indexOf("/storage"));
                Toast.makeText(this, filePath , Toast.LENGTH_LONG).show();
                //filePath.substring(filePath.indexOf("/storage"));
                int lasti = filePath.lastIndexOf("/");
                String m = filePath.substring(lasti+1);
                filename.setText(m);
                con1.setVisibility(View.VISIBLE);
            }
        }
    }

    public  void uploadFile(String fp){
        try {
            File file = new File(fp);
            FileInputStream myInput = new FileInputStream(file);
            Workbook wb = new HSSFWorkbook(myInput);
            Sheet mySheet = wb.getSheetAt(0);
            Iterator<Row> rowIterator = mySheet.rowIterator();
            //System.out.println(mySheet.getRow(1).getCell(0));
            int rowno = 0,crr=0,ccc=0;
            String temp;
            while (rowIterator.hasNext()) {
                Log.d("row", " row no " + rowno);
                HSSFRow myRow = (HSSFRow) rowIterator.next();
                Iterator<Cell> cellIter = myRow.cellIterator();
                int colno = 0;
                while (cellIter.hasNext()) {
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    ccc=colno;
                    if (colno == 0) {
                        temp = myCell.toString();
                        roll = temp.substring(0, temp.indexOf("."));
                        str[crr][ccc]=roll;
                    } else if (colno == 1) {
                        temp = myCell.toString();
                        marks = temp.substring(0, temp.indexOf("."));
                        str[crr][ccc]=marks;
                    }
                    colno++;
                    if(colno==2)
                        break;
                }
                FirebaseDatabase.getInstance().getReference().child("Marks/"+dept+year+sem+ct+roll+"/"+paper.getText().toString()).setValue(marks);
                rowno++;
                crr++;
                if(rowno==5)
                    break;
            }
        } catch (Exception e) {
            Log.e("Error", "error " + e.toString());
        }
    }
}
