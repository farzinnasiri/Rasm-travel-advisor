package com.rasm.Administering;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Administer {

    private static Administer administer;
    private static Context context;

    private boolean isLoggedIn;
    private Administer(){
        load_data();
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

    }

    public boolean isLoggedIn(){
        return  isLoggedIn;
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


        return false;
    }

    /*
        check if the username exist or not
        if the answer is not the person can create account
     */
    public boolean if_username_exist(String username) {
        return false;
    }
}
