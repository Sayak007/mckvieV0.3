package com.techclub.mckvie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class depsandprogs extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depsandprogs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        TextView aue = (TextView)findViewById(R.id.aue);
        TextView cse = (TextView)findViewById(R.id.cse);
        TextView ece = (TextView)findViewById(R.id.ece);
        TextView it = (TextView)findViewById(R.id.it);
        TextView me = (TextView)findViewById(R.id.me);
        TextView ee = (TextView)findViewById(R.id.ee);
        TextView ecse = (TextView)findViewById(R.id.ecse);
        TextView mca = (TextView)findViewById(R.id.mca);

        aue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(depsandprogs.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/departments-and-programs/automobile-engineering/");
                startActivity(intent1);
            }
        });
        cse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(depsandprogs.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/departments-and-programs/computer-science-engineering/");
                startActivity(intent1);
            }
        });
        ece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(depsandprogs.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/departments-and-programs/electronics-communication-engineering/");
                startActivity(intent1);
            }
        });
        ee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(depsandprogs.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/departments-and-programs/electrical-engineering/");
                startActivity(intent1);
            }
        });
        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(depsandprogs.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/departments-and-programs/information-technology/");
                startActivity(intent1);
            }
        });
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(depsandprogs.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/departments-and-programs/mechanical-engineering/");
                startActivity(intent1);
            }
        });
        ecse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(depsandprogs.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/departments-and-programs/electronics-communication-engineering/");
                startActivity(intent1);
            }
        });
        mca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(depsandprogs.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/departments-and-programs/mca/");
                startActivity(intent1);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp () {
        onBackPressed();
        return true;
    }
}
