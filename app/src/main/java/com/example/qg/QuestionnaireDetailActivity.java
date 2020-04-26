package com.example.qg;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.widget.Toolbar;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.NavUtils;

import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.qg.QuestionnaireListActivity.QID_MESSAGE;


/**
 * An activity representing a single Questionnaire detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link QuestionnaireListActivity}.
 */



public class QuestionnaireDetailActivity extends AppCompatActivity {

    PQDetailDatabaseHelper detaildHelper;
    ListView detail;
    int q_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
//        q_id = Integer.parseInt(intent.getStringExtra(QID_MESSAGE));
        //q_id = 0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        detaildHelper = new PQDetailDatabaseHelper(this);
        detail = (ListView) findViewById(R.id.lv);
        fillListView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Edit mode", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(QuestionnaireDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(QuestionnaireDetailFragment.ARG_ITEM_ID));
            QuestionnaireDetailFragment fragment = new QuestionnaireDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.questionnaire_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, QuestionnaireListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillListView() {
        Cursor data = detaildHelper.getData(q_id);
        ArrayList<String> questionList = new ArrayList<>();
        while(data.moveToNext()) {
            questionList.add(data.getString(2)+" "+data.getString(3));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, questionList);
        detail.setAdapter(adapter);
    }
}
