package com.rasm.database;

import android.provider.BaseColumns;

public class PlaceImagesContract {

    private PlaceImagesContract() {
    }

    public static final class PlaceImagesEntry implements BaseColumns {

        public static final String TABLE_NAME = "place_images";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PLACE = "place";
        public static final String COLUMN_IMAGES = "images";


    }
}
