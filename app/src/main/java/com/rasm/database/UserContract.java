package com.rasm.database;

import android.provider.BaseColumns;

public class UserContract {
    private UserContract() {
    }

    public static final class UserEntry implements BaseColumns {

        public static final String TABLE_NAME = "adventures";

        public static final String _ID = BaseColumns._ID;
        public static final String _COLUMN_NAME = "name";
        public static final String COLUMN_PASS = "password";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_EMAIL= "email";
        public static final String COLUMN_PROFILE_PICTURE = "picture";
        public static final String COLUMN_ADVENTURES = "adventures";
        public static final String COLUMN_VISIBILITY = "visibility";
        public static final String COLUMN_SCORE = "score";
        public static final String COLUMN_UPLOADEDFILES = "uploaded";

        public static final int VISIBILITY_PRIVATE = 0;
        public static final int VISIBILITY_PUBLIC = 1;



    }
}
