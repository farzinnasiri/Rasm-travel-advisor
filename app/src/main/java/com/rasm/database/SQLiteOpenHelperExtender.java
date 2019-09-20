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

import com.rasm.adventures.Adventure;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

public class SQLiteOpenHelperExtender extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "adventures.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES_ADVENTURES = "CREATE TABLE " + AdventureContract.AdventureEntry.TABLE_NAME + " ("
            + AdventureContract.AdventureEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + AdventureContract.AdventureEntry.COLUMN_USER + " TEXT NOT NULL, "
            + AdventureContract.AdventureEntry.COLUMN_CONDITION + " INTEGER NOT NULL DEFAULT 0, "
            + AdventureContract.AdventureEntry.COLUMN_VISIBILITY + " INTEGER NOT NULL DEFAULT 0, "
            + AdventureContract.AdventureEntry.COLUMN_IMAGES + " TEXT, "
            + AdventureContract.AdventureEntry.COLUMN_TIME_START + " DATETIME, "
            + AdventureContract.AdventureEntry.COLUMN_TIME_END + " DATEETIME, "

            + AdventureContract.AdventureEntry.COLUMN_DESCRIPTIONS + " TEXT, "
            + AdventureContract.AdventureEntry.COLUMN_STREAM + " TEXT, "
            + AdventureContract.AdventureEntry.COLUMN_STYLE + " INTEGER NOT NULL DEFAULT 0);";
    private static final String SQL_CREATE_ENTRIES_USERS = "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " ("
//            +UserContract.UserEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + UserContract.UserEntry._COLUMN_NAME + " TEXT PRIMARY KEY NOT NULL, "
            + UserContract.UserEntry.COLUMN_PASS + " TEXT NOT NULL, "
            + UserContract.UserEntry.COLUMN_PHONE + " VARCHAR NOT NULL, "
            + UserContract.UserEntry.COLUMN_PROFILE_PICTURE + " BLOB, "
            + UserContract.UserEntry.COLUMN_EMAIL + " TEXT, "
            + UserContract.UserEntry.COLUMN_ADVENTURES + " BLOB, "
            + UserContract.UserEntry.COLUMN_VISIBILITY + " INTEGER NOT NULL DEFAULT 0, "
            + UserContract.UserEntry.COLUMN_UPLOADEDFILES + " BLOB, "
            + UserContract.UserEntry.COLUMN_SCORE + " INTEGER NOT NULL DEFAULT 0);";
    private static final String SQL_CREATE_ENTRIES_USER_ADVENTURE = "CREATE TABLE " + UserAdventureContract.UserAdventureEntry.TABLE_NAME + " ("
//            +UserContract.UserEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "FOREIGN KEY(" + UserAdventureContract.UserAdventureEntry.COLUMN_ADVENTUTRE + ") REFERENCES " + AdventureContract.AdventureEntry.TABLE_NAME + "(" + AdventureContract.AdventureEntry._ID + ")  ON DELETE CASCADE ON UPDATE CASCADE , "
            + "FOREIGN KEY(" + UserAdventureContract.UserAdventureEntry.COLUMN_USER + ") REFERENCES " + UserContract.UserEntry.TABLE_NAME + "(" + UserContract.UserEntry._COLUMN_NAME + ")  ON DELETE CASCADE ON UPDATE CASCADE;";
    private static final String SQL_CREATE_ENTRIES_PLACES = "CREATE TABLE " + PlaceContract.PlaceEntry.TABLE_NAME + " ("
            + PlaceContract.PlaceEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PlaceContract.PlaceEntry.COLUMN_DESCRIPTION + " TEXT, "
            + PlaceContract.PlaceEntry.COLUMN_TYPE + " TEXT, "
            + PlaceContract.PlaceEntry.COLUMN_IMAGES + " TEXT, "
            + PlaceContract.PlaceEntry.COLUMN_NAME + " TEXT NOT NULL, "
            + PlaceContract.PlaceEntry.COLUMN_POSITION + " VARCHAR);";

    private static final String SQL_CREATE_ENTRIES_FRIENDS = "CREATE TABLE " + FriendsContract.FriendsEntry.TABLE_NAME + " ("
            + FriendsContract.FriendsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "FOREIGN KEY(" + FriendsContract.FriendsEntry.COLUMN_USER + ") REFERENCES " + UserContract.UserEntry.TABLE_NAME + "(" + UserContract.UserEntry._COLUMN_NAME + ")  ON DELETE CASCADE ON UPDATE CASCADE , "
            + "FOREIGN KEY(" + FriendsContract.FriendsEntry.COLUMN_FRIEND + ") REFERENCES " + UserContract.UserEntry.TABLE_NAME + "(" + UserContract.UserEntry._COLUMN_NAME + ")  ON DELETE CASCADE ON UPDATE CASCADE;";

    public SQLiteOpenHelperExtender(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_ADVENTURES);
        db.execSQL(SQL_CREATE_ENTRIES_USERS);
        db.execSQL(SQL_CREATE_ENTRIES_USER_ADVENTURE);
        db.execSQL(SQL_CREATE_ENTRIES_PLACES);
        db.execSQL(SQL_CREATE_ENTRIES_FRIENDS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AdventureContract.AdventureEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserAdventureContract.UserAdventureEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PlaceContract.PlaceEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FriendsContract.FriendsEntry.TABLE_NAME);

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
            String selectQuery = "SELECT" + columnName + " FROM " + tableName;
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
        cv.put(UserContract.UserEntry._COLUMN_NAME, userName);
        cv.put(UserContract.UserEntry.COLUMN_PASS, pass);
        cv.put(UserContract.UserEntry.COLUMN_PHONE, phoneNumber);
        cv.put(UserContract.UserEntry.COLUMN_EMAIL, email);
        cv.put(UserContract.UserEntry.COLUMN_SCORE, 0);
        db.insert(UserContract.UserEntry.TABLE_NAME, null, cv);
        insertBitmap(image, UserContract.UserEntry.TABLE_NAME, UserContract.UserEntry.COLUMN_PROFILE_PICTURE);

    }


    public boolean checkForUserName(String userName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + UserContract.UserEntry._COLUMN_NAME + " FROM " + UserContract.UserEntry.TABLE_NAME, null);
        cursor.moveToFirst();
        while (cursor != null) {
            if (cursor.getString(0) == userName)
                return true;
            cursor.moveToNext();
        }
        return checkForUserPhone(userName);
    }

    public boolean checkForUserPhone(String userName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + UserContract.UserEntry.COLUMN_PHONE + " FROM " + UserContract.UserEntry.TABLE_NAME, null);
        cursor.moveToFirst();
        while (cursor != null) {
            if (cursor.getString(0) == userName)
                return true;
            cursor.moveToNext();
        }
        return false;
    }


    public boolean userpassMatches(String userName, String pass) {
        SQLiteDatabase db = getReadableDatabase();
        if (checkForUserName(userName)) {

            Cursor cursor = db.rawQuery("SELECT " + UserContract.UserEntry.COLUMN_PASS + ", " + UserContract.UserEntry._COLUMN_NAME + " FROM " + UserContract.UserEntry.TABLE_NAME + " WHERE " + UserContract.UserEntry._COLUMN_NAME + "= '" + userName + "' ," + UserContract.UserEntry.COLUMN_PASS + " = '" + pass + "'", null);
            cursor.moveToFirst();
            if (cursor.getColumnIndex(UserContract.UserEntry._COLUMN_NAME) != -1)
                return true;


        }
        return false;
    }

    public String giveUserName(String userName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + UserContract.UserEntry._COLUMN_NAME + " FROM " + UserContract.UserEntry.TABLE_NAME, null);
        cursor.moveToFirst();
        while (cursor != null) {
            if (cursor.getString(0) == userName)
                return userName;
            cursor.moveToNext();
        }
        cursor = db.rawQuery("SELECT " + UserContract.UserEntry._COLUMN_NAME + " FROM " + UserContract.UserEntry.TABLE_NAME + " WHERE " + UserContract.UserEntry.COLUMN_PHONE + "= '" + userName + "'", null);
        cursor.moveToFirst();
        String s = cursor.getString(0);
        return s;
    }

    public String getUserMail(String userName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + UserContract.UserEntry.COLUMN_EMAIL + " FROM " + UserContract.UserEntry.TABLE_NAME + " WHERE " + UserContract.UserEntry._COLUMN_NAME + "= '" + userName + "'", null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public String getUserScore(String userName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + UserContract.UserEntry.COLUMN_SCORE + " FROM " + UserContract.UserEntry.TABLE_NAME + " WHERE " + UserContract.UserEntry._COLUMN_NAME + "= '" + userName + "'", null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public HashMap getUserDatas(String userName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserContract.UserEntry.TABLE_NAME + " WHERE " + UserContract.UserEntry._COLUMN_NAME + "= '" + userName + "'", null);
        HashMap map = new HashMap();
        int i = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_SCORE);
        map.put("score", cursor.getString(i));
        i = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_EMAIL);
        map.put("email", cursor.getString(i));
        i = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_PASS);
        map.put("pass", cursor.getString(i));
        i = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_PHONE);
        map.put("phone", cursor.getString(i));
        i = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_VISIBILITY);
        map.put("visibility", cursor.getString(i));
        map.put("image", getBitmap(UserContract.UserEntry.TABLE_NAME, UserContract.UserEntry.COLUMN_PROFILE_PICTURE));
        i = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_ADVENTURES);
        map.put("adventures", cursor.getBlob(i));
        return map;

    }

    public ArrayList<Adventure> getUserAdventures(String userName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + UserAdventureContract.UserAdventureEntry.COLUMN_ADVENTUTRE + " FROM " + UserAdventureContract.UserAdventureEntry.TABLE_NAME + " WHERE " + UserAdventureContract.UserAdventureEntry.COLUMN_USER + "= '" + userName + "'", null);
        ArrayList<Adventure> list = new ArrayList<Adventure>();
        cursor.moveToFirst();
        while (cursor != null) {
            String advId = cursor.getString(0);
            Cursor c = db.rawQuery("SELECT " + AdventureContract.AdventureEntry._ID + " FROM " + UserContract.UserEntry.TABLE_NAME + " WHERE " + UserContract.UserEntry._ID + "= '" + advId + "'", null);
            c.moveToFirst();
            list.add(new Adventure(getAdventureDatas(advId)));
            cursor.moveToNext();
        }
        return list;
    }

    public HashMap getAdventureDatas(String advId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + AdventureContract.AdventureEntry.TABLE_NAME + " WHERE " + AdventureContract.AdventureEntry._ID + "= '" + advId + "'", null);
        HashMap map = new HashMap();
        int i = cursor.getColumnIndex(AdventureContract.AdventureEntry.COLUMN_CONDITION);
        map.put("condition", cursor.getInt(i));
        i = cursor.getColumnIndex(AdventureContract.AdventureEntry.COLUMN_DESCRIPTIONS);
        map.put("description", cursor.getString(i));
        i = cursor.getColumnIndex(AdventureContract.AdventureEntry.COLUMN_STREAM);
        map.put("stream", cursor.getString(i));
        i = cursor.getColumnIndex(AdventureContract.AdventureEntry.COLUMN_STYLE);
        map.put("style", cursor.getInt(i));
        i = cursor.getColumnIndex(AdventureContract.AdventureEntry.COLUMN_VISIBILITY);
        map.put("visibility", cursor.getInt(i));
        i = cursor.getColumnIndex(AdventureContract.AdventureEntry.COLUMN_USER);
        map.put("user", cursor.getString(i));
        i = cursor.getColumnIndex(AdventureContract.AdventureEntry.COLUMN_TIME_START);
        map.put("start", cursor.getString(i));
        i = cursor.getColumnIndex(AdventureContract.AdventureEntry.COLUMN_TIME_END);
        map.put("end", cursor.getString(i));
        i = cursor.getColumnIndex(AdventureContract.AdventureEntry.COLUMN_IMAGES);
        map.put("images", stringToBitmapArray(cursor.getString(i)));
        map.put("id", advId);
        return map;

    }

    public ArrayList<String> getAdventureUsers(String advID) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + UserAdventureContract.UserAdventureEntry.COLUMN_USER + " FROM " + UserAdventureContract.UserAdventureEntry.TABLE_NAME + " WHERE " + UserAdventureContract.UserAdventureEntry.COLUMN_ADVENTUTRE + "= '" + advID + "'", null);
        ArrayList<String> list = new ArrayList<String>();
        cursor.moveToFirst();
        while (cursor != null) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        return list;
    }

    public void insertNewAdventure(String ID, String userName, String startTime, String endTime, int condition, String stream, int style, int visibility, ArrayList<Bitmap> images, String descriptions) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(AdventureContract.AdventureEntry.COLUMN_USER, userName);
        cv.put(AdventureContract.AdventureEntry.COLUMN_CONDITION, condition);
        cv.put(AdventureContract.AdventureEntry.COLUMN_STREAM, stream);
        cv.put(AdventureContract.AdventureEntry.COLUMN_STYLE, style);
        cv.put(AdventureContract.AdventureEntry.COLUMN_IMAGES, arrayBitmapToJsonString(images));
        cv.put(AdventureContract.AdventureEntry.COLUMN_DESCRIPTIONS, descriptions);
        cv.put(AdventureContract.AdventureEntry.COLUMN_VISIBILITY, visibility);
        cv.put(AdventureContract.AdventureEntry.COLUMN_TIME_START, startTime);
        cv.put(AdventureContract.AdventureEntry.COLUMN_TIME_END, endTime);

        db.insert(AdventureContract.AdventureEntry.TABLE_NAME, null, cv);
        cv = new ContentValues();
        cv.put(UserAdventureContract.UserAdventureEntry.COLUMN_USER, userName);
        cv.put(UserAdventureContract.UserAdventureEntry.COLUMN_ADVENTUTRE, ID);
        db.insert(UserAdventureContract.UserAdventureEntry.TABLE_NAME, null, cv);

    }


    public void insertPlace(String name, String describe, ArrayList<Bitmap> images, String position, String type) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PlaceContract.PlaceEntry.COLUMN_NAME, name);
        cv.put(PlaceContract.PlaceEntry.COLUMN_POSITION, position);
        cv.put(PlaceContract.PlaceEntry.COLUMN_TYPE, type);
        cv.put(PlaceContract.PlaceEntry.COLUMN_DESCRIPTION, describe);
        cv.put(PlaceContract.PlaceEntry.COLUMN_IMAGES, arrayBitmapToJsonString(images));
        db.insert(UserContract.UserEntry.TABLE_NAME, null, cv);

    }

    public void insertNewFriend(String friend, String userName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FriendsContract.FriendsEntry.COLUMN_USER, userName);
        cv.put(FriendsContract.FriendsEntry.COLUMN_FRIEND, friend);
        db.insert(UserAdventureContract.UserAdventureEntry.TABLE_NAME, null, cv);
    }

    public HashMap getPlaceDatas(String position) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + PlaceContract.PlaceEntry.TABLE_NAME + " WHERE " + PlaceContract.PlaceEntry.COLUMN_POSITION + "= '" + position + "'", null);
        HashMap map = new HashMap();
        int i = cursor.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_DESCRIPTION);
        map.put("description", cursor.getString(i));
        i = cursor.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_NAME);
        map.put("name", cursor.getString(i));
        i = cursor.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_TYPE);
        map.put("type", cursor.getString(i));
        i = cursor.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_IMAGES);
        ArrayList<Bitmap> images = stringToBitmapArray(cursor.getString(i));
        map.put("images", images);

        return map;
    }


    public static ArrayList<Bitmap> stringToBitmapArray(String json) {
        JSONArray jsonArr = null;
        ArrayList<Bitmap> images = new ArrayList<Bitmap>();
        try {
            jsonArr = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArr.length(); i++) {
            byte[] bytes = null;
            Bitmap bitmap = null;
            try {
                String str = (String) jsonArr.get(i);
                bytes = str.getBytes(Charset.forName("UTF-8"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            images.add(bitmap);
        }

        return images;
    }

    public static String arrayBitmapToJsonString(ArrayList<Bitmap> images) {
        JSONArray jsonArr = new JSONArray();
        for (int i = 0; i < images.size(); i++) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            images.get(i).compress(Bitmap.CompressFormat.PNG, 100, out);
            byte[] buffer = out.toByteArray();
            String s = new String(buffer, Charset.forName("UTF-8"));
            jsonArr.put(s);
        }
        return jsonArr.toString();
    }


}