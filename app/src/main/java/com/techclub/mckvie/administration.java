package com.techclub.mckvie;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
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

public class administration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administration);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        TextView txt1 = findViewById(R.id.admin1);
        TextView txt2 = findViewById(R.id.admin2);
        TextView txt3 = findViewById(R.id.admin3);
        TextView txt4 = findViewById(R.id.admin4);
        TextView txt5 = findViewById(R.id.admin5);
        TextView txt6 = findViewById(R.id.admin6);
        TextView txt7 = findViewById(R.id.admin7);

        String text1 = "Mr. S S Kejriwal(Chairman)";
        String text2 = "Mr. Kishan Kejriwal(Managing Trustee)";
        String text3 = "Prof.(Dr.) Parasar Bandyopadhyay(Director)";
        String text4 = "Prof.(Dr.) B.Chattopadhyay(Principal and Dean(Academics))";
        String text5 = "Prof.(Dr.) Debapriya De(Dean(Research and Consultancy))";
        String text6 = "Prof.(Dr.) Arun Kumar Jalan(Dean(Student Affairs))";
        String text7 = "Prof.(Dr.) Ananta Kumar Das(Registrar)";
        SpannableString ss1= new SpannableString(text1);
        SpannableString ss2 = new SpannableString(text2);
        SpannableString ss3 = new SpannableString(text3);
        SpannableString ss4 = new SpannableString(text4);
        SpannableString ss5 = new SpannableString(text5);
        SpannableString ss6 = new SpannableString(text6);
        SpannableString ss7= new SpannableString(text7);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        StyleSpan italicSpan = new StyleSpan(Typeface.ITALIC);


        ss1.setSpan(boldSpan, 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        ss2.setSpan(boldSpan, 0, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        ss3.setSpan(boldSpan, 0, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        ss4.setSpan(boldSpan, 0, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        ss5.setSpan(boldSpan, 0, 23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        ss6.setSpan(boldSpan, 0, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        ss7.setSpan(boldSpan, 0, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );



        ss1.setSpan(italicSpan, 16, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        ss2.setSpan(italicSpan, 19, 37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        ss3.setSpan(italicSpan, 32, 42, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        ss4.setSpan(italicSpan, 26, 57, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        ss5.setSpan(italicSpan, 23, 55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        ss6.setSpan(italicSpan, 27, 50, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        ss7.setSpan(italicSpan, 27, 38, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );

        txt1.setText(ss1);
        txt2.setText(ss2);
        txt3.setText(ss3);
        txt4.setText(ss4);
        txt5.setText(ss5);
        txt6.setText(ss6);
        txt7.setText(ss7);





    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}