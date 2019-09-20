package com.rasm.database;

import android.provider.BaseColumns;

public class AdventureContract {

    private AdventureContract() {
    }

    public static final class AdventureEntry implements BaseColumns {

        public static final String TABLE_NAME = "adventures";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_USER = "name";
        public static final String COLUMN_STREAM = "stream";
        public static final String COLUMN_TIME_START = "startDate";
        public static final String COLUMN_TIME_END = "endDate";
        public static final String COLUMN_VISIBILITY = "visibility";
        public static final String COLUMN_CONDITION = "condition";
        public static final String COLUMN_IMAGES = "images";
        public static final String COLUMN_DESCRIPTIONS = "descriptions";
        public static final String COLUMN_STYLE = "style";

        public static final int STYLE_ONE_PERSON = 0;
        public static final int STYLE_MULTI_PERSON = 1;
        public static final int STYLE_TOUR = 2;

        public static final int VISIBILITY_PRIVATE = 0;
        public static final int VISIBILITY_PUBLIC = 1;

        public static final int CONDITION_DONE = 1;
        public static final int CONDITIONS_ONGOING = 0;

    }

}
