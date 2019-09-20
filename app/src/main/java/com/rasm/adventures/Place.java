package com.rasm.adventures;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;


public class Place {

private String descriptions, id  , title;
private ArrayList<Bitmap> images;

    public Place(HashMap map){

        initializeFields(map);
//        orderDescriptionsWithImages();

    }

    private void initializeFields(HashMap map) {

    }

    public ArrayList<Bitmap>  getPictures(){
        return images;
    }

    public String getTitle(){
        return title;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Bitmap> getImages() {
        return images;
    }

    public void setImages(ArrayList<Bitmap> images) {
        this.images = images;
    }
}
