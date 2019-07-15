package com.techclub.mckvie;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class academiccalendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academiccalendar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        final TextView ac1 = (TextView)findViewById(R.id.tv1);
        final TextView ac2 = (TextView)findViewById(R.id.tv2);
        final TextView ac3 = (TextView)findViewById(R.id.tv3);
        final TextView ac4 = (TextView)findViewById(R.id.tv4);
        final TextView ac5 = (TextView)findViewById(R.id.tv5);

        final DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Academic Calendar/AC1/mString");

        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String url = dataSnapshot.getValue(String.class);
                ac1.setText(url);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final DatabaseReference ref11 = FirebaseDatabase.getInstance().getReference("Academic Calendar/AC1/mUrl");
        ac1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref11.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String url = dataSnapshot.getValue(String.class);
                        //Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });

        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Academic Calendar/AC2/mString");

        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String url1 = dataSnapshot.getValue(String.class);
                ac2.setText(url1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final DatabaseReference ref21 = FirebaseDatabase.getInstance().getReference("Academic Calendar/AC2/mUrl");
        ac2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref21.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String url = dataSnapshot.getValue(String.class);
                        //Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });

        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Academic Calendar/AC3/mString");

        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String url1 = dataSnapshot.getValue(String.class);
                ac3.setText(url1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final DatabaseReference ref31 = FirebaseDatabase.getInstance().getReference("Academic Calendar/AC3/mUrl");
        ac3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref31.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String url = dataSnapshot.getValue(String.class);
                        //Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });

        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("Academic Calendar/AC4/mString");

        ref4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String url1 = dataSnapshot.getValue(String.class);
                ac4.setText(url1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final DatabaseReference ref41 = FirebaseDatabase.getInstance().getReference("Academic Calendar/AC4/mUrl");
        ac4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref41.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String url = dataSnapshot.getValue(String.class);
                        //Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });

        DatabaseReference ref5 = FirebaseDatabase.getInstance().getReference("Academic Calendar/AC5/mString");

        ref5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String url1 = dataSnapshot.getValue(String.class);
                ac5.setText(url1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final DatabaseReference ref51 = FirebaseDatabase.getInstance().getReference("Academic Calendar/AC5/mUrl");
        ac5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref51.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String url = dataSnapshot.getValue(String.class);
                        //Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}