package com.techclub.mckvie;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class wallchat extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallchat);
        
        ImageView wall1 = (ImageView) findViewById(R.id.wall1);
        ImageView wall2 = (ImageView) findViewById(R.id.wall2);

        wall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}