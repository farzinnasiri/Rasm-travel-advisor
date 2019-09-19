package com.rasm.database;

import android.provider.BaseColumns;

public class PlaceContract {

    private PlaceContract() {
    }

    public static final class PlaceEntry implements BaseColumns {

        public static final String TABLE_NAME = "places";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGES = "images";
        public static final String COLUMN_POSITION = "position";


    }

}
