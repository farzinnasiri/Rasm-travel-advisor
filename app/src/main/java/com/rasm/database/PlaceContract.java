package com.rasm.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;

import com.rasm.MainActivity;

import java.util.ArrayList;

public class PlaceContract {


    private PlaceContract() {
    }


    public static final class PlaceEntry implements BaseColumns {

        public static final String TABLE_NAME = "places";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGES = "images";
        public static final String COLUMN_POSITION = "position";

        public static final String TYPE_RESTAURANT = "restaurant";
        public static final String TYPE_CAFE = "cafe";
        public static final String TYPE_ANCIENT ="ancient";
        public static final String TYPE_PARK ="park";
        public static final String TYPE_SHRINE ="shrine";


    }

}
