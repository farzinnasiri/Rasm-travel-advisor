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
import java.sql.ResultSet;
import java.util.ArrayList;

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
            + UserContract.UserEntry.COLUMN_PROFILE_PICTURE + " TEXT, "
            + UserContract.UserEntry.COLUMN_EMAIL + " TEXT, "
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


    public Bitmap getBitmap(String tableName, String columnName) {
        Bitmap bitmap = null;
        // Open the database for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();

        try {
            String selectQuery = "SELECT"+ columnName+" FROM " + tableName;
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


    public boolean checkForUserName(String userName) {
       SQLiteDatabase db = getReadableDatabase();
       Cursor cursor = db.rawQuery("SELECT "+ UserContract.UserEntry.COLUMN_NAME +" FROM " + UserContract.UserEntry.TABLE_NAME, null);
        cursor.moveToFirst();
        while(cursor!=null) {
            if(cursor.getString(0) == userName)
                 return true;
            cursor.moveToNext();
        }
        return checkForUserPhone(userName);
    }

    public boolean checkForUserPhone(String userName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+ UserContract.UserEntry.COLUMN_PHONE +" FROM " + UserContract.UserEntry.TABLE_NAME, null);
        cursor.moveToFirst();
        while(cursor!=null) {
            if (cursor.getString(0) == userName)
                return true;
            cursor.moveToNext();
        }
        return false;
    }


    public boolean userpassMatches(String userName, String pass){
        SQLiteDatabase db = getReadableDatabase();
        if(checkForUserName(userName)) {

            Cursor cursor = db.rawQuery("SELECT " + UserContract.UserEntry.COLUMN_PASS+", "+ UserContract.UserEntry.COLUMN_NAME + " FROM " + UserContract.UserEntry.TABLE_NAME + " WHERE "+ UserContract.UserEntry.COLUMN_NAME + "= '" + userName+"' ,"+ UserContract.UserEntry.COLUMN_PASS + " = '"+pass+"'", null);
            cursor.moveToFirst();
                if(cursor.getColumnIndex(UserContract.UserEntry.COLUMN_NAME)!=-1)
                    return true;


        }
        return false;
    }


    public String getUserMail(String userName){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+ UserContract.UserEntry.COLUMN_EMAIL +" FROM " + UserContract.UserEntry.TABLE_NAME +" WHERE "+ UserContract.UserEntry.COLUMN_NAME+"= '"+userName+"'", null);
       return cursor.getString(0);
    }
    public ArrayList<Adventure> getUserAdventures(String userName){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+ UserAdventureContract.UserAdventureEntry.COLUMN_ADVENTUTRE +" FROM " + UserAdventureContract.UserAdventureEntry.TABLE_NAME +" WHERE "+ UserAdventureContract.UserAdventureEntry.COLUMN_USER+"= "+userName+"'", null);
        ArrayList<Adventure> list = new ArrayList<Adventure>();
        cursor.moveToFirst();
        while(cursor!=null) {
            list.add(new Adventure(cursor.getBlob(0)));
            cursor.moveToNext();
        }
        return list;
    }

    public void insertNewAdventure(String ID, String userName, int condition, String stream, int style, int visibility) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(AdventureContract.AdventureEntry.COLUMN_USER, userName);
        cv.put(AdventureContract.AdventureEntry.COLUMN_CONDITION, condition);
        cv.put(AdventureContract.AdventureEntry.COLUMN_STREAM, stream);
        cv.put(AdventureContract.AdventureEntry.COLUMN_STYLE, style);
//        cv.put(AdventureContract.AdventureEntry.COLUMN_UPLOADEDFILES, );
        cv.put(AdventureContract.AdventureEntry.COLUMN_VISIBILITY, visibility);
        db.insert(AdventureContract.AdventureEntry.TABLE_NAME, null, cv);
        cv = new ContentValues();
        cv.put(UserAdventureContract.UserAdventureEntry.COLUMN_USER, userName);
        cv.put(UserAdventureContract.UserAdventureEntry.COLUMN_ADVENTUTRE,ID);
        db.insert(UserAdventureContract.UserAdventureEntry.TABLE_NAME, null, cv);

//        insertBitmap(image, UserContract.UserEntry.TABLE_NAME, UserContract.UserEntry.COLUMN_PROFILE_PICTURE);

    }
//    public int getUserCondition(String userName){
//
//    }


}