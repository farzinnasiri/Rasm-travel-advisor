package com.rasm.database;

import android.provider.BaseColumns;

public class UserAdventureContract {
    private UserAdventureContract() {
    }

    public static final class UserAdventureEntry implements BaseColumns {

        public static final String TABLE_NAME = "user_adventure";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_USER = "name";
        public static final String COLUMN_ADVENTUTRE = "adventure";


    }
}