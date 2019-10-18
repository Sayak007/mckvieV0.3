package com.techclub.mckvie;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.drm.DrmManagerClient;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import androidx.annotation.NonNull;

import com.github.library.bubbleview.BubbleImageView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.text.format.DateFormat;
import android.widget.Toast;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.github.library.bubbleview.BubbleTextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.developers.mobile.targeting.proto.Conditions;
import com.google.errorprone.annotations.FormatString;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.content.res.ResourcesCompat;
import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.String;
import java.net.URI;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class chatmain extends AppCompatActivity {

    private static int SIGN_IN_REQUEST_CODE = 1;
    private static int CROP_IMAGE_ACTIVITY_REQUEST_CODE = 1;
    final static int PICK_PDF_CODE = 2342;
    private FirebaseListAdapter<ChatMessage> adapter;
    RelativeLayout activity_chat;
    RelativeLayout base;
    private StorageReference mStorage;

    //Add Emojicon
    EmojiconEditText emojiconEditText;
    ImageView emojiButton, submitButton;
    EmojIconActions emojIconActions;
    TextView typing;
    ImageView attachments;
    String m;
    Integer flag = 0,flog = 0;
    private static final int CHOOSE_IMAGE = 101;
    ImageView camera, documents;
    Uri uri;
    Bitmap bitmap;
    FloatingActionButton ImageSend;
    long downloadID;
    String dataTitle, dataMessage;
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<View> views = new ArrayList<View>();


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_sign_out) {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Snackbar.make(activity_chat, "You have been signed out.", Snackbar.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
        if (item.getItemId() == R.id.not_enab) {
            if(flog==0) {
                FirebaseMessaging.getInstance().subscribeToTopic("notifications")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (!task.isSuccessful()) {
                                    String msg = "Notification Not Enabled!";
                                    Toast.makeText(chatmain.this, msg, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(chatmain.this, "Notification Enabled!", Toast.LENGTH_SHORT).show();
                                    flog = 1;
                                }
                            }
                        });
            }
            else{
                Toast.makeText(chatmain.this, "Notification already enabled!", Toast.LENGTH_SHORT).show();
            }

        }
        if (item.getItemId() == R.id.not_disab) {
            if(flog == 0){
                Toast.makeText(chatmain.this,"Already disabled!",Toast.LENGTH_LONG).show();
            }
            else{
                FirebaseMessaging.getInstance().unsubscribeFromTopic("notifications")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (!task.isSuccessful()) {
                                    String msg = "Notification Enabled!";
                                    Toast.makeText(chatmain.this, msg, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(chatmain.this, "Notification Disabled!", Toast.LENGTH_SHORT).show();
                                    flog = 0;
                                }
                            }
                        });;
                Toast.makeText(chatmain.this, "Notification Disabled!", Toast.LENGTH_SHORT).show();
            }
        }
        if(item.getItemId()==R.id.wall){
            //startActivity(new Intent(chatmain.this,wallchat.class));
            Toast.makeText(chatmain.this, "UNDER TESTING", Toast.LENGTH_LONG).show();
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatmain);

        /*View myview = (RelativeLayout)findViewById(R.id.activity_chat);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        int bg = sharedPref.getInt("background_resource",R.drawable.walldef); // the second parameter will be fallback if the preference is not found
        myview.setBackgroundResource(bg);*/

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chats");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        base = (RelativeLayout) findViewById(R.id.base);
        activity_chat = (RelativeLayout) findViewById(R.id.activity_chat);
        mStorage = FirebaseStorage.getInstance().getReference();
        StorageReference filePath = mStorage.child("ChatImages").child(String.valueOf(DateFormat.format("dd-MMM(HH:mm:ss)", new Date())));

        String m1;

        //Add Emoji

        emojiButton = (ImageView) findViewById(R.id.emoji_button);
        submitButton = (ImageView) findViewById(R.id.submit_button);
        emojiconEditText = (EmojiconEditText) findViewById(R.id.emojicon_edit_text);
        typing = (TextView) findViewById(R.id.typing);
        attachments = (ImageView) findViewById(R.id.attach);
        camera = (ImageView) findViewById(R.id.camera);
        documents = (ImageView) findViewById(R.id.documents);
        emojIconActions = new EmojIconActions(getApplicationContext(), activity_chat, emojiButton, emojiconEditText);
        emojIconActions.ShowEmojicon();
        emojIconActions.setIconsIds(R.drawable.ic_action_keyboard, R.drawable.happy_256);
        final RelativeLayout att = (RelativeLayout) findViewById(R.id.att);
        final ChatMessage t = new ChatMessage();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m = emojiconEditText.getText().toString();
                m = m.trim();
                if (!m.equals("")) {
                    FirebaseDatabase.getInstance().getReference().child("chats").push().setValue(new ChatMessage(m,
                            FirebaseAuth.getInstance().getCurrentUser().getEmail(), "text", FirebaseAuth.getInstance().getCurrentUser().getUid()));
                    emojiconEditText.setText("");
                    emojiconEditText.requestFocus();
                } else {
                    Toast.makeText(chatmain.this, "Enter text...", Toast.LENGTH_SHORT).show();
                    emojiconEditText.setText("");
                    emojiconEditText.requestFocus();
                }
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMinCropResultSize(40,40)
                        .setMaxCropResultSize(10000,10000)
                        .start(chatmain.this);
                att.setVisibility(View.GONE);
                flag = 0;
            }
        });

        documents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_CODE);
                att.setVisibility(View.GONE);
                flag = 0;
            }
        });


        attachments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    att.setVisibility(View.VISIBLE);
                    flag = flag + 1;
                } else {
                    flag = flag - 1;
                    att.setVisibility(View.GONE);
                }
            }
        });

        //Check if not sign-in then navigate Signin page
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent myIntent = new Intent(this, LoginActivity.class);
            startActivity(myIntent);
        } else {
            Snackbar.make(activity_chat, "Welcome " + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Snackbar.LENGTH_SHORT).show();
            //Load content
            displayChatMessage();
        }

        registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));


    }

    private void displayChatMessage() {

        final ListView listOfMessage = (ListView) findViewById(R.id.list_of_message);
        Query query = FirebaseDatabase.getInstance().getReference().child("chats");


        ChatMessage m = new ChatMessage();

        FirebaseListOptions<ChatMessage> options2 = new FirebaseListOptions.Builder<ChatMessage>()
                .setQuery(query, ChatMessage.class)
                .setLayout(R.layout.list_item)
                .build();


        adapter = new FirebaseListAdapter<ChatMessage>(options2) {
            @Override
            protected void populateView(View v, final ChatMessage model, int position) {
                final TextView messageText1, messageText2, messageUser, messageTime, messageUser2, messageTime2;
                final ImageView prof1, prof2;
                messageText1 = (TextView) v.findViewById(R.id.message_text);
                messageText2 = (TextView) v.findViewById(R.id.message_text2);
                messageUser = (TextView) v.findViewById(R.id.message_user);
                messageTime = (TextView) v.findViewById(R.id.message_time);
                messageUser2 = (TextView) v.findViewById(R.id.message_user2);
                messageTime2 = (TextView) v.findViewById(R.id.message_time2);
                prof1 = (ImageView) v.findViewById(R.id.prof1);
                prof2 = (ImageView) v.findViewById(R.id.prof2);
                final String msg_type = model.getMessageType();

                if (model.getMessageUser().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
                    if (msg_type.equals("image")) {
                        messageText2.setVisibility(View.VISIBLE);
                        messageText1.setVisibility(View.INVISIBLE);
                        messageUser2.setVisibility(View.VISIBLE);
                        messageUser.setVisibility(View.INVISIBLE);
                        messageTime2.setVisibility(View.VISIBLE);
                        messageTime.setVisibility(View.INVISIBLE);
                        prof1.setVisibility(View.INVISIBLE);
                        prof2.setVisibility(View.VISIBLE);

                        Resources res = getApplicationContext().getResources();
                        Drawable myImage = ResourcesCompat.getDrawable(res, R.drawable.chatim, null);
                        messageText2.setCompoundDrawablesWithIntrinsicBounds(null, myImage, null, null);

                        int i = model.getMessageUser().indexOf("@");
                        messageUser2.setText(model.getMessageUser().substring(0, i));
                        messageTime2.setText(DateFormat.format("dd/MMM (HH:mm)", model.getMessageTime()));
                        messageText2.setText("Tap to download...");


                        messageText2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                StorageReference path = mStorage.child("ChatImages").child(model.getMessageText());
                                path.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                                        DownloadManager.Request request = new DownloadManager.Request(uri);
                                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                        downloadID=downloadManager.enqueue(request);
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(uri);
                                        startActivity(intent);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Handle any errors
                                    }
                                });
                            }
                        });

                    }
                    if (msg_type.equals("pdf")) {
                        messageText2.setVisibility(View.VISIBLE);
                        messageText1.setVisibility(View.GONE);
                        messageUser2.setVisibility(View.VISIBLE);
                        messageUser.setVisibility(View.INVISIBLE);
                        messageTime2.setVisibility(View.VISIBLE);
                        messageTime.setVisibility(View.INVISIBLE);
                        prof1.setVisibility(View.INVISIBLE);
                        prof2.setVisibility(View.VISIBLE);
                        messageText2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                        Resources res = getApplicationContext().getResources();
                        Drawable myImage1 = ResourcesCompat.getDrawable(res, R.drawable.chatpdf, null);
                        messageText2.setCompoundDrawablesWithIntrinsicBounds(null, myImage1, null, null);

                        int i = model.getMessageUser().indexOf("@");
                        messageUser2.setText(model.getMessageUser().substring(0, i));
                        messageTime2.setText(DateFormat.format("dd/MMM (HH:mm)", model.getMessageTime()));
                        messageText2.setText("Tap to download...");


                        messageText2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                StorageReference path = mStorage.child("ChatPDFs").child(model.getMessageText());
                                path.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                                        DownloadManager.Request request = new DownloadManager.Request(uri);
                                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                        downloadID=downloadManager.enqueue(request);
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(uri);
                                        startActivity(intent);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Handle any errors
                                    }
                                });

                            }
                        });

                    }
                    if (msg_type.equals("text")) {
                        messageText2.setVisibility(View.VISIBLE);
                        messageText1.setVisibility(View.INVISIBLE);
                        messageUser2.setVisibility(View.VISIBLE);
                        messageUser.setVisibility(View.INVISIBLE);
                        messageTime2.setVisibility(View.VISIBLE);
                        messageTime.setVisibility(View.INVISIBLE);
                        prof1.setVisibility(View.INVISIBLE);
                        messageText2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                        messageText1.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                        messageText2.setText(model.getMessageText());

                        messageText2.setOnClickListener(new View.OnClickListener() {
                            int i = 0;
                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                i++;
                                Handler handler = new Handler();
                                Runnable r = new Runnable() {

                                    @Override
                                    public void run() {
                                        i = 0;
                                    }
                                };

                                if (i == 1) {
                                    //Single click
                                    messageText2.setMovementMethod(LinkMovementMethod.getInstance());
                                    handler.postDelayed(r, 250);

                                } else if (i == 2) {
                                    ClipboardManager cm = (ClipboardManager)chatmain.this.getSystemService(Context.CLIPBOARD_SERVICE);
                                    cm.setText(messageText2.getText());
                                    Toast.makeText(chatmain.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });

                        int i = model.getMessageUser().indexOf("@");
                        messageUser2.setText(model.getMessageUser().substring(0, i));
                        messageTime2.setText(DateFormat.format("dd/MMM (HH:mm)", model.getMessageTime()));
                        prof2.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (msg_type.equals("image")) {
                        messageText2.setVisibility(View.INVISIBLE);
                        messageText1.setVisibility(View.VISIBLE);
                        messageUser2.setVisibility(View.INVISIBLE);
                        messageUser.setVisibility(View.VISIBLE);
                        messageTime2.setVisibility(View.INVISIBLE);
                        messageTime.setVisibility(View.VISIBLE);
                        prof2.setVisibility(View.INVISIBLE);
                        int i = model.getMessageUser().indexOf("@");
                        messageUser.setText(model.getMessageUser().substring(0, i));
                        messageTime.setText(DateFormat.format("dd/MMM (HH:mm)", model.getMessageTime()));

                        Resources res = getApplicationContext().getResources();
                        Drawable myImage = ResourcesCompat.getDrawable(res, R.drawable.chatim, null);
                        messageText1.setCompoundDrawablesWithIntrinsicBounds(null, myImage, null, null);
                        messageText1.setText("Tap to download...");

                        int[] androidColors = getResources().getIntArray(R.array.androidcolors);
                        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
                        messageUser.setTextColor(randomAndroidColor);

                        messageText1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                StorageReference path = mStorage.child("ChatImages").child(model.getMessageText());
                                path.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                                        DownloadManager.Request request = new DownloadManager.Request(uri);
                                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                        downloadID=downloadManager.enqueue(request);
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(uri);
                                        startActivity(intent);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Handle any errors
                                    }
                                });
                            }
                        });
                        prof1.setVisibility(View.VISIBLE);
                    }
                    if (msg_type.equals("pdf")) {
                        messageText2.setVisibility(View.GONE);
                        messageText1.setVisibility(View.VISIBLE);
                        messageUser2.setVisibility(View.INVISIBLE);
                        messageUser.setVisibility(View.VISIBLE);
                        messageTime2.setVisibility(View.INVISIBLE);
                        messageTime.setVisibility(View.VISIBLE);
                        prof2.setVisibility(View.INVISIBLE);
                        int i = model.getMessageUser().indexOf("@");
                        messageUser.setText(model.getMessageUser().substring(0, i));
                        messageTime.setText(DateFormat.format("dd/MMM (HH:mm)", model.getMessageTime()));

                        Resources res = getApplicationContext().getResources();
                        Drawable myImage1 = ResourcesCompat.getDrawable(res, R.drawable.chatpdf, null);
                        messageText1.setCompoundDrawablesWithIntrinsicBounds(null, myImage1, null, null);
                        messageText1.setText("Tap to download...");

                        int[] androidColors = getResources().getIntArray(R.array.androidcolors);
                        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
                        messageUser.setTextColor(randomAndroidColor);

                        messageText1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                StorageReference path = mStorage.child("ChatPDFs").child(model.getMessageText());
                                path.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                                        DownloadManager.Request request = new DownloadManager.Request(uri);
                                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                        downloadID=downloadManager.enqueue(request);
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(uri);
                                        startActivity(intent);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Handle any errors
                                    }
                                });
                            }
                        });
                        prof1.setVisibility(View.VISIBLE);
                    }

                    if (msg_type.equals("text")) {
                        messageText1.setVisibility(View.VISIBLE);
                        messageText2.setVisibility(View.INVISIBLE);
                        messageUser.setVisibility(View.VISIBLE);
                        messageUser2.setVisibility(View.INVISIBLE);
                        messageTime.setVisibility(View.VISIBLE);
                        messageTime2.setVisibility(View.INVISIBLE);
                        messageText1.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                        messageText2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                        messageText1.setText(model.getMessageText());
                        messageText1.setOnClickListener(new View.OnClickListener() {
                            int i = 0;
                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                i++;
                                Handler handler = new Handler();
                                Runnable r = new Runnable() {

                                    @Override
                                    public void run() {
                                        i = 0;
                                    }
                                };

                                if (i == 1) {
                                    //Single click
                                    messageText1.setMovementMethod(LinkMovementMethod.getInstance());
                                    handler.postDelayed(r, 250);

                                } else if (i == 2) {
                                    ClipboardManager cm = (ClipboardManager)chatmain.this.getSystemService(Context.CLIPBOARD_SERVICE);
                                    cm.setText(messageText1.getText());
                                    Toast.makeText(chatmain.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });

                        prof2.setVisibility(View.INVISIBLE);
                        int i = model.getMessageUser().indexOf("@");
                        messageUser.setText(model.getMessageUser().substring(0, i));
                        messageTime.setText(DateFormat.format("dd/MMM(HH:mm)", model.getMessageTime()));
                        int[] androidColors = getResources().getIntArray(R.array.androidcolors);
                        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
                        messageUser.setTextColor(randomAndroidColor);
                        prof1.setVisibility(View.VISIBLE);
                    }
                }
            }
        };
        listOfMessage.setAdapter(adapter);

        listOfMessage.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                final int lastItem = firstVisibleItem + visibleItemCount;
                if (lastItem == totalItemCount) {
                    //load more data
                    adapter.startListening();

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                uri = result.getUri();
                final String m1 = String.valueOf(DateFormat.format("dd_MMM(HH:mm:ss)", new Date()));
                StorageReference filePath = mStorage.child("ChatImages").child(m1);
                filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        if (!m1.equals("")) {
                            FirebaseDatabase.getInstance().getReference().child("chats").push().setValue(new ChatMessage(m1,
                                    FirebaseAuth.getInstance().getCurrentUser().getEmail(), "image", FirebaseAuth.getInstance().getCurrentUser().getUid()));
                            emojiconEditText.setText("");
                            emojiconEditText.requestFocus();
                        }
                    }

                });
            }
        }

        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                uri = data.getData();
                final String m1 = String.valueOf(DateFormat.format("dd_MMM(HH:mm:ss)", new Date()));
                StorageReference filePath = mStorage.child("ChatPDFs").child(m1);
                filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        if (!m1.equals("")) {
                            FirebaseDatabase.getInstance().getReference().child("chats").push().setValue(new ChatMessage(m1,
                                    FirebaseAuth.getInstance().getCurrentUser().getEmail(), "pdf", FirebaseAuth.getInstance().getCurrentUser().getUid()));
                            emojiconEditText.setText("");
                            emojiconEditText.requestFocus();
                        }
                    }

                });
            } else {
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Fetching the download id received with the broadcast
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            //Checking if the received broadcast is for our enqueued download by matching download id
            if (downloadID == id) {
                Toast.makeText(chatmain.this, "Download Completed.Open from the notifications.", Toast.LENGTH_LONG).show();

            }
        }
    };


}
