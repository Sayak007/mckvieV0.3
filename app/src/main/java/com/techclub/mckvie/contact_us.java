package com.techclub.mckvie;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class contact_us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactus);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        TextView textView36 = (TextView) findViewById(R.id.textView36);
        TextView textView28 = (TextView) findViewById(R.id.textView28);
        TextView textView24 = (TextView) findViewById(R.id.textView24);
        TextView textView31 = (TextView) findViewById(R.id.textView31);


        textView36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(contact_us.this, visit.class));
            }
        });



        textView28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "9007017339";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9007017339"));


            //if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
              //  return;
            //} else {
                startActivity(intent);
           // }

        }

});
        textView24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "3326549318";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:3326549318"));


                //if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                //  return;
                //} else {
                startActivity(intent);
                // }

            }

        });

        textView31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "3326548604";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:3326548604"));

                startActivity(intent);


            }

        });

    }

    public void process(View view) {
        if (view.getId() == R.id.textView26) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] to = {"principal@mckvie.edu.in", "mckvie@vsnl.net"};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.setType("message/rfc822");
            Intent chooser = Intent.createChooser(intent, "Send Email");
            startActivity(chooser);

        }

        if (view.getId() == R.id.textView30) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] to = {"pa.kk@outlook.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.setType("message/rfc822");
            Intent chooser = Intent.createChooser(intent, "Send Email");
            startActivity(chooser);
        }

        if(view.getId()==R.id.textView35) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] to = {"principal@mckvie.edu.in"};
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
