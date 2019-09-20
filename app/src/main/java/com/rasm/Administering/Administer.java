package com.rasm.Administering;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;

import com.rasm.R;
import com.rasm.adventures.Adventure;
import com.rasm.adventures.Place;
import com.rasm.database.PlaceContract;
import com.rasm.database.SQLiteOpenHelperExtender;

import java.util.ArrayList;
import java.util.HashMap;

public class Administer {

    private static Administer administer;
    private static Context context;

    private boolean isLoggedIn;
    private String username;

    private SQLiteOpenHelperExtender database;

    private Administer(){
        load_data();
        insertFakeData();
    }

    private void insertFakeData() {
        ArrayList<Bitmap> fakeImages = new ArrayList<>();
        fakeImages.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.image));
        fakeImages.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.image));
        fakeImages.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.image));

        database.insertPlace("حافظیه","حافظیه یا آرامگاه حافظ نام مجموعه ای آرامگاهی در شمال شهر شیراز و در جنوب دروازهٔ قرآن است که آرامگاه حافظ شیرازی را در خود جای داده است. مساحت حافظیه ۲ هکتار بوده و از ۲ صحن شمالی و جنوبی تشکیل یافته است و این صحنها توسط تالاری از یکدیگر جدا شده اند. این مجموعه ۴ در ورودی-خروجی دارد که در اصلی در سمت جنوب آن، دو در در سمت غرب و یک در در سمت شمال شرق مجموعه قرار گرفته است.[۱]","", PlaceContract.PlaceEntry.TYPE_SHRINE);
        database.insertPlace("سعدیه","حافظیه یا آرامگاه حافظ نام مجموعه ای آرامگاهی در شمال شهر شیراز و در جنوب دروازهٔ قرآن است که آرامگاه حافظ شیرازی را در خود جای داده است. مساحت حافظیه ۲ هکتار بوده و از ۲ صحن شمالی و جنوبی تشکیل یافته است و این صحنها توسط تالاری از یکدیگر جدا شده اند. این مجموعه ۴ در ورودی-خروجی دارد که در اصلی در سمت جنوب آن، دو در در سمت غرب و یک در در سمت شمال شرق مجموعه قرار گرفته است.[۱]","", PlaceContract.PlaceEntry.TYPE_SHRINE);
        database.insertPlace("کبکیه","حافظیه یا آرامگاه حافظ نام مجموعه ای آرامگاهی در شمال شهر شیراز و در جنوب دروازهٔ قرآن است که آرامگاه حافظ شیرازی را در خود جای داده است. مساحت حافظیه ۲ هکتار بوده و از ۲ صحن شمالی و جنوبی تشکیل یافته است و این صحنها توسط تالاری از یکدیگر جدا شده اند. این مجموعه ۴ در ورودی-خروجی دارد که در اصلی در سمت جنوب آن، دو در در سمت غرب و یک در در سمت شمال شرق مجموعه قرار گرفته است.[۱]","", PlaceContract.PlaceEntry.TYPE_SHRINE);
        database.insertPlace("جامنیه","حافظیه یا آرامگاه حافظ نام مجموعه ای آرامگاهی در شمال شهر شیراز و در جنوب دروازهٔ قرآن است که آرامگاه حافظ شیرازی را در خود جای داده است. مساحت حافظیه ۲ هکتار بوده و از ۲ صحن شمالی و جنوبی تشکیل یافته است و این صحنها توسط تالاری از یکدیگر جدا شده اند. این مجموعه ۴ در ورودی-خروجی دارد که در اصلی در سمت جنوب آن، دو در در سمت غرب و یک در در سمت شمال شرق مجموعه قرار گرفته است.[۱]","", PlaceContract.PlaceEntry.TYPE_SHRINE);
        database.insertPlace("اندرویدیه","حافظیه یا آرامگاه حافظ نام مجموعه ای آرامگاهی در شمال شهر شیراز و در جنوب دروازهٔ قرآن است که آرامگاه حافظ شیرازی را در خود جای داده است. مساحت حافظیه ۲ هکتار بوده و از ۲ صحن شمالی و جنوبی تشکیل یافته است و این صحنها توسط تالاری از یکدیگر جدا شده اند. این مجموعه ۴ در ورودی-خروجی دارد که در اصلی در سمت جنوب آن، دو در در سمت غرب و یک در در سمت شمال شرق مجموعه قرار گرفته است.[۱]","", PlaceContract.PlaceEntry.TYPE_SHRINE);

//        database.insertNewUser("سلام","salam ","09123341234","",BitmapFactory.decodeResource(context.getResources(), R.drawable.image));
//        database.insertNewUser("rgd","ddd ","09123345234","",BitmapFactory.decodeResource(context.getResources(), R.drawable.image));
//        database.insertNewUser("asd","fff ","0912336234","",BitmapFactory.decodeResource(context.getResources(), R.drawable.image));
//        database.insertNewUser("wer","salghham ","09111341234","",BitmapFactory.decodeResource(context.getResources(), R.drawable.image));
        database.insertNewUser("uyt","saljjam","09123341134","",BitmapFactory.decodeResource(context.getResources(), R.drawable.image));

        database.insertNewFriend("rgd", "سلام");
        database.insertNewFriend("asd", "سلام");
        database.insertNewFriend("wer", "سلام");
        database.insertNewFriend("uyt", "سلام");




        //database.insertNewUser();

    }

    /*
        sync from database
     */
    private void load_data() {
        load_from_storage();
        load_from_database();
    }

    private void load_from_database() {
        database = new SQLiteOpenHelperExtender(context);
    }

    private void load_from_storage() {

          SharedPreferences sharedPreferences = PreferenceManager
                  .getDefaultSharedPreferences(context);
          isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
          if(isLoggedIn){
              username = sharedPreferences.getString("username" , "");
          }

    }

    public boolean isLoggedIn(){
        return  isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn){
        this.isLoggedIn = isLoggedIn;

        updateData();
    }
    public void setLoggedIn(boolean isLoggedIn, String uesrName){
        this.isLoggedIn = isLoggedIn;
        this.username = username;

        updateData();
    }
    public void insertNewUser(String username, String password, String phone, String s, Bitmap bitmap) {
    database.insertNewUser(username, password,phone,s,bitmap);
    }


    private void updateData() {
        update_to_storage();
    }

    private void update_to_storage() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.putString("username" , username);
        editor.apply();
    }

    public static Administer getInstance(){
        if(administer == null){
            administer = new Administer();
        }
        return administer;
    }

    public static void setContext(Context context){
        Administer.context = context;
    }

    /*
        this method get username or phone and password and check if both
        are correct or not!
     */
    public boolean check_info_of_user_is_correct(String user_or_pass , String pass) {

        return  database.userpassMatches(user_or_pass, pass);
    }
    public String getUsername(){
        return database.giveUserName(username);
    }

    /*
        check if the username exist or not
        if the answer is not the person can create account
     */
    public boolean if_username_exist(String username) {
        return false;
    }

    public ArrayList<Adventure> getUserAdventure(String username){
        return database.getUserAdventures(username);
    }
    public HashMap getUserDatas(String username){
      return  database.getUserDatas(username);
    }

    public ArrayList<String> getUserFriends(String username){
        return database.getFriends(username);
    }


    public ArrayList<Adventure> getFriendsAdventure(String username){
        ArrayList<Adventure> adventures = new ArrayList<>();

        for(String friend: getUserFriends(username)){
            adventures.addAll(getUserAdventure(friend));
        }
        return adventures;
    }


    public ArrayList<Adventure> getFriendsAventure(String username , int condition){
        ArrayList<Adventure> adventures = new ArrayList<>();

        for(Adventure adventure: getFriendsAdventure(username)){
                if(adventure.getCondition() == 1){
                    adventures.add(adventure);
            }
        }
        return adventures;

    }



    public ArrayList<Place> getSuggestedPlace(int howMany){




        return null;
    }


}
