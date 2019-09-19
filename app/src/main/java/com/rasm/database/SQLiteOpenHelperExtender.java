package com.rasm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class SQLiteOpenHelperExtender extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "adventures.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES_ADVENTURES = "CREATE TABLE " + AdventureContract.AdventureEntry.TABLE_NAME + " ("
            + AdventureContract.AdventureEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + AdventureContract.AdventureEntry.COLUMN_USER + " TEXT NOT NULL, "
            + AdventureContract.AdventureEntry.COLUMN_CONDITION + " INTEGER NOT NULL DEFAULT 0, "
            + AdventureContract.AdventureEntry.COLUMN_VISIBILITY + " INTEGER NOT NULL DEFAULT 0, "
            + AdventureContract.AdventureEntry.COLUMN_UPLOADEDFILES + " TEXT, "
            + AdventureContract.AdventureEntry.COLUMN_STREAM + " TEXT, "
            + AdventureContract.AdventureEntry.COLUMN_STYLE + " INTEGER NOT NULL DEFAULT 0);";
    private static final String SQL_CREATE_ENTRIES_USERS = "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " ("
//            +UserContract.UserEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + UserContract.UserEntry.COLUMN_NAME + " TEXT PRIMARY KEY NOT NULL, "
            + UserContract.UserEntry.COLUMN_PASS + " TEXT NOT NULL, "
            + UserContract.UserEntry.COLUMN_PHONE + " VARCHAR NOT NULL, "
            + UserContract.UserEntry.COLUMN_PROFILE_PICTURE + " TEXT NOT NULL, "
            + UserContract.UserEntry.COLUMN_EMAIL + " TEXT NOT NULL, "
            + UserContract.UserEntry.COLUMN_ADVENTURES + " TEXT, "
            + UserContract.UserEntry.COLUMN_VISIBILITY + " INTEGER NOT NULL DEFAULT 0, "
            + UserContract.UserEntry.COLUMN_UPLOADEDFILES + " TEXT, "
            + UserContract.UserEntry.COLUMN_SCORE + " INTEGER NOT NULL DEFAULT 0);";
    private static final String SQL_CREATE_ENTRIES_USER_ADVENTURE = "CREATE TABLE " + UserAdventureContract.UserAdventureEntry.TABLE_NAME + " ("
//            +UserContract.UserEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "FOREIGN KEY(" + UserAdventureContract.UserAdventureEntry.COLUMN_ADVENTUTRE + ") REFERENCES " + AdventureContract.AdventureEntry.TABLE_NAME + "(" + AdventureContract.AdventureEntry._ID + ")  ON DELETE CASCADE ON UPDATE CASCADE , "
            + "FOREIGN KEY(" + UserAdventureContract.UserAdventureEntry.COLUMN_USER + ") REFERENCES " + UserContract.UserEntry.TABLE_NAME + "(" + UserContract.UserEntry.COLUMN_NAME + ")  ON DELETE CASCADE ON UPDATE CASCADE;";

    public SQLiteOpenHelperExtender(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        Log.v(LOG_TAG,SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_ADVENTURES);
        db.execSQL(SQL_CREATE_ENTRIES_USERS);
        db.execSQL(SQL_CREATE_ENTRIES_USER_ADVENTURE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL("DROP TABLE IF EXISTS " + AdventureContract.AdventureEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserAdventureContract.UserAdventureEntry.TABLE_NAME);

        onCreate(db);
    }

    public void insertBitmap(Bitmap bm, String tableName, String columnName) {

        // Convert the image into byte array
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, out);
        byte[] buffer = out.toByteArray();
        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction.
        db.beginTransaction();
        ContentValues values;

        try {
            values = new ContentValues();
            values.put(columnName, buffer);
            // Insert Row
            long i = db.insert(tableName, null, values);
            Log.i("Insert", i + "");
            // Insert into database successfully.
            db.setTransactionSuccessful();

        } catch (SQLiteException e) {
            e.printStackTrace();

        } finally {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }
    }


    public Bitmap getBitmap(int id, String tableName, String columnName) {
        Bitmap bitmap = null;
        // Open the database for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();

        try {
            String selectQuery = "SELECT * FROM " + tableName + " WHERE id = " + id;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    // Convert blob data to byte array
                    byte[] blob = cursor.getBlob(cursor.getColumnIndex(columnName));
                    // Convert the byte array to Bitmap
                    bitmap = BitmapFactory.decodeByteArray(blob, 0, blob.length);

                }

            }
            db.setTransactionSuccessful();

        } catch (SQLiteException e) {
            e.printStackTrace();

        } finally {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }
        return bitmap;

    }

    public void insertNewUser(String userName, String pass, String phoneNumber, String email, Bitmap image) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(UserContract.UserEntry.COLUMN_NAME, userName);
        cv.put(UserContract.UserEntry.COLUMN_PASS, pass);
        cv.put(UserContract.UserEntry.COLUMN_PHONE, phoneNumber);
        cv.put(UserContract.UserEntry.COLUMN_EMAIL, email);
        cv.put(UserContract.UserEntry.COLUMN_SCORE, 0);
        cv.put(UserContract.UserEntry.COLUMN_NAME, userName);
        db.insert(UserContract.UserEntry.TABLE_NAME, null, cv);
        insertBitmap(image, UserContract.UserEntry.TABLE_NAME, UserContract.UserEntry.COLUMN_PROFILE_PICTURE);

    }


    public boolean checkForUser(String userName) {
        SQLiteDatabase db = getReadableDatabase();
//        db.exe

return true;
    }
}