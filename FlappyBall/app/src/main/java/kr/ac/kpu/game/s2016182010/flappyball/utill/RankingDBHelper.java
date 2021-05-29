package kr.ac.kpu.game.s2016182010.flappyball.utill;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;


public class RankingDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + RankingContract.RankEntry.TABLE_NAME + " (" +
                    RankingContract.RankEntry._ID + " INTEGER PRIMARY KEY," +
                    RankingContract.RankEntry.COLUMN_NAME_PLAYER_NAME + " TEXT," +
                    RankingContract.RankEntry.COLUMN_NAME_VALUE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + RankingContract.RankEntry.TABLE_NAME;

    public RankingDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addRank(String name, int value) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RankingContract.RankEntry.COLUMN_NAME_PLAYER_NAME, name);
        values.put(RankingContract.RankEntry.COLUMN_NAME_VALUE, value);

        long newRowId = db.insert(RankingContract.RankEntry.TABLE_NAME, null, values);
    }

    public ArrayList<Pair<String, Integer>> readRanking() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                RankingContract.RankEntry.COLUMN_NAME_PLAYER_NAME,
                RankingContract.RankEntry.COLUMN_NAME_VALUE
        };

        String selection = RankingContract.RankEntry.COLUMN_NAME_PLAYER_NAME + " = ?";
        String[] selectionArgs = { "My Title" };

        String sortOrder =
                RankingContract.RankEntry.COLUMN_NAME_VALUE + " DESC";

        Cursor cursor = db.query(
                RankingContract.RankEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,
//                selection,              // The columns for the WHERE clause
//                selectionArgs,          // The values for the WHERE clause
                null,
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<Pair<String, Integer>> itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(RankingContract.RankEntry._ID));
            String playerId = cursor.getString(
                    cursor.getColumnIndexOrThrow(RankingContract.RankEntry.COLUMN_NAME_PLAYER_NAME));
            int value = cursor.getInt(
                    cursor.getColumnIndexOrThrow(RankingContract.RankEntry.COLUMN_NAME_VALUE));
            itemIds.add(new Pair<String, Integer>(playerId, value));
        }
        cursor.close();
        Log.d("DB", itemIds.toString());
        return itemIds;
    }

}