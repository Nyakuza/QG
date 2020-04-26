package com.example.qg;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

// Trivia Rush selection menu

public class MenuActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myapp.MESSAGE";



    Button c1;
    Button c2;
    Button c3;
    Button c4;
    Button c5;
    Button c6;

    Button d1;
    Button d2;
    Button d3;
    Button list;
    Button create;
    Button start;
    Button leaderb;

    ImageView i1;
    ImageView i2;
    ImageView i3;
    ImageView i4;
    ImageView i5;
    ImageView i6;

    ImageView i7;
    ImageView i8;
    ImageView i9;

    //https://opentdb.com/api.php?amount=1&category=23&difficulty=medium&type=multiple

    String category = "17";
    String diff = "hard";




    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        loadData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        list = (Button) findViewById(R.id.button12);
        create = (Button) findViewById(R.id.create);
        c1 = (Button) findViewById(R.id.button5);
        c2 = (Button) findViewById(R.id.button6);
        c3 = (Button) findViewById(R.id.button7);
        c4 = (Button) findViewById(R.id.button8);
        c5 = (Button) findViewById(R.id.button13);
        c6 = (Button) findViewById(R.id.button14);


        d1 = (Button) findViewById(R.id.d1);
        d2 = (Button) findViewById(R.id.d2);
        d3 = (Button) findViewById(R.id.d3);

        i1 = (ImageView) findViewById(R.id.imageView3);
        i2 = (ImageView) findViewById(R.id.imageView4);
        i3 = (ImageView) findViewById(R.id.imageView5);
        i4 = (ImageView) findViewById(R.id.imageView6);
        i5 = (ImageView) findViewById(R.id.imageView7);
        i6 = (ImageView) findViewById(R.id.imageView8);

        i7 = (ImageView) findViewById(R.id.imageView9);
        i8 = (ImageView) findViewById(R.id.imageView10);
        i9 = (ImageView) findViewById(R.id.imageView11);

       setInvis();
       setInvis2();

        final Animation fadein = AnimationUtils.loadAnimation(MenuActivity.this,R.anim.fade2);

        start = (Button) findViewById(R.id.start);
        leaderb = (Button) findViewById(R.id.leaderboards);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent i = new Intent(MenuActivity.this,FirstActivity.class);
                startActivity(i);
            }
        };


        leaderb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this,PlayerListActivity.class);
                startActivity(i);

            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sendMessage(v);

            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this,HistoryActivity.class);
                startActivity(i);

            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this,NewQActivity.class);
                startActivity(i);

            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                category = "17";
                setInvis();
                i1.setVisibility(View.VISIBLE);
                i1.startAnimation(fadein);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                category = "18";
                setInvis();
                i2.setVisibility(View.VISIBLE);
                i2.startAnimation(fadein);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                category = "19";
                setInvis();
                i3.setVisibility(View.VISIBLE);
                i3.startAnimation(fadein);
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                category = "23";
                setInvis();
                i4.setVisibility(View.VISIBLE);
                i4.startAnimation(fadein);
            }
        });

        c5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                category = "22";
                setInvis();
                i5.setVisibility(View.VISIBLE);
                i5.startAnimation(fadein);
            }
        });

        c6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                category = "10";
                setInvis();
                i6.setVisibility(View.VISIBLE);
                i6.startAnimation(fadein);
            }
        });


        d1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               diff = "easy";
                setInvis2();
                i7.setVisibility(View.VISIBLE);
                i7.startAnimation(fadein);
            }
        });

        d2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diff = "medium";
                setInvis2();
                i8.setVisibility(View.VISIBLE);
                i8.startAnimation(fadein);
            }
        });

        d3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diff = "hard";
                setInvis2();
                i9.setVisibility(View.VISIBLE);
                i9.startAnimation(fadein);
            }
        });

    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, QuestionActivity.class);
        String message = "https://opentdb.com/api.php?amount=1&category=";
        message = message + category + "&difficulty=" + diff + "&type=multiple";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void setInvis() {
        i1.setVisibility(View.INVISIBLE);
        i2.setVisibility(View.INVISIBLE);
        i3.setVisibility(View.INVISIBLE);
        i4.setVisibility(View.INVISIBLE);
        i5.setVisibility(View.INVISIBLE);
        i6.setVisibility(View.INVISIBLE);
    }

    public void setInvis2() {
        i7.setVisibility(View.INVISIBLE);
        i8.setVisibility(View.INVISIBLE);
        i9.setVisibility(View.INVISIBLE);

    }

/*
    public void loadData() {
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();

    }
*/
}
