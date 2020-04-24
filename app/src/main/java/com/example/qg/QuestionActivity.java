package com.example.qg;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.os.Bundle;

import android.os.AsyncTask;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.qg.MenuActivity.EXTRA_MESSAGE;


public class QuestionActivity extends AppCompatActivity {

     String API_URL = "https://opentdb.com/api.php?amount=1&type=multiple";

    Button button1, button2, button3, button4;
    TextView tv;
    TextView tv2;
    ImageView iv;
    int cb;
    int points = 0;
    int counter = 1;
    List<String> questionlist;
    boolean finished = false;
    DatabaseHelper dbHelper;


    public class Wrapper //class to transfer outputs from doInBackground to onPostExecute
    {
        public Wrapper(List<Result> results, String s) {

        }

        public List<Result> results;
        public String s;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        API_URL = intent.getStringExtra(EXTRA_MESSAGE);
        dbHelper = new DatabaseHelper(this);
        tv = (TextView)findViewById(R.id.tv);
        tv2 = (TextView)findViewById(R.id.tv2);
        button1 = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        iv = (ImageView)findViewById(R.id.imageView2);
        final Animation c = AnimationUtils.loadAnimation(QuestionActivity.this,R.anim.fade);

        final ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar);
        pb.setProgress(0);

        iv.setVisibility(View.INVISIBLE);



        Question();
        //setContentView(R.layout.activity_main);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb == 0) {
                    points++;
                    pb.setProgress(points);
                    iv.setVisibility(View.VISIBLE);
                    iv.startAnimation(c);
                    iv.setVisibility(View.INVISIBLE);

                }
                counter++;
                if (counter >= 20) {
                    finale();
                } else {
                    Question();
                }
            }

        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (cb == 1) {
                    points++;
                    pb.setProgress(points);
                    iv.setVisibility(View.VISIBLE);
                    iv.startAnimation(c);
                    iv.setVisibility(View.INVISIBLE);
                }
                counter++;
                if (counter >= 20) {
                    finale();
                } else {
                    Question();
                }
            }

        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb == 2) {
                    points++;
                    pb.setProgress(points);

                }
                counter++;
                if (counter >= 20) {
                    finale();
                } else {
                    Question();
                }
            }

        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb == 3) {
                    points++;
                    pb.setProgress(points);
                }
                counter++;
                if (counter >= 20) {
                    finale();

                } else {
                    Question();
                }
            }

        });
    }

    public void Question() {
        Animation b = AnimationUtils.loadAnimation(QuestionActivity.this,R.anim.slide2);

        tv.startAnimation(b);
        tv.setVisibility(View.INVISIBLE);
        @SuppressLint("StaticFieldLeak") AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {

                //running internet requests in the background and filling the wrapper

                Retriever retriever = new Retriever();
                String r;
                r = retriever.getHTTPData(API_URL);
                //Example re = new Gson().fromJson(r,Example.class);

                // List<Result> results = re.getResults();

                //Wrapper wrapper = new Wrapper(results,r);
                return r;
            }

            @Override
            protected void onPostExecute(String stream) {
                //converting the single string of the response into an object of returned class through Json
                //Result r = w.results.get(1);
                //tv.setText(r.getQuestion());
                //Example re = new Gson().fromJson(w,Example.class); //FUCK IT DOESNT WORK WHY DOES IT RETURN NULLS
                //tv.setText(re.getResponseCode().toString());
                stream = fixString(stream);
               // questionlist.add(stream);
                Pattern questionPattern = Pattern.compile("question\":\"(.*?)\"");   // ill just use these instead
                Matcher m = questionPattern.matcher(stream);
                String q = "Empty";
                if (m.find()) {
                    q = m.group(1);

                }


                Pattern cAPattern = Pattern.compile("correct_answer\":\"(.*?)\"");   // ill just use these instead
                m = cAPattern.matcher(stream);

                String ca = "Empty";
                if (m.find()) {
                    ca = m.group(1);
                }


                Pattern iAPattern = Pattern.compile("incorrect_answers\":(.*?)\\}");   // ill just use these instead
                m = iAPattern.matcher(stream);

                String ia = null;
                if (m.find()) {
                    ia = m.group(1);
                }

                Pattern iAPatternIndividual = Pattern.compile("\\\"(.*?)\\\"");   // ill just use these instead
                m = iAPatternIndividual.matcher(ia);

                String ia1 = "Empty";
                String ia2 = "Empty";
                String ia3 = "Empty";
                if (m.find()) {
                    ia1 = m.group(1);
                    //ia2 = m.group(2);
                    //ia3 = m.group(3);
                }
                if (m.find()) {
                    ia2 = m.group(1);

                }
                if (m.find()) {
                    ia3 = m.group(1);

                }



            Random rand = new Random();
            int pos = rand.nextInt(4);

            switch (pos) {

                case 0:
                    cb = 0;
                    button1.setText(ca);
                    pos = rand.nextInt(4);
                    switch (pos) {
                        case 0:
                            button4.setText(ia1);
                            button2.setText(ia2);
                            button3.setText(ia3);
                            break;
                        case 1:
                            button4.setText(ia2);
                            button2.setText(ia1);
                            button3.setText(ia3);
                            break;
                        case 2:
                            button4.setText(ia3);
                            button2.setText(ia2);
                            button3.setText(ia1);
                            break;

                    }
                    break;

                case 1:
                    cb = 1;
                    button2.setText(ca);
                    pos = rand.nextInt(4);
                    switch (pos) {
                        case 0:
                            button1.setText(ia1);
                            button4.setText(ia2);
                            button3.setText(ia3);
                            break;
                        case 1:
                            button1.setText(ia2);
                            button4.setText(ia1);
                            button3.setText(ia3);
                            break;
                        case 2:
                            button1.setText(ia3);
                            button4.setText(ia2);
                            button3.setText(ia1);
                            break;

                    }
                    break;

                case 2:
                    cb = 2;
                    button3.setText(ca);
                    pos = rand.nextInt(3);
                    switch (pos) {
                        case 0:
                            button1.setText(ia1);
                            button2.setText(ia2);
                            button4.setText(ia3);
                            break;
                        case 1:
                            button1.setText(ia2);
                            button2.setText(ia1);
                            button4.setText(ia3);
                            break;
                        case 2:
                            button1.setText(ia3);
                            button2.setText(ia2);
                            button4.setText(ia1);
                            break;

                    }
                    break;

                case 3:
                    cb = 3;
                    button4.setText(ca);
                    pos = rand.nextInt(3);
                    switch (pos) {
                        case 0:
                            button1.setText(ia1);
                            button2.setText(ia2);
                            button3.setText(ia3);
                            break;
                        case 1:
                            button1.setText(ia2);
                            button2.setText(ia1);
                            button3.setText(ia3);
                            break;
                        case 2:
                            button1.setText(ia3);
                            button2.setText(ia2);
                            button3.setText(ia1);
                            break;

                    }
                    break;
            }

                int temp_placeholder = 0;
                Boolean insertData = dbHelper.addData(temp_placeholder,q,ca,ia1,ia2,ia3);

                tv.setText(q);
                button1.setText(ca);
                button2.setText(ia1);
                button3.setText(ia2);
                button4.setText(ia3);
                tv2.setText("Question "+Integer.toString(counter));

                tv.setVisibility(View.VISIBLE);
                Animation a = AnimationUtils.loadAnimation(QuestionActivity.this,R.anim.slide);
                tv.startAnimation(a);


            }
        };
        asyncTask.execute();

    }

    public void finale() {
        double result = ((double)points/(double)counter) * (100.0);
        tv2.setText("Finale");
        tv.setText("You got "+Double.toString(result)+" %");
        button1.setText("V");
        button2.setText("V");
        button3.setText("V");
        button4.setText("V");
        if (finished == true ) {
            Intent i = getIntent();
            finish();
            startActivity(i);
        }
        finished = true;

    }

    public String fixString(String s) {

        s = s.replaceAll("&quot;","\\\"");
        s = s.replaceAll("&#039;","'");
        return s;
    }
}