package com.techclub.mckvie;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.google.developers.mobile.targeting.proto.Conditions;

import org.w3c.dom.Text;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class aff_acc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aff_acc);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        TextView clk1 = (TextView) findViewById(R.id.clk1);
        TextView clk2 = (TextView) findViewById(R.id.clk2);
        TextView clk3 = (TextView) findViewById(R.id.clk3);
        TextView clk4 = (TextView) findViewById(R.id.clk4);
        TextView clk5 = (TextView) findViewById(R.id.clk5);
        TextView clk6 = (TextView) findViewById(R.id.clk6);
        TextView clk7 = (TextView) findViewById(R.id.clk7);
        TextView clk8 = (TextView) findViewById(R.id.clk8);
        TextView clk9= (TextView) findViewById(R.id.clk9);
        TextView clk10 = (TextView) findViewById(R.id.clk10);


        clk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent brow = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mckvie.edu.in/site/assets/files/1123/nba_me_ee_cse_ece.pdf"));
                startActivity(brow);
            }
        });
        clk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent brow = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.mckvie.edu.in/site/assets/files/1123/mckv_institute_of_engineering30_3_2019_13_59_51.pdf"));
                startActivity(brow);
            }
        });
        clk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent brow = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.mckvie.edu.in/site/assets/files/1123/nba.pdf"));
                startActivity(brow);
            }
        });
        clk4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent brow = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.mckvie.edu.in/site/assets/files/1210/nba_accredition_letters.pdf"));
                startActivity(brow);
            }
        });
        clk5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent brow = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.mckvie.edu.in/site/assets/files/1210/nba-ece-extension.pdf"));
                startActivity(brow);
            }
        });
        clk6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent brow = new Intent(Intent.ACTION_VIEW,Uri.parse("https://mckvie.edu.in/site/assets/files/1123/makaut_affiliation_b_tech-_mtech-_mca.pdf"));
                startActivity(brow);
            }
        });
        clk7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent brow = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.mckvie.edu.in/site/assets/files/1123/makaut_affiliation_2018-19.pdf"));
                startActivity(brow);
            }
        });
        clk8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent brow = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.mckvie.edu.in/site/assets/files/1123/mca_affiliation.pdf"));
                startActivity(brow);
            }
        });
        clk9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent brow = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.mckvie.edu.in/site/assets/files/1123/mtech_affiliation.pdf"));
                startActivity(brow);
            }
        });
        clk10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent brow = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.mckvie.edu.in/site/assets/files/1123/makaut_aafiliation.pdf"));
                startActivity(brow);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}