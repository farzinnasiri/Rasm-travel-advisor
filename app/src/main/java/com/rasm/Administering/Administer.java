package com.rasm.Administering;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;

import com.rasm.adventures.Adventure;
import com.rasm.adventures.Place;
import com.rasm.database.SQLiteOpenHelperExtender;

import java.util.ArrayList;

public class Administer {

    private static Administer administer;
    private static Context context;

    private boolean isLoggedIn;
    private String username;

    private SQLiteOpenHelperExtender database;

    private Administer() {
        load_data();
    }

    /*
        sync from database
     */
    private void load_data() {
        load_from_storage();
        load_from_database();
    }

    private void load_from_database() {
//        database = new SQLiteOpenHelperExtender(context);
    }

    private void load_from_storage() {

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            username = sharedPreferences.getString("username", "");
        }

    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;

        updateData();
    }

    public void setLoggedIn(boolean isLoggedIn, String username) {
        this.isLoggedIn = isLoggedIn;
        this.username = username;
        updateData();
    }

    private void updateData() {
        update_to_storage();
    }

    private void update_to_storage() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.putString("username", username);
        editor.apply();
    }

    public static Administer getInstance() {
        if (administer == null) {
            administer = new Administer();
        }
        return administer;
    }

    public static void setContext(Context context) {
        Administer.context = context;
    }

    /*
        this method get username or phone and password and check if both
        are correct or not!
     */
    public boolean check_info_of_user_is_correct(String user_or_phone, String pass) {

        return database.userpassMatches(user_or_phone, pass);

    }

    /*
        check if the username exist or not
        if the answer is not the person can create account
     */
    public boolean if_username_exist(String username) {
        return database.checkForUserName(username);
    }

    public ArrayList<Adventure> getUserAdventure(String username) {
        return database.getUserAdventures(username);
    }

    public ArrayList<Place> getSuggestedPlace(int howMany) {


        return null;
    }

    public void insertNewUser(String userName, String pass, String phoneNumber, String email, Bitmap image) {
        database.insertNewUser(userName, pass, phoneNumber, email, image);
    }
}
