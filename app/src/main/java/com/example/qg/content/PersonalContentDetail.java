package com.example.qg.content;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.qg.PQDatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PersonalContentDetail {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Quest> ITEMS = new ArrayList<Quest>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Quest> ITEM_MAP = new HashMap<String, Quest>();

    private static final int COUNT = 5;

    private static Context context;
    private static SQLiteDatabase db;
    static PQDatabaseHelper helper;
/*

    static {
        // Add some sample items.
        helper = new PQDatabaseHelper(context);
       db = helper.getWritableDatabase();
        //PQDatabaseHelper dbHelper1 = new PQDatabaseHelper(context);
       Boolean insertData = helper.addData(db,"Column 1 Test","Column 2 Test");

        for (int i = 1; i <= COUNT; i++) {
            addItem(createQuizItem(PersonalContent.context, i));

        }
    }

  */

    public static void addItem(Quest item) {
        ITEMS.add(item);
        ITEM_MAP.put(Integer.toString(item.id), item);
    }

    public static Quest createQuizItem (int a, int b, String c) {

        return new Quest(a,b,c);

    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Category ").append(position).append("\n");
        for (int i = 0; i < position; i++) {
            builder.append("\nSample Question.");
            builder.append("\n        Sample Answer.");
        }
        return builder.toString();
    }

    /*
     * A dummy item representing a piece of content.
     */
    public static class Quest {
        public final int id;
        public final int qn;
        public final String question;

        public Quest(int id, int qn, String question) {
            this.id = id;
            this.qn = qn;
            this.question = question;
        }

        @Override
        public String toString() {
            return question;
        }

    }

}

