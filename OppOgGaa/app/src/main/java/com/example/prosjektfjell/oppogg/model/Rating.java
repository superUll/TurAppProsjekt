package com.example.prosjektfjell.oppogg.model;

/**
 * Created by oleandreheggdal on 18.11.2016.
 */

public class Rating {

    private String rating,comment,mId;

    public Rating() {
    }

    public Rating(String rating, String comment) {
        this.rating = rating;
        this.comment = comment;


    }

    public String getRating() {
        return rating;
    }

    public String setRating(String rating) {
        this.rating = rating;
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getMid() {
        return mId;
    }

    public void setMid(String mId) {
        this.mId = mId;
    }



}
