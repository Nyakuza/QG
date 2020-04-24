package com.example.qg;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lv = (ListView) findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);

        fillListView();

    }

    private void fillListView() {
        Cursor data = dbHelper.getData();
        ArrayList<String> questionList = new ArrayList<>();
        while(data.moveToNext()) {
            questionList.add(data.getString(2)+" "+data.getString(3));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, questionList);
        lv.setAdapter(adapter);
    }
}
