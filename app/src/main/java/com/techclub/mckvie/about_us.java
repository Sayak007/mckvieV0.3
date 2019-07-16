package com.techclub.mckvie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.security.PrivateKey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class about_us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        TextView h = (TextView) findViewById(R.id.h);
        TextView hh = (TextView) findViewById(R.id.hh);
        TextView hhh = (TextView) findViewById(R.id.hhh);
        TextView hhhh = (TextView) findViewById(R.id.hhhh);
        TextView hhhhh = (TextView) findViewById(R.id.hhhhh);
        TextView emaill = (TextView) findViewById(R.id.emaill);
        ImageView fb = (ImageView) findViewById(R.id.fb_icon);
        ImageView twitter = (ImageView) findViewById(R.id.twitter_icon);
        final TextView devs =(TextView)findViewById(R.id.devs);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent brow = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com"));
                startActivity(brow);
            }
        });
        twitter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent brow = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com"));
                startActivity(brow);
            }
        });

        devs.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(about_us.this, devs);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.developers, popup.getMenu());

                Object menuHelper;
                Class[] argTypes;
                try {
                    Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
                    fMenuHelper.setAccessible(true);
                    menuHelper = fMenuHelper.get(popup);
                    argTypes = new Class[] { boolean.class };
                    menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
                } catch (Exception e) {
                }
                popup.show();
            }
        });
    }

        public void process(android.view.View x){
            if (x.getId() == R.id.emaill) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                String[] to = {"SayakNOOB@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.setType("message/rfc822");
                Intent chooser = Intent.createChooser(intent, "Send Email");
                startActivity(chooser);
            }
        }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}








