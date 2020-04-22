package com.example.qg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MenuActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    Button b1 = (Button) findViewById(R.id.button5);
    Button b2 = (Button) findViewById(R.id.button6);
    Button b3 = (Button) findViewById(R.id.button7);
    Button b4 = (Button) findViewById(R.id.button8);

    String message = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                message = "https://opentdb.com/api.php?amount=1&category=17&type=multiple";
                sendMessage(v);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                message = "https://opentdb.com/api.php?amount=1&category=18&type=multiple";
                sendMessage(v);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                message = "https://opentdb.com/api.php?amount=1&category=19&type=multiple";
                sendMessage(v);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                message = "https://opentdb.com/api.php?amount=1&category=23&type=multiple";
                sendMessage(v);
            }
        });

    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


}
