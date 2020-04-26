package com.example.qg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

// Activity for submitting a new question to the database

public class NewQActivity extends AppCompatActivity {

    Button submit;
    Button b;
    ImageView cover;
    TextView t;
    String[] difficulty;
    String[] category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_q);

        submit = (Button) findViewById(R.id.submit);
        cover = (ImageView) findViewById(R.id.imageView);
        b = (Button) findViewById(R.id.b);
        t = (TextView) findViewById(R.id.t);
        category = new String[]{"Computers", "Math", "Science","History"};
        difficulty = new String[]{"Easy","Medium","Hard"};

        Spinner cat = (Spinner) findViewById(R.id.cat);
        Spinner diff = (Spinner) findViewById(R.id.diff);

        ArrayAdapter adiff = new ArrayAdapter(this,android.R.layout.simple_spinner_item,difficulty);
        adiff.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diff.setAdapter(adiff);


        ArrayAdapter acat = new ArrayAdapter(this,android.R.layout.simple_spinner_item,category);
        acat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat.setAdapter(acat);

        cover.setVisibility(View.INVISIBLE);
        b.setVisibility(View.INVISIBLE);
        t.setVisibility(View.INVISIBLE);
        b.setEnabled(false);

        final Animation animation = AnimationUtils.loadAnimation(NewQActivity.this,R.anim.slide);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submit.setEnabled(false);
                submit.setVisibility(View.INVISIBLE);
                cover.setVisibility(View.VISIBLE);
                cover.startAnimation(animation);
                b.setEnabled(true);
                b.setVisibility(View.VISIBLE);
                b.startAnimation(animation);
                t.setVisibility(View.VISIBLE);
                t.startAnimation(animation);

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewQActivity.this,MenuActivity.class);
                startActivity(i);

            }
        });
    }
}
