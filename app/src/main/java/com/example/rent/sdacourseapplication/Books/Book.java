package com.example.rent.sdacourseapplication.Books;

import android.support.annotation.DrawableRes;

/**
 * Created by RENT on 2017-03-02.
 */
public class Book {


    private int id;

    @DrawableRes
    private int imageResourceId;
    private String title;

    public void setRead(boolean read) {
        isRead = read;
    }

    private boolean isRead;

    public Book(int id,int imageResourceId, String title) {
        this.id = id;
        this.imageResourceId = imageResourceId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public boolean isRead() {
        return isRead;
    }

    public String getTitle() {
        return title;
    }

    @DrawableRes
    public int getImageResourceId() {
        return imageResourceId;
    }




}
