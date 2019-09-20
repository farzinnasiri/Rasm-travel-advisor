package com.rasm.database;

import android.provider.BaseColumns;

public class FriendsContract {
    private FriendsContract() {
    }

    public static final class FriendsEntry implements BaseColumns {

        public static final String TABLE_NAME = "user-friends";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_FRIEND = "friend";


    }
}
