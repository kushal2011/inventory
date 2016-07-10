package com.example.kusha_000.inventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by kusha_000 on 29-06-2016.
 */
public class DataBaseUtils {
    private static DataBaseUtils _INSTANCE;
    private final String LOG_TAG = DataBaseUtils.class.getSimpleName();
    private DatabaseHelper mDatabaseHelper;

    private DataBaseUtils(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public static DataBaseUtils getInstance(Context context) {
        if (context == null) return null;
        if (_INSTANCE == null) {
            _INSTANCE = new DataBaseUtils(context);
        }
        return _INSTANCE;
    }

    public void insertIntoDB(String tableName, ContentValues contentValues) {
        if (tableName != null && contentValues != null) {
            SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
            db.insert(tableName, null, contentValues);
        } else {
            Log.e(LOG_TAG, "Error inserting into db");
        }
    }

    public Cursor readFromDB(String tableName, String[] projection) {
        if (tableName != null) {
            SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
            return db.query(tableName, projection, null, null, null, null, null);
        } else {
            Log.e(LOG_TAG, "Error reading from table");
        }
        return null;
    }

    public Cursor readFromDB(String tableName, String[] projection, String selection,
                             String[] selectionArgs) {
        if (tableName != null) {
            SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
            return db.query(tableName, projection, selection, selectionArgs, null, null, null);
        } else {
            Log.e(LOG_TAG, "Error reading from table");
        }
        return null;
    }

    public void deleteEntries(String tableName, String selection, String[] selectionArgs) {
        if (tableName != null) {
            SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
            db.delete(tableName, selection, selectionArgs);
        } else {
            Log.e(LOG_TAG, "Error deleting table");
        }
    }

    public void updateEntry(String tableName, ContentValues contentValues, String selection,
                            String[] selectionArgs) {
        if (tableName != null) {
            SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
            db.update(tableName, contentValues, selection, selectionArgs);
        } else {
            Log.e(LOG_TAG, "Error updating records");
        }
    }
}
