package com.example.qg;

//Record/History of games Activity

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    private ListView lv;
    TextView summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lv = (ListView) findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);
        summary = (TextView) findViewById(R.id.summary);

        fillListView();
        fillSummary();

    }

    private void fillListView() {
        Cursor data = dbHelper.getData();
        ArrayList<String> questionList = new ArrayList<>();
        while(data.moveToNext()) {
            questionList.add("Question: \n  " + data.getString(2)+"\n  Correct Answer: \n  "+data.getString(3));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, questionList);
        lv.setAdapter(adapter);
    }

    private void fillSummary() {
        Cursor data = dbHelper.getCount();
        data.moveToNext();
        int n = data.getInt(0);
        data = dbHelper.getCorrect();
        data.moveToNext();
        int c = data.getInt(0);
        double p = (double)(c/n)*100;
        summary.setText("Correct: " + p + "%     Total: "+n);


    }
}
