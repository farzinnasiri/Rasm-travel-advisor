package com.rasm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class SQLiteOpenHelperExtender extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "adventures.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES_ADVENTURES = "CREATE TABLE "+ AdventureContract.AdventureEntry.TABLE_NAME +" ("
            +AdventureContract.AdventureEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +AdventureContract.AdventureEntry.COLUMN_USER + " TEXT NOT NULL, "
            +AdventureContract.AdventureEntry.COLUMN_CONDITION + " INTEGER NOT NULL DEFAULT 0, "
            +AdventureContract.AdventureEntry.COLUMN_VISIBILITY + " INTEGER NOT NULL DEFAULT 0, "
            +AdventureContract.AdventureEntry.COLUMN_UPLOADEDFILES + " TEXT, "
            +AdventureContract.AdventureEntry.COLUMN_STREAM + " TEXT, "
            +AdventureContract.AdventureEntry.COLUMN_STYLE + " INTEGER NOT NULL DEFAULT 0);";
    private static final String SQL_CREATE_ENTRIES_USERS = "CREATE TABLE "+ UserContract.UserEntry.TABLE_NAME +" ("
//            +UserContract.UserEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +UserContract.UserEntry.COLUMN_NAME + " TEXT PRIMARY KEY NOT NULL, "
            +UserContract.UserEntry.COLUMN_PASS + " TEXT NOT NULL, "
            +UserContract.UserEntry.COLUMN_PHONE + " VARCHAR NOT NULL, "
            +UserContract.UserEntry.COLUMN_PROFILE_PICTURE + " TEXT NOT NULL, "
            +UserContract.UserEntry.COLUMN_EMAIL + " TEXT NOT NULL, "
            +UserContract.UserEntry.COLUMN_ADVENTURES + " TEXT, "
            +UserContract.UserEntry.COLUMN_VISIBILITY + " INTEGER NOT NULL DEFAULT 0, "
            +UserContract.UserEntry.COLUMN_UPLOADEDFILES + " TEXT, "
            +UserContract.UserEntry.COLUMN_SCORE + " INTEGER NOT NULL DEFAULT 0);";
    private static final String SQL_CREATE_ENTRIES_USER_ADVENTURE = "CREATE TABLE "+ UserAdventureContract.UserAdventureEntry.TABLE_NAME +" ("
//            +UserContract.UserEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +"FOREIGN KEY(" + UserAdventureContract.UserAdventureEntry.COLUMN_ADVENTUTRE + ") REFERENCES " + AdventureContract.AdventureEntry.TABLE_NAME       + "(" + AdventureContract.AdventureEntry._ID + ")  ON DELETE CASCADE ON UPDATE CASCADE , "
            +"FOREIGN KEY(" + UserAdventureContract.UserAdventureEntry.COLUMN_USER + ") REFERENCES " + UserContract.UserEntry.TABLE_NAME       + "(" + UserContract.UserEntry.COLUMN_NAME + ")  ON DELETE CASCADE ON UPDATE CASCADE;";

    public SQLiteOpenHelperExtender(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        Log.v(LOG_TAG,SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_ADVENTURES);
        db.execSQL(SQL_CREATE_ENTRIES_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL("DROP TABLE IF EXISTS " + AdventureContract.AdventureEntry.TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME );

        onCreate(db);
    }




}
