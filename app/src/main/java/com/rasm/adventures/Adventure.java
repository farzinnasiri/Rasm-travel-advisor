package com.rasm.adventures;

import android.graphics.Bitmap;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.rasm.database.SQLiteOpenHelperExtender;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;

public class Adventure {

    private String username;
    private String stream;
    private String title;
    private String date;



    private String descriptions;
    private String id ;
    private int condition, style,visibility;
    private ArrayList<Bitmap> images;
//    private ArrayList<ImageWithDescription> imageWithDescriptions;

    public Adventure(HashMap map){

        initializeFields(map);
//        orderDescriptionsWithImages();

    }


    private void initializeFields(HashMap map) {
        condition = (int)map.get("condition");
        style = (int)map.get("style");
        visibility = (int)map.get("style");
        username = (String)map.get("username");
        id = (String)map.get("id");
        stream = (String)map.get("stream");
        descriptions = (String)map.get("description");
        title = (String)map.get("title");
//        images = SQLiteOpenHelperExtender.stringToBitmapArray((String) map.get("images"));
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

    public String getId() {
        return id;
    }

    public ArrayList<Bitmap> getImages() {
        return images;
    }

    public int getVisibility() {
        return visibility;
    }

    public String getStream() {
        return stream;
    }
    public String getUsername() {
        return username;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImages(ArrayList<Bitmap> images) {
        this.images = images;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public void setUsername(String username) {
        this.username = username;
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
