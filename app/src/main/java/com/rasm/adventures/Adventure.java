package com.rasm.adventures;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.rasm.R;
import com.rasm.database.SQLiteOpenHelperExtender;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;

public class Adventure {

    private String stream;
    private String title;



    private String date;

    private String descriptions;
    private int id ;
    private int condition, style,visibility;
    private ArrayList<Integer> images;

    public Adventure(int id, String title, String date, String descriptions,
                     ArrayList<Integer> images) {
        this.title = title;
        this.date = date;
        this.descriptions = descriptions;
        this.images = images;
        this.id = id;
    }





    public int getCondition() {
        return condition;
    }

    public int getStyle() {
        return style;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getImages() {
        return images;
    }

    public int getVisibility() {
        return visibility;
    }

    public String getStream() {
        return stream;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImages(ArrayList<Integer> images) {
        this.images = images;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public void setStyle(int style) {
        this.style = style;
    }


    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
    //    private void orderDescriptionsWithImages() {
//        imageWithDescriptions = new ArrayList<ImageWithDescription>();
//        for(int i=0;i<images.size();i++){
//            imageWithDescriptions.add();
//        }
//
//    }
//    class ImageWithDescription{
//      private Bitmap image;
//      private String description;
//
//        public ImageWithDescription (Bitmap image, String description){
//            this.image = image;
//            this.description = description;
//        }
//
//    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
