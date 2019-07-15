package com.techclub.mckvie;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class wallchat extends AppCompatActivity {

    RelativeLayout chat;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallchat);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        chat=(RelativeLayout)findViewById(R.id.activity_chat);
        ImageView wall1 = (ImageView) findViewById(R.id.wall1);
        ImageView wall2 = (ImageView) findViewById(R.id.wall2);
        ImageView wall3 = (ImageView) findViewById(R.id.wall3);
        ImageView wall4 = (ImageView) findViewById(R.id.wall4);
        ImageView wall5 = (ImageView) findViewById(R.id.wall5);
        ImageView wall6 = (ImageView) findViewById(R.id.wall6);
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        wall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putInt("background_resource", R.drawable.wall1);
                editor.apply();
                    Intent intent = new Intent(wallchat.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(wallchat.this,"Wallpaper Changed...Reopen Chat Forum!",Toast.LENGTH_LONG).show();
            }
        });

        wall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putInt("background_resource", R.drawable.wallchat2);
                editor.apply();
                Intent intent = new Intent(wallchat.this,HomeActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(wallchat.this,"Wallpaper Changed...Reopen Chat Forum!",Toast.LENGTH_LONG).show();
            }
        });

        wall3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putInt("background_resource", R.drawable.wall3);
                editor.apply();
                Intent intent = new Intent(wallchat.this,HomeActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(wallchat.this,"Wallpaper Changed...Reopen Chat Forum!",Toast.LENGTH_LONG).show();
            }
        });

        wall4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putInt("background_resource", R.drawable.wall4);
                editor.apply();
                Intent intent = new Intent(wallchat.this,HomeActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(wallchat.this,"Wallpaper Changed...Reopen Chat Forum!",Toast.LENGTH_LONG).show();
            }
        });

        wall5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putInt("background_resource", R.drawable.walldef);
                editor.apply();
                Intent intent = new Intent(wallchat.this,HomeActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(wallchat.this,"Wallpaper Changed...Reopen Chat Forum!",Toast.LENGTH_LONG).show();
            }
        });

        wall6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putInt("background_resource", R.drawable.wall6);
                editor.apply();
                Intent intent = new Intent(wallchat.this,HomeActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(wallchat.this,"Wallpaper Changed...Reopen Chat Forum!",Toast.LENGTH_LONG).show();
            }
        });

    }

}