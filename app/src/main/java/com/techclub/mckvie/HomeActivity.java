package com.techclub.mckvie;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,Tab1.OnFragmentInteractionListener,Tab2.OnFragmentInteractionListener,Tab3.OnFragmentInteractionListener {

    DrawerLayout drawerLayout;

    boolean isOpen = false;
    TextView textViewName, textViewEmail;
    String admin1;

    private FirebaseAuth mAuth;

    View hView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setuptoolbar();

        mAuth = FirebaseAuth.getInstance();

        floating_button();

        buttonTap();

        final NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final ImageView img_banner = findViewById(R.id.home_image);
        final ImageView forward = findViewById(R.id.front_arrow);
        final ImageView backward = findViewById(R.id.back_arrow);


        new Runnable() {
            int currentIndex = 1;
            int updateInterval = 5000;

            @Override
            public void run() {

                currentIndex += 1;
                if(currentIndex > 5){
                    currentIndex = 1;
                }

                forward.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentIndex += 1;

                        if(currentIndex > 5){
                            currentIndex = 1;
                        }

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Banner/banner"+currentIndex);

                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String url = dataSnapshot.getValue(String.class);
                                ImageViewAnimatedChange(HomeActivity.this, url, img_banner);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
                    }
                });

                backward.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentIndex -= 1;

                        if(currentIndex == 0){
                            currentIndex = 5;
                        }

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Banner/banner"+currentIndex);

                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String url = dataSnapshot.getValue(String.class);
                                ImageViewAnimatedChange(HomeActivity.this, url, img_banner);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Banner/banner"+currentIndex);

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String url = dataSnapshot.getValue(String.class);
                        ImageViewAnimatedChange(HomeActivity.this, url, img_banner);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                img_banner.postDelayed(this, updateInterval);
            }
        }.run();

        hView = navigationView.inflateHeaderView(R.layout.navigation_header);

        textViewName = hView.findViewById(R.id.username);
        textViewEmail = hView.findViewById(R.id.useremail);

        if (mAuth.getCurrentUser() != null) {
            FirebaseDatabase database1 = FirebaseDatabase.getInstance();
            DatabaseReference ref1 = database1.getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid());

            ref1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    admin1 = dataSnapshot.child("admin").getValue(String.class);
                    textViewName.setText(name);
                    textViewEmail.setText(email);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.navigation_menu_logout);
        }
        else {

            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.navigation_menu_login);
        }

        FirebaseRecyclerAdapter<object, HomeActivity.NewsViewHolder> mPeopleRVAdapter2;
        final TextView title = findViewById(R.id.textViewTitle);

        mPeopleRVAdapter2 = mckvie_notices("Notices/all");
        mPeopleRVAdapter2.startListening();
        title.setText("NOTICES");

        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Notices"));
        tabLayout.addTab(tabLayout.newTab().setText("News"));
        tabLayout.addTab(tabLayout.newTab().setText("Events"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                FirebaseRecyclerAdapter<object, HomeActivity.NewsViewHolder> mPeopleRVAdapter2;

                if(tab.getPosition() == 1){
                    mPeopleRVAdapter2 = mckvie_notices("Notices/News");
                    mPeopleRVAdapter2.startListening();
                    title.setText("NEWS");
                }

                if(tab.getPosition() == 0){
                    mPeopleRVAdapter2 = mckvie_notices("Notices/all");
                    mPeopleRVAdapter2.startListening();
                    title.setText("NOTICES");
                }

                if(tab.getPosition() == 2){
                    mPeopleRVAdapter2 = mckvie_notices("Notices/Events");
                    mPeopleRVAdapter2.startListening();
                    title.setText("EVENTS");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        ImageView profile = findViewById(R.id.profile_image);

        /*profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser()!=null){
                    Intent myIntent = new Intent(HomeActivity.this, ProfileActivity.class);
                    startActivity(myIntent);
                } else {
                    Intent myIntent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(myIntent);
                }
            }
        });

        final ImageView logoutButton = findViewById(R.id.logout_image);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView profile = findViewById(R.id.profile_image);
                Bitmap profilePic = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

                FirebaseAuth.getInstance().signOut();
                if(mAuth.getCurrentUser() == null){
                    textViewName.setText("Welcome to the Official App of");
                    textViewEmail.setText("MCKV Institute of Engineering");

                    navigationView.getMenu().clear();
                    navigationView.inflateMenu(R.menu.navigation_menu_login);
                    profile.setImageBitmap(profilePic);
                    Toast.makeText(HomeActivity.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                    logoutButton.setVisibility(View.GONE);
                }
            }
        });

        Button signin = findViewById(R.id.sign_in_1);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(myIntent);
            }
        });*/

    }

    public static void ImageViewAnimatedChange(final Context c, final String url, final ImageView v) {
        final Animation anim_out = AnimationUtils.loadAnimation(c, android.R.anim.fade_out);
        final Animation anim_in  = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation)
            {
                Picasso.with(c).load(url)
                        .into(v);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}
                    @Override public void onAnimationEnd(Animation animation) {}
                });
                v.startAnimation(anim_in);
            }
        });
        v.startAnimation(anim_out);
    }

    public FirebaseRecyclerAdapter<object, HomeActivity.NewsViewHolder> mckvie_notices(String dept_out) {

        FirebaseRecyclerAdapter<object, HomeActivity.NewsViewHolder> mPeopleRVAdapter;

        setTitle(dept_out);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(dept_out);
        mDatabase.keepSynced(true);
        RecyclerView mPeopleRV = findViewById(R.id.myRecycleView1);
        final ProgressBar mProgress = findViewById(R.id.progress_home);
        mProgress.setVisibility(View.VISIBLE);

        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference().child(dept_out);
        Query personsQuery = personsRef.orderByKey();

        mPeopleRV.hasFixedSize();
        mPeopleRV.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<object>().setQuery(personsQuery, object.class).build();

        mPeopleRVAdapter = new FirebaseRecyclerAdapter<object, HomeActivity.NewsViewHolder>(personsOptions) {
            @Override
            protected void onBindViewHolder(HomeActivity.NewsViewHolder holder, final int position, final object model) {
                holder.setTitle(model.getTitle());
                holder.setTime(model.getTime());

                mProgress.setVisibility(View.INVISIBLE);

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = model.getUrl();
                        if(!url.equals("none")) {
                            Intent intent = new Intent(HomeActivity.this, webview.class);
                            intent.putExtra("id", url);
                            startActivity(intent);
                        }
                    }
                });

                holder.setIsRecyclable(false);
            }

            @Override
            public HomeActivity.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_row, parent, false);

                return new HomeActivity.NewsViewHolder(view);
            }
        };

        mPeopleRV.setAdapter(mPeopleRVAdapter);

        return mPeopleRVAdapter;

    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        View mView;
        String date1;

        public NewsViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            TextView post_title = (TextView)mView.findViewById(R.id.post_title);
            post_title.setText(title.substring(0,1).toUpperCase() + title.substring(1));
        }

        private void setTime(String time){
            ImageView newLogo = mView.findViewById(R.id.new_logo);
            date1 = time;

            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date post_date = format.parse(date1);
                Calendar c = Calendar.getInstance();
                c.setTime(post_date);
                c.add(Calendar.DATE, 2);
                Date futureDate = c.getTime();
                Date currentDate = Calendar.getInstance().getTime();

                if (!currentDate.after(futureDate)) {
                    newLogo.setVisibility(View.VISIBLE);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void buttonTap() {

        TextView syllabus =findViewById(R.id.syllabus);
        ImageView iv_play = findViewById(R.id.iv_play_pause);
        TextView know_mckvie = findViewById(R.id.know_mckvie);
        TextView Contactus = findViewById(R.id.contact_us);
        TextView marks = findViewById(R.id.online_mark);
        TextView feed_back = findViewById(R.id.feedback);
        TextView attendance = findViewById(R.id.attendance);

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, webview.class);
                intent.putExtra("id", "https://bit.ly/2JWAZpU");
                startActivity(intent);
            }
        });

        syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, Syllabus.class));
            }
        });



        iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, YoutubeActivity.class));
            }
        });

        know_mckvie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, know_mckvie.class));
            }
        });
        Contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, contact_us.class));
            }
        });

        marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAuth.getCurrentUser() != null) {
                    startActivity(new Intent(HomeActivity.this, MarksActivity.class));
                } else {
                    Toast.makeText(HomeActivity.this, "Please Sign In First", Toast.LENGTH_LONG).show();
                }
            }
        });
        feed_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, feedback.class));
            }
        });
    }

    public void floating_button() {
        //floating action button start
        final FloatingActionButton  fab_plus = findViewById(R.id.fab_plus);
        final FloatingActionButton  fab_call = findViewById(R.id.fab_call);
        final FloatingActionButton  fab_message = findViewById(R.id.fab_message);
        final FloatingActionButton  fab_email = findViewById(R.id.fab_email);
        final Animation FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_opn);
        final Animation FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        final Animation FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clkwse);
        final Animation FabRanti = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlkwse);


        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    fab_call.startAnimation(FabClose);
                    fab_message.startAnimation(FabClose);
                    fab_email.startAnimation(FabClose);
                    fab_plus.startAnimation(FabRanti);
                    fab_message.setClickable(false);
                    fab_call.setClickable(false);
                    fab_email.setClickable(false);
                    isOpen = false;
                } else {
                    fab_call.startAnimation(FabOpen);
                    fab_message.startAnimation(FabOpen);
                    fab_email.startAnimation(FabOpen);
                    fab_plus.startAnimation(FabRClockwise);
                    fab_call.setClickable(true);
                    fab_message.setClickable(true);
                    fab_email.setClickable(true);
                    isOpen = true;
                }
            }
        });
        //floating action button en

        //chat start
        fab_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, chatmain.class);
                startActivity(intent);
            }
        });

        fab_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser() != null) {
                    Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        fab_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MarksActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();

        //ImageView logoutButton = findViewById(R.id.logout_image);
        //Button signin = findViewById(R.id.sign_in_1);

        if (mAuth.getCurrentUser() != null) {

            try {
                File f = new File("/data/user/0/com.techclub.mckvie/app_imageDir", "profile_pic.jpg");
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                ImageView img = hView.findViewById(R.id.profile_image);
                img.setImageBitmap(b);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }

            //signin.setVisibility(View.GONE);
            //logoutButton.setVisibility(View.VISIBLE);
        }
        else {
            textViewName.setText("Welcome to the Official App of");
            textViewEmail.setText("MCKV Institute of Engineering");

            //signin.setVisibility(View.VISIBLE);
            //logoutButton.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Intent myIntent;

        Bitmap profilePic = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

        NavigationView navigationView = findViewById(R.id.nav_view);

        switch (item.getItemId()) {

            case R.id.nav_signin:
                myIntent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(myIntent);

                break;

            case R.id.admission:
                myIntent = new Intent(HomeActivity.this, AdmissionActivity.class);
                startActivity(myIntent);

                break;

            case R.id.placement:
                myIntent = new Intent(HomeActivity.this, PlacementActivity.class);
                startActivity(myIntent);

                break;

            case R.id.nav_account:
                myIntent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(myIntent);
                break;

            case R.id.nav_signout:
                FirebaseAuth.getInstance().signOut();
                if(mAuth.getCurrentUser() == null){
                    textViewName.setText("Welcome to the Official App of");
                    textViewEmail.setText("MCKV Institute of Engineering");

                    navigationView.getMenu().clear();
                    navigationView.inflateMenu(R.menu.navigation_menu_login);
                    ImageView img = findViewById(R.id.profile_image);
                    img.setImageBitmap(profilePic);
                    Toast.makeText(HomeActivity.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.location:
                Uri mapUri = Uri.parse("geo:0,0?q=22.619659, 88.347703(MCKV Institute Of Engineering)");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;
            case R.id.info:
                myIntent = new Intent(HomeActivity.this,info.class);
                myIntent.putExtra("flag", 0);
                startActivity(myIntent);
                break;

            case R.id.notices:
                myIntent = new Intent(HomeActivity.this, NoticeActivity.class);
                myIntent.putExtra("flag", 2);
                startActivity(myIntent);
                break;

            case R.id.dept_notices:
                myIntent = new Intent(HomeActivity.this, NoticeActivity.class);
                myIntent.putExtra("flag", 1);
                startActivity(myIntent);
                break;

            case R.id.news:
                myIntent = new Intent(HomeActivity.this,NoticeActivity.class);
                myIntent.putExtra("flag", 3);
                startActivity(myIntent);
                break;

            case R.id.events:
                myIntent = new Intent(HomeActivity.this, NoticeActivity.class);
                myIntent.putExtra("flag", 4);
                startActivity(myIntent);
                break;

            case R.id.admin:
                if(mAuth.getCurrentUser() == null) {
                    myIntent = new Intent(HomeActivity.this,LoginActivity.class);
                    startActivity(myIntent);
                }
                else {
                    if(admin1.equals("true")) {
                        myIntent = new Intent(HomeActivity.this, admin_app.class);
                        startActivity((myIntent));
                    }
                    else {
                        Toast.makeText(HomeActivity.this, "Administrator Rights Required", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody="App Link Here";
                String shareSubject="Share This App";
                sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
                startActivity(Intent.createChooser(sharingIntent,"Share Using"));
                break;

            case R.id.about_us:
                myIntent = new Intent(HomeActivity.this, about_us.class);
                myIntent.putExtra("flag", 5);
                startActivity(myIntent);
                break;

        }
        return true;
    }

    private void setuptoolbar()
    {
        drawerLayout = findViewById(R.id.draw);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        final ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }


    @Override
    protected void onStart() {
        super.onStart();




    }

    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(homeIntent);
        }
    }

}