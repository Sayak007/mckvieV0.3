package com.techclub.mckvie;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class admin_app extends AppCompatActivity {

    EditText Desc,Image,Url,Title;
    Button Insert;
    FirebaseDatabase database;
    DatabaseReference ref;
    Query ref2;
    object object1;
    int c=10000;
    String msg1="10000";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_app);

        Desc=(EditText)findViewById(R.id.desc1);
        Image=(EditText)findViewById(R.id.image1);
        Url=(EditText)findViewById(R.id.url1);
        Title=(EditText)findViewById(R.id.title1);
        Insert=(Button) findViewById(R.id.insert1);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Notices");
        ref2 = FirebaseDatabase.getInstance().getReference().child("Notices").orderByKey().limitToFirst(1);
        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    msg1=child.getKey();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        object1 = new object();

    }

    private void getvalues(){
        object1.setDesc(Desc.getText().toString());
        object1.setImage(Image.getText().toString());
        object1.setTitle(Title.getText().toString());
        object1.setUrl(Url.getText().toString());
    }

    public void btinsert(View view){
        if(Integer.parseInt(msg1)!=0)
        c=Integer.parseInt(msg1);
        c=c-1;
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getvalues();
                ref.child(Integer.toString(c)).setValue(object1);
                Toast.makeText(admin_app.this,"Notice Inserted...",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        finish();
    }
}