package com.techclub.mckvie;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.techclub.mckvie.R;

public class help_feedback extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_feedback);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");*/
        TextView hlpfdbk = (TextView) findViewById(R.id.hlpfdbk);
        hlpfdbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent brow = new Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/pxxjiYtZQKKzp9zy7"));
                startActivity(brow);
            }
        });
    }
}