package com.techclub.mckvie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class labsandfacilities extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.labsandfacilities);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        ImageView video = (ImageView) findViewById(R.id.video);

        TextView aue = (TextView)findViewById(R.id.auel);
        TextView basic = (TextView)findViewById(R.id.basicl);
        TextView comp = (TextView)findViewById(R.id.compl);
        TextView ece = (TextView)findViewById(R.id.ecel);
        TextView me = (TextView)findViewById(R.id.mel);
        TextView it = (TextView)findViewById(R.id.itl);
        TextView ee = (TextView)findViewById(R.id.eel);
        TextView mca = (TextView)findViewById(R.id.mcal);
        TextView hss = (TextView)findViewById(R.id.hssl);
        TextView library = (TextView)findViewById(R.id.libral);
        TextView hostel = (TextView)findViewById(R.id.hostel);

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(labsandfacilities.this, YoutubeActivity.class));
            }
        });

        aue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(labsandfacilities.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/labs-and-facilities/automobile-labs/");
                startActivity(intent1);
            }
        });
        basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(labsandfacilities.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/labs-and-facilities/basic-labs/");
                startActivity(intent1);
            }
        });
        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(labsandfacilities.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/labs-and-facilities/computer-labs/");
                startActivity(intent1);
            }
        });
        ece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(labsandfacilities.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/labs-and-facilities/electronics-communications-labs/");
                startActivity(intent1);
            }
        });
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(labsandfacilities.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/labs-and-facilities/mechanical-labs/");
                startActivity(intent1);
            }
        });
        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(labsandfacilities.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/labs-and-facilities/it-labs/");
                startActivity(intent1);
            }
        });
        ee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(labsandfacilities.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/labs-and-facilities/electrical-labs/");
                startActivity(intent1);
            }
        });
        mca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(labsandfacilities.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/labs-and-facilities/mca-labs/");
                startActivity(intent1);
            }
        });
        hss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(labsandfacilities.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/labs-and-facilities/hss-labs/");
                startActivity(intent1);
            }
        });
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(labsandfacilities.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/labs-and-facilities/library/");
                startActivity(intent1);
            }
        });
        hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(labsandfacilities.this, webview.class);
                intent1.putExtra("id", "https://www.mckvie.edu.in/academics/labs-and-facilities/hostel-facilities/");
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
