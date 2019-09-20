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

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Adventure> adventures = new ArrayList<>();
    private ArrayList<Place> places = new ArrayList<>();


    private Administer(){
        load_data();
        insertFakeData();
    }

    private void insertFakeData() {
        ArrayList<Integer> fakeImages = new ArrayList<>();
//        fakeImages.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.image));
//        fakeImages.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.image));
//        fakeImages.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.image));
        fakeImages.add(R.drawable.image);
        fakeImages.add(R.drawable.image);

        places.add(new Place("حافظیه","حافظیه یا آرامگاه حافظ نام مجموعه ای آرامگاهی در شمال شهر شیراز و در جنوب دروازهٔ قرآن است که آرامگاه حافظ شیرازی را در خود جای داده است. مساحت حافظیه ۲ هکتار بوده و از ۲ صحن شمالی و جنوبی تشکیل یافته است و این صحنها توسط تالاری از یکدیگر جدا شده اند. این مجموعه ۴ در ورودی-خروجی دارد که در اصلی در سمت جنوب آن، دو در در سمت غرب و یک در در سمت شمال شرق مجموعه قرار گرفته است.[۱]","","شیراز" , fakeImages));
        places.add(new Place("سعدیه","حافظیه یا آرامگاه حافظ نام مجموعه ای آرامگاهی در شمال شهر شیراز و در جنوب دروازهٔ قرآن است که آرامگاه حافظ شیرازی را در خود جای داده است. مساحت حافظیه ۲ هکتار بوده و از ۲ صحن شمالی و جنوبی تشکیل یافته است و این صحنها توسط تالاری از یکدیگر جدا شده اند. این مجموعه ۴ در ورودی-خروجی دارد که در اصلی در سمت جنوب آن، دو در در سمت غرب و یک در در سمت شمال شرق مجموعه قرار گرفته است.[۱]","","شیراز" , fakeImages));
        places.add(new Place("کبکیه","حافظیه یا آرامگاه حافظ نام مجموعه ای آرامگاهی در شمال شهر شیراز و در جنوب دروازهٔ قرآن است که آرامگاه حافظ شیرازی را در خود جای داده است. مساحت حافظیه ۲ هکتار بوده و از ۲ صحن شمالی و جنوبی تشکیل یافته است و این صحنها توسط تالاری از یکدیگر جدا شده اند. این مجموعه ۴ در ورودی-خروجی دارد که در اصلی در سمت جنوب آن، دو در در سمت غرب و یک در در سمت شمال شرق مجموعه قرار گرفته است.[۱]","", ,"مازندران" , fakeImages));
        places.add(new Place("جامنیه","حافظیه یا آرامگاه حافظ نام مجموعه ای آرامگاهی در شمال شهر شیراز و در جنوب دروازهٔ قرآن است که آرامگاه حافظ شیرازی را در خود جای داده است. مساحت حافظیه ۲ هکتار بوده و از ۲ صحن شمالی و جنوبی تشکیل یافته است و این صحنها توسط تالاری از یکدیگر جدا شده اند. این مجموعه ۴ در ورودی-خروجی دارد که در اصلی در سمت جنوب آن، دو در در سمت غرب و یک در در سمت شمال شرق مجموعه قرار گرفته است.[۱]","", "تهران" , fakeImages));
        places.add(new Place("اندرویدیه","حافظیه یا آرامگاه حافظ نام مجموعه ای آرامگاهی در شمال شهر شیراز و در جنوب دروازهٔ قرآن است که آرامگاه حافظ شیرازی را در خود جای داده است. مساحت حافظیه ۲ هکتار بوده و از ۲ صحن شمالی و جنوبی تشکیل یافته است و این صحنها توسط تالاری از یکدیگر جدا شده اند. این مجموعه ۴ در ورودی-خروجی دارد که در اصلی در سمت جنوب آن، دو در در سمت غرب و یک در در سمت شمال شرق مجموعه قرار گرفته است.[۱]","", "یزد" , fakeImages));

        users.add(new User("علی","123","ali@yahoo.com","09120177645", "aliguli", 0,new ArrayList<User>(),0,new ArrayList<Integer>()));
        users.add(new User("قلی","323","gholi97@gmail.com","09306056534", "gholiguli", 1,new ArrayList<User>(),0,new ArrayList<Integer>()));
        users.add(new User("تقی","54565","taghi@gmail.com","09126745098", "taghiguli", 2,new ArrayList<User>(),0,new ArrayList<Integer>()));
        users.add(new User("نقی","10","naghi@gmail.com","09182345876", "naghiguli", 2,new ArrayList<User>(),0,new ArrayList<Integer>()));
        users.add(new User("شقی","23","sharali123@yahoo.com","09351458745", "shaghiguli", 1,new ArrayList<User>(),0,new ArrayList<Integer>()));
        users.add(new User("گری","777","sh3i123@yahoo.com","09128768876", "gareguri", 0,new ArrayList<User>(),0,new ArrayList<Integer>()));
        users.add(new User("سری","678","sasdfd@yahoo.com","091887634987", "sariguli", 1,new ArrayList<User>(),0,new ArrayList<Integer>()));

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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Adventure> getAdventures() {
        return adventures;
    }

    public void setAdventures(ArrayList<Adventure> adventures) {
        this.adventures = adventures;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    /*
            sync from database
         */
    private void load_data() {
        load_from_storage();
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


    public void setLoggedIn(boolean isLoggedIn, String username){
        this.isLoggedIn = isLoggedIn;
        this.username = username;

        updateData();
    }
    public void addNewUser(String username, String score, String email, String phone, String pass, int visibility, ArrayList<User> friends, int image , ArrayList<Integer> AdventuresId) {
        users.add(new User(username , score , email ,phone , pass , visibility, friends ,  image , AdventuresId));
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
        try {

        return getUser(user_or_pass).getPass().equals(pass);
        }catch (Exception e){
            return false;
        }
    }
    public String getUsername(){
        update_to_storage();
        return username;
    }

    /*
        check if the username exist or not
        if the answer is not the person can create account
     */
    public boolean if_username_exist(String username) {
        return false;
    }

    public ArrayList<Adventure> getUserAdventure(String username){
        ArrayList<Adventure> adventures = new ArrayList<>();

        for(Integer adventure : getUser(username).)

    }
    public HashMap getUserDatas(String username){
      return  database.getUserDatas(username);
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



    public ArrayList<Place> getSuggestedPlace(){

        return places;
    }
    public ArrayList<Place> getAllPlace(){
        return database.getAllPlace();
    }


    public User getUser(String username) {

        for(User data: users){
            if(data.getUsername().equals(username)){
                return data;
            }

        }


        return null;
    }

    public ArrayList<User> getUserFriends(String username){
        return getUser(username).getFriends();
    }
}
