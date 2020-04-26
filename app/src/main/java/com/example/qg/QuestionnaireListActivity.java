package com.example.qg;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qg.content.PersonalContent;

import java.util.ArrayList;
import java.util.List;

import static com.example.qg.content.PersonalContent.createQuizItem;

/** Activity meant to display a list of player created quizzes, for local use and storage, all
* classes starting with 'questionnaire' and 'PersonalContent' are meant to be part of this module structured a recycler
* view of player created quizzes, on click leading to detail activity with a recycler view of questions
 * referring to each one quiz, populated from PersonalContent  and PersonalContendDetal respectively.
 * Unfortunately I did not manage to finish working on functionality of this part.
*/
public class QuestionnaireListActivity extends AppCompatActivity {


    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    Context context;
    public static final String QID_MESSAGE = "com.example.myapp.MESSAGE";
    PQDatabaseHelper dbHelper1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_list);

        PersonalContent.ITEM_MAP.clear();
        PersonalContent.ITEMS.clear();

        dbHelper1 = new PQDatabaseHelper(this);
        //Boolean insertData = dbHelper1.addData("Column 1 Test","Column 2 Test");
        Cursor data = dbHelper1.getData();
        while(data.moveToNext()) {
            PersonalContent.addItem(createQuizItem(data.getInt(0),data.getString(1),data.getString(2)));
            //new PersonalContent.Qn(data.getInt(1),data.getString(2),data.getString(3));
        }


        ArrayList<String> quizList = new ArrayList<>();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "New Empty Quiz List Added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Boolean insertData = dbHelper1.addData("New Quiz","New Entry");
                Intent i = new Intent(QuestionnaireListActivity.this,QuestionnaireListActivity.class);
                startActivity(i);

            }
        });

        if (findViewById(R.id.questionnaire_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.questionnaire_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, PersonalContent.ITEMS, mTwoPane));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {



        private final QuestionnaireListActivity mParentActivity;
        private final List<PersonalContent.Qn> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PersonalContent.Qn item = (PersonalContent.Qn) view.getTag();

                Context context = view.getContext();
                Intent intent = new Intent(context, QuestionnaireDetailActivity.class);
                //intent.putExtra(QuestionnaireDetailFragment.ARG_ITEM_ID, item.id);
                intent.putExtra(QID_MESSAGE,item.id);

                context.startActivity(intent);
                /*
                if (false) {
                    Bundle arguments = new Bundle();
                    arguments.putString(QuestionnaireDetailFragment.ARG_ITEM_ID, Integer.toString(item.id));
                    QuestionnaireDetailFragment fragment = new QuestionnaireDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.questionnaire_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, QuestionnaireDetailActivity.class);
                    //intent.putExtra(QuestionnaireDetailFragment.ARG_ITEM_ID, item.id);
                    intent.putExtra(QID_MESSAGE,item.id);

                    context.startActivity(intent);
                }
                */

            }
        };

        SimpleItemRecyclerViewAdapter(QuestionnaireListActivity parent,
                                      List<PersonalContent.Qn> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }
/*
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, PersonalContent.ITEMS, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final QuestionnaireListActivity mParentActivity;
        private final List<PersonalContent.DummyItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonalContent.DummyItem item = (PersonalContent.DummyItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(QuestionnaireDetailFragment.ARG_ITEM_ID, item.id);
                    QuestionnaireDetailFragment fragment = new QuestionnaireDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.questionnaire_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, QuestionnaireDetailActivity.class);
                    intent.putExtra(QuestionnaireDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(QuestionnaireListActivity parent,
                                      List<PersonalContent.DummyItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }
*/
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.questionnaire_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(Integer.toString(mValues.get(position).id));
            //holder.mIdView.setText("##");
            holder.mContentView.setText(mValues.get(position).name);

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}
