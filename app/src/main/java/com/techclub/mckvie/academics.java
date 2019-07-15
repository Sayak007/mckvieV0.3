package com.techclub.mckvie;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class academics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academics);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        final ImageView more =(ImageView)findViewById(R.id.more);
        TextView depsandpgs = (TextView) findViewById(R.id.dept);
        TextView labs = (TextView) findViewById(R.id.labs);

        labs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(academics.this,labsandfacilities.class));
            }
        });

        depsandpgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(academics.this, depsandprogs.class));
            }
        });


        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(academics.this, more);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.acad, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.acadone:
                                startActivity(new Intent(academics.this, Syllabus.class));
                                break;
                            case R.id.acadtwo:
                                startActivity(new Intent(academics.this, academiccalendar.class));
                                break;

                            case R.id.acadthree:
                                startActivity(new Intent(academics.this, yearlyPlanner.class));
                                break;
                            case R.id.acadfour:
                                startActivity(new Intent(academics.this, depsandprogs.class));
                                break;
                            case R.id.acadfive:
                                startActivity(new Intent(academics.this, labsandfacilities.class));
                                break;
                            case R.id.acadsix:
                                Intent intent1 = new Intent(academics.this, webview.class);
                                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/university-toppers/");
                                startActivity(intent1);
                                break;
                            case R.id.acadseven:
                                Intent intent2 = new Intent(academics.this, webview.class);
                                intent2.putExtra("id", "https://www.mckvie.edu.in/academics/publication/");
                                startActivity(intent2);
                                break;
                            case R.id.acadeight:
                                Intent intent = new Intent(academics.this, webview.class);
                                intent.putExtra("id", "https://www.mckvie.edu.in/academics/heads-of-the-departments/");
                                startActivity(intent);
                                break;
                        }
                        Toast.makeText(
                                academics.this,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });

    }
        @Override
        public boolean onSupportNavigateUp () {
            onBackPressed();
            return true;
        }

}

