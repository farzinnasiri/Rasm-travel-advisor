package com.rasm.trip;

import android.media.Image;

import java.util.ArrayList;
import java.util.Calendar;

public class Adventure {

    private String title;
    private String id;
    private ArrayList<Image> pictures;
    private Calendar tarikhShoroo;
    private Calendar tarikhPayan;
    private String description;

    public Adventure(String title, String id, ArrayList<Image> pictures, Calendar tarikhShoroo, Calendar tarikhPayan , String description) {
        this.title = title;
        this.id = id;
        this.pictures = pictures;
        this.tarikhShoroo = tarikhShoroo;
        this.tarikhPayan = tarikhPayan;
        this.description = description;
    }
}
